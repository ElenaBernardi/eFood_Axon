package it.uniroma3.domain;
import org.axonframework.modelling.command.EntityId;

import javax.persistence.Id;

public class OrderLineItem {
    @EntityId
    private String lineItemId;
    private String menuItemId;
    private int quantity;

    public OrderLineItem() {
    }

}