/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.dubbo.demo.consumer.comp;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.demo.DemoService;

import org.springframework.stereotype.Component;

@Component("demoServiceComponent")
public class DemoServiceComponent implements DemoService {
    /**
     * mock 在服务调用失败时起作用
     * mock return 表示返回null
     * mock return `mock`  表示返回mock
     * mock demoService 或者其他的值则需要提供一个DemoServiceMock的实现，并且和DemoService在同一个包中
     * mock true 或者 default 提供一个DemoServiceMock的实现，并且和DemoService在同一个包中
     * mock org.apache.dubbo.demo.consumer.comp.DemoServiceMock 必须指定全类名
     *
     * stub 用于远程方法执行前后做处理
     */
    @Reference(proxy = "jdk", cluster = "failover", mock = "org.apache.dubbo.demo.consumer.comp.DemoServiceMock", check = false, stub = "org.apache.dubbo.demo.consumer.comp.DemoServiceStub")
    private DemoService demoService;

    @Override
    public String sayHello(String name) {
        return demoService.sayHello(name);
    }
}
