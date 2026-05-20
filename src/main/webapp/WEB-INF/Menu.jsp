<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="it.ristorante.beans.Piatto" %>

<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Menù - Idea Bistrot</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" />
        
    </head>
    <body class="bg-bistrot-dark">
    
    <%--Carrello a tendina usando offcanvas di bootstrap --%>
    
    <div class="offcanvas offcanvas-end bg-dark text-white" tabindex="-1" id="carrelloLaterale" aria-labelledby="offcanvasRightLabel">
  <div class="offcanvas-header border-bottom border-warning">
    <h5 id="offcanvasRightLabel" class="text-warning text-uppercase"><i class="fas fa-shopping-cart me-2"></i>Il Tuo Ordine</h5>
    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    
<div id="lista-carrello">
        <% 
        Map<String, Integer> carrello = (Map<String, Integer>) session.getAttribute("carrello");
        double totaleComplessivo = 0; // Inizializziamo il totale a zero

        if (carrello == null || carrello.isEmpty()) {
        %>
            <p class="text-center text-muted">Il carrello è vuoto</p>
        <% 
        } else {
            // Apriamo una connessione veloce per recuperare i prezzi dei piatti nel carrello
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connPrezzi = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante_db", "root", "root");

                for (Map.Entry<String, Integer> entry : carrello.entrySet()) {
                    String nomePiatto = entry.getKey();
                    int qta = entry.getValue();
                    double prezzoUnitario = 0;

                    // Cerchiamo il prezzo del piatto singolo
                    PreparedStatement psP = connPrezzi.prepareStatement("SELECT prezzo FROM piatti WHERE nome = ?");
                    psP.setString(1, nomePiatto);
                    ResultSet rsP = psP.executeQuery();
                    if(rsP.next()) {
                        prezzoUnitario = rsP.getDouble("prezzo");
                    }
                    
                    double subtotale = prezzoUnitario * qta;
                    totaleComplessivo += subtotale; // Sommiamo al totale generale
        %>
            <div class="d-flex justify-content-between align-items-center mb-3 p-2 border-bottom border-secondary">
                <div>
                    <h6 class="mb-0 text-white"><%= nomePiatto %></h6>
                    <small class="text-warning"><%= qta %> x <%= String.format("%.2f", prezzoUnitario) %>€</small>
                </div>
                <div class="text-end">
                    <span class="fw-bold text-white"><%= String.format("%.2f", subtotale) %>€</span><br>
                    <a href="RimuoviDalCarrello?nomePiatto=<%= nomePiatto %>" class="text-danger small ms-2">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                </div>
            </div>
        <% 
                }
                connPrezzi.close();
            } catch (Exception e) {
                out.println("Errore calcolo totale: " + e.getMessage());
            }
        } 
        %>
    </div>

    <% if (carrello != null && !carrello.isEmpty()) { %>
       <div class="mt-4 p-3 rounded d-flex justify-content-between align-items-center box-totale-carrello">
            <h5 class="mb-0 text-uppercase text-white" style="font-size: 0.9rem;">Totale Ordine:</h5>
            <h4 class="mb-0 text-warning fw-bold"><%= String.format("%.2f", totaleComplessivo) %>€</h4>
        </div>

        <form action="ConfermaOrdine" method="POST" class="mt-4 p-3 border border-warning rounded">
            <h6 class="text-warning mb-3">Dati per il ritiro</h6>
            <div class="mb-2">
                <label class="small">Nominativo</label>
                <input type="text" name="nome_ritiro" class="form-control form-control-sm bg-dark text-white border-secondary" required>
            </div>
            <div class="mb-2">
                <label class="small">Email (per sconto 10%)</label>
                <input type="email" name="email_cliente" class="form-control form-control-sm bg-dark text-white border-secondary" required>
            </div>
            <div class="mb-3">
                <label class="small">Ora ritiro</label>
                <input type="time" name="ora_ritiro" class="form-control form-control-sm bg-dark text-white border-secondary" required>
            </div>
            <button type="submit" class="btn btn-warning w-100 btn-sm fw-bold">CONFERMA ORDINE</button>
        </form>
    <% } %>
  </div>
</div>
     
    
        <%@ include file="/WEB-INF/navbarmenu.txt" %>
	
        <section class="page-section" id="menu-ristorante">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase text-white">Il Nostro Menù</h2>
                    <h3 class="section-subheading text-muted mb-5">Scelte gourmet e ingredienti di prima qualità</h3>
                </div>

                <ul class="nav nav-tabs justify-content-center mb-5" id="menuTab" role="tablist">
                    <li class="nav-item"><button class="nav-link active btn-menu-custom" data-bs-toggle="tab" data-bs-target="#antipasti">Antipasti</button></li>
                    <li class="nav-item"><button class="nav-link btn-menu-custom" data-bs-toggle="tab" data-bs-target="#pizze">Le Nostre Pizze</button></li>
                    <li class="nav-item"><button class="nav-link btn-menu-custom" data-bs-toggle="tab" data-bs-target="#pizze-gourmet">Pizze Gourmet</button></li>
                    <li class="nav-item"><button class="nav-link btn-menu-custom" data-bs-toggle="tab" data-bs-target="#carni">Le Nostre Carni</button></li>
                    <li class="nav-item"><button class="nav-link btn-menu-custom" data-bs-toggle="tab" data-bs-target="#beverage">Beverage</button></li>
                </ul>

                <div class="tab-content text-white">
                    <% List<Piatto> piatti = (List<Piatto>) request.getAttribute("elencoPiatti"); %>

                    <%-- Definiamo un array di categorie per non sbagliare a scrivere --%>
                    <% String[] categorie = {"antipasti", "pizze", "pizze-gourmet", "carni", "beverage"};
                       for(String cat : categorie) { 
                           String activeClass = cat.equals("antipasti") ? "show active" : ""; %>
                        
                        <div class="tab-pane fade <%= activeClass %>" id="<%= cat %>">
                            <div class="row">
                                <% if(piatti != null) { 
                                    for(Piatto p : piatti) { 
                                        if(p.getCategoria().equalsIgnoreCase(cat)) { %>
                                            <div class="col-md-6">
                                                <div class="menu-item">
                                                    <h4><%= p.getNome() %> <span class="price">€<%= p.getPrezzo() %></span></h4>
                                                    <p><%= p.getDescrizione() %></p>
                                                    
                                                    <form action="AggiungiAlCarrello" method="POST">
                                                        <input type="hidden" name="nomePiatto" value="<%= p.getNome() %>">
                                                        <input type="hidden" name="categoriaProvenienza" value="<%= cat %>">
                                                        <button type="submit" class="btn btn-outline-warning btn-add">
                                                            <i class="fas fa-plus me-1"></i> Aggiungi
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                <%      } 
                                    } 
                                } %>
                            </div>
                        </div>
                    <% } %>

                </div>
            </div>
        </section>

        <%@ include file="/WEB-INF/footer.txt" %>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
        <script>
        document.addEventListener("DOMContentLoaded", function() {
            var hash = window.location.hash;
            if (hash) {
                // Rimuove il simbolo # per trovare l'id
                var cleanHash = hash.replace('#', '');
                // Trova il bottone della tab che punta a quell'id
                var targetTab = document.querySelector('button[data-bs-target="#' + cleanHash + '"]');
                if (targetTab) {
                    var tab = new bootstrap.Tab(targetTab);
                    tab.show();
                }
            }
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('apriCarrello')) {
                var myOffcanvas = document.getElementById('carrelloLaterale');
                var bsOffcanvas = new bootstrap.Offcanvas(myOffcanvas);
                bsOffcanvas.show();
            }
        });
    </script>        
    </body>
</html>