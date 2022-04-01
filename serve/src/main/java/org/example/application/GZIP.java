package org.example.application;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 *
 * 开发公司：sojson.com<br/>
 * 版权：sojson.com<br/>
 * <p>
 *
 * 字符串压缩
 *
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2015年12月19日 　<br/>
 * <p>
 * *******
 * <p>
 * @author zhou-baicheng
 * @email  json@sojson.com
 * @version 1.0,2015年12月19日 <br/>
 *
 */
public class GZIP {
    static String s = "HTTP/1.1 200 OK\n" +
            "Server: TencentWAF\n" +
            "Date: Fri, 01 Apr 2022 18:21:26 GMT\n" +
            "Content-Type: application/json\n" +
            "Transfer-Encoding: chunked\n" +
            "Connection: keep-alive\n" +
            "x-proxy-by: Tif-APIGate\n" +
            "X-Healthcode-Resp-Sign: zbcIgJ/k68KUH+UOVv6/S50hhElhXTlF5xGK8gJOqjhVsmc7johbhd/4cDKRQ0YgxPOxIq4nvAxKnYp2z+tUnDJmDq9jv1xBYKak/aK+kgaWEtESXeslVjipDUhr7JfSOtCgrLeYJ80o/zgScjJmLxhXsNqoisDp0Cxtc4SWZeU=\n" +
            "x-forwarded-for: \n" +
            "content-encoding: gzip\n" +
            "Content-Length: 500\n" +
            "Cache-Control: max-age=0, must-revalidate\n\n{" +
            "  \"errcode\": 0," +
            "  \"errmsg\": \"\"," +
            "  \"data\": {" +
            "    \"color\": \"red\"," +
            "    \"description\": \"\"," +
            "    \"user_status\": {" +
            "      \"certified\": true," +
            "      \"residential_address\": \"\"," +
            "      \"before_address\": \"\"," +
            "      \"identity_registered\": false," +
            "      \"associated_reports\": []," +
            "      \"entry_declaration\": false," +
            "      \"overseas\": false," +
            "      \"reason\": \"\"," +
            "      \"from\": \"\"," +
            "      \"pull_from_nation\": false" +
            "    }," +
            "    \"vaccinate\": [" +
            "      {" +
            "        \"inoc_bact_code\": \"5601\"," +
            "        \"inoc_bact_name\": \"新冠疫苗（Vero细胞）\"," +
            "        \"inoc_time\": \"3\"," +
            "        \"inoc_date\": \"2021-12-27 17:00:10\"," +
            "        \"inoc_depa_name\": \"凤凰街道凤苑卫生服务站\"," +
            "        \"inoc_batchno\": \"202109G0594\"," +
            "        \"inoc_corp_name\": \"兰州生物\"" +
            "      }," +
            "      {" +
            "        \"inoc_bact_code\": \"5601\"," +
            "        \"inoc_bact_name\": \"新冠疫苗（Vero细胞）\"," +
            "        \"inoc_time\": \"2\"," +
            "        \"inoc_date\": \"2021-06-20 10:35:00\"," +
            "        \"inoc_depa_name\": \"凤凰街道凤苑卫生服务站\"," +
            "        \"inoc_batchno\": \"202105C0147\"," +
            "        \"inoc_corp_name\": \"成都生物\"" +
            "      }," +
            "      {" +
            "        \"inoc_bact_code\": \"5601\"," +
            "        \"inoc_bact_name\": \"新冠疫苗（Vero细胞）\"," +
            "        \"inoc_time\": \"1\"," +
            "        \"inoc_date\": \"2021-05-26 10:08:00\"," +
            "        \"inoc_depa_name\": \"凤凰街道凤苑卫生服务站\"," +
            "        \"inoc_batchno\": \"2021040071C\"," +
            "        \"inoc_corp_name\": \"成都生物\"" +
            "      }" +
            "    ]," +
            "    \"vaccinate_icon\": 22," +
            "    \"nucleic_test\": []," +
            "    \"age\": 25" +
            "  }" +
            "}";
    public static void main(String[] args) throws IOException {
        compress(s);
    }
    /**
     * 字符串的压缩
     *
     * @param str
     *            待压缩的字符串
     * @return 返回压缩后的字符串
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        FileOutputStream fileOutputStream = new FileOutputStream("d:/1");
        fileOutputStream.write(out.toByteArray());
        fileOutputStream.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("UTF-8");
    }

    /**
     * 字符串的解压
     *
     * @param str
     *            对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    public static String unCompress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("UTF-8"));
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write(buffer, 0, n);
        }

        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("UTF-8");
    }

}
