package wusc.edu.pay.facade.fee.utils;

import wusc.edu.pay.common.utils.string.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * 费率配置属性文件读取工具类
 */
public class ReadFeeConfig {

    /** 属性文件名称 */
    private static final String CONFIG_FILE_NAME = "fee-config";

    private static final String SPLIT_FILE = ",";

    private static final Map<String, String> CONFIGS;

    // 读取属性文件
    static {
        String[] paths = CONFIG_FILE_NAME.split(SPLIT_FILE);
        CONFIGS = new HashMap<>();
        for (String path : paths) {
            ResourceBundle bundle = ResourceBundle.getBundle(path);

            for (String key : bundle.keySet()) {
                CONFIGS.put(key, bundle.getString(key));
            }
        }
    }

    /**
     * 按key/value方式获取properties文件中的值
     *
     * @param key
     * @return
     */
    public static String getText(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return CONFIGS.get(key);
    }

}
