package it.ristorante.servlet.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.ristorante.beans.Piatto;
import it.ristorante.beans.RigaCarrello;
import it.ristorante.dao.PiattoDAO;

@WebServlet("/mostraMenu") //chiama la servlet
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        PiattoDAO dao = new PiattoDAO();

        try {
            // 1. Chiedo al DAO la lista dei piatti e la metto nella request
            List<Piatto> listaPiatti = dao.getMenuCompleto();
            request.setAttribute("elencoPiatti", listaPiatti);

            // 2. Preparo le righe del carrello (con prezzo e totale) per la JSP
            List<RigaCarrello> righe = new ArrayList<>();
            double totale = 0;

            HttpSession session = request.getSession();
            Map<String, Integer> carrello = (Map<String, Integer>) session.getAttribute("carrello");
            if (carrello != null) {
                for (Map.Entry<String, Integer> voce : carrello.entrySet()) {
                    RigaCarrello riga = new RigaCarrello(voce.getKey(), voce.getValue(), dao.getPrezzo(voce.getKey()));
                    righe.add(riga);
                    totale += riga.getSubtotale();
                }
            }
            request.setAttribute("righeCarrello", righe);
            request.setAttribute("totaleCarrello", totale);

        } catch (SQLException e) {
            throw new ServletException("Errore nella lettura del menu", e);
        }
        
        // 3. Spedisco tutto alla pagina JSP
        request.getRequestDispatcher("WEB-INF/Menu.jsp").forward(request, response);
    }
}
