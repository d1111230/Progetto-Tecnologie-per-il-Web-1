package it.ristorante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.ristorante.utils.DBUtil;

/**
 * Tutte le query sulla tabella "admin" (il personale che puo' entrare nell'area privata).
 */
public class AdminDAO {

    /** true se esiste un admin con quell'username (l'email) e quella password. */
    public boolean verificaCredenziali(String username, String password) throws SQLException {
        String sql = "SELECT 1 FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /** Inserisce un nuovo membro del personale. */
    public void inserisci(String username, String password) throws SQLException {
        String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        }
    }
}
