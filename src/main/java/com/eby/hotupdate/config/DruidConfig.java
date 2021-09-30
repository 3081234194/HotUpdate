package com.eby.hotupdate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> b = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        HashMap<String,String> initParam = new HashMap<>();
        initParam.put("loginUsername","admin");
        initParam.put("loginPassword","admin123");
        initParam.put("allow","");
        b.setInitParameters(initParam);
        return b;
    }
}
