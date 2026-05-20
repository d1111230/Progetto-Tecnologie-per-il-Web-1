package it.ristorante.servlet.privato.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
@WebServlet("/privato/EliminaPiatto")
public class EliminaPiatto extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Controllo di sicurezza della sessione dell'amministratore
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // 2. Recuperiamo il parametro 'nome' inviato dal link della tabella JSP
        String nomeParam = request.getParameter("nome");

        if (nomeParam != null && !nomeParam.trim().isEmpty()) {
            try {
                // Caricamento del driver JDBC per MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Connessione al database ristorante_db
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
                
                // Query di eliminazione basata sulla chiave primaria 'nome'
                String sql = "DELETE FROM piatti WHERE nome = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nomeParam);
                
                // Esecuzione dell'aggiornamento sul DB
                ps.executeUpdate();
                
                // Chiusura delle risorse
                ps.close();
                conn.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 3. Ricarica immediatamente la pagina di gestione menù aggiornata senza il piatto rimosso
        response.sendRedirect(request.getContextPath() + "/privato/GestioneMenu");
    }


}