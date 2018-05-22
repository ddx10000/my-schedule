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
import java.util.List;
import java.util.Map;

@RestController
public class MyControler {

    @Autowired
    MySqlService mySqlService;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        List<User> users = mySqlService.get(user);
        if (users.size() == 0){
            ModelAndView mav = new ModelAndView("login", new HashMap<>());
            return mav;
        }
        return "login success";
    }

    @GetMapping("/doregister")
    public Object doregister(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username);
        List<User> users = mySqlService.get(user);
        if (users.size() > 0){
            ModelAndView mav = new ModelAndView("register", new HashMap<>());
            return mav;
        } else {
            user.setPassWord(password);
            mySqlService.save(user);
            return "register success";
        }
    }
}