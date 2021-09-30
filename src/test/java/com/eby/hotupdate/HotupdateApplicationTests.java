package com.eby.hotupdate;

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
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(redisUtils.hasKey("token:user:"+"fejfkef"));
    }

}
