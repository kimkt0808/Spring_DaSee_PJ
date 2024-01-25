package com.pj.dasee.domain.item.application;

import com.pj.dasee.domain.item.dao.ItemRepository;
import com.pj.dasee.domain.item.domain.Item;
import com.pj.dasee.domain.item.dto.ItemCreateRequest;
import com.pj.dasee.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item createItem(ItemCreateRequest itemCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (user == null) {
            throw new IllegalArgumentException("User must be logged in to create an item");
        }

        Item item = itemCreateRequest.toEntity(user);
        return itemRepository.save(item);
    }

    @Transactional
    public Item readItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Item editItemForm(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public void editItem(Item editEntity) {
        Item item = itemRepository.findById(editEntity.getId()).orElseThrow(() ->
                new IllegalArgumentException("Invalid item id: " + editEntity.getId()));

        if (item != null){
            item.updateItem(editEntity.getTitle(), editEntity.getPrice(), editEntity.getDescription());

            itemRepository.save(item);
        }
    }

    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid item id: " + id));

        if (item != null) {
            itemRepository.delete(item);
        }
    }

    @Transactional
    public List<Item> listItem() {
        return itemRepository.findAll();
    }

}
