package com.edb.quarkus.ext.rocketmq.deployment;

import com.edb.quarkus.plugin.rocketmq.annotation.MqConsumer;
import com.edb.quarkus.plugin.rocketmq.annotation.MqProducer;
import com.edb.quarkus.plugin.rocketmq.produce.RocketMqProvider;
import com.edb.quarkus.plugin.rocketmq.produce.RocketMqStarter;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.arc.deployment.ResourceAnnotationBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedResourceBuildItem;
import org.jboss.jandex.DotName;

class EdbExtRocketmqProcessor {

    private static final String FEATURE = "edb-ext-rocketmq";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    private static final DotName RMQ_CONSUMER = DotName.createSimple(MqConsumer.class.getName());

    @BuildStep
    void load(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        // 启动时加载
        additionalBeans.produce(new AdditionalBeanBuildItem(RocketMqStarter.class));
        additionalBeans.produce(new AdditionalBeanBuildItem(RocketMqProvider.class));
    }


    @BuildStep
    void setupResourceInjection(BuildProducer<ResourceAnnotationBuildItem> resourceAnnotations, BuildProducer<GeneratedResourceBuildItem> resources) {
        resources.produce(new GeneratedResourceBuildItem("META-INF/services/io.quarkus.arc.ResourceReferenceProvider",
                RocketMqProvider.class.getName().getBytes()));
        // 加入对象资源申明注解
        resourceAnnotations.produce(new ResourceAnnotationBuildItem(DotName.createSimple(MqProducer.class.getName())));
    }

    /**
     * 加载指定注解的bean
     * @return
     */
    @BuildStep
    BeanDefiningAnnotationBuildItem additionalBeanDefiningAnnotation() {
        return new BeanDefiningAnnotationBuildItem(RMQ_CONSUMER);
    }

}
