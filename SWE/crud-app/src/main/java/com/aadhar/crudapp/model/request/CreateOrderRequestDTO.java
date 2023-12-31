package com.aadhar.crudapp.model.request;

public class CreateOrderRequestDTO {
    private String name;
    private int qty;
    private double price;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreateOrderRequestDTO{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CreateOrderRequestDTO(String name, int qty, double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
}
