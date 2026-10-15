package it.ristorante.servlet.privato.personale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import it.ristorante.dao.AdminDAO;

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

    // Riceve i dati dal form e li inserisce nella tabella admin (tramite il DAO)
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
            try {
                new AdminDAO().inserisci(utente.trim(), pass.trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Torna alla dashboard
        response.sendRedirect(request.getContextPath() + "/privato/AreaPrivata");
    }
}
