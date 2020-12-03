package com.example.springbootelasticsearch.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Http工具类
 * @author Erwin Feng
 * @since 2020/7/5
 */
public class HttpUtils {

    /**
     * http get请求
     * @param url
     * @return
     */
    public static String get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送http请求
     * @param authorTokenUrl
     * @return
     * @throws IOException
     */
    public static String getData(String authorTokenUrl) throws IOException {
        URL url = new URL(authorTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //设置请求头
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if(connection.getResponseCode() == 200)
        {
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream inBuffer = new BufferedInputStream(inputStream);
            int len = 0;
            //10MB的缓存
            byte[] buffer = new byte[10485760];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = inBuffer.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            inputStream.close();
            return baos.toString();
        }
        return null;
    }

}
