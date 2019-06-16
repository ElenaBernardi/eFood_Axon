package it.uniroma3.domain;

import java.util.concurrent.CompletableFuture;

public interface IKitchenService {
    public CompletableFuture<String> newTicket(NewTicketDTO newTicketDTO);
}
