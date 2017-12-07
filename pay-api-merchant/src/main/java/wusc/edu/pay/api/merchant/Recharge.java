package wusc.edu.pay.api.merchant;

import org.apache.commons.httpclient.HttpException;
import wusc.edu.pay.api.merchant.utils.Context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class Recharge {

    /**
     * 生成充值url,post方式
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws HttpException
     */
    public String buildPayUrlPost(Map<String, String> map) throws UnsupportedEncodingException {
        String p1_Amount = map.get("p1_Amount");
        String p2_LoginName = map.get("p2_LoginName");
        String p3_ReturnUrl = map.get("p3_ReturnUrl");
        String p4_OrderNo = map.get("p4_OrderNo");
        String hmac = map.get("hmac");

        StringBuilder html = new StringBuilder();
        html.append("<form name='toPay' action='").append(Context.RECHARGE_URL).append("' method='POST'>\r");
        html.append("<input type='hidden' name='p1_Amount' value='").append(p1_Amount).append("'>\r");
        html.append("<input type='hidden' name='p2_LoginName' value='").
                append(URLEncoder.encode(p2_LoginName, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p3_ReturnUrl' value='").
                append(URLEncoder.encode(p3_ReturnUrl, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p4_OrderNo' value='").
                append(URLEncoder.encode(p4_OrderNo, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='hmac' value='").append(URLEncoder.encode(hmac, "utf-8"))
                .append("'>\r");
        html.append("</form>");
        return html.toString();
    }

    /**
     * 生成充值url,get方式
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    public String buildPayUrlGet(Map<String, String> map) throws UnsupportedEncodingException {
        String p1_Amount = map.get("p1_Amount");
        String p2_LoginName = map.get("p2_LoginName");
        String p3_ReturnUrl = map.get("p3_ReturnUrl");
        String p4_OrderNo = map.get("p4_OrderNo");
        String hmac = map.get("hmac");

        StringBuilder url = new StringBuilder(Context.RECHARGE_URL);
        url.append("?p1_Amount=").append(p1_Amount);
        url.append("&p2_LoginName=").append(URLEncoder.encode(p2_LoginName, "utf-8"));
        url.append("&p3_ReturnUrl=").append(URLEncoder.encode(p3_ReturnUrl, "utf-8"));
        url.append("&p4_OrderNo=").append(URLEncoder.encode(p4_OrderNo, "utf-8"));
        url.append("&hmac=").append(URLEncoder.encode(hmac, "utf-8"));
        return url.toString();
    }

}
