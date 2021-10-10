package com.eby.hotupdate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eby.hotupdate.mapper.UserMapper;
import com.eby.hotupdate.pojo.ResCommonBean;
import com.eby.hotupdate.pojo.User;
import com.eby.hotupdate.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eby
 * @since 2021-09-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public List<User> getAllUser(){
        return userMapper.selectList(null);
    }

    public String login(String username, String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",username);
        map.put("password",password);
        wrapper.allEq(map,true);
        User user =  userMapper.selectOne(wrapper);
        if(user!=null){
            String token = (String) redisTemplate.opsForValue().get("user:token:"+username);
            if(token!=null){
                //重置token过期时间
                redisTemplate.expire("user:token:"+username,1, TimeUnit.DAYS);
                redisTemplate.expire("token:user:"+token,1,TimeUnit.DAYS);
                log.info("采用redis中");
                return token;
            }else{
                String genToken = UUID.randomUUID().toString().replace("-","");
                redisTemplate.opsForValue().set("user:token:"+username,genToken,1,TimeUnit.DAYS);
                redisTemplate.opsForValue().set("token:user:"+genToken,user,1,TimeUnit.DAYS);
                log.info("生成token:"+genToken);
                return genToken;
            }
        }
        return null;
    }
}
