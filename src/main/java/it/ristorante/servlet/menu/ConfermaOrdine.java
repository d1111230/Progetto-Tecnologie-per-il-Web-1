package it.ristorante.servlet.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.util.Map;

import it.ristorante.dao.OrdineDAO;

@WebServlet("/ConfermaOrdine")
public class ConfermaOrdine extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> carrello = (Map<String, Integer>) session.getAttribute("carrello");

        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("mostraMenu");
            return;
        }

        // Recupero dati dal form
        String nomeRitiro = request.getParameter("nome_ritiro");
        String emailCliente = request.getParameter("email_cliente");
        String oraRitiro = request.getParameter("ora_ritiro");

        try {
            // Il DAO salva testata e dettagli in un'unica transazione (commit o rollback)
            new OrdineDAO().salvaOrdine(emailCliente, nomeRitiro, oraRitiro, carrello);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("mostraMenu?errore=database");
            return;
        }

        session.removeAttribute("carrello");

        // Cookie per precompilare il form alla prossima visita (30 giorni)
        Cookie cookieNome = new Cookie("nomeRitiro", nomeRitiro);
        cookieNome.setMaxAge(60 * 60 * 24 * 30);
        cookieNome.setPath(request.getContextPath() + "/");
        response.addCookie(cookieNome);

        Cookie cookieEmail = new Cookie("emailCliente", emailCliente);
        cookieEmail.setMaxAge(60 * 60 * 24 * 30);
        cookieEmail.setPath(request.getContextPath() + "/");
        response.addCookie(cookieEmail);

        // La home apre il modal "Ordine Ricevuto!" quando trova questo attributo
        request.setAttribute("ordine", "successo");
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
