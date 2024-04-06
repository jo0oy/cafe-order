package com.jo0oy.cafeorder.user.business;

import com.jo0oy.cafeorder.global.annotation.Business;
import com.jo0oy.cafeorder.user.converter.UserConverter;
import com.jo0oy.cafeorder.user.dto.UserRegisterRequest;
import com.jo0oy.cafeorder.user.dto.UserResponse;
import com.jo0oy.cafeorder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User 비즈니스 로직을 처리하는 로직
 */
@Slf4j
@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserResponse register(UserRegisterRequest request) {

        var userEntity = userConverter.toEntity(request);
        var registeredUser = userService.register(userEntity);

        return userConverter.toResponse(registeredUser);
    }

    public UserResponse getUser(Long userId) {

        var userEntity = userService.getUserWithThrow(userId);

        return userConverter.toResponse(userEntity);
    }
}
