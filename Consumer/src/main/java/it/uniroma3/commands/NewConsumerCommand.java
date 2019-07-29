package it.uniroma3.commands;

public class NewConsumerCommand extends BaseCommand1<String> {
    private String nome;
    private String cognome;

    public NewConsumerCommand(String id, String nome, String cognome) {
        super(id);
        this.nome = nome;
        this.cognome = cognome;
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
}
