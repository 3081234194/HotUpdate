package com.eby.hotupdate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eby.hotupdate.pojo.Projects;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eby
 * @since 2021-09-30
 */
public interface IProjectsService extends IService<Projects> {

    //插入，返回链接
    Projects insert(String token, String name, String description);
}
