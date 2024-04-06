package com.jo0oy.cafeorder.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest() {
        //given
        var user = User.builder()
            .username("user1")
            .password("user1pwd")
            .phoneNumber("010-1111-1111")
            .build();

        //when
        User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser.getId()).isEqualTo(1L);
    }

    @Test
    void findByIdTest() {
        //given
        var user = User.builder()
            .username("user1")
            .password("user1pwd")
            .phoneNumber("010-1111-1111")
            .build();

        User savedUser = userRepository.save(user);

        //when
        Optional<User> findUser = userRepository.findById(savedUser.getId());

        //then
        assertThat(findUser).isPresent();
        assertThat(findUser.get().getId()).isEqualTo(savedUser.getId());
    }
}
