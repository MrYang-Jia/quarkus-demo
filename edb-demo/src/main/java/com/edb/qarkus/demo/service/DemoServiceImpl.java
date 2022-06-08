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
package com.edb.qarkus.demo.service;

import com.edb.plugin.dubbo.service.DubboDemoService;
import org.apache.dubbo.config.annotation.DubboReference;

import javax.inject.Singleton;

/**
 * @ClassName DemoServiceImpl
 * @Description: DemoServiceImpl
 * @Author 杨志佳
 * @Date 2022/6/8
 * @Version V1.0
 **/
@Singleton
public class DemoServiceImpl implements DemoService{
    @DubboReference(check = false,group="demo",version = "1.0.0")
    DubboDemoService dubboDemoService;
    public String check(){
        if(dubboDemoService==null)
        return "service is null ";
        else{
            dubboDemoService.name();
            return "service is success";
        }
    }
}
