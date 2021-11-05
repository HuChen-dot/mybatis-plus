package com.hu.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 6:47 下午
 **/
@Data
public class PageInfo {
    /**
     * 当前页
     */
    @TableField(exist = false)
    private Integer pageNo;

    /**
     * 每页条数
     */
    @TableField(exist = false)
    private Integer pageSize;
    /**
     * 总记录的数量
     */
    @TableField(exist = false)
    private Integer total;

    /**
     * 总共的页数
     */
    @TableField(exist = false)
    private Integer totalPage;

    /**
     * 是否启用分页
     */
    @TableField(exist = false)
    private boolean isOpenPage=false;

}

