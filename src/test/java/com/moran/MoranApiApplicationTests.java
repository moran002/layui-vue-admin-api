package com.moran;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.json.JSONUtil;
import com.moran.conf.constant.CommonConstant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MoranApiApplicationTests {
    @Resource
    private TestService testService;
    @Test
    void contextLoads() {
        List<com.moran.model.Test> list = testService.findAll();
        System.out.println(JSONUtil.toJsonStr(list));
    }

    @Test
    void rsaTest() {
        String s = CommonConstant.RSA.encryptBase64("这是一段加密文本", KeyType.PrivateKey);
        System.out.println(s);
        System.out.println(s.length());
        String s1 = CommonConstant.RSA.decryptStr(s, KeyType.PublicKey);
        System.out.println(s1);
    }

}
