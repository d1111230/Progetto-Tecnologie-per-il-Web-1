package it.ristorante.servlet.privato.personale;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ristorante.beans.Admin;
import it.ristorante.dao.AdminDAO;

@WebServlet("/privato/GestionePersonale")
public class GestionePersonale extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Controllo di sicurezza della sessione
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        List<Admin> lista = new ArrayList<>();
        try {
            lista = new AdminDAO().getListaAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Passiamo la lista alla JSP tramite attributo della richiesta
        request.setAttribute("listaAdmin", lista);
        request.getRequestDispatcher("/WEB-INF/privato/gestionepersonale.jsp").forward(request, response);
    }
}
