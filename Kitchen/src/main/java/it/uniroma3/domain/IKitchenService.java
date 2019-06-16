package it.uniroma3.domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IKitchenService {
    public CompletableFuture<String> newTicket(NewTicketDTO newTicketDTO);
    public List<TicketSummary> findAll() ;

}
