package it.ristorante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.ristorante.beans.Ordine;
import it.ristorante.utils.DBUtil;

public class OrdineDAO {

    // Salva testata e dettagli in un'unica transazione (commit oppure rollback)
    public void salvaOrdine(String emailCliente, String nomeRitiro, String oraRitiro,
                            Map<String, Integer> carrello) throws SQLException {

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // 1. Testata dell'ordine (totale inizialmente a 0)
                String sqlO = "INSERT INTO ordini (email_cliente, nome_ritiro, ora_ritiro, totale_netto) VALUES (?, ?, ?, 0)";
                try (PreparedStatement psO = conn.prepareStatement(sqlO)) {
                    psO.setString(1, emailCliente);
                    psO.setString(2, nomeRitiro);
                    psO.setString(3, oraRitiro);
                    psO.executeUpdate();
                }

                // 2. Data/ora assegnata dal database: serve a collegare i dettagli alla testata
                Timestamp dataOrdine = null;
                String sqlData = "SELECT data_ordine FROM ordini WHERE email_cliente = ? ORDER BY data_ordine DESC LIMIT 1";
                try (PreparedStatement psData = conn.prepareStatement(sqlData)) {
                    psData.setString(1, emailCliente);
                    try (ResultSet rs = psData.executeQuery()) {
                        if (rs.next()) {
                            dataOrdine = rs.getTimestamp("data_ordine");
                        }
                    }
                }

                // 3. Una riga di dettaglio per ogni piatto e calcolo del totale
                double totale = 0;
                String sqlPrezzo = "SELECT prezzo FROM piatti WHERE nome = ?";
                String sqlD = "INSERT INTO dettagli_ordini (email_cliente, data_ordine, nome_piatto, quantita, prezzo_unitario) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement psPrezzo = conn.prepareStatement(sqlPrezzo);
                     PreparedStatement psD = conn.prepareStatement(sqlD)) {

                    for (Map.Entry<String, Integer> voce : carrello.entrySet()) {
                        double prezzo = 0;
                        psPrezzo.setString(1, voce.getKey());
                        try (ResultSet rs = psPrezzo.executeQuery()) {
                            if (rs.next()) {
                                prezzo = rs.getDouble("prezzo");
                            }
                        }

                        psD.setString(1, emailCliente);
                        psD.setTimestamp(2, dataOrdine);
                        psD.setString(3, voce.getKey());
                        psD.setInt(4, voce.getValue());
                        psD.setDouble(5, prezzo);
                        psD.executeUpdate();

                        totale += prezzo * voce.getValue();
                    }
                }

                // 4. Aggiorno la testata con il totale calcolato
                String sqlU = "UPDATE ordini SET totale_netto = ? WHERE email_cliente = ? AND data_ordine = ?";
                try (PreparedStatement psU = conn.prepareStatement(sqlU)) {
                    psU.setDouble(1, totale);
                    psU.setString(2, emailCliente);
                    psU.setTimestamp(3, dataOrdine);
                    psU.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    /** Tutti gli ordini con i loro piatti (una riga per piatto), dal piu' recente. */
    public List<Ordine> getOrdini() throws SQLException {
        List<Ordine> lista = new ArrayList<>();
        String sql = "SELECT o.email_cliente, o.data_ordine, o.nome_ritiro, o.ora_ritiro, o.totale_netto, o.stato, "
                   + "d.quantita, d.nome_piatto "
                   + "FROM ordini o "
                   + "INNER JOIN dettagli_ordini d ON o.email_cliente = d.email_cliente AND o.data_ordine = d.data_ordine "
                   + "ORDER BY o.data_ordine DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ordine ord = new Ordine();
                ord.setEmailCliente(rs.getString("email_cliente"));
                ord.setDataOrdine(rs.getTimestamp("data_ordine"));
                ord.setNomeRitiro(rs.getString("nome_ritiro"));
                ord.setOraRitiro(rs.getString("ora_ritiro"));
                ord.setTotaleNetto(rs.getDouble("totale_netto"));
                ord.setStato(rs.getString("stato"));
                ord.setQuantita(rs.getInt("quantita"));
                ord.setNomePiatto(rs.getString("nome_piatto"));
                lista.add(ord);
            }
        }
        return lista;
    }
}
