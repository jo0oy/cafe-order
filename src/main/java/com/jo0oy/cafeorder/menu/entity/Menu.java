package com.jo0oy.cafeorder.menu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 메뉴 id

    @Column(length = 30)
    private String menuName; // 메뉴명

    private Integer menuPrice; // 메뉴 가격

    @Column(length = 100)
    private String description; // 메뉴 설명

    private String category; // 카테고리

    @Builder
    private Menu(
        Long id,
        String menuName,
        Integer menuPrice,
        String description,
        String category
    ) {
        this.id = id;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.description = description;
        this.category = category;
    }
}
