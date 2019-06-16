package it.uniroma3.web;

import io.swagger.annotations.Api;
import it.uniroma3.domain.ConsumerSummary;
import it.uniroma3.domain.IConsumerService;
import it.uniroma3.domain.NewConsumerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/consumers")
@Api(value = "Consumer Commands", description = "Consumer Commands Related Endpoints", tags = "Consumer Commands")
public class ConsumerController {
    @Autowired
    private IConsumerService consumerService;

    @PostMapping
    public CompletableFuture<String> newConsumer(@RequestBody NewConsumerDTO newConsumerDTO){
        return consumerService.newConsumer(newConsumerDTO);
    }
    @GetMapping
    public  List<ConsumerSummary> findAllConsumers(){
        System.out.println("############################# fatta una query");

        return consumerService.findAll();
    }
}
