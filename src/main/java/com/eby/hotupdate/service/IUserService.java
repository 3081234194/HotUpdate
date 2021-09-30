package com.eby.hotupdate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eby.hotupdate.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eby
 * @since 2021-09-28
 */
public interface IUserService extends IService<User> {

    //获取用户列表
    List<User> getAllUser();
    //账号密码登录--成功返回token,错误返回null
    String login(String username, String password);
}
