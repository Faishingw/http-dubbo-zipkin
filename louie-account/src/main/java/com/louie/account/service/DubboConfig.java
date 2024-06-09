package com.louie.account.service;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.louie.common.dubbo.RpcManager;
import com.louie.common.dubbo.RpcManagerImpl;
import com.louie.core.CallResult;
import com.louie.core.Service;
import com.louie.core.ServiceDispacher;
import com.louie.core.ServiceDispacherImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubbo
@DubboComponentScan(basePackages = "com.louie") // 扫描Dubbo服务所在的包
public class DubboConfig {

    // 这里不需要显式创建RpcManager的Bean，因为@DubboService注解会自动注册服务
    // 你的RpcManagerImpl类上应该有一个@DubboService注解
    @DubboService(interfaceClass = RpcManager.class, group = "account", version = "1.0.0")
    static class RpcManagerImpl implements RpcManager {


        public CallResult invoke(Service service) {
            // TODO Auto-generated method stub
            //System.out.println("client r:"+obj.toString());
            //return obj.toString();

            ServiceDispacher serviceDispacher = new ServiceDispacherImpl();
            return serviceDispacher.invoke(service);

        }

    }

}