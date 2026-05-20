package it.ristorante.servlet.privato;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AreaPrivata extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // CONTROLLO DI SICUREZZA:
        // Se l'attributo "admin" non esiste in sessione, significa che non ha fatto il login
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("RichiestaLogin"); // Lo rispediamo fuori
            return;
        }

        // Se è loggato, gli mostriamo la plancia di comando
        request.getRequestDispatcher("/WEB-INF/privato/AreaPrivata.jsp").forward(request, response);
    }
}