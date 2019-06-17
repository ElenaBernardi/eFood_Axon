package it.uniroma3.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
    //viene usato Java Generics in maniera che il campo id sia flessibile
    //ad ogni classe diversa che estende questa classe

    //quest'annotazione serve ad axon per identificare l'istanza dell'aggregato.
    //in altre parole questa istanza serve ad axon per determinare
    //l'istanza dell'aggregato che deve gestire il comando
    //può essere posta nel campo o nel getter method
    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(T id){
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
