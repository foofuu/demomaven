package com.fufu.spring.transaction;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhoujunfu on 2018/8/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
    Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    UserInfoLogService userInfoLogService;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        String sql = "insert into userinfo (name, balance) values (?, ?)";
        Object[] args = new Object[]{userInfo.getName(), userInfo.getBalance()};
        jdbcTemplate.update(sql, args);

        try {
            userInfoLogService.addLog(userInfo, "add");
        } catch (Exception e) {
            logger.info("");
        }
    }
}
