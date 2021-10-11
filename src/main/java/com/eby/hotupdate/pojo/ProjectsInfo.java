package com.eby.hotupdate.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author eby
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 外键--projects主键
     */
    private Long id;

    /**
     * 更新链接
     */
    private String updateUrl;

    /**
     * 版本号
     */
    private String version;

    /**
     * 是否需要弹窗提示
     */
    private Boolean dialog;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 是否强制更新
     */
    private Boolean force;


}
