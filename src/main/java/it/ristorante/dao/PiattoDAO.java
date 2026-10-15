package it.ristorante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ristorante.beans.Piatto;
import it.ristorante.utils.DBUtil;

/**
 * Tutte le query sulla tabella "piatti".
 * Le servlet non scrivono SQL: chiamano questi metodi.
 */
public class PiattoDAO {

    /** Restituisce tutti i piatti del menu. */
    public List<Piatto> getMenuCompleto() throws SQLException {
        List<Piatto> lista = new ArrayList<>();
        String sql = "SELECT nome, descrizione, prezzo, categoria FROM piatti";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Piatto p = new Piatto();
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
        }
        return lista;
    }

    /** Restituisce il prezzo di un piatto, oppure 0 se non esiste. */
    public double getPrezzo(String nome) throws SQLException {
        String sql = "SELECT prezzo FROM piatti WHERE nome = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("prezzo") : 0;
            }
        }
    }

    /** Inserisce un nuovo piatto nel menu. */
    public void inserisci(Piatto p) throws SQLException {
        String sql = "INSERT INTO piatti (nome, descrizione, prezzo, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescrizione());
            ps.setDouble(3, p.getPrezzo());
            ps.setString(4, p.getCategoria());
            ps.executeUpdate();
        }
    }

    /** Elimina il piatto con quel nome. */
    public void elimina(String nome) throws SQLException {
        String sql = "DELETE FROM piatti WHERE nome = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
        }
    }
}
