package com.louie.account.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import com.louie.core.CallParams;
import com.louie.core.CallResult;

//@DubboService(version = "1.0.0", group = "account") // 设置版本和分组
@Service("account.user") // Spring的@Service注解仍然是可选的，取决于你是否在Spring中管理该Bean
public class UserService {

    public CallResult info(CallParams params) {
        CallResult cr = new CallResult();
        Map<String, Object> data = new HashMap<>();
        data.put("userId", "ujklskkkkliiekkikhhjkj");
        cr.setCode(0);
        cr.setData(data);

        return cr;
    }
}