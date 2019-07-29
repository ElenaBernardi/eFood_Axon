package it.uniroma3.domain;

import it.uniroma3.commands.CheckConsumerIdCommand;
import it.uniroma3.commands.ConsumerApprovedCommand;
import it.uniroma3.commands.ConsumerDisapprovedCommand;
import it.uniroma3.commands.NewConsumerCommand;
import it.uniroma3.events.ConsumerApprovedEvent;
import it.uniroma3.events.ConsumerDisapprovedEvent;
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
    @CommandHandler
    public void on(CheckConsumerIdCommand cmd){
        System.out.println("############ consumer approved ############");
        apply(new ConsumerApprovedEvent(cmd.getOrderId(),cmd.getTicketId()));
    }
    @CommandHandler
    public void on(ConsumerApprovedCommand cmd){
        apply(new ConsumerApprovedEvent(cmd.getOrderId(),cmd.getTicketId()));
    }
    @CommandHandler
    public void on(ConsumerDisapprovedCommand cmd){
        apply(new ConsumerDisapprovedEvent(cmd.getOrderId(),cmd.getTicketId()));
    }
    @EventSourcingHandler
    public void on(NewConsumerEvent evt){
        this.id = evt.getId();
        this.nome = evt.getNome();
        this.cognome = evt.getCognome();

    }
}
