package com.edb.quarkus.plugin.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MqConsumer
 * @Description: mq消费者注解
 * @Author 杨志佳
 * @Date 2022/2/16
 * @Version V1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface MqConsumer {
    // 消费者名称
    String name() default "main";
    // 队列分组名称
    String groupName();
    // 标题
    String topic();
    // 过滤规则
    String subExpression();
}
