package com.yonhu.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    private static HttpClient httpclient = HttpClients.createDefault();

    public static String post(String url, String msg) {
        String result = null;
        try {
            HttpPost httpPost = assemblyHttpPost(url, msg);
            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("statusCode = " + response.getStatusLine().getStatusCode());
                return "";
            }
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static HttpPost assemblyHttpPost(String url, String paramsJson) {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        httppost.setEntity(new StringEntity(paramsJson, "utf-8"));
        return httppost;
    }

}
