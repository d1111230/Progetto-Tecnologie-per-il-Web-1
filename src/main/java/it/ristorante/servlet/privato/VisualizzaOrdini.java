package it.ristorante.servlet.privato;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import it.ristorante.beans.Ordine;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/privato/VisualizzaOrdini")
public class VisualizzaOrdini extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // Una lista di oggetti standard, identica a come estrai i piatti!
        List<Ordine> listaOrdini = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
            
            String sql = "SELECT o.email_cliente, o.data_ordine, o.nome_ritiro, o.ora_ritiro, o.totale_netto, o.stato, "
                       + "d.quantita, d.nome_piatto "
                       + "FROM ordini o "
                       + "INNER JOIN dettagli_ordini d ON o.email_cliente = d.email_cliente AND o.data_ordine = d.data_ordine "
                       + "ORDER BY o.data_ordine DESC";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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
                
                listaOrdini.add(ord);
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listaOrdini", listaOrdini);
        request.getRequestDispatcher("/WEB-INF/privato/visualizzaordini.jsp").forward(request, response);
    }
}