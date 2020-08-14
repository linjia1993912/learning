package learning.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description:
 * @Author: linjia
 * @Date: 2018/8/7 13:19
 */
public class BcUtils {
    /**
     * 解析请求参数
     * @param request
     * @return
     */
    public static Map<String,Object> parsingParams(HttpServletRequest request){
        HashMap<String, Object> paramMap = new HashMap<>();
        Map<String, String[]> requestMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> it = requestMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String[]> entity = it.next();
            if(entity!=null && entity.getValue()!=null && entity.getValue().length>0){
                paramMap.put(entity.getKey(),entity.getValue()[0]);
            }
        }
        return paramMap;
    }

    /**
     * 发送http请求验证params
     * params为?后面的所有参数
     * @param params
     * @param authorTokenUrl
     * @return
     * @throws IOException
     */
    public static boolean authorParams(String params,String authorTokenUrl) throws IOException {
//        authorTokenUrl = authorTokenUrl+"?"+params;
        URL url = new URL(authorTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(60000);
        connection.setDoOutput(true);
        //设置传入的参数格式 如?token=123&param=123
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(params.getBytes());
        if(connection.getResponseCode() == 200)
        {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String temp = "";
            while ((temp = reader.readLine()) != null)
            {
                buffer.append(temp);
                buffer.append("\r\n");
            }
            System.out.println(buffer.toString());
            reader.close();
            outputStream.close();
            inputStream.close();
            return true;
        }
        connection.disconnect();
        return false;
    }

}
