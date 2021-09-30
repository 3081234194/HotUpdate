package com.eby.hotupdate.controller;


import com.eby.hotupdate.annotion.AuthToken;
import com.eby.hotupdate.mapper.UserMapper;
import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.pojo.User;
import com.eby.hotupdate.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eby
 * @since 2021-09-28
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @AuthToken
    @GetMapping("/all")
    public ResCommonBean getAllUser(){
        return ResCommonBean.successData(userService.getAllUser());
    }

    @PostMapping("/login")
    public ResCommonBean login(String username, String password){

        String u =  userService.login(username,password);

        if(u==null) {
            return ResCommonBean.error("账户或密码错误");
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("token",u);
        return ResCommonBean.successData(map);
    }

}
