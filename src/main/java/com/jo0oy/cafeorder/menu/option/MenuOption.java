package com.jo0oy.cafeorder.menu.option;


import com.jo0oy.cafeorder.menu.optiongroup.MenuOptionGroup;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String optionName;

    private Integer optionPrice;

    private Integer ordering;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;

    @Builder
    private MenuOption(
        Long id,
        String optionName,
        Integer optionPrice,
        Integer ordering,
        MenuOptionGroup menuOptionGroup
    ) {
        this.id = id;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.ordering = ordering;
        this.menuOptionGroup = menuOptionGroup;
    }
}
