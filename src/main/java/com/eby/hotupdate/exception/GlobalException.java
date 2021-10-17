package com.eby.hotupdate.exception;

import com.eby.hotupdate.pojo.ResCommonBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{
    private ResCommonBeanEnum resCommonBeanEnum;
}
