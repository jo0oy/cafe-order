package com.jo0oy.cafeorder.menu.option;

import com.jo0oy.cafeorder.menu.optiongroup.MenuOptionGroup;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuantityMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String optionName;

    private Integer optionPrice;

    private Integer defaultAmount;

    private Integer minAmount;

    private Integer maxAmount;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;

    @Builder
    private QuantityMenuOption(
        Long id,
        String optionName,
        Integer optionPrice,
        Integer defaultAmount,
        Integer minAmount,
        Integer maxAmount,
        MenuOptionGroup menuOptionGroup
    ) {
        this.id = id;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.defaultAmount = defaultAmount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.menuOptionGroup = menuOptionGroup;
    }
}
