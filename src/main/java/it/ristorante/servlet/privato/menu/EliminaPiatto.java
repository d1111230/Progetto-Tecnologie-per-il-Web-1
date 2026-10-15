package it.ristorante.servlet.privato.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import it.ristorante.dao.PiattoDAO;

@WebServlet("/privato/EliminaPiatto")
public class EliminaPiatto extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    // POST: l'eliminazione modifica i dati, quindi arriva da un form e non da un link (GET)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Controllo di sicurezza della sessione dell'amministratore
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // 2. Recuperiamo il parametro 'nome' inviato dal form della tabella JSP
        String nomeParam = request.getParameter("nome");

        if (nomeParam != null && !nomeParam.trim().isEmpty()) {
            try {
                new PiattoDAO().elimina(nomeParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 3. Ricarica la pagina di gestione menù aggiornata senza il piatto rimosso
        response.sendRedirect(request.getContextPath() + "/privato/GestioneMenu");
    }

    // Aprire l'indirizzo scrivendolo nel browser (GET) non cancella niente
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/privato/GestioneMenu");
    }
}
