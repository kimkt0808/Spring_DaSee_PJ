package com.pj.dasee.domain.item.domain;

import com.pj.dasee.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Item(Long id, String title, Integer price, String description, User user) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.user = user;
    }

    public void updateItem(String title, Integer price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }
}
