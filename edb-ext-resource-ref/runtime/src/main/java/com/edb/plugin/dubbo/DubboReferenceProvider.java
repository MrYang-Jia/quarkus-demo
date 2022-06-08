
package com.edb.plugin.dubbo;

import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.ResourceReferenceProvider;
import org.apache.dubbo.config.annotation.DubboReference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @ClassName DubboReferenceProvider
 * @Description: DubboReferenceProvider
 * @Author MrYang
 * @Date 2022/6/8
 * @Version V1.0
 **/
public class DubboReferenceProvider implements ResourceReferenceProvider {

    /**
     * 服务注入时触发
     * @param type
     * @param annotations
     * @return
     */
    @Override
    public InstanceHandle<Object> get(Type type, Set<Annotation> annotations) {
        DubboReference dubboReference = getAnnotation(annotations, DubboReference.class);
        if (dubboReference != null) {
            InstanceHandle<Object> instanceHandle  = new InstanceHandle() {
                @Override
                public Object get() {
                    System.out.println("====== init ========");
                    // 直接返回一个用于本次案例的测试对象即可
                    return new com.edb.plugin.dubbo.service.DubboDemoServiceImpl();
                }
            };
            return instanceHandle;
        }
        System.out.println("====== return null ========");
        return null;
    }
}
