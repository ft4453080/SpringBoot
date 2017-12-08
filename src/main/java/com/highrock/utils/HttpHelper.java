package com.highrock.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by user on 2017/7/19.
 */
public class HttpHelper {
    static final int timeOut = 180 * 1000;
    /**
     * 设置请求和传输超时时间,每个版本都不一样,查询相应的API
     * setConnectTimeout：设置连接超时时间，单位毫秒。
     * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     */
    public static final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();

    public static void sendGET(String GET_URL,String oauthUn,String oauthPwd) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpGet httpMethod = new HttpGet(GET_URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        if(oauthUn!=null || oauthPwd!=null ){
            setHeaderAuth(httpMethod,oauthUn,oauthPwd);
        }
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
       //System.out.println("GET Response Status:"+ httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        httpClient.close();
    }

    public static void sendGET(String GET_URL) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpGet httpMethod = new HttpGet(GET_URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        //System.out.println("GET Response Status:"+ httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        System.out.println(response.toString());
        httpClient.close();
    }

    public static String sendPOST(String URL,String jsonParam,String oauthUn,String oauthPwd) throws IOException {

        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPost httpMethod = new HttpPost(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        if(oauthUn!=null || oauthPwd!=null ){
            setHeaderAuth(httpMethod,oauthUn,oauthPwd);
        }
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
/*        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> it = paramMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
        httpPost.setEntity(postParams);*/
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        //System.out.println("POST Response Status:: " + httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String sendPOST(String URL,String jsonParam) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPost httpMethod = new HttpPost(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String sendPUT(String URL,String jsonParam,String oauthUn,String oauthPwd) throws IOException {

        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPut httpMethod = new HttpPut(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        if(oauthUn!=null || oauthPwd!=null ){
            setHeaderAuth(httpMethod,oauthUn,oauthPwd);
        }
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String sendPUT(String URL,String jsonParam) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPut httpMethod = new HttpPut(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String sendPATCH(String URL,String jsonParam,String oauthUn,String oauthPwd) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPatch httpMethod = new HttpPatch(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        if(oauthUn!=null || oauthPwd!=null ){
            setHeaderAuth(httpMethod,oauthUn,oauthPwd);
        }
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String sendPATCH(String URL,String jsonParam) throws IOException {
        CloseableHttpClient httpClient = HttpPool.getInstance().getHttpClient();
        HttpPatch httpMethod = new HttpPatch(URL);
        httpMethod.addHeader("User-Agent", USER_AGENT);
        httpMethod.addHeader("Content-type","application/json; charset=utf-8");
        httpMethod.setHeader("Accept", "application/json");
        httpMethod.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        httpMethod.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = httpClient.execute(httpMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    /**
     * 基于Base64的加密方式,加密强度非常低，提供简单的用户验证功能，适合于对安全性要求不高的系统或设备中
     * 缺乏灵活可靠的认证策略，如无法提供域（domain或realm）认证功能.
     * 解密 request.getHeader("Authorization");   new String( new BASE64Decoder().decodeBuffer(author...))
     * 认证之后将认证信息放在session，以后在session有效期内就不用再认证了。
     */
    private static HttpRequestBase setHeaderAuth(HttpRequestBase httpMethod, String oauthUn, String oauthPwd){
        try {
            String author = "Basic " + Base64.getEncoder().encodeToString((oauthUn+":"+oauthPwd).getBytes());
            httpMethod.addHeader("Authorization",author);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return httpMethod;
    }
    /**
     *   connection的版本这么写
     *   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
     *   String authStringEnc = Base64.encodeBase(authStr.getBytes());
     *   connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
     */





}
