package com.louie;

import com.louie.common.dubbo.RpcManager;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Configuration
@EnableDubbo
@DubboComponentScan(basePackages = "com.louie") // 扫描Dubbo服务所在的包
public class DubboConfig {
    @DubboReference(version = "1.0.0", group = "order", id = "orderCall")
    private RpcManager rpcManager;

    public static void main(String[] args) throws UnsupportedEncodingException {
        String token = "jkfldjsliewklkklls";
        String id = "89";
        String data = URLEncoder.encode("{\"token\":\"" + token + "\",\"id\":" + id + "}", "UTF-8");
        String url = "http://localhost:8083/webapi/service?service=order.customer.orderInfo&data=" + data;
        System.out.println(url);
    }

}