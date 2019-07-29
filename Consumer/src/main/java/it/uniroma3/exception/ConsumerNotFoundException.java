package it.uniroma3.exception;

public class ConsumerNotFoundException extends RuntimeException{
        public ConsumerNotFoundException(String id) {
            super("Consumer con id=" + id + " non trovato!");
        }
}
