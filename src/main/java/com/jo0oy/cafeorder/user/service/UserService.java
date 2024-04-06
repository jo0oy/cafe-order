package com.jo0oy.cafeorder.user.service;

import com.jo0oy.cafeorder.global.error.ErrorCode;
import com.jo0oy.cafeorder.global.error.UserErrorCode;
import com.jo0oy.cafeorder.global.exception.ApiException;
import com.jo0oy.cafeorder.user.entity.User;
import com.jo0oy.cafeorder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User 도메인 로직을 처리하는 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User register(User user) {
        return Optional.ofNullable(user)
            .map(it ->
                userRepository.save(user)
            )
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    @Transactional(readOnly = true)
    public User getUserWithThrow(
        Long userId
    ) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
