package com.hot.damn;

import com.hot.damn.model.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {
    private final List<Coffee> coffeeList = new ArrayList<>();

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "coffee-form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Coffee coffee) {
        coffeeList.add(coffee);
        return "redirect:/coffee/list";
    }

    @GetMapping("/list")
    public String listCoffees(Model model) {
        model.addAttribute("coffees", coffeeList);
        return "coffee-list";
    }
}
