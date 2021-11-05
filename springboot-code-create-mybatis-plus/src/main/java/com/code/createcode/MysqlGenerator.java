package com.code.createcode;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: hu.chen
 * @Description: 代码生成器演示例子
 * @DateTime: 2021/11/5 1:55 下午
 **/
public class MysqlGenerator {

    /**
     * RUN THIS
     */
    public static void main(String[] args) {


        CodeInfo codeInfo=new CodeInfo();
        codeInfo.setAuthor("chenhu");
        codeInfo.setPackageName("com.code");
        codeInfo.setTableName(Arrays.asList("user","orders"));

        // 代码⽣成器
        AutoGenerator mpg = new AutoGenerator();


        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = getProjectPath();
        //项目的类路径,代码的生成位置
        gc.setOutputDir(projectPath+ "src/main/java");
        //设置作者名
        gc.setAuthor(codeInfo.getAuthor());
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.196.79.22:3306/seckill?connectTimeout=10000&socketTimeout=10000&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        //代码生成的包路径
        pc.setParent(codeInfo.getPackageName());
        mpg.setPackageInfo(pc);


        // 自定义配置
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
                return projectPath + "src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        //生成的表名
        strategy.setInclude(codeInfo.getTableName().toArray(new String[0]));
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    public static String getProjectPath(){
        URL xmlpath =Testone.class.getClassLoader().getResource("");
        String path = xmlpath.getPath();
        String projectPath = path.substring(0, path.indexOf("target/classes/"));
        return projectPath;
    }


}
