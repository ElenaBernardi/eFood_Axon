package it.uniroma3.config;

import it.uniroma3.domain.Ticket;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<Ticket> ticketAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<Ticket> repository = EventSourcingRepository.builder(Ticket.class).eventStore(eventStore).build();
        return repository;
    }
}