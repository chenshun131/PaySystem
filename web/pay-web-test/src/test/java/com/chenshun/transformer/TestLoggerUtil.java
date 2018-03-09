package com.chenshun.transformer;

import com.chenshun.transformer.util.LoggerUtil;

public class TestLoggerUtil
{
    public static void main(String[] args)
    {
        String logText = "114.92.217.149^A1450569601.351^A/BEIfeng.gif?u_nu=1&u_sd=6D4F89C0-E17B-45D0-BFE0-059644C1878D&c_time=1450569596991&ver=1&en=e_l&pl=website&sdk=js&b_rst=1440*900&u_ud=4B16B8BB-D6AA-4118-87F8-C58680D22657&b_iev=Mozilla%2F5.0%20(Windows%20NT%205.1)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F45.0.2454.101%20Safari%2F537.36&l=zh-CN&bf_sid=33cbf257-3b11-4abd-ac70-c5fc47afb797_11177014";
        System.out.println(LoggerUtil.handleLogText(logText));
    }
}
