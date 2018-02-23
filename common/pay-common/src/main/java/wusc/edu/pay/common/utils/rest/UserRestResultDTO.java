package wusc.edu.pay.common.utils.rest;

/**
 * User: mew <p />
 * Time: 17/6/19 12:06  <p />
 * Version: V1.0  <p />
 * Description: RESTful响应DTO
 * <br />
 * 建议DTO全部采用大写,最好不要使用驼峰命名.因为这是三个独立单词的首字母.VO也按照这个方式命名. <p />
 */
public class UserRestResultDTO {

    /** 响应的业务数据 */
    private String data;

    /** 响应的状态编码 */
    private String code;

    /** 响应的时间 */
    private String time;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
