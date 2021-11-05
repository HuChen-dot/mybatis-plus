package com.hu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 12:46 下午
 **/
@Data
public class User extends PageInfo{
    /**
     *  用户ID
     */
    private Long id;
    /**
     *  用户名称
     */
    @TableField("user_name")
    private String userName01;
    /**
     *  用户手机号
     */
    private String userPhone;
    /**
     *  用户密码
     */
    @TableField(select = false)
    private String userPassword;

    @TableField(exist = false)
    private String orderid;
}
