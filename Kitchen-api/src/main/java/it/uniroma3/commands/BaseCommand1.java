package it.uniroma3.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand1<T> {
    @TargetAggregateIdentifier
    private T id;

    public BaseCommand1(T id){
        this.id = id;
    }
    public T getId() {
        return id;
    }

}
