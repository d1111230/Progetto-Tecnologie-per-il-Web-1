package it.ristorante.beans;

import java.sql.Timestamp;

public class Ordine {
    private String emailCliente;
    private Timestamp dataOrdine;
    private String nomeRitiro;
    private String oraRitiro;
    private double totaleNetto;
    private String stato;
    private int quantita;
    private String nomePiatto;

    // Costruttore vuoto
    public Ordine() {}

    // Getter e Setter
    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }

    public Timestamp getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(Timestamp dataOrdine) { this.dataOrdine = dataOrdine; }

    public String getNomeRitiro() { return nomeRitiro; }
    public void setNomeRitiro(String nomeRitiro) { this.nomeRitiro = nomeRitiro; }

    public String getOraRitiro() { return oraRitiro; }
    public void setOraRitiro(String oraRitiro) { this.oraRitiro = oraRitiro; }

    public double getTotaleNetto() { return totaleNetto; }
    public void setTotaleNetto(double totaleNetto) { this.totaleNetto = totaleNetto; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public String getNomePiatto() { return nomePiatto; }
    public void setNomePiatto(String nomePiatto) { this.nomePiatto = nomePiatto; }
}