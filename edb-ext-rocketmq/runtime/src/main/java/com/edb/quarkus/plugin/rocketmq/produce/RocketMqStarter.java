
package com.edb.quarkus.plugin.rocketmq.produce;

import com.edb.quarkus.plugin.rocketmq.annotation.MqConsumer;
import io.quarkus.runtime.Startup;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.*;

/**
 * @ClassName RocketMqStarter
 * @Description: RocketMqStarter
 * @Author 杨志佳
 * @Date 2022/4/18
 * @Version V1.0
 **/
@ApplicationScoped
@Startup
public class RocketMqStarter {
    private Logger logger = LoggerFactory.getLogger(RocketMqStarter.class);



    public static String naming(String name,String groupName){
        return name + "-" +groupName;
    }

    /**
     * 启动时初始化
     */
    @PostConstruct
    public void start() {
        // 获取所有cdi Bean
        Set<Bean<?>> beans = CDI.current().getBeanManager().getBeans(Object.class, new AnnotationLiteral<Any>() {
        });
        // 拼接消费者
        for (Bean<?> bean : beans) {
            MqConsumer mqConsumer = bean.getBeanClass().getAnnotation(MqConsumer.class);
            if (mqConsumer != null) { // 消费者
                try {
                    Thread.sleep(600L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.debug("===rocketmq===init");
            }
        }
        logger.debug("===rocketmq===start");

    }

    @PreDestroy
    void destroy() {
        logger.debug("===rocketmq===destroy");
    }
}
