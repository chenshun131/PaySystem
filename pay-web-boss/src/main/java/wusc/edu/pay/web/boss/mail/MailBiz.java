package wusc.edu.pay.web.boss.mail;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import wusc.edu.pay.common.param.MailParam;
import wusc.edu.pay.facade.notify.util.NotifyUtil;

import java.util.Map;


/**
 * @描述: 邮件模板解释及通过MQ发送.
 * @作者: WuShuicheng.
 * @创建: 2014-9-12,下午4:44:49
 * @版本: V1.0
 */
@Component("mailBiz")
public class MailBiz {

    private static final Logger logger = LoggerFactory.getLogger(MailBiz.class);

    @Autowired
    private JmsTemplate notifyJmsTemplate;

    @Autowired
    private VelocityEngine velocityEngine;// spring配置中定义

    /**
     * 邮件内容模板解释.
     *
     * @param emailTemplatePath
     *         邮件模板路径.
     * @param paramModel
     *         模板中的参数变量.
     * @return content
     * @throws Exception
     */
    public String mergeEmailTemplate(String emailTemplatePath, Map<String, Object> paramModel) throws Exception {
        try {
            return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplatePath, "UTF-8", paramModel);
        } catch (Exception e) {
            logger.error("==>merge " + emailTemplatePath + " exception:", e);
            throw e;
        }
    }

    /**
     * 发送邮件
     *
     * @param toMail
     *         收件人
     * @param subject
     *         主题
     * @param content
     *         邮件内容
     */
    public void sendMail(String toMail, String subject, String content) {
        final MailParam mailParam = new MailParam(toMail, subject, content);
        notifyJmsTemplate.send(session -> session.createTextMessage(NotifyUtil.formatMail(mailParam)));
    }

}
