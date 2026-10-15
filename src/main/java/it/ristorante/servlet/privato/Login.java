package it.ristorante.servlet.privato;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import it.ristorante.dao.AdminDAO;

public class Login extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Recuperiamo i parametri inviati dal form HTML
        String emailInserita = request.getParameter("email");
        String passwordInserita = request.getParameter("password");
        
        boolean credenzialiValide = false;
        
        try {
            // La query sulla tabella admin sta nel DAO
            credenzialiValide = new AdminDAO().verificaCredenziali(emailInserita, passwordInserita);
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
