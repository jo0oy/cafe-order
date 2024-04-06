package com.jo0oy.cafeorder.user.converter;

import com.jo0oy.cafeorder.global.annotation.Converter;
import com.jo0oy.cafeorder.global.error.ErrorCode;
import com.jo0oy.cafeorder.global.exception.ApiException;
import com.jo0oy.cafeorder.user.dto.UserRegisterRequest;
import com.jo0oy.cafeorder.user.dto.UserResponse;
import com.jo0oy.cafeorder.user.entity.User;

import java.util.Optional;

@Converter
public class UserConverter {

    public User toEntity(UserRegisterRequest request){

        return Optional.ofNullable(request)
            .map(it ->{
                // to entity
                return User.builder()
                    .username(request.username())
                    .password(request.password())
                    .phoneNumber(request.phoneNumber())
                    .build();
            })
            .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(User entity) {

        return Optional.ofNullable(entity)
            .map(it ->{
                // to response
                return UserResponse.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .phoneNumber(entity.getPhoneNumber())
                    .build();
            })
            .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
