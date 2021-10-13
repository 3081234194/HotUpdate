package com.eby.hotupdate;

import com.eby.hotupdate.service.impl.ProjectsServiceImpl;
import com.eby.hotupdate.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
class HotupdateApplicationTests {

    @Autowired
    private ProjectsServiceImpl projectsService;
    @Autowired
    private RedisUtils redisUtils;
    @Test
    void contextLoads() throws SQLException {
        System.out.println("-----");
        System.out.println(projectsService.getAll());
        System.out.println("-----");
    }

}
