package com.hu.interceptor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * @Author: hu.chen
 * @Description:
 * @DateTime: 2021/11/4 6:33 下午
 **/
//注意这是个大括号，也就是这里可以定义多个@Signature，对多个地方进行拦截，都使用这个拦截器
@Intercepts({   //type代表需要拦截四大组件中的哪一个组建
        @Signature(type = Executor.class,
                //method代表拦截这个组件对象中的哪一个方法
                method = "update",
                //args代表拦截方法的入参类型（因为同样一个方法可能有多个重载，所以要通过入参类型来确定唯一一个）
                args = {MappedStatement.class,
                        Object.class}),
        @Signature(type = Executor.class,
                //method代表拦截这个组件对象中的哪一个方法
                method = "query",
                //args代表拦截方法的入参类型（因为同样一个方法可能有多个重载，所以要通过入参类型来确定唯一一个）
                args = {MappedStatement.class,
                        Object.class, RowBounds.class, ResultHandler.class})
})
@Component

public class Myplug_in implements Interceptor {

    /**
     * 拦截方法：只要拦截的目标接口的目标方法被执行时，每次都会执行这个方法
     */
    @Override
    public Object intercept(Invocation invocation)
            throws Throwable {
        try {
            // 获取xml中的一个select/update/insert/delete节点，是一条SQL语句
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            // 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }

            // BoundSql就是封装myBatis最终产生的sql类
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            // 获取节点的配置
            Configuration configuration = mappedStatement.getConfiguration();
            // 获取到最终的sql语句
            String sql = showSql(configuration, boundSql);
            System.err.println("sql --> " + sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 执行完上面的任务后，不改变原有的sql执行过程
        return invocation.proceed();
    }


    /**
     *  如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
     * @param obj
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                    DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }


    /**
     * 对sql中 进行？的替换
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        // 获取参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        // sql语句中多个空格都用一个空格代替
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
            // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            // 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?",
                        Matcher.quoteReplacement(getParameterValue(parameterObject)));

            } else {
                // MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 该分支是动态sql
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?",
                                Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        // 打印出缺失，提醒该参数缺失并防止错位
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }


    /**
     * 主要是为了把当前自定义的拦截器对象生成代理，添加到执行链中
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        //这是固定写法
        return Plugin.wrap(target, this);
    }

    /**
     * 获取配置文件的参数
     * <plugins>
     * <plugin interceptor="com.hu.interceptor.Myplug_in">
     * <property name="helperDialect" value="mysql"/>
     * <property name="pageSizeZero" value="true"/>
     * </plugin>
     * </plugins>
     * 获取的就是 property 的值
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

        System.err.println("获取到的文件参数是:" + properties);

    }
}
