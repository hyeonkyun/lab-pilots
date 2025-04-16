package kr.pe.hyeonkyun.service.impl;

import kr.pe.hyeonkyun.domain.User;
import kr.pe.hyeonkyun.dto.LoginRequest;
import kr.pe.hyeonkyun.dto.SignupRequest;
import kr.pe.hyeonkyun.repository.UserRepository;
import kr.pe.hyeonkyun.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request) {
        if (userRepository.findByUsername(request.username).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다");
        }
        User user = new User();
        user.setUsername(request.username);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole("USER");
        userRepository.save(user);
    }

    public boolean login(LoginRequest request) {
        return userRepository.findByUsername(request.username)
                .filter(user -> passwordEncoder.matches(request.password, user.getPassword()))
                .isPresent();
    }
}
