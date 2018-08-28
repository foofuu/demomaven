package com.fufu.spring.transaction;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zhoujunfu on 2018/8/28.
 */
@Service("userInfoLogService")
public class UserInfoLogServiceImpl implements UserInfoLogService {

    Logger logger = LoggerFactory.getLogger(UserInfoLogServiceImpl.class);

    @Resource
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLog(UserInfo userInfo, String operate) {
        if (userInfo == null || StringUtils.isBlank(operate)) {
            return;
        }
        String sql = "insert into userinfolog (userid, operate) values (?, ?)";
        Object[] args = new Object[]{userInfo.getBalance(), operate};
        jdbcTemplate.update(sql, args);

        throw new RuntimeException();
    }
}
