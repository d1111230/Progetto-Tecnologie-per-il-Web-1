package it.ristorante.servlet.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/RimuoviDalCarrello")
public class RimuoviDalCarrello extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomePiatto = request.getParameter("nomePiatto");
        HttpSession session = request.getSession();
        Map<String, Integer> carrello = (Map<String, Integer>) session.getAttribute("carrello");

        if (carrello != null && nomePiatto != null) {
            carrello.remove(nomePiatto); // Rimuove l'elemento dalla mappa
            session.setAttribute("carrello", carrello);
        }

        // Torna al menu e riapre la tendina per far vedere che è sparito
        response.sendRedirect("mostraMenu?apriCarrello=true");
    }
}