package com.schedule.myschedule.service;

import com.gitee.sunchenbin.mybatis.actable.manager.common.BaseMysqlCRUDManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySqlService {
    @Autowired
    BaseMysqlCRUDManager baseMysqlCRUDManager;

    public <T> void save(T t){
        baseMysqlCRUDManager.save(t);
    }

    public <T> List<T> get(T t){
        return baseMysqlCRUDManager.query(t).getData();
    }

    public <T> void delete(T t){
        baseMysqlCRUDManager.delete(t);
    }
}
