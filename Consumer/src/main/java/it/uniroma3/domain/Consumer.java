package it.uniroma3.domain;

import it.uniroma3.commands.NewConsumerCommand;
import it.uniroma3.events.NewConsumerEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Consumer {
    @AggregateIdentifier
    private String id;
    private String nome;
    private String cognome;

    //constructor
    public Consumer(){}

    @CommandHandler
    public Consumer(NewConsumerCommand cmd){
        apply(new NewConsumerEvent(cmd.getId(), cmd.getNome(), cmd.getCognome()));
    }
    @EventSourcingHandler
    public void on(NewConsumerEvent evt){
        this.id = evt.getId();
        this.nome = evt.getNome();
        this.cognome = evt.getCognome();

    }
}
