package it.uniroma3.config;

import it.uniroma3.domain.Consumer;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<Consumer> accountAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<Consumer> repository = EventSourcingRepository.builder(Consumer.class).eventStore(eventStore).build();
        return repository;
    }
}