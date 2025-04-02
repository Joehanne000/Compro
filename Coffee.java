package com.FinalNaTo.Cofi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Coffee {
    private int id;
    private String customerName;
    private String coffeeType;
    private String size;
    private int quantity;
    private double price;
    private LocalDate orderDate;
    private String address;

    // Default constructor
    public Coffee() {}

    // Constructor with parameters
    public Coffee(int id, String customerName, String coffeeType, String size, int quantity, double price, LocalDate orderDate, String address) {
        this.id = id;
        this.customerName = customerName;
        this.coffeeType = coffeeType;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Helper method to calculate the total price
    public double calculateTotal() {
        return this.price * this.quantity;
    }

    // Format date in a readable way
    public String getFormattedOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
