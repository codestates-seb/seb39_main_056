package com.noterror.app.api.domain.user.service;

import com.noterror.app.api.domain.entity.User;
import com.noterror.app.api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->  new NullPointerException("회원을 조회할 수 없습니다."));
    }
}
