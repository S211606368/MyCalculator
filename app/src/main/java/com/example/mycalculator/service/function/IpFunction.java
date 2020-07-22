package com.example.mycalculator.service.function;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ip工具类
 * @author LIN
 */
public class IpFunction {

    /**
     * 获取外网ip
     * @return 客户ip
     * @throws IOException
     * @throws JSONException
     */
    public static String getNetIp(){
        try{
            URL url = new URL("http://ip138.com");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                JSONObject obj = new JSONObject(stringBuilder.toString());
                String data = obj.getString("data");
                JSONObject dataObj = new JSONObject(data);
                String ip = dataObj.getString("ip");
                bufferedReader.close();
                inputStream.close();
                return ip;
            } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getLoginDate(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(new Date());
    }
}
