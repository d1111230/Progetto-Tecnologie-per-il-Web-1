package it.ristorante.servlet.privato.menu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ristorante.beans.Piatto;
import it.ristorante.dao.PiattoDAO;

@WebServlet("/privato/GestioneMenu")
public class GestioneMenu extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controllo di sicurezza della sessione
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        List<Piatto> lista = new ArrayList<>();
        try {
            lista = new PiattoDAO().getMenuCompleto();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Passiamo la lista alla JSP tramite attributo della richiesta
        request.setAttribute("listaPiatti", lista);
        request.getRequestDispatcher("/WEB-INF/privato/gestionemenu.jsp").forward(request, response);
    }
}
