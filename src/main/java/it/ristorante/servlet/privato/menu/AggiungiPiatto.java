package it.ristorante.servlet.privato.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/privato/AggiungiPiatto")
public class AggiungiPiatto extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    // 1. Il metodo doGet si limita ad aprire la pagina del form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/privato/aggiungipiatto.jsp").forward(request, response);
    }

    // 2. Il metodo doPost riceve i dati compilati dal form e li salva nel DB
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // Recupero dei parametri inviati dal form della JSP
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String prezzoStr = request.getParameter("prezzo");
        String categoria = request.getParameter("categoria");

        if (nome != null && !nome.trim().isEmpty() && prezzoStr != null) {
            try {
                double prezzo = Double.parseDouble(prezzoStr);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
                
                // Query SQL per inserire i 4 campi reali della tua tabella piatti
                String sql = "INSERT INTO piatti (nome, descrizione, prezzo, categoria) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, descrizione);
                ps.setDouble(3, prezzo);
                ps.setString(4, categoria);
                
                ps.executeUpdate();
                
                ps.close();
                conn.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Una volta salvato, reindirizza l'admin alla tabella del menu aggiornata
        response.sendRedirect(request.getContextPath() + "/privato/GestioneMenu");
    }
}