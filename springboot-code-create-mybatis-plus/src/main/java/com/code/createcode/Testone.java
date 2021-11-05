package com.code.createcode;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/5 2:34 下午
 **/
public class Testone {

    public static void main(String[] args) throws IOException {

        CodeInfo codeInfo=new CodeInfo();
        codeInfo.setAuthor("chenhu");
        codeInfo.setPackageName("com.code");
        codeInfo.setTableName(Arrays.asList("user","orders"));

        CodeGeneratorFactory builder = new CodeGeneratorFactoryBuilder().builder(codeInfo);

        CodeGenerator codeGenerator = builder.create();

        codeGenerator.execute();
    }
}
