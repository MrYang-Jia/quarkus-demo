package com.edb.quarkus.plugin.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MqProducer
 * @Description: mq生产者注解
 * @Author 杨志佳
 * @Date 2022/2/16
 * @Version V1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface MqProducer {
    // 生产者名称
    String name() default "main";
    // 队列分组名称
    String groupName();
}
