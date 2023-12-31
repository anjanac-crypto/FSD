package com.aadhar.crudapp.Service;
import com.aadhar.crudapp.entity.Order;
import com.aadhar.crudapp.model.request.CreateOrderRequestDTO;
import com.aadhar.crudapp.model.request.UpdateOrderRequestDTO;
import com.aadhar.crudapp.Repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Order addOrder(CreateOrderRequestDTO createOrderRequestDTO){
        // map the request dto object to an Order object
        Order order = modelMapper.map(createOrderRequestDTO, Order.class);

        //save the order object into the db
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional;
    }

    public Boolean updateOrderById(int id, UpdateOrderRequestDTO updateOrderRequestDTO){
        // fetch the order corresponding to ID
        Optional<Order> extractedOrderOptional = orderRepository.findById(id) ;
        if(extractedOrderOptional.isEmpty()){
            return Boolean.FALSE;
        }

        Order extractedOrder = extractedOrderOptional.get();

        // null attributes we dont want to change, // change or update the attributes which are not null
        if(updateOrderRequestDTO.getName() != null){
            extractedOrder.setName(updateOrderRequestDTO.getName());
        }
        if(updateOrderRequestDTO.getPrice() != null){
            extractedOrder.setPrice(updateOrderRequestDTO.getPrice());
        }
        if(updateOrderRequestDTO.getQty() != null){
            extractedOrder.setQty(updateOrderRequestDTO.getQty());
        }

        //Save the updated order in the db
        orderRepository.save(extractedOrder);
        return Boolean.TRUE;
    }

    public Boolean deleteOrderById(Integer id){
        // fetch the order corresponding to ID
        Optional<Order> extractedOrderOptional = orderRepository.findById(id) ;
        if(extractedOrderOptional.isEmpty()){
            return Boolean.FALSE;
        }

        orderRepository.deleteById(id);
        return Boolean.TRUE;
    }

}