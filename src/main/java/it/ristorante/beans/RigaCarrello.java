package it.ristorante.beans;

/**
 * Una riga del carrello: un piatto, la quantita' e il prezzo.
 * Viene preparata dalla servlet cosi' la JSP si limita a stamparla.
 */
public class RigaCarrello {
    private String nome;
    private int quantita;
    private double prezzoUnitario;

    public RigaCarrello() {}

    public RigaCarrello(String nome, int quantita, double prezzoUnitario) {
        this.nome = nome;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public double getPrezzoUnitario() { return prezzoUnitario; }
    public void setPrezzoUnitario(double prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }

    public double getSubtotale() { return prezzoUnitario * quantita; }
}
