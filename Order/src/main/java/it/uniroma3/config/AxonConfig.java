package it.uniroma3.config;

import it.uniroma3.domain.Order;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<Order> ticketAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<Order> repository = EventSourcingRepository.builder(Order.class).eventStore(eventStore).build();
        return repository;
    }
}