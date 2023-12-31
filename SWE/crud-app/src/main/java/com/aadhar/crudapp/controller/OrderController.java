package com.aadhar.crudapp.controller;


import com.aadhar.crudapp.entity.Order;
import com.aadhar.crudapp.model.request.CreateOrderRequestDTO;
import com.aadhar.crudapp.model.request.UpdateOrderRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ModelMapper modelMapper;

    private HashMap<String, Order> mapStore = new HashMap<>();

    // API to return all the orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> allOrdersList = new ArrayList<>(mapStore.values());
        return ResponseEntity.ok(allOrdersList);
    }

    //http://localhost:8080/users/A
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable("orderId") String orderId){
        Order order = mapStore.get(orderId);
        if(order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Order Found");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/api")
    public ResponseEntity<Object> getOrderByRequestParam(@RequestParam("orderId") String orderId){
        Order order = mapStore.get(orderId);
        if(order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Order Found");
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDTO orderRequestDTO){
        // map the request dto object to an Order object
        Order order = modelMapper.map(orderRequestDTO, Order.class);
        order.setId(UUID.randomUUID().toString());

        //save the order object into the map
        mapStore.put(order.getId(), order);
        return ResponseEntity.ok(order);
    }


    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@RequestBody UpdateOrderRequestDTO orderRequestDTO, @PathVariable("orderId") String orderId){
        // fetch the order corresponding to orderRequestDTO by ID
        Order extractedOrder = mapStore.get(orderId);

        // null attributes we dont want to change, // change or update the attributes which are not null
        if(orderRequestDTO.getName() != null){
            extractedOrder.setName(orderRequestDTO.getName());
        }
        if(orderRequestDTO.getPrice() != null){
            extractedOrder.setPrice(orderRequestDTO.getPrice());
        }
        if(orderRequestDTO.getQty() != null){
            extractedOrder.setQty(orderRequestDTO.getQty());
        }

        //Save the updated order in the mapStore

        mapStore.put(extractedOrder.getId(), extractedOrder);
        int a = 10;
        return ResponseEntity.ok().build();

    }

    // delete a specific order
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteOrderById(@RequestParam("orderId") String orderId){
        Order extractedOrder = mapStore.get(orderId);

        if(extractedOrder == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Order Exists for this Order Id !");
        }
        mapStore.remove(extractedOrder.getId());
        return ResponseEntity.ok(extractedOrder);
    }




}
