package com.du.easyservice.demo;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

public class SocketUtil {

    private static Logger logger = Logger.getLogger(SocketUtil.class);


    public static void main(String[] args) throws UnsupportedEncodingException {

/*

        String s = tcpPost("10.255.2.211", "8010",new String("<?xml version='1.0' encoding='GBK'?><msg> <msg_head><msg_type>0</msg_type> <msg_id>1005</msg_id><msg_sn>0</msg_sn><version>1</version></msg_head><msg_body><origin_data_len>95</origin_data_len><origin_data>150010122000038316|黑龙江北大荒投资担保股份有限公司|150010122000022226|五|1.00</origin_data> </msg_body> </msg>".getBytes(),"GBK"));

        s = "MIIGIAYJKoZIhvcNAQcCoIIGETCCBg0CAQExCzAJBgUrDgMCGgUAMG4GCSqGSIb3\n" +
                "DQEHAaBhBF8xNTAwMTAxMjIwMDAwMzgzMTZ86buR6b6Z5rGf5YyX5aSn6I2S5oqV\n" +
                "6LWE5ouF5L+d6IKh5Lu95pyJ6ZmQ5YWs5Y+4fDE1MDAxMDEyMjAwMDAyMjIyNnzk\n" +
                "upR8MS4wMKCCBBgwggQUMIIC/KADAgECAgUmdVHLczANBgkqhkiG9w0BAQsFADA9\n" +
                "MQswCQYDVQQGEwJDTjEUMBIGA1UECgwLTlhZMjA0OFRFU1QxGDAWBgNVBAMMD05Y\n" +
                "WTIwNDhST09UVEVTVDAeFw0xOTExMjUwNjU2MjhaFw0yNDExMjMwNjU2MjhaMIGl\n" +
                "MQswCQYDVQQGEwJDTjE3MDUGA1UECgwuUnVyYWwgQ3JlZGl0IEJhbmtzIEZ1bmRz\n" +
                "IENsZWFyaW5nIENlbnRlcihURVNUKTENMAsGA1UECwwEMjYwMDEUMBIGA1UECwwL\n" +
                "RW50ZXJwcmlzZXMxODA2BgNVBAMMLzA2M0A4OTEyMzMwMDA2ODAyOTE2M1g4QGxl\n" +
                "aWxvbmdqaWFuZ0AxMDAwMDE5OTUzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\n" +
                "CgKCAQEAs70W4Lf3VZxUWl7VvmoHxg+YJjpk45GRL0TRj86qciJNJ/YC/ME085pb\n" +
                "UAhTsiJaMW407miJQWiudyB8Rk4xrBZsVrXdKn9dXyduHawJqM9dKZ1CR6cCBKIa\n" +
                "rVdGhNfCO44TziIfaATy0kVvqUHQNR3Z09+zWIU6Y0phTDj9wY+JQkW9ZdwZSwlG\n" +
                "lUtcCqbWB6HHYXnEGMlSfclkH0qsd8rmoQlLj7GV5X20OM6iGIGvtkYydml3S8ZA\n" +
                "6T9XgKi7BQsfx8ZchQInSTcm5KRS6Hw36WfIcv8CX7iUL+BG1wFXt4cg9TinDH8L\n" +
                "m9qLoOqwrXicxtdTAI7m8DyTHM/bDQIDAQABo4GxMIGuMB8GA1UdIwQYMBaAFGWl\n" +
                "/Zsgr8T9YLPNDQrCWbRrcqTBMAkGA1UdEwQCMAAwVAYDVR0fBE0wSzBJoEegRaRD\n" +
                "MEExDjAMBgNVBAMMBWNybDI2MQwwCgYDVQQLDANjcmwxFDASBgNVBAoMC05YWTIw\n" +
                "NDhURVNUMQswCQYDVQQGEwJDTjALBgNVHQ8EBAMCB4AwHQYDVR0OBBYEFABLqfjt\n" +
                "SoYHnsokO8wtiSw6ZjP4MA0GCSqGSIb3DQEBCwUAA4IBAQAAuoXS5MjNjjavFhhi\n" +
                "msgduZfpoTBWMAJ21Qu07ymJqVV9UxWmdiJtPgmCEH/IENmlqNXZzIUFpSxWjw3Y\n" +
                "vc+GK9oTVpxeD6fCosKsBUd7IzSfygwyItxsvjdir/T9kTbs7dahGdca1HBcy8kY\n" +
                "pTo0+wbyj6tLk2WcKttKhT7s3gFP4e+y3t7iF8JOD8gGoD073xMOxdttrtUsKCYp\n" +
                "QFF6oBiHqQZ8nB3+AHU2LJiIC1KqIoLjHkJgaUW+uwIyXwEkmSEdYTrxXX1lDswS\n" +
                "PSx0CCfoqC43xEm8dfC7qsFktpXH3VZNsVxkkXEvJvlh0fvz+XgkEkBaWqsOGyY6\n" +
                "FWdAMYIBbTCCAWkCAQEwRjA9MQswCQYDVQQGEwJDTjEUMBIGA1UECgwLTlhZMjA0\n" +
                "OFRFU1QxGDAWBgNVBAMMD05YWTIwNDhST09UVEVTVAIFJnVRy3MwCQYFKw4DAhoF\n" +
                "ADANBgkqhkiG9w0BAQEFAASCAQA5x7s5AWubYlaV0qXsmpTsd0oa2jKJTz+pCWwo\n" +
                "LohtVUyPE/wO9gmWSItzKITkfpxSRMNQYEr6URkjTZtQAj4/MO4hhGA4vZXgqwW9\n" +
                "LZMafa4AzlCM8qyJ2kjfyWIqtD4fJnk3mujpYaoz4eOLMUgYXg97V7qcwUIuvCco\n" +
                "hLnWjETsDGfZbLZZYexGyS+zpXr/a7NbuG0ST3RJ2DpzNM29R7OOiLRrbz4v60NQ\n" +
                "+PgnYKlk3SLWJR/Y2rrQynLXuDYaGtIZxro27A0TAAVelvIRDqC8TJQOwj4u6h2/\n" +
                "gpkhVjQvAO6gYqan2LEMuLjLwXAx05F3S7vj82i3i2Y+afiN";

        String  send = "CBE003|#<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<QLBankData><opReq><bsnCode>CBE003</bsnCode><cstNo>30000143</cstNo><reqTime>20200702160148</reqTime><ReqParam><serialNo>146T27A02Q8F094C</serialNo><payAccount>150010122000038316</payAccount><currencyType>CNY</currencyType><payAccountName>黑龙江北大荒投资担保股份有限公司</payAccountName><recAccount>150010122000022226</recAccount><recAccountName>五</recAccountName><tranAmt>1.00</tranAmt><payUse>11111</payUse><remark>11111</remark><branchId>2600</branchId><signDataStr>150010122000038316|黑龙江北大荒投资担保股份有限公司|150010122000022226|五|1.00</signDataStr>"+s +"<signData>\n" +
                "</signData></ReqParam></opReq></QLBankData>";
*/

String  a = "6锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷支锟斤拷=====锟斤拷锟斤拷xml<?xml version=\"1.0\" encoding=\"UTF-8\" ?><QLBankData><opRep><opResult><code>0000</code></opResult><opResult><payBal/><payBalAvl/><totalAmt/><errorCode>310019</errorCode><errorMessage>锟斤拷濮濄倕锟解?筹拷锟絖_150010122000022226</errorMessage></opResult></opRep></QLBankData>";
        System.out.println(new String(a.getBytes("UTF-8"),"GBK"));


        int a11  = 10 ;

    }


    /**
     * 发送socket请求
     * @param clientIp
     * @param clientPort
     * @param msg
     * @return
     */
    private static synchronized String tcpPost(String clientIp,String clientPort,String msg){
        String rs = "";

        if(clientIp==null||"".equals(clientIp)||clientPort==null||"".equals(clientPort)){
            logger.error("Ip或端口不存在...");
            return null;
        }

        int clientPortInt = Integer.parseInt(clientPort);

        logger.info("clientIp："+clientIp+" clientPort："+clientPort);

        Socket s = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            s = new Socket(clientIp, clientPortInt);
            s.setSendBufferSize(4096);
            s.setTcpNoDelay(true);
            s.setSoTimeout(60*1000);
            s.setKeepAlive(true);
            out = s.getOutputStream();
            in = s.getInputStream();

            //准备报文msg
            logger.info("准备发送报文："+msg);

            out.write(msg.getBytes("GBK"));
            out.flush();

            byte[] rsByte = readStream(in);

            if(rsByte!=null){
                rs = new String(rsByte, "GBK");
            }


        } catch (Exception e) {
            logger.error("tcpPost发送请求异常："+e.getMessage());
        }finally{
            logger.info("tcpPost(rs)："+rs);
            try {
                if(out!=null){
                    out.close();
                    out = null;
                }
                if(in!=null){
                    in.close();
                    in = null;
                }
                if(s!=null){
                    s.close();
                    s = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rs;

    }

    /**
     * 读取输入流
     * @param in
     * @return
     */
    private static byte[] readStream(InputStream in){
        if(in==null){
            return null;
        }

        byte[] b = null;
        ByteArrayOutputStream outSteam = null;
        try {
            byte[] buffer = new byte[1024];
            outSteam = new ByteArrayOutputStream();

            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            b = outSteam.toByteArray();
        } catch (IOException e) {
            logger.error("读取流信息异常"+e);
            e.printStackTrace();
        } finally{
            try {
                if(outSteam!=null){
                    outSteam.close();
                    outSteam = null;
                }
                if(in!=null){
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

}
