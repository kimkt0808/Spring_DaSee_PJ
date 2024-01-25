package com.pj.dasee.domain.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/api/items/createItemForm")
    public String createItemForm() {
        return "Item/createItemForm";
    }
}
