package it.uniroma3.web;

import io.swagger.annotations.Api;
import it.uniroma3.domain.IKitchenService;
import it.uniroma3.domain.NewTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/tickets")
@Api(value = "Kitchen Commands", description = "Kitchen Commands Related Endpoints", tags = "Kitchen Commands")
public class KitchenController {
    @Autowired
    private IKitchenService kitchenService;

    @PostMapping
    public CompletableFuture<String> newTicket(@RequestBody NewTicketDTO newTicketDTO){
        return kitchenService.newTicket(newTicketDTO);
    }
}
