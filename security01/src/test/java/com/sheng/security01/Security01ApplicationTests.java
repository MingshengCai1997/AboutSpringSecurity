package com.sheng.security01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Security01ApplicationTests {

    @Test
    void contextLoads() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String test = bCryptPasswordEncoder.encode("test");
        System.out.println(test + "----这个是对test字符进行编码");
        String encode = bCryptPasswordEncoder.encode(test);
        System.out.println(encode + " ==== 这个是编码之后再编码");
    }

}
