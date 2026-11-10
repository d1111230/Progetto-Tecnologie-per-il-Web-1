package it.ristorante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ristorante.beans.Admin;
import it.ristorante.utils.DBUtil;

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

    public List<Admin> getListaAdmin() throws SQLException {
        List<Admin> lista = new ArrayList<>();
        String sql = "SELECT username FROM admin";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Admin a = new Admin();
                a.setUsername(rs.getString("username"));
                lista.add(a);
            }
        }
        return lista;
    }
    
    
    public void elimina(String username) throws SQLException {
        String sql = "DELETE FROM admin WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
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
