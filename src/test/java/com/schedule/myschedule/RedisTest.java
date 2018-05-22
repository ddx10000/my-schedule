package com.schedule.myschedule;

import com.schedule.myschedule.service.RedisService;
import com.schedule.myschedule.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisService redisService;

    @Before
    public void setUp() {

    }

    @Test
    public void get() {
        User user = new User();
        user.setUserName("wangjianfeng2");
        user.setPassWord("wangjianfeng2");
        redisService.add("userByName:" + user.getUserName(), user, 10L);
        List<User> list = new ArrayList<>();
        list.add(user);
        redisService.add("list", list, 10L);
        User user1 = redisService.get("userByName:wangjianfeng2");
        Assert.notNull(user1, "user is null");
        List<User> list2 = redisService.getUserList("list");
        Assert.notNull(list2, "list is null");
    }
}