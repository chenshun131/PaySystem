package wusc.edu.pay.app.notify.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wusc.edu.pay.app.notify.App;
import wusc.edu.pay.app.notify.entity.NotifyParam;
import wusc.edu.pay.facade.notify.entity.NotifyRecord;
import wusc.edu.pay.facade.notify.enums.NotifyStatusEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


@Component
public class NotifyQueue implements Serializable {

    private static final long serialVersionUID = -6656720745614487807L;

    private static final Logger logger = LoggerFactory.getLogger(NotifyQueue.class);

    @Autowired
    private NotifyParam notifyParam;

    /**
     * 将传过来的对象进行通知次数判断，之后决定是否放在任务队列中
     *
     * @param notifyRecord
     */
    public void addElementToList(NotifyRecord notifyRecord) {
        if (notifyRecord == null) {
            return;
        }

        // 通知次数
        Integer notifyTimes = notifyRecord.getNotifyTimes();
        Integer maxNotifyTime = 0;
        try {
            maxNotifyTime = notifyParam.getMaxNotifyTime();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        // 刚刚接收到的数据
        if (notifyRecord.getVersion() == 0) {
            notifyRecord.setLastNotifyTime(new Date());
        }

        long time = notifyRecord.getLastNotifyTime().getTime();
        Map<Integer, Integer> timeMap = notifyParam.getNotifyParams();
        if (notifyTimes < maxNotifyTime) {
            Integer nextKey = notifyTimes + 1;
            Integer next = timeMap.get(nextKey);
            if (next != null) {
                time += 1000 * 60 * next + 1;
                notifyRecord.setLastNotifyTime(new Date(time));
                App.tasks.put(new NotifyTask(notifyRecord, this, notifyParam));
            }
        } else {
            try {
                // 持久化到数据库中
                NotifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(),
                        NotifyStatusEnum.FAILED.getValue());
                logger.info("Update NotifyRecord failed,merchantNo:" + notifyRecord.getMerchantNo() +
                        ",merchantOrderNo:" + notifyRecord.getMerchantOrderNo());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

}
