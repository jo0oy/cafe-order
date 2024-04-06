package com.jo0oy.cafeorder.user.service;

import com.jo0oy.cafeorder.config.DatabaseCleanAfterEach;
import com.jo0oy.cafeorder.global.exception.ApiException;
import com.jo0oy.cafeorder.user.entity.User;
import com.jo0oy.cafeorder.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;

@DatabaseCleanAfterEach
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

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

    @DisplayName("정상적으로 회원 등록에 성공한다.")
    @Test
    void registerUserSuccessTest() {
        //given
        var username = "testUser2";
        var password = "testUser2Pwd";
        var phoneNumber = "010-0000-0001";

        var userEntity = User.builder()
            .username(username)
            .password(password)
            .phoneNumber(phoneNumber)
            .build();

        //when
        var result = userService.register(userEntity);

        //then
        assertThat(result.getId()).isGreaterThan(1L);
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getPassword()).isEqualTo(password);
    }

    @DisplayName("username이 Null일 경우, 회원 등록에 실패한다.")
    @Test
    void registerUserFailTest_UsernameNullError() {
        //given
        var password = "testUser2Pwd";
        var phoneNumber = "010-0000-0001";

        var userEntity = User.builder()
            .password(password)
            .phoneNumber(phoneNumber)
            .build();

        //when & then
        assertThatThrownBy(
            () -> userService.register(userEntity)
        ).isInstanceOf(DataIntegrityViolationException.class);
    }

    @DisplayName("회원 id로 회원 조회에 성공한다.")
    @Test
    void getUserByIdSuccessTest() {
        //given
        var userId = 1L;

        //when
        var result = userService.getUserWithThrow(userId);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testUser");
    }

    @DisplayName("존재하지 않는 회원 id로 회원 조회시 실패한다.")
    @Test
    void getUserByIdFailTest() {
        //given
        var userId = 10000L;

        //when & then
        assertThatThrownBy(() -> userService.getUserWithThrow(userId))
            .isInstanceOf(ApiException.class);
    }
}