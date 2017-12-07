package wusc.edu.pay.common.param;

import java.io.Serializable;

/**
 * @描述: 短信参数实体.
 * @作者: WuShuicheng.
 * @创建: 2014-9-16,下午6:12:08
 * @版本: V1.0
 */
public class SmsParam implements Serializable {

    private static final long serialVersionUID = 6296401388735702975L;

    /** 接收短信的手机号码 */
    String phone;

    /** 短信内容 */
    String content;

    public SmsParam() {
        super();
    }

    public SmsParam(String phone, String content) {
        super();
        this.phone = phone;
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
