package com.fh.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClient {



    public static String httpDelete(String url, Integer id,Map<String,String> headers){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete httpDelete =null;
        if (null!=id){
            url+="/"+id+".do";
            httpDelete = new HttpDelete(url);
        }
        headers(headers, httpDelete);
        String result ="";
        CloseableHttpResponse response =null;

        try {
            response = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=httpDelete){
                httpDelete.releaseConnection();
                httpDelete=null;
            }
            if (null!=httpClient){
                try {
                    httpClient.close();
                    httpClient=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public static String HttpPut(String url, Map<String,String> params,Map<String,String> headers){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut(url);
        if (null!=params && !params.isEmpty()){
            Gson gson = new Gson();
            String paramsToJson = gson.toJson(params);
            StringEntity stringEntity = null;
            try {
                stringEntity = new StringEntity(paramsToJson);
                stringEntity.setContentType("application/json");
                httpPut.setEntity(stringEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        headers(headers, httpPut);

        String result ="";
        CloseableHttpResponse response =null;

        try {
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=httpPut){
                httpPut.releaseConnection();
                httpPut=null;
            }
            if (null!=httpClient){
                try {
                    httpClient.close();
                    httpClient=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private static void headers(Map<String, String> headers, HttpRequest request) {
        if (null!=headers && !headers.isEmpty()){
            Iterator<Map.Entry<String, String>> header = headers.entrySet().iterator();
            while (header.hasNext()){
                Map.Entry<String, String> next = header.next();
                String key = next.getKey();
                String value = next.getValue();
                request.addHeader(key,value);
            }
        }
    }


    /**
     * Httpclient 发送 Get 请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String HttpGet(String url, Map<String,String> params,Map<String,String> headers){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet=null;
        String result ="";
        CloseableHttpResponse response =null;
        if (null!=params && !params.isEmpty()){
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                pairs.add(new BasicNameValuePair(key,value));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = null;
            try {
                urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, "utf-8");
                String string = EntityUtils.toString(urlEncodedFormEntity);
                url += "?"+string;


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        httpGet = new HttpGet(url);

        headers(headers, httpGet);
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=httpGet){
                httpGet.releaseConnection();
                httpGet=null;
            }
            if (null!=httpClient){
                try {
                    httpClient.close();
                    httpClient=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




        return result;
    }
    /**
     * Httpclient 发送Post 请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String httpPost(String url, Map<String,String> params,Map<String,String> headers){
        //打开浏览器
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        CloseableHttpResponse response=null;
        String result ="";
        if (null!=params && params.size()>0){
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                pairs.add(new BasicNameValuePair(key,value));
            }
        }
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            headers(headers, httpPost);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=httpPost){
                httpPost.releaseConnection();
            }
            if(null != httpClient){
                try {
                    httpClient.close();
                    httpClient=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return result;
    }



}
