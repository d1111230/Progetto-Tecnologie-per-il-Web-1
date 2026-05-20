package it.ristorante.servlet.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

@WebServlet("/ConfermaOrdine")
public class ConfermaOrdine extends HttpServlet {
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

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");
            conn.setAutoCommit(false); 

            // 1. Inserimento Ordine (totale inizialmente a 0)
            String sqlO = "INSERT INTO ordini (email_cliente, nome_ritiro, ora_ritiro, totale_netto) VALUES (?, ?, ?, 0)";
            PreparedStatement psO = conn.prepareStatement(sqlO);
            psO.setString(1, emailCliente);
            psO.setString(2, nomeRitiro);
            psO.setString(3, oraRitiro);
            psO.executeUpdate();

            // 2. Recupero del Timestamp creato dal DB per collegare i dettagli
            Timestamp dataO = null;
            PreparedStatement psCheck = conn.prepareStatement("SELECT data_ordine FROM ordini WHERE email_cliente=? ORDER BY data_ordine DESC LIMIT 1");
            psCheck.setString(1, emailCliente);
            ResultSet rsC = psCheck.executeQuery();
            if(rsC.next()) dataO = rsC.getTimestamp("data_ordine");

            // 3. Inserimento Dettagli e calcolo totale lordo
            double totaleFinale = 0;
            String sqlD = "INSERT INTO dettagli_ordini (email_cliente, data_ordine, nome_piatto, quantita, prezzo_unitario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psD = conn.prepareStatement(sqlD);

            for (Map.Entry<String, Integer> entry : carrello.entrySet()) {
                double prezzo = 0;
                PreparedStatement psP = conn.prepareStatement("SELECT prezzo FROM piatti WHERE nome = ?");
                psP.setString(1, entry.getKey());
                ResultSet rsP = psP.executeQuery();
                if(rsP.next()) prezzo = rsP.getDouble("prezzo");

                psD.setString(1, emailCliente);
                psD.setTimestamp(2, dataO);
                psD.setString(3, entry.getKey());
                psD.setInt(4, entry.getValue());
                psD.setDouble(5, prezzo);
                psD.executeUpdate();
                
                totaleFinale += (prezzo * entry.getValue());
            }
            

            // 4. Aggiornamento dell'ordine con il totale calcolato 
            PreparedStatement psU = conn.prepareStatement("UPDATE ordini SET totale_netto = ? WHERE email_cliente = ? AND data_ordine = ?");
            psU.setDouble(1, totaleFinale);
            psU.setString(2, emailCliente);
            psU.setTimestamp(3, dataO);
            psU.executeUpdate();

            conn.commit(); 
            session.removeAttribute("carrello"); 
            
         // 1. Passiamo il parametro di successo come Attributo della richiesta (Request Attribute)
            request.setAttribute("ordine", "successo");

            // 2. Facciamo il forward interno verso la cartella protetta WEB-INF
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            
        } catch (Exception e) {
            if(conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
            response.sendRedirect("mostraMenu?errore=database");
        } finally {
            if(conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }
}