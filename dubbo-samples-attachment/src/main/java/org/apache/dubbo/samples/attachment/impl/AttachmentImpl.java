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

package org.apache.dubbo.samples.attachment.impl;

import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.samples.attachment.api.AttachmentService;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AttachmentImpl implements AttachmentService {

    public String sayHello(String name) {
        RpcContext context = RpcContext.getContext();

        // the attachment will be remove after this
        // idea consumer端使用attachment, 然后提供端通过attachment来使用
        // 可以用来传递隐藏的参数值，例如需要验证的时候，验证的token不应该是这个方法的参数之一，就可以通过隐藏参数来传递，然后在filter中进行验证
        String index = context.getAttachment("index");
        System.out.println("receive attachment index: " + index);

        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name +
                ", request from consumer: " + context.getRemoteAddress());
        return "Hello " + name + ", response from provider: " + context.getLocalAddress() +
                ", attachment - index: " + index;
    }
}
