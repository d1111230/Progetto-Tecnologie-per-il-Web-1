package it.ristorante.servlet.privato.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import it.ristorante.beans.Piatto;
import it.ristorante.dao.PiattoDAO;

@WebServlet("/privato/AggiungiPiatto")
public class AggiungiPiatto extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    // 1. Il metodo doGet si limita ad aprire la pagina del form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/privato/aggiungipiatto.jsp").forward(request, response);
    }

    // 2. Il metodo doPost riceve i dati compilati dal form e li salva nel DB (tramite il DAO)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/richiestaLogin");
            return;
        }

        // Recupero dei parametri inviati dal form della JSP
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String prezzoStr = request.getParameter("prezzo");
        String categoria = request.getParameter("categoria");

        if (nome != null && !nome.trim().isEmpty() && prezzoStr != null) {
            try {
                Piatto p = new Piatto();
                p.setNome(nome);
                p.setDescrizione(descrizione);
                p.setPrezzo(Double.parseDouble(prezzoStr));
                p.setCategoria(categoria);

                new PiattoDAO().inserisci(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Una volta salvato, reindirizza l'admin alla tabella del menu aggiornata
        response.sendRedirect(request.getContextPath() + "/privato/GestioneMenu");
    }
}
