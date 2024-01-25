package com.pj.dasee.domain.item.dto;

import com.pj.dasee.domain.item.domain.Item;
import com.pj.dasee.domain.user.domain.User;
import lombok.*;

@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Getter
public class ItemCreateRequest {

    private final Long id;
    private final String title;
    private final Integer price;
    private final String description;

    private final Long userId;

    @Builder
    public ItemCreateRequest(Long id, String title, Integer price, String description, Long userId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.userId = userId;
    }

    public Item toEntity(User user) {
        return Item.builder()
                .id(id)
                .title(title)
                .price(price)
                .description(description)
                .user(user)
                .build();
    }

    public Item toEntity() {
        return Item.builder()
                .id(id)
                .title(title)
                .price(price)
                .description(description)
                .build();
    }
}
