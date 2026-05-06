package it.ristorante.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import it.ristorante.beans.Piatto;
import it.ristorante.beans.PiattoDAO;

@WebServlet("/mostraMenu") //chiama la servlet
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Chiedo al DAO la lista dei piatti
        PiattoDAO dao = new PiattoDAO();
        List<Piatto> listaPiatti = dao.getMenuCompleto();
        
        // 2. Metto la lista nella "busta" (request) per spedirla alla pagina JSP
        request.setAttribute("elencoPiatti", listaPiatti);
        
        // 3. Spedisco tutto alla pagina JSP
        request.getRequestDispatcher("Menu.jsp").forward(request, response);
    }
}