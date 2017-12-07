package wusc.edu.pay.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BankCode {

    ICBC {
        @Override
        public String getDesc() {
            return "工商银行";
        }
    },
    CMBCHINA {
        @Override
        public String getDesc() {
            return "招商银行";
        }
    },
    ABC {
        @Override
        public String getDesc() {
            return "中国农业银行";
        }
    },
    CCB {
        @Override
        public String getDesc() {
            return "建设银行";
        }
    },
    BCCB {
        @Override
        public String getDesc() {
            return "北京银行";
        }
    },
    BOCO {
        @Override
        public String getDesc() {
            return "交通银行";
        }
    },
    CIB {
        @Override
        public String getDesc() {
            return "兴业银行";
        }
    },
    NJCB {
        @Override
        public String getDesc() {
            return "南京银行";
        }
    },
    CMBC {
        @Override
        public String getDesc() {
            return "中国民生银行";
        }
    },
    CEB {
        @Override
        public String getDesc() {
            return "光大银行";
        }
    },
    BOC {
        @Override
        public String getDesc() {
            return "中国银行";
        }
    },
    PINGANBANK {
        @Override
        public String getDesc() {
            return "平安银行";
        }
    },
    CBHB {
        @Override
        public String getDesc() {
            return "渤海银行";
        }
    },
    HKBEA {
        @Override
        public String getDesc() {
            return "东亚银行";
        }
    },
    NBCB {
        @Override
        public String getDesc() {
            return "宁波银行";
        }
    },
    ECITIC {
        @Override
        public String getDesc() {
            return "中信银行";
        }
    },
    CGB {
        @Override
        public String getDesc() {
            return "广发银行";
        }
    },
    SHB {
        @Override
        public String getDesc() {
            return "上海银行";
        }
    },
    SPDB {
        @Override
        public String getDesc() {
            return "上海浦东发展银行";
        }
    },
    POST {
        @Override
        public String getDesc() {
            return "中国邮政";
        }
    },
    BJRCB {
        @Override
        public String getDesc() {
            return "北京农村商业银行";
        }
    },
    HXB {
        @Override
        public String getDesc() {
            return "华夏银行";
        }
    },
    CZ {
        @Override
        public String getDesc() {
            return "浙商银行";
        }
    },
    HZBANK {
        @Override
        public String getDesc() {
            return "杭州银行";
        }
    },
    SRCB {
        @Override
        public String getDesc() {
            return "上海农村商业银行";
        }
    },
    NCBBANK {
        @Override
        public String getDesc() {
            return "南洋商业银行";
        }
    },
    SCCB {
        @Override
        public String getDesc() {
            return "河北银行";
        }
    },
    ZJTLCB {
        @Override
        public String getDesc() {
            return "泰隆银行";
        }
    },
    SINA {
        @Override
        public String getDesc() {
            return "新浪微博支付";
        }
    },
    YEEPAY {
        @Override
        public String getDesc() {
            return "易宝支付";
        }
    },
    TFTPAY {
        @Override
        public String getDesc() {
            return "腾付通支付";
        }
    },
    MOBAOPAY {
        @Override
        public String getDesc() {
            return "摩宝支付";
        }
    },
    OTHER {
        @Override
        public String getDesc() {
            return "其他";
        }
    };

    public static Map<String, String> toStringMap() {
        BankCode[] type = values();
        Map<String, String> map = new LinkedHashMap<>();
        for (BankCode e : type) {
            map.put(e.toString(), e.getDesc());
        }
        return map;
    }

    public abstract String getDesc();

    public static String getCode(String desc) {
        String code = null;
        BankCode[] enumAry = BankCode.values();
        for (BankCode anEnumAry : enumAry) {
            if (desc.equals(anEnumAry.getDesc())) {
                code = anEnumAry.toString();
                break;
            }
        }
        return code;
    }

    public static Map<String, String> getTopFourMap() {
        // 建、工、农、中国
        BankCode[] type = {BankCode.CCB, BankCode.ICBC, BankCode.ABC, BankCode.BOC};
        Map<String, String> map = new LinkedHashMap<>();
        for (BankCode e : type) {
            map.put(e.toString(), e.getDesc());
        }
        return map;
    }

    public static Map<String, String> getCommonMap() {
        // 招商、广发、光大、平安、交通、邮政、民生、中信、兴业
        BankCode[] type = {BankCode.CMBCHINA, BankCode.CGB, BankCode.CEB, BankCode.PINGANBANK, BankCode.BOCO,
                BankCode.POST, BankCode.CMBC, BankCode.ECITIC, BankCode.CIB};
        Map<String, String> map = new LinkedHashMap<>();
        for (BankCode e : type) {
            map.put(e.toString(), e.getDesc());
        }
        return map;
    }

}
