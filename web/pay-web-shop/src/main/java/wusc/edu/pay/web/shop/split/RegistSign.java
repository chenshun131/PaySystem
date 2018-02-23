package wusc.edu.pay.web.shop.split;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import wusc.edu.pay.common.config.PublicConfig;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class RegistSign {

    public String sign(Map paramMap) {
        String merchantKey = "";
        Set keys = paramMap.keySet();
        Map<String, Object> parameters = new HashMap<>(keys.size());
        for (Object key : keys) {
            try {
                parameters.put(key.toString(),
                        new String((((String[]) paramMap.get(key.toString()))[0]).getBytes("ISO-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String actionUrl = "";
        Properties properties = new Properties();
        for (String name : parameters.keySet()) {
            String value = parameters.get(name).toString();
            if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || StringUtils.equalsIgnoreCase("hmac", name)) {
            } else if (StringUtils.equalsIgnoreCase("actionName", name)) {
                // 获取action地址
                actionUrl = StringUtils.trim(value);
            } else {
                properties.setProperty(name, StringUtils.trim(value));
            }
        }

        StringBuilder content = new StringBuilder("");
        StringBuilder html = new StringBuilder("<form name='toSplit' action='")
                .append(PublicConfig.WEB_TRADE_URL).append(actionUrl)
                .append(".action' method='POST'>");

        ArrayList keyList = new ArrayList(properties.keySet());
        Collections.sort(keyList);
        for (Object aKeyList : keyList) {
            String tempKey = (String) aKeyList;
            String value = properties.getProperty(tempKey);
            if ("merchantKey".equals(tempKey)) {
                merchantKey = value;
            } else {
                if ("".equals(content.toString())) {
                    content.append(tempKey).append("=").append(value);
                } else {
                    content.append("&").append(tempKey).append("=").append(value);
                }

                html.append("<input type='hidden' name='").append(tempKey).append("' value='").append(value).append("'></br>");
            }
        }

        String hmacData = content.toString() + merchantKey;
        String hmac = DigestUtils.md5Hex(content.toString() + merchantKey).toUpperCase();

        System.out.println("==>client hmac data：" + hmacData);
        System.out.println("==>client hmac：" + hmac);
        html.append("<input type='hidden' name='hmac' value='").append(hmac)
                .append("'></br></form><script>document.forms['toSplit'].submit();</script>");
        return html.toString();
    }

}
