package com.aadhar.crudapp.controller;


import com.aadhar.crudapp.Service.OrderService;
import com.aadhar.crudapp.entity.Order;
import com.aadhar.crudapp.model.request.CreateOrderRequestDTO;
import com.aadhar.crudapp.model.request.UpdateOrderRequestDTO;
import com.aadhar.crudapp.Service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //private HashMap<String, Order> mapStore = new HashMap<>();

    // API to return all the orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.getOrders();
        return ResponseEntity.ok(orderList);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDTO orderRequestDTO){
        Order createdOrder = orderService.addOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    //http://localhost:8080/users/A
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderByOrderId(@PathVariable("orderId") Integer orderId){
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);
        if(optionalOrder.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Order Exists For This Order Id");
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @GetMapping("/api")
    public ResponseEntity<Object> getOrderByRequestParam(@RequestParam("orderId") Integer orderId){
        return getOrderByOrderId(orderId);
    }




    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@RequestBody UpdateOrderRequestDTO updateOrderRequestDTO, @PathVariable("orderId") Integer orderId){
        Boolean updateStatus = orderService.updateOrderById(orderId, updateOrderRequestDTO);
        if(!updateStatus){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // delete a specific order
    @DeleteMapping
    public ResponseEntity<Object> deleteOrderById(@RequestParam("orderId") Integer orderId){
        Boolean deleteStatus = orderService.deleteOrderById(orderId);
        if(!deleteStatus){
            return ResponseEntity.ok("Order not present for Order ID "+orderId);
        }
        return ResponseEntity.ok("Order Deleted Successfully for Order ID "+ orderId);
    }
}