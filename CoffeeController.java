package com.JOWHAN.damn;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CoffeeController {
    CoffeeService coffeeService;

    public CoffeeController() {
        coffeeService = new CoffeeService();
    }

    // ✅ Home route redirect
    @GetMapping("/home")
    public String homeRedirect(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

    // COFFEE ROUTES

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("coffees", coffeeService.searchCoffee(search));
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        coffeeService.deleteCoffee(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "add";
    }

    @PostMapping("/save")
    public String store(@RequestParam String name,
                        @RequestParam String type,
                        @RequestParam String size,
                        @RequestParam double price,
                        @RequestParam String roastLevel,
                        @RequestParam String origin,
                        @RequestParam boolean isDecaf,
                        @RequestParam int stock,
                        @RequestParam String flavorNotes,
                        @RequestParam String brewMethod,
                        HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        Coffee coffee = new Coffee(coffeeService.getLastId() + 1, name, type, size, price, roastLevel, origin, isDecaf, stock, flavorNotes, brewMethod);
        coffeeService.addCoffee(coffee);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        Coffee c = coffeeService.getCoffee(id);
        if (c != null) {
            model.addAttribute("coffee", c);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id,
                         @RequestParam String name,
                         @RequestParam String type,
                         @RequestParam String size,
                         @RequestParam double price,
                         @RequestParam String roastLevel,
                         @RequestParam String origin,
                         @RequestParam boolean isDecaf,
                         @RequestParam int stock,
                         @RequestParam String flavorNotes,
                         @RequestParam String brewMethod,
                         HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        Coffee coffee = coffeeService.getCoffee(id);
        if (coffee != null) {
            coffee.setName(name);
            coffee.setType(type);
            coffee.setSize(size);
            coffee.setPrice(price);
            coffee.setRoastLevel(roastLevel);
            coffee.setOrigin(origin);
            coffee.setDecaf(isDecaf);
            coffee.setStock(stock);
            coffee.setFlavorNotes(flavorNotes);
            coffee.setBrewMethod(brewMethod);
            coffeeService.updateCoffee(id, coffee);
        }
        return "redirect:/";
    }

    // ✅ Catalog Route
    @GetMapping("/catalog")
    public String catalog(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("coffees", coffeeService.getAllCoffees());
        return "catalog";
    }
}
