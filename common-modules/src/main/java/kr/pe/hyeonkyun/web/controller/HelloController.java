package kr.pe.hyeonkyun.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/api/v1/say-hello")
    public String SayHello() {
        return "hello!";
    }
}

//* H2 콘솔: http://localhost:8080/h2-console
//* 회원가입: POST /auth/signup
//* 로그인: POST /auth/login

