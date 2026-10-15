package it.ristorante.servlet.privato;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/privato/AreaPrivata")
public class AreaPrivata extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // Se l'attributo "admin" non esiste in sessione, non ha fatto il login
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin"); // Lo rispediamo fuori
            return;
        }

        // Loggato: mostra la dashboard
        request.getRequestDispatcher("/WEB-INF/privato/AreaPrivata.jsp").forward(request, response);
    }
}