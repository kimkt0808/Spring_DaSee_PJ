package com.pj.dasee.domain.item.api;

import com.pj.dasee.domain.item.application.ItemService;
import com.pj.dasee.domain.item.domain.Item;
import com.pj.dasee.domain.item.dto.ItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ItemApi {

    private final ItemService itemService;

    @PostMapping("/api/items/create")
    public String createItem(ItemCreateRequest itemCreateRequest) {
        Item saved = itemService.createItem(itemCreateRequest);

        return "redirect:/api/items/" + saved.getId();
    }

    @GetMapping("/api/items/{id}")
    public String readItem(@PathVariable Long id, Model model) {
        Item items = itemService.readItem(id);
        model.addAttribute("items", items);

        return "Item/readItem";
    }

    @GetMapping("/api/items/{id}/editItemForm")
    public String editItemForm(@PathVariable Long id, Model model) {
        Item items = itemService.editItemForm(id);
        model.addAttribute("items", items);

        return "Item/editItemForm";
    }

    @PostMapping("/api/items/edit")
    public String editItem(ItemCreateRequest itemCreateRequest){
        Item editEntity = itemCreateRequest.toEntity();
        itemService.editItem(editEntity);

        return "redirect:/api/items/" + editEntity.getId();
    }

    @GetMapping("/api/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);

        return "redirect:/";
    }
}
