package it.uniroma3.web;

import io.swagger.annotations.Api;
import it.uniroma3.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/orders")
@Api(value = "Order Commands", description = "Order Commands Related Endpoints", tags = "Order Commands")

public class OrderController {
    @Autowired
    IOrderService orderService;

    /** Crea un nuovo ordine **/
    @PostMapping("/")
    public CompletableFuture<String> newOrder(@RequestBody NewOrderDTO newOrderDTO){
        return orderService.newOrder(newOrderDTO);
    }
    @GetMapping
    public List<OrderSummary> findAllConsumers(){
            return  orderService.findAll();
    }
}
