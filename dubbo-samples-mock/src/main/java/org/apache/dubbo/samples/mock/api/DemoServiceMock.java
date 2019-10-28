/*
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
 */

package org.apache.dubbo.samples.mock.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// idea mock 和 stub的区别可能是， mock 是 伪装某个服务功能, 而stub是控制某个功能的行为，修改或者增加逻辑
public class DemoServiceMock implements DemoService {
    private static Logger logger = LoggerFactory.getLogger(DemoServiceMock.class);

    public String sayHello(String name) {
        logger.warn("about to execute mock: " + DemoServiceMock.class.getSimpleName());
        return "mock " + name;
    }
}
