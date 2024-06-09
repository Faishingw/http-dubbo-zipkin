package com.louie.order;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.louie.common.dubbo.RpcManager;
import com.louie.common.dubbo.RpcManagerImpl;
import com.louie.core.CallResult;
import com.louie.core.Service;
import com.louie.core.ServiceDispacher;
import com.louie.core.ServiceDispacherImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.DubboReference;
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
    @DubboService(interfaceClass = RpcManager.class, group = "order", version = "1.0.0")
    static class RpcManagerImpl implements RpcManager {


        public CallResult invoke(Service service) {
            // TODO Auto-generated method stub
            //System.out.println("client r:"+obj.toString());
            //return obj.toString();

            ServiceDispacher serviceDispacher = new ServiceDispacherImpl();
            return serviceDispacher.invoke(service);

        }

    }

    @DubboReference(version = "1.0.0", group = "account", id = "accountCall")
    private RpcManager rpcManager;

  /*  @Bean
    public RpcManager accountCall() {
        // 创建一个新的ReferenceConfig实例
        ReferenceConfig<RpcManager> referenceConfig = new ReferenceConfig<>();
        // 设置服务接口
        referenceConfig.setInterface(RpcManager.class);
        // 设置分组
        referenceConfig.setGroup("account");
        // 设置其他属性，如timeout, retries等
        referenceConfig.setTimeout(120000);
        referenceConfig.setRetries(0);
        // 注意：check属性在ReferenceConfig中并不直接支持，但你可以通过其他方式禁用启动时检查

        // 获取Dubbo服务的引用
        return referenceConfig.get();
    }*/


}