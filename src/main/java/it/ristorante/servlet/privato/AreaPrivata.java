package it.ristorante.servlet.privato;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/privato/AreaPrivata")
public class AreaPrivata extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // CONTROLLO DI SICUREZZA:
        // Se l'attributo "admin" non esiste in sessione, significa che non ha fatto il login
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin"); // Lo rispediamo fuori
            return;
        }

        // Se è loggato, gli mostriamo la plancia di comando
        request.getRequestDispatcher("/WEB-INF/privato/AreaPrivata.jsp").forward(request, response);
    }
}