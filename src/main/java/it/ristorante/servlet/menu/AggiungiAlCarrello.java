package it.ristorante.servlet.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet che gestisce l'aggiunta dei prodotti nel carrello in sessione.
 * Non scrive nel database, mantiene i dati finché il browser è aperto.
 */
@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrello extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Recuperiamo i dati dal form
        String nomePiatto = request.getParameter("nomePiatto");
        String categoria = request.getParameter("categoriaProvenienza");

        // 2. Accediamo alla sessione e recuperiamo/creiamo il carrello
        HttpSession session = request.getSession();
        Map<String, Integer> carrello = (Map<String, Integer>) session.getAttribute("carrello");

        if (carrello == null) {
            carrello = new HashMap<>();
        }

        // 3. Logica di aggiunta effettiva
        if (nomePiatto != null && !nomePiatto.trim().isEmpty()) {
            int quantitaAttuale = carrello.getOrDefault(nomePiatto, 0);
            carrello.put(nomePiatto, quantitaAttuale + 1);
        }

        // 4. Salviamo il carrello aggiornato nella sessione
        session.setAttribute("carrello", carrello);

        // 5. 
        if (categoria != null && !categoria.isEmpty()) {
            response.sendRedirect("mostraMenu#" + categoria);
        } else {
            response.sendRedirect("mostraMenu");
        }
        }
    // Gestiamo anche il GET per sicurezza, rimandando semplicemente al menu
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("mostraMenu");
    }
}