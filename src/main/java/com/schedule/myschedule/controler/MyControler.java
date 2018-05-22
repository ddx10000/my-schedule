package com.schedule.myschedule.controler;

import com.gitee.sunchenbin.mybatis.actable.manager.common.BaseMysqlCRUDManager;
import com.schedule.myschedule.entity.User;
import com.schedule.myschedule.service.MySqlService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/home")
public class MyControler {

    @Autowired
    MySqlService mySqlService;

    @PostMapping("/add1")
    public String add1(@RequestBody JSONObject jsonObject){
        User test = new User();
        test.setUserName(jsonObject.getString("name"));
        mySqlService.save(test);
        return "success";
    }
    @PostMapping("/get")
    public String get(@RequestBody JSONObject jsonObject){
        User test = new User();
        test.setUserName(jsonObject.getString("name"));
        return mySqlService.get(test).toString();
    }
    @PostMapping("/delet")
    public String delete(@RequestBody JSONObject jsonObject){
        User test = new User();
        test.setUserName(jsonObject.getString("name"));
        mySqlService.delete(test);
        return "success";
    }

    @GetMapping("/login")
    public Object login(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("login", new HashMap<>());
        return mav;
    }

    @GetMapping("/register")
    public Object register(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("register", new HashMap<>());
        return mav;
    }

    @GetMapping("/dologin")
    public Object dologin(HttpServletRequest request, HttpServletResponse response){
//        ModelAndView mav = new ModelAndView("login", new HashMap<>());
        return "login success";
    }

    @GetMapping("/doregister")
    public Object doregister(HttpServletRequest request, HttpServletResponse response){
//        ModelAndView mav = new ModelAndView("login", new HashMap<>());
        return "register success";
    }
}