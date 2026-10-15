package it.ristorante.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/richiestaLogin")
public class RichiestaLogin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Questa servlet serve solo a visualizzare il file JSP protetto
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}