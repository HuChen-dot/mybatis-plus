package com.code.createcode;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hu.chen
 * @Description: 代码生成器构建工厂
 * @DateTime: 2021/11/5 3:01 下午
 **/
public class CodeGeneratorFactoryBuilder {

    private AutoGenerator autoGenerator;

    /**
     * 构建 AutoGenerator 对象
     * @param codeInfo
     */
    public CodeGeneratorFactory builder(CodeInfo codeInfo) {
        // 代码⽣成器
        autoGenerator = new AutoGenerator();
        doBilder(codeInfo);
        return new CodeGeneratorFactory(autoGenerator);
    }


    private void doBilder(CodeInfo codeInfo) {
        builderGlobalConfig(codeInfo);
    }

    /**
     * 构建全局配置
     *
     * @param codeInfo
     */
    private void builderGlobalConfig(CodeInfo codeInfo) {
        GlobalConfig gc = new GlobalConfig();
        String projectPath = codeInfo.getProjectPath();
        //项目的类路径,代码的生成位置
        gc.setOutputDir(projectPath + "src/main/java");
        //设置作者名
        gc.setAuthor(codeInfo.getAuthor());
        gc.setOpen(false);
        autoGenerator.setGlobalConfig(gc);
        //调用数据源配置
        builderDataSource(codeInfo);
    }

    /**
     * 构建数据源
     *
     * @param codeInfo
     */
    private void builderDataSource(CodeInfo codeInfo) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.196.79.22:3306/seckill?connectTimeout=10000&socketTimeout=10000&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        autoGenerator.setDataSource(dsc);
        //调用包配置
        builderPackageConfig(codeInfo);
    }

    /**
     * 构建包配置
     *
     * @param codeInfo
     */
    private void builderPackageConfig(CodeInfo codeInfo) {
        PackageConfig pc = new PackageConfig();
        //代码生成的包路径
        pc.setParent(codeInfo.getPackageName());
        autoGenerator.setPackageInfo(pc);

        //调用自定义配置
        builderInjectionConfig(codeInfo);
    }

    /**
     * 构建自定义配置
     *
     * @param codeInfo
     */
    private void builderInjectionConfig(CodeInfo codeInfo) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return codeInfo.getProjectPath() + "src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));
        //调用策略配置
        bilderStrategyConfig(codeInfo);
    }

    /**
     * 构建策略配置
     *
     * @param codeInfo
     */
    private void bilderStrategyConfig(CodeInfo codeInfo) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        //生成的表名
        strategy.setInclude(codeInfo.getTableName().toArray(new String[0]));
        autoGenerator.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
    }


}
