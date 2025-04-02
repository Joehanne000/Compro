package com.FinalNaTo.Cofi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoffeeService {

    private ArrayList<Coffee> orders;

    public CoffeeService() {
        orders = new ArrayList<>();
        // Adding some sample orders for testing purposes
        orders.add(new Coffee(1, "Alice", "Latte", "Medium", 2, 3.50, java.time.LocalDate.parse("2025-04-01"), "123 Elm St."));
        orders.add(new Coffee(2, "Bob", "Espresso", "Small", 1, 2.50, java.time.LocalDate.parse("2025-04-02"), "456 Oak St."));
        orders.add(new Coffee(3, "Charlie", "Cappuccino", "Large", 3, 4.00, java.time.LocalDate.parse("2025-04-03"), "789 Pine St."));
    }

    public ArrayList<Coffee> getOrders() {
        return orders;
    }

    public void addOrder(Coffee order) {
        orders.add(order);
    }

    public void deleteOrder(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    public Coffee getOrderById(int id) {
        for (Coffee order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public List<Coffee> searchOrders(String keyword) {
        if (keyword.trim().isEmpty()) {
            return orders;
        }

        return orders.stream()
                .filter(order ->
                        order.getCustomerName().toLowerCase().contains(keyword.toLowerCase()) ||
                                order.getCoffeeType().toLowerCase().contains(keyword.toLowerCase()) ||
                                order.getAddress().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int getLastOrderId() {
        if (orders.isEmpty()) {
            return 0;
        }
        return orders.get(orders.size() - 1).getId();
    }
}
