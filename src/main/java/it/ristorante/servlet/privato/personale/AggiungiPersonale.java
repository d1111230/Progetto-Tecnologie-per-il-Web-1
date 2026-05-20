package it.ristorante.servlet.privato.personale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/privato/AggiungiPersonale")
public class AggiungiPersonale extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    // Mostra la pagina con il form di registrazione
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/privato/aggiungipersonale.jsp").forward(request, response);
    }

    // Riceve i dati dal form e li inserisce nella tabella admin
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // Recuperiamo i parametri dal form
        String utente = request.getParameter("username");
        String pass = request.getParameter("password");

        if (utente != null && !utente.trim().isEmpty() && pass != null && !pass.trim().isEmpty()) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
                
                // NOTA: Controlla se nella tua tabella i campi si chiamano username e password!
                String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, utente.trim());
                ps.setString(2, pass.trim()); // In un'app reale qui si userebbe un hash (es. BCrypt), ma per scopi scolastici va benissimo in chiaro
                
                ps.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Pulizia e chiusura risorse obbligatoria per evitare memory leak!
                try { if (ps != null) ps.close(); } catch (Exception e) {}
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }
        }

        // Una volta inserito il dipendente, lo reindirizziamo alla dashboard dell'Area Privata
        response.sendRedirect(request.getContextPath() + "/privato/AreaPrivata");
    }
}