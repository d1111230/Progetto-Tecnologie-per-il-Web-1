package it.ristorante.beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.ristorante.utils.DBUtil; 

public class PiattoDAO {

    public List<Piatto> getMenuCompleto() {
        List<Piatto> lista = new ArrayList<>();
        String query = "SELECT * FROM piatti"; 

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Piatto p = new Piatto();
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}