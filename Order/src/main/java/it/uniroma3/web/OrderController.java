package it.uniroma3.web;

import io.swagger.annotations.Api;
import it.uniroma3.domain.IOrderService;
import it.uniroma3.domain.NewOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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
}
