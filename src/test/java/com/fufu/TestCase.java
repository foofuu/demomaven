package com.fufu;

import com.fufu.spring.transaction.UserInfo;
import com.fufu.spring.transaction.UserInfoService;
import com.fufu.spring.transaction.UserInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by zhoujunfu on 2018/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestCase {

    @Resource
    UserInfoService userInfoService;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("fufu");
        userInfo.setBalance(new Random(47).nextInt(1000));

        userInfoService.addUser(userInfo);
    }
}
