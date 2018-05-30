package com.fufu.disconf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhoujunfu on 2018/5/28.
 */
public class DisconfOperator {

    private OkHttpClient client;

    private HashMap<String, List<Cookie>> cookieStore;

    public DisconfOperator(){

        cookieStore = new HashMap<>();

        client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                if (cookies == null) {
                    cookieStore.put(httpUrl.host(), list);
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(httpUrl.host());
                return cookies == null ? new ArrayList<Cookie>() : cookies;
            }
        }).build();
    }

    public static DisconfInfo getDisconfInfo() throws IOException {
        Properties disconf = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("disconf-auto.properties");
        InputStream inputStream = classPathResource.getInputStream();
        disconf.load(inputStream);

        DisconfInfo disconfInfo = new DisconfInfo();
        disconfInfo.setConfServerHost(disconf.getProperty("conf_server_host"));
        disconfInfo.setDisconfHostUrl("http://" + disconf.getProperty("conf_server_host"));
        disconfInfo.setVersion(disconf.getProperty("version"));
        disconfInfo.setApp(disconf.getProperty("app"));
        disconfInfo.setEnv(disconf.getProperty("env"));
        disconfInfo.setUserName(disconf.getProperty("username"));
        disconfInfo.setUserPwd(disconf.getProperty("userpwd"));

        String enableAutoUpload = disconf.getProperty("enable.auto.upload");
        disconfInfo.setEnableAutoUpload(StringUtils.isBlank(enableAutoUpload) ? null : Boolean.valueOf(enableAutoUpload));

        String enableAutoOverride = disconf.getProperty("enable.auto.override");
        disconfInfo.setEnableAutoOverride(StringUtils.isBlank(enableAutoOverride) ? null : Boolean.valueOf(enableAutoOverride));

        return disconfInfo;
    }

    /**
     * 检查disconf信息
     * @param disconfInfo
     * @return
     */
    public static void checkDisconfInfo(DisconfInfo disconfInfo){
        if(StringUtils.isBlank(disconfInfo.getConfServerHost())){
            throw new RuntimeException("disconf host 为空！");
        }

        if(StringUtils.isBlank(disconfInfo.getVersion())){
            throw new RuntimeException("disconf version 为空！");
        }

        if(StringUtils.isBlank(disconfInfo.getApp())){
            throw new RuntimeException("disconf app 为空！");
        }

        if(StringUtils.isBlank(disconfInfo.getEnv())){
            throw new RuntimeException("disconf env 为空！");
        }

        if (StringUtils.isBlank(disconfInfo.getUserName())) {
            throw new RuntimeException("disconf username 为空！");
        }

        if (StringUtils.isBlank(disconfInfo.getUserPwd())) {
            throw new RuntimeException("disconf userpwd 为空！");
        }
    }

    /**
     * 登录disconf
     * @param disconfInfo
     * @return
     * @throws Exception
     */
    public String login(DisconfInfo disconfInfo) throws Exception {
        String sessionId = null;

        RequestBody body = new FormBody.Builder()
                .add("name", disconfInfo.getUserName())
                .add("password", disconfInfo.getUserPwd())
                .add("remember", "0")
                .build();

        Request request = new Request.Builder()
                .url(disconfInfo.getDisconfHostUrl() + "/api/account/signin")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        String res = response.body().string();
        response.close();

        JSONObject jsonObj = null;
        try {
            jsonObj = getJsonObj(res);
        } catch (Exception e) {
            throw new RuntimeException(String.format("登录发生异常，返回结果：%s", res), e);
        }

        if(jsonObj!= null && jsonObj.getBoolean("success")){
            sessionId = jsonObj.getString("sessionId");
        }else{
            throw new RuntimeException(String.format("登录disconf失败，返回结果：%s", res));
        }

        return sessionId;
    }

    /**
     * 转换成JSON
     * @param text
     * @return
     */
    private static JSONObject getJsonObj(String text){

        return JSON.parseObject(text);
    }

    public boolean checkAppExist(DisconfInfo disconfInfo) throws Exception {

        Request request = new Request.Builder()
                .url(disconfInfo.getDisconfHostUrl() + "/api/app/list")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        String res = response.body().string();
        response.close();

        JSONObject jsonObj = null;
        try {
            jsonObj = getJsonObj(res);
        } catch (Exception e) {
            throw new RuntimeException(String.format("检查App是否存在发生异常，返回结果：%s", res), e);
        }

        if(jsonObj!= null && jsonObj.getBoolean("success")){
            JSONArray appArray = jsonObj.getJSONObject("page").getJSONArray("result");
            if(appArray.size() > 0){
                for(int i = 0; i < appArray.size(); i++){
                    JSONObject appInfo = appArray.getJSONObject(i);
                    if(disconfInfo.getApp().equals(appInfo.getString("name"))){
                        //记录appId
                        disconfInfo.setAppId(appInfo.getString("id"));
                        return true;
                    }
                }
            }
        }else{
            throw new RuntimeException(String.format("检查App是否存在失败，返回结果：%s", res));
        }

        return false;
    }

    /**
     * 检查环境信息是否存在
     * @param disconfInfo
     * @return
     */
    public boolean checkEnvExist(DisconfInfo disconfInfo) throws Exception {

        Request request = new Request.Builder()
                .url(disconfInfo.getDisconfHostUrl() + "/api/env/list")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String res = response.body().string();
        response.close();

        JSONObject jsonObj = null;
        try {
            jsonObj = getJsonObj(res);
        } catch (Exception e) {
            throw new RuntimeException(String.format("检查环境信息发生异常，返回结果：%s", res), e);
        }

        if(jsonObj!= null && jsonObj.getBoolean("success")){
            JSONArray appArray = jsonObj.getJSONObject("page").getJSONArray("result");
            if(appArray.size() > 0){
                for(int i = 0; i < appArray.size(); i++){
                    JSONObject appInfo = appArray.getJSONObject(i);
                    if(disconfInfo.getEnv().equals(appInfo.getString("name"))){
                        //记录envId
                        disconfInfo.setEnvId(appInfo.getString("id"));
                        return true;
                    }
                }
            }
        }else{
            throw new RuntimeException(String.format("检查环境信息是否存在失败，返回结果：%s", res));
        }

        return false;
    }

    /**
     * 上传disconf普通配置文件
     * @param disconfInfo
     * @param confFileStreamList
     * @throws Exception
     */
    public void uploadDisconfFileConf(DisconfInfo disconfInfo, List<FileStream> confFileStreamList) throws Exception {

        if(confFileStreamList == null || confFileStreamList.size() <= 0){
            return;
        }

        for(FileStream fileStream : confFileStreamList){

            String fileName = fileStream.getFileName();

            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), IOUtils.toByteArray(fileStream.getInputStream()));

            RequestBody multipartBody = new MultipartBody.Builder()
                    .addFormDataPart("appId", disconfInfo.getAppId())
                    .addFormDataPart("version", disconfInfo.getVersion())
                    .addFormDataPart("envId", disconfInfo.getEnvId())
                    .addFormDataPart("myfilerar", fileName,fileBody)
                    .build();

            Request request = new Request.Builder()
                    .url(disconfInfo.getDisconfHostUrl() + "/api/web/config/file/" + String.valueOf(fileStream.getFileCode()))
                    .post(multipartBody)
                    .build();

            System.out.println(disconfInfo.getDisconfHostUrl() + "/api/web/config/file/" + String.valueOf(fileStream.getFileCode()));
            Response response = client.newCall(request).execute();

            String res = response.body().string();
            response.close();

            JSONObject jsonObj = null;
            try {
                jsonObj = getJsonObj(res);
            } catch (Exception e) {
                throw new RuntimeException(String.format("上传普通配置文件发生异常，fileName:%s，返回结果：%s", fileName, res), e);
            }

            if(jsonObj == null || !jsonObj.getBoolean("success")){
                throw new RuntimeException(String.format("上传普通配置文件失败，fileName:%s，返回结果：%s", fileName, res));
            }

            System.out.println(String.format("上传普通配置文件成功，fileName:%s", fileName));
        }

    }
}
