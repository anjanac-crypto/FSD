package com.aadhar.crudapp.model.request;

public class UpdateOrderRequestDTO {
    private String name;
    private Integer qty;
    private Double price;

    @Override
    public String toString() {
        return "CreateOrderRequestDTO{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }

    public UpdateOrderRequestDTO(String name, Integer qty, Double price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
