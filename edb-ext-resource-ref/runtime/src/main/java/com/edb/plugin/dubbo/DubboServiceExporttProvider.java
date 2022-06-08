/**
 * Copyright (c) 2021 , YangZhiJia 杨志佳 (edbplus@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.edb.plugin.dubbo;

import io.quarkus.runtime.Startup;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.annotation.DubboService;
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
import java.util.Set;

@ApplicationScoped
@Startup
public class DubboServiceExporttProvider {

    private Logger logger = LoggerFactory.getLogger(DubboServiceExporttProvider.class);


    /**
     * 启动
     */
    @PostConstruct
    public void exportStart() {
        // 获取所有cdi Bean
        Set<Bean<?>> beans = CDI.current().getBeanManager().getBeans(Object.class, new AnnotationLiteral<Any>() {
        });
        for (Bean<?> bean : beans) {
            DubboService dubboService = bean.getBeanClass().getAnnotation(DubboService.class);
            if (dubboService != null) { // 凡是 DubboService 实体注解的 cdi Bean 则注册生成 dubbo provider
                Instance<?> instance = CDI.current().select(bean.getBeanClass());
                Class<?> interfaceClass = dubboService.interfaceClass();
                // 注册服务
                this.registerDubboService(dubboService, interfaceClass, instance.get());
                logger.info("finding dubbo service =>:{}",bean.getBeanClass());
            }
        }
    }

    @PreDestroy
    void destroy() {
        DubboShutdownHook.destroyAll(); // 关闭dubbo相关服务
    }

    /**
     * 注册服务
     * @param dubboService
     * @param clz
     * @param obj
     */
    private void registerDubboService(DubboService dubboService, Class clz, Object obj) {
//        ApplicationConfig applicationConfig = DubboConfigUtils.applicationConfig(dubboConfig);
//        RegistryConfig registryConfig = DubboConfigUtils.registryConfig(dubboConfig);
//        ProtocolConfig protocolConfig = DubboConfigUtils.protocolConfig(dubboConfig);
//        ServiceConfig<Object> service = new ServiceConfig<>();
//        service.setApplication(applicationConfig);
//        service.setProtocol(protocolConfig);
//        this.bindParams(service, dubboService,registryConfig); //绑定属性
//        service.setInterface(clz);
//        service.setRef(obj);
//        service.export();
    }

    /**
     * 绑定属性
     * @param target
     * @param orign
     */
    private void bindParams(ServiceConfig<Object> target, DubboService orign,RegistryConfig registryConfig) {
//        target.setRetries(orign.retries());
//
//        // 分组
//        if(StringUtils.isNotEmpty(orign.group())){
//            String groupName = DubboConfigUtils.petchProperName(orign.group());
//            target.setGroup(groupName); // 分组
//        }
//
//        // 版本号
//        String version = DubboConfigUtils.petchProperName(orign.version());
//        target.setVersion(version); // 版本号
//
//        target.setRegistry(registryConfig); // 注册中心
//        if (orign.timeout() != 0) {
//            target.setTimeout(orign.timeout());
//        }
//        if (StringUtils.isNotEmpty(orign.onconnect())) {
//            target.setOnconnect(orign.onconnect());
//        }

    }
}

