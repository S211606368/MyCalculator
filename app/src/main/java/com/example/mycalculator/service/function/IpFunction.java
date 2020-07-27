package com.example.mycalculator.service.function;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Ip工具类
 * @author 林书浩
 * @date 2020/07/27
 */
public class IpFunction {

    /**
     * 获取外网ip
     *
     * @return 客户ip
     * @throws IOException
     * @throws JSONException
     */
    public static String getNetIp(){

        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL("https://www.51xc.cn/ipAddressServlet.action");
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = httpUrlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpUrlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(str);
                }

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                String ip = jsonObject.getString("ip");

                bufferedReader.close();
                inputStream.close();

                return ip;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLocalIp(){
        try{
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
                NetworkInterface intf = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)){
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return "null";
    }

    public static String getLoginDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

}
