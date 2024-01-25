package com.pj.dasee.domain.item.dao;

import com.pj.dasee.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
