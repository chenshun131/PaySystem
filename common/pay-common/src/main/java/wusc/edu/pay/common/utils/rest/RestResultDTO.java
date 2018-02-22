package wusc.edu.pay.common.utils.rest;

import java.text.MessageFormat;

/**
 * User: mew <p />
 * Time: 17/7/11 14:34  <p />
 * Version: V1.0  <p />
 * Description: RESTful响应DTO <br/>
 * 建议DTO全部采用大写,最好不要使用驼峰命名.因为这是三个独立单词的首字母.VO也按照这个方式命名. <p />
 */
public class RestResultDTO {

    /** 响应的业务数据 */
    private Object body;

    /** 响应的状态编码 */
    private String statusCode;

    /** 响应的状态编码对应的文字说明 */
    private String message;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestResultDTO() {
        super();
    }

    public RestResultDTO(Object body, String statusCode, String message) {
        super();
        this.body = body;
        this.statusCode = statusCode;
        this.message = message;
    }

    public RestResultDTO(StatusCode statusCode) {
        super();
        if (statusCode != null) {
            this.statusCode = String.valueOf(statusCode.getValue());
            this.message = statusCode.getReasonPhrase();
        }
    }

    public void initStatus(StatusCode statusCode) {
        this.statusCode = String.valueOf(statusCode.getValue());
        this.message = statusCode.getReasonPhrase();
    }

    public static void renderRest(RestResultDTO restResultDTO, StatusCode statusCode, String title, String content) {
        if (restResultDTO == null) {
            restResultDTO = new RestResultDTO();
        }
        restResultDTO.initStatus(statusCode);
        restResultDTO.setBody(MessageFormat.format(title + ":{0}", content));
    }

}
