package com.code.createcode;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/5 3:19 下午
 **/
public class DefaultCodeGenerator implements CodeGenerator{

    private AutoGenerator autoGenerator;

    public DefaultCodeGenerator(AutoGenerator autoGenerator){
        this.autoGenerator=autoGenerator;
    }

    @Override
    public void execute() {
        autoGenerator.execute();
    }
}
