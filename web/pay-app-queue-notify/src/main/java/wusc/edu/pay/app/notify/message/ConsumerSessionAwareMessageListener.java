package wusc.edu.pay.app.notify.message;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
import wusc.edu.pay.app.notify.core.NotifyPersist;
import wusc.edu.pay.app.notify.core.NotifyQueue;
import wusc.edu.pay.common.exceptions.BizException;
import wusc.edu.pay.facade.notify.entity.NotifyRecord;
import wusc.edu.pay.facade.notify.enums.NotifyStatusEnum;
import wusc.edu.pay.facade.notify.service.NotifyFacade;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import static wusc.edu.pay.app.notify.core.NotifyPersist.saveNotifyRecord;

/**
 * @描述: 通知队列监听器.
 * @作者: HuPitao, WuShuicheng.
 * @创建: 2014-5-8,下午3:58:28
 * @版本: V1.0
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerSessionAwareMessageListener.class);

    @Autowired
    private JmsTemplate notifyJmsTemplate;

    @Autowired
    private Destination sessionAwareQueue;

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private NotifyFacade notifyFacade;

    @Override
    public synchronized void onMessage(Message message, Session session) {
        try {
            ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
            final String ms = msg.getText();
            logger.info("== receive message:" + ms);

            // 这里转换成相应的对象还有问题
            NotifyRecord notifyRecord = JSONObject.parseObject(ms, NotifyRecord.class);
            if (notifyRecord == null) {
                return;
            }
            notifyRecord.setStatus(NotifyStatusEnum.CREATED.getValue());

            // 判断数据库中是否已有通知记录
            if (notifyRecord.getId() == null) {
                // log.info("ActiveMQUtil.notifyFacade:" +
                // ActiveMQUtil.notifyFacade);

                // 主动休眠，防止类notifyRecordFacade未加载完成，监听服务就开启监听出现空指针异常
                while (notifyFacade == null) {
                    Thread.sleep(1000);
                }

                try {
                    // 将获取到的通知先保存到数据库中
                    long notifyId = saveNotifyRecord(notifyRecord);
                    // 插入后，立即返回ID by chenjianhua
                    notifyRecord.setId(notifyId);
                    // 添加到通知队列
                    notifyQueue.addElementToList(notifyRecord);
                } catch (RpcException e) {
                    notifyJmsTemplate.send(sessionAwareQueue, session1 -> session1.createTextMessage(ms));
                    logger.error("RpcException :", e);
                } catch (BizException e) {
                    logger.error("BizException :", e);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
