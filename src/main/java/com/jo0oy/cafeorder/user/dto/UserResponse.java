package com.jo0oy.cafeorder.user.dto;

import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String username,
    String phoneNumber
) {
}
