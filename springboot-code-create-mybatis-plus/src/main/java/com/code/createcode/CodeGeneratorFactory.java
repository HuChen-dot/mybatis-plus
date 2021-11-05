package com.code.createcode;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/5 3:23 下午
 **/
public class CodeGeneratorFactory {

    private AutoGenerator autoGenerator;

    public CodeGeneratorFactory(AutoGenerator autoGenerator){

        this.autoGenerator=autoGenerator;
    }
    /**
     * 获取代码生成器对象
     * @return
     */
    public CodeGenerator create() {
        return new DefaultCodeGenerator(autoGenerator);
    }
}
