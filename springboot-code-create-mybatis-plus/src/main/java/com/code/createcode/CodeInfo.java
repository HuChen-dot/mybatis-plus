package com.code.createcode;

import lombok.Data;

import java.net.URL;
import java.util.List;

/**
 * @Author: hu.chen
 * @Description: 代码生成时所需要的信息
 * @DateTime: 2021/11/5 2:26 下午
 **/
@Data
public class CodeInfo {

    /**
     * 包名前缀
     */
    private String packageName;

    /**
     * 开发人员名称（作者名）
     */
    private String author;

    /**
     * 表名
     **/
    private List<String> tableName;


    public  String getProjectPath(){
        URL xmlpath =CodeInfo.class.getClassLoader().getResource("");
        String path = xmlpath.getPath();
        String projectPath = path.substring(0, path.indexOf("target/classes/"));
        return projectPath;
    }

}
