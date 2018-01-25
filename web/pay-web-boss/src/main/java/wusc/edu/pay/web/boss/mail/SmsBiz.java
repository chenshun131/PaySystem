package wusc.edu.pay.web.boss.mail;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import wusc.edu.pay.common.param.SmsParam;
import wusc.edu.pay.facade.notify.util.NotifyUtil;

import java.util.Map;


/**
 * @描述: 短息发送业务类
 * @作者: WuShuicheng
 * @创建: 2014-9-26,下午12:44:56
 * @版本: V1.0
 */
@Component("smsBiz")
public class SmsBiz {

    private static final Logger logger = LoggerFactory.getLogger(SmsBiz.class);

    // Velocity 模板引擎 (spring配置中定义)
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JmsTemplate notifyJmsTemplate;


    /**
     * 短信内容模板解释
     *
     * @param smsTemplatePath
     *         短信模板路径
     * @param paramModel
     *         模板中的参数变量
     * @return content
     */
    public String mergeSmsTemplate(String smsTemplatePath, Map<String, Object> paramModel) {
        try {
            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, smsTemplatePath, "UTF-8", paramModel);
        } catch (Exception e) {
            logger.error("==>merge " + smsTemplatePath + " com.chenshun.test.exception:", e);
            throw e;
        }
    }

    /**
     * 发送短信
     *
     * @param phone
     *         手机号
     * @param content
     *         短信内容
     */
    public void sendSms(String phone, String content) {
        final SmsParam smsParam = new SmsParam(phone, content);
        notifyJmsTemplate.send(session -> session.createTextMessage(NotifyUtil.formatSms(smsParam)));
    }

}
