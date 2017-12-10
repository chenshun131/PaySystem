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
        System.out.println("encrypt = " + encrypt);
    }

}
