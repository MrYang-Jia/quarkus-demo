
package com.edb.quarkus.plugin.rocketmq.produce;

import com.edb.quarkus.plugin.rocketmq.annotation.MqProducer;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.ResourceReferenceProvider;
import org.apache.rocketmq.client.producer.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @ClassName RocketMqProvider
 * @Description: RocketMqProvider
 * @Author 杨志佳
 * @Date 2022/4/18
 * @Version V1.0
 **/
@Singleton
public class RocketMqProvider implements ResourceReferenceProvider {

    private Logger logger = LoggerFactory.getLogger(RocketMqProvider.class);


    /**
     * 注入对象时生成 mq 生产者
     * @param type
     * @param annotations
     * @return
     */
    @Override
    public InstanceHandle<Object> get(Type type, Set<Annotation> annotations) {
        // 该方法的源码触发节点位于 ArcContainerImpl.getResource -> InstanceHandle<Object> getResource(Type type, Set<Annotation> annotations)
        MqProducer mqProducer = getAnnotation(annotations, MqProducer.class);
        if (mqProducer != null) { // 生产者

            InstanceHandle<Object> instanceHandle  = new InstanceHandle(){
                @Override
                public Object get() {
                    logger.info("--- rocketmq provider ----");
                    return null;
                }
            };
            return instanceHandle;
        }
        return null;
    }
}
