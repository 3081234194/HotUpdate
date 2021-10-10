package com.eby.hotupdate.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author eby
 * @since 2021-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 软件项目名字--限定20字
     */
    private String name;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 所属用户，外键用户id
     */
    private Integer belong;

    /**
     * 子访问路径，新建时自动创建
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近更新时间
     */
    private Date updateTime;


}
