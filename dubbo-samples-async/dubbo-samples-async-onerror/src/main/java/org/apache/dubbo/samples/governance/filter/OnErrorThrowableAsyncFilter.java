/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.samples.governance.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.ListenableFilter;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = 9997)
public class OnErrorThrowableAsyncFilter extends ListenableFilter {

    // 内部使用了OnErrorListener
    public OnErrorThrowableAsyncFilter() {
        listener = new OnErrorListener();
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    // idea 实现了Filter的onError
    class OnErrorListener implements Listener {

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {

        }

        @Override
        public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
            System.out.println("OnErrorThrowableAsyncFilter onError executed: " + t.getMessage());
            if (invocation != null) {
                throw new RuntimeException("Exception from onError");
            }
        }
    }

}
