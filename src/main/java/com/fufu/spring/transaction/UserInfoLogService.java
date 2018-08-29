package com.fufu.spring.transaction;

/**
 * Created by zhoujunfu on 2018/8/28.
 */
public interface UserInfoLogService {
    void addLog(UserInfo userInfo, String operate);
}
