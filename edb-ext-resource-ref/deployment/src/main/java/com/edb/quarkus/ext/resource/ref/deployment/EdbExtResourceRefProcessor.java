package com.edb.quarkus.ext.resource.ref.deployment;

import com.edb.plugin.dubbo.DubboReferenceProvider;
import com.edb.plugin.dubbo.DubboServiceExporttProvider;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.arc.deployment.ResourceAnnotationBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedResourceBuildItem;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.jboss.jandex.DotName;

class EdbExtResourceRefProcessor {

    private static final String FEATURE = "edb-ext-resource-ref";

    private static final DotName DUBBO_PROVIDER = DotName.createSimple(DubboService.class.getName());

    private static final DotName DubboReferenceName = DotName.createSimple(DubboReference.class.getName());

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }


    /**
     * 注册资源 @DubboReference 可引用,但是偶然性失效，推测是 quarkus 本身扩展的健壮性 存在问题
     * @param resourceAnnotations
     * @param resources
     */
    @BuildStep
    void setupResourceInjection(BuildProducer<ResourceAnnotationBuildItem> resourceAnnotations, BuildProducer<GeneratedResourceBuildItem> resources) {
        resources.produce(new GeneratedResourceBuildItem("META-INF/services/io.quarkus.arc.ResourceReferenceProvider",
                DubboReferenceProvider.class.getName().getBytes()));
        resourceAnnotations.produce(new ResourceAnnotationBuildItem(DubboReferenceName));
    }

    @BuildStep
    void load(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        // 不可移除的类 -- 做增强，避免丢失
        additionalBeans.produce(AdditionalBeanBuildItem.builder().setUnremovable()
                        .addBeanClass(DubboReferenceProvider.class)
                        .addBeanClass(DubboServiceExporttProvider.class)
                        .build()
        );
    }


    @BuildStep
    BeanDefiningAnnotationBuildItem additionalBeanDefiningAnnotation() {
        // 如果您需要处理带有特定注释的类，则可以通过BeanDefiningAnnotationBuildItem
        return new BeanDefiningAnnotationBuildItem(DUBBO_PROVIDER);
    }

}
