package com.jo0oy.cafeorder.user.repository;

import com.jo0oy.cafeorder.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
