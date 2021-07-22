package com.sheng.security01.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testanno")
public class SecurityAnnoTest {

    @GetMapping("testSecured")
    @Secured({"ROLE_AA", "ROLE_BB"})
    public String demo1() {
        return "hello Secured";
    }
}
