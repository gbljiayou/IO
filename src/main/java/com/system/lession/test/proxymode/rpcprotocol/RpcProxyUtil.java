package com.system.lession.test.proxymode.rpcprotocol;


import com.system.lession.test.rpcenv.PackageMsg;
import com.system.lession.test.rpcenv.connectpool.ClientFactory;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * @author: 马士兵教育
 * @create: 2020-07-18 11:36
 */
public class RpcProxyUtil {
    public static <T>T proxyGet(Class<T>  interfaceInfo){


        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};


        return (T) Proxy.newProxyInstance(loader, methodInfo, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                //业务协议体body
                RpcContent content = RpcContent.createContent(name, methodName, args, parameterTypes);
                //通信协议头header及requestID及body体
                PackageMsg packageMsg = PackageMsg.requestHeader(content);
                CompletableFuture<Object> cf = ClientFactory.sendMsg(packageMsg,interfaceInfo);
                return cf.get() ;
            }
        });
    }

}

