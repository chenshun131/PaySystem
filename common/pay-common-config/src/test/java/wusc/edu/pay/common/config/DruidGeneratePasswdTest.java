package wusc.edu.pay.common.config;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * User: chenshun131 <p />
 * Time: 17/12/9 15:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DruidGeneratePasswdTest {

    public static void main(String[] args) throws Exception {
        String encrypt = ConfigTools.encrypt("123456");
        // encrypt = Biyu5YzU+6sxDRbmWEa3B2uUcImzDo0BuXjTlL505+/pTb+/0Oqd3ou1R6J8+9Fy3CYrM18nBDqf6wAaPgUGOg==
        System.out.println("encrypt = " + encrypt);
    }

}
