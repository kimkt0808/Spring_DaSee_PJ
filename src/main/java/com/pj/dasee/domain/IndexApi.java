package com.pj.dasee.domain;

import com.pj.dasee.domain.item.application.ItemService;
import com.pj.dasee.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexApi {

    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        List<Item> itemList = itemService.listItem();
        model.addAttribute("itemList", itemList);

        return "index";
    }
}
