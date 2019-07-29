package it.uniroma3.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@NamedQuery(name="ConsumerSummary.findAllConsumers", query="SELECT c FROM ConsumerSummary c")
@NamedQuery(name = "ConsumerSummary.findAllOrdersByConsumerId", query="SELECT o.id, c.id FROM ConsumerSummary c JOIN OrderSummary o ON c.id=o.consumerId ")
//@NamedQuery(name = "ConsumerSummary.findAllOrdersByConsumerId", query="SELECT o.id FROM OrderSummary o ")
public class ConsumerSummary {
    @Id
    private String id;
    private String nome;
    private String cognome;

    public ConsumerSummary() {}

    public ConsumerSummary(String id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "ConsumerSummary{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumerSummary that = (ConsumerSummary) o;
        return id.equals(that.id) &&
                nome.equals(that.nome) &&
                cognome.equals(that.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome);
    }
}
