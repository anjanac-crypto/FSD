package com.aadhar.crudapp.Repository;

import com.aadhar.crudapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}