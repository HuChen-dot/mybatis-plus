package com.hu.pojo;

import lombok.Data;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 12:46 下午
 **/
@Data
public class User {

    /**
     *  用户ID
     */
    private Long id;
    /**
     *  用户名称
     */
    private String userName;
    /**
     *  用户手机号
     */
    private String userPhone;
    /**
     *  用户密码
     */
    private String userPassword;
}
