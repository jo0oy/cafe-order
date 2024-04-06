package com.jo0oy.cafeorder.user.dto;

import lombok.Builder;

@Builder
public record UserRegisterRequest(
    String username,
    String password,
    String phoneNumber
) {
}
