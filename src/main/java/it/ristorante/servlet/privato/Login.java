package it.ristorante.servlet.privato;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class Login extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Recuperiamo i parametri inviati dal form HTML
        String emailInserita = request.getParameter("email");
        String passwordInserita = request.getParameter("password");
        
        boolean credenzialiValide = false;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
            
            // Interroghiamo la tabella controllando username (dove c'è l'email) e password
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emailInserita);
            ps.setString(2, passwordInserita);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                credenzialiValide = true;
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (credenzialiValide) {
            HttpSession session = request.getSession(true);
            // Creiamo il token richiesto dalla pagina privata
            session.setAttribute("admin", "loggato");
            
            // Reindirizziamo alla servlet che governa l'area privata
            response.sendRedirect(request.getContextPath() + "/privato/AreaPrivata");
        } else {
            request.setAttribute("errore", "Credenziali non valide.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/richiestaLogin");
    }
}