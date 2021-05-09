package org.example.common.utils.ipv2;

/**
 * @ClassName GeoLite2Demo
 * @Description TODO
 * @Author qgp
 * @Date 2019/9/23 14:28
 * @Version 1.0
 **/
public class GeoLite2Demo {
    public static void main(String[] args) {
//        IPEntity msg = IPUtils.getIPMsg("128.101.101.101");
        IPEntity msg = IPUtils.getIPMsg("223.104.96.110");
        System.out.println(msg.toString());
    }
}
