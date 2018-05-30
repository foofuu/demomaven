package com.fufu.disconf;

/**
 * Created by zhoujunfu on 2018/5/28.
 */
public class DisconfInfo {

    private String confServerHost;
    private String disconfHostUrl;
    private String version;
    private String app;
    private String env;
    private String userName;
    private String userPwd;

    private Boolean enableAutoUpload;
    private Boolean enableAutoOverride;

    private String appId;
    private String envId;

    public String getConfServerHost() {
        return confServerHost;
    }

    public void setConfServerHost(String confServerHost) {
        this.confServerHost = confServerHost;
    }

    public String getDisconfHostUrl() {
        return disconfHostUrl;
    }

    public void setDisconfHostUrl(String disconfHostUrl) {
        this.disconfHostUrl = disconfHostUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Boolean getEnableAutoUpload() {
        return enableAutoUpload;
    }

    public void setEnableAutoUpload(Boolean enableAutoUpload) {
        this.enableAutoUpload = enableAutoUpload;
    }

    public Boolean getEnableAutoOverride() {
        return enableAutoOverride;
    }

    public void setEnableAutoOverride(Boolean enableAutoOverride) {
        this.enableAutoOverride = enableAutoOverride;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", confServerHost=").append(confServerHost);
        sb.append(", disconfHostUrl=").append(disconfHostUrl);
        sb.append(", version=").append(version);
        sb.append(", app=").append(app);
        sb.append(", env=").append(env);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", enableAutoUpload=").append(enableAutoUpload);
        sb.append(", enableAutoOverride=").append(enableAutoOverride);
        sb.append(", appId=").append(appId);
        sb.append(", envId=").append(envId);
        sb.append(']');
        return sb.toString();
    }
}
