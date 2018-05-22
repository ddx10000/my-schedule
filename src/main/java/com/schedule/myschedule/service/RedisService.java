package com.schedule.myschedule.service;

import com.alibaba.fastjson.JSON;
import com.schedule.myschedule.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void add(String key, User user, Long time) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(user), time, TimeUnit.MINUTES);
    }

    public void add(String key, List<User> users, Long time) {
        String src = JSON.toJSONString(users);
        stringRedisTemplate.opsForValue().set(key, src, time, TimeUnit.MINUTES);
    }

    public User get(String key) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            return JSON.parseObject(source,User.class);
//            return gson.fromJson(source, User.class);
        }
        return null;
    }

    public List<User> getUserList(String key) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            return JSON.parseArray(source, User.class);
//            return gson.fromJson(source, new TypeToken<List<User>>() {
//            }.getType());
        }
        return null;
    }

    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}