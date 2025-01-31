/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.apache.dubbo.samples.configcenter.multiregistries;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.samples.configcenter.multiregistries.action.AnnotationAction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class AnnotationConsumer {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String hello = annotationAction.doSayHello("world");
        System.out.println("result: " + hello);
    }


    // idea brilliant，可以把注解扫描的部分放进一个内部类中，这样就不会被spring的自动扫描碰到
    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.configcenter.multiregistries.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"org.apache.dubbo.samples.configcenter.multiregistries.action"})
    static public class ConsumerConfiguration {

    }

}
