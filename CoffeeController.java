package com.FinalNaTo.Cofi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class CoffeeController {

    private CoffeeService coffeeService;

    public CoffeeController() {
        coffeeService = new CoffeeService();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", coffeeService.getOrders());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("orders", coffeeService.searchOrders(keyword));
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        coffeeService.deleteOrder(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String create(Model model) {
        String[] sizes = {"Small", "Medium", "Large"};
        model.addAttribute("sizes", sizes);
        return "create";
    }

    @PostMapping("/save")
    public String store(@RequestParam String customerName,
                        @RequestParam String coffeeType,
                        @RequestParam String size,
                        @RequestParam int quantity,
                        @RequestParam double price,
                        @RequestParam String address) {

        int newId = coffeeService.getLastOrderId() + 1;
        Coffee newOrder = new Coffee(newId, customerName, coffeeType, size, quantity, price, LocalDate.now(), address);
        coffeeService.addOrder(newOrder);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Coffee order = coffeeService.getOrderById(id);
        if (order != null) {
            String[] sizes = {"Small", "Medium", "Large"};
            model.addAttribute("sizes", sizes);
            model.addAttribute("order", order);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id,
                         @RequestParam String customerName,
                         @RequestParam String coffeeType,
                         @RequestParam String size,
                         @RequestParam int quantity,
                         @RequestParam double price,
                         @RequestParam String address) {

        Coffee order = coffeeService.getOrderById(id);
        if (order != null) {
            order.setCustomerName(customerName);
            order.setCoffeeType(coffeeType);
            order.setSize(size);
            order.setQuantity(quantity);
            order.setPrice(price);
            order.setAddress(address);
        }
        return "redirect:/";
    }
}
