package com.edb.quarkus.plugin.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ConsumerItem
 * @Description: 消费者配置类
 * @Author 杨志佳
 * @Date 2022/4/18
 * @Version V1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface ConsumerItem {
    String topic(); // 标题
    String subExpression(); // 消费分类 , 多个可以用 || 隔开,全部则是 *
    Class<?> receiverClass(); // 消费者对象类名，通过类名去构建消费类
}
