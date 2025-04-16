package kr.pe.hyeonkyun.service;

import kr.pe.hyeonkyun.dto.LoginRequest;
import kr.pe.hyeonkyun.dto.SignupRequest;
import kr.pe.hyeonkyun.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    public void signup(SignupRequest request);

    public boolean login(LoginRequest request);
}
