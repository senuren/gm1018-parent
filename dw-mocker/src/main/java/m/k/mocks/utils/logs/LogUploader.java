package m.k.mocks.utils.logs;

import c.e.p.gm.commons.constants.GmConstants;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @date 2019-10-18 - 15:01
 */
public class LogUploader {
    public static void sendLogStream(String log){
        try{
            //不同的日志类型对应不同的URL
            //String logserver = "127.0.0.1";
            //String serverPort = "8080";
            //已经在常量值模块写好了。

            URL url  =new URL(GmConstants.PROTOCOL_HTTP+
                              GmConstants.logserverHost+
                              GmConstants.logserverPort+
                              GmConstants.postPath);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为post
            conn.setRequestMethod("POST");

            //时间头用来供server进行时钟校对的
            conn.setRequestProperty("clientTime",System.currentTimeMillis() + "");
            //允许上传数据
            conn.setDoOutput(true);
            //设置请求的头信息,设置内容类型为JSON
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            System.out.println("upload" + log);

            //输出流
            OutputStream out = conn.getOutputStream();
            out.write((GmConstants.UPLOAD_OUT_REQUEST_IN_logStr
                       +"="
                       +log)
                              .getBytes());
            out.flush();
            out.close();
            int code = conn.getResponseCode();
            System.out.println(code);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
