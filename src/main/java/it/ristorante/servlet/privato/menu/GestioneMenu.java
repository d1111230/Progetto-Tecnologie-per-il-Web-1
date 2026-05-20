package it.ristorante.servlet.privato.menu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import it.ristorante.beans.Piatto; // Assicurati che il nome del tuo bean sia corretto (Patto o Piatto)

@WebServlet("/privato/GestioneMenu")
public class GestioneMenu extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controllo di sicurezza della sessione
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        List<Piatto> lista = new ArrayList<>();

        // Connessione al DB per estrarre tutti i piatti presenti nel menù
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
            
            String sql = "SELECT * FROM piatti";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Piatto p = new Piatto();
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Passiamo la lista alla JSP tramite attributo della richiesta
        request.setAttribute("listaPiatti", lista);
        request.getRequestDispatcher("/WEB-INF/privato/gestionemenu.jsp").forward(request, response);
    }


}