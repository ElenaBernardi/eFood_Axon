package it.uniroma3.queries;

public class FindAllOrdersByConsumerId {
    String consumerId;

    public FindAllOrdersByConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerId() {
        return consumerId;
    }
}
