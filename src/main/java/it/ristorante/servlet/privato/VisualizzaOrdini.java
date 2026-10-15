package it.ristorante.servlet.privato;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ristorante.beans.Ordine;
import it.ristorante.dao.OrdineDAO;

@WebServlet("/privato/VisualizzaOrdini")
public class VisualizzaOrdini extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        List<Ordine> listaOrdini = new ArrayList<>();
        try {
            // La JOIN tra ordini e dettagli_ordini sta nel DAO
            listaOrdini = new OrdineDAO().getOrdini();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listaOrdini", listaOrdini);
        request.getRequestDispatcher("/WEB-INF/privato/visualizzaordini.jsp").forward(request, response);
    }
}
