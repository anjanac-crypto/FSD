package com.aadhar.crudapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Sunday")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer qty;
    private Double price;

}