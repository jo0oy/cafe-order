package com.jo0oy.cafeorder.user.business;

import com.jo0oy.cafeorder.config.DatabaseCleanAfterEach;
import com.jo0oy.cafeorder.user.dto.UserRegisterRequest;
import com.jo0oy.cafeorder.user.dto.UserResponse;
import com.jo0oy.cafeorder.user.entity.User;
import com.jo0oy.cafeorder.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@DatabaseCleanAfterEach
@SpringBootTest
class UserBusinessTest {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        var testUser = userRepository.save(
            User.builder()
                .username("testUser")
                .password("testUserPwd")
                .phoneNumber("010-0000-0000")
                .build()
        );
    }

    @Test
    void registerSuccessTest() {
        // given
        var username = "testUser2";
        var password = username + "Pwd";
        var phoneNumber = "010-0000-0001";

        var request = UserRegisterRequest.builder()
            .username(username)
            .password(password)
            .phoneNumber(phoneNumber)
            .build();

        // when
        var result = userBusiness.register(request);

        // then
        assertThat(result).isInstanceOf(UserResponse.class);
        assertThat(result.username()).isEqualTo(username);
    }

    @Test
    void getUserByIdSuccessTest() {
        // given
        var userId = 1L;

        // when
        var result = userBusiness.getUser(userId);

        // then
        assertThat(result).isInstanceOf(UserResponse.class);
        assertThat(result.username()).isEqualTo("testUser");
    }
}