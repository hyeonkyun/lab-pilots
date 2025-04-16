package kr.pe.hyeonkyun.web.controller;

import kr.pe.hyeonkyun.dto.LoginRequest;
import kr.pe.hyeonkyun.dto.SignupRequest;
import kr.pe.hyeonkyun.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean result = userService.login(request);
        return result ? ResponseEntity.ok("로그인 성공") : ResponseEntity.status(401).body("로그인 실패");
    }
}
