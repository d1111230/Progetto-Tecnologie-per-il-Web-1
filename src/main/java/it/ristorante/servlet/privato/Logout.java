package it.ristorante.servlet.privato;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/privato/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperiamo la sessione corrente se esiste, senza crearne una nuova
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Invalida la sessione distruggendo tutti i dati memorizzati (incluso l'attributo "admin")
            session.invalidate();
        }
        
        // Reindirizza l'utente alla pagina pubblica di richiesta login o alla home
        response.sendRedirect(request.getContextPath() + "/richiestaLogin");
    }

}