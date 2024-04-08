package com.jo0oy.cafeorder.menu.optiongroup;


import com.jo0oy.cafeorder.menu.entity.Menu;
import com.jo0oy.cafeorder.menu.option.MenuOption;
import com.jo0oy.cafeorder.menu.option.QuantityMenuOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MenuOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String optionGroupName;

    private Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuOptionGroup", cascade = CascadeType.ALL)
    private List<MenuOption> menuOptions = new ArrayList<>();

    @OneToMany(mappedBy = "menuOptionGroup", cascade = CascadeType.ALL)
    private List<QuantityMenuOption> quantityMenuOptions = new ArrayList<>();

    @Builder
    private MenuOptionGroup(
        Long id,
        String optionGroupName,
        Integer ordering,
        Menu menu,
        List<MenuOption> menuOptions,
        List<QuantityMenuOption> quantityMenuOptions
    ) {
        this.id = id;
        this.optionGroupName = optionGroupName;
        this.ordering = ordering;
        this.menu = menu;
        menuOptions.forEach(this::addMenuOption);
        quantityMenuOptions.forEach(this::addQuantityMenuOption);
    }

    public void addMenuOption(MenuOption menuOption) {
        this.menuOptions.add(menuOption);
        menuOption.setMenuOptionGroup(this);
    }

    public void addQuantityMenuOption(QuantityMenuOption quantityMenuOption) {
        this.quantityMenuOptions.add(quantityMenuOption);
        quantityMenuOption.setMenuOptionGroup(this);
    }
}
