<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="it.ristorante.beans.Piatto" %>
<%@ page import="it.ristorante.beans.RigaCarrello" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

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
        // Il carrello (righe, prezzi e totale) e' preparato da MenuServlet: qui si stampa soltanto
        List<RigaCarrello> righeCarrello = (List<RigaCarrello>) request.getAttribute("righeCarrello");
        Double totaleAttr = (Double) request.getAttribute("totaleCarrello");
        double totaleComplessivo = (totaleAttr != null) ? totaleAttr : 0;
        boolean carrelloVuoto = (righeCarrello == null || righeCarrello.isEmpty());

        if (carrelloVuoto) {
        %>
            <p class="text-center text-muted">Il carrello è vuoto</p>
        <% 
        } else {
            for (RigaCarrello riga : righeCarrello) {
        %>
            <div class="d-flex justify-content-between align-items-center mb-3 p-2 border-bottom border-secondary">
                <div>
                    <h6 class="mb-0 text-white"><%= riga.getNome() %></h6>
                    <small class="text-warning"><%= riga.getQuantita() %> x <%= String.format("%.2f", riga.getPrezzoUnitario()) %>€</small>
                </div>
                <div class="text-end">
                    <span class="fw-bold text-white"><%= String.format("%.2f", riga.getSubtotale()) %>€</span><br>
                    <%-- POST: togliere un piatto modifica il carrello, quindi e' un form e non un link --%>
                    <form action="RimuoviDalCarrello" method="post" class="d-inline">
                        <input type="hidden" name="nomePiatto" value="<%= riga.getNome().replace("\"", "&quot;") %>">
                        <button type="submit" class="btn btn-link text-danger small p-0 ms-2" aria-label="Rimuovi dal carrello">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </form>
                </div>
            </div>
        <% 
            }
        } 
        %>
    </div>

    <% if (!carrelloVuoto) { %>
       <div class="mt-4 p-3 rounded d-flex justify-content-between align-items-center box-totale-carrello">
            <h5 class="mb-0 text-uppercase text-white totale-label">Totale Ordine:</h5>
            <h4 class="mb-0 text-warning fw-bold"><%= String.format("%.2f", totaleComplessivo) %>€</h4>
        </div>

        <%
            // Cookie "ricordami": se il cliente ha già ordinato in passato, precompiliamo
            // nome ed email leggendo i cookie salvati da ConfermaOrdine (non sono dati sensibili).
            String nomeSalvato = "";
            String emailSalvata = "";
            Cookie[] cookiesOrdine = request.getCookies();
            if (cookiesOrdine != null) {
                for (Cookie c : cookiesOrdine) {
                    if (c.getName().equals("nomeRitiro")) {
                        nomeSalvato = c.getValue();
                    } else if (c.getName().equals("emailCliente")) {
                        emailSalvata = c.getValue();
                    }
                }
            }
        %>

        <form action="ConfermaOrdine" method="POST" class="mt-4 p-3 border border-warning rounded">
            <h6 class="text-warning mb-3">Dati per il ritiro</h6>
            <div class="mb-2">
                <label class="small">Nominativo</label>
                <input type="text" name="nome_ritiro" value="<%= nomeSalvato %>" class="form-control form-control-sm bg-dark text-white border-secondary" required>
            </div>
            <div class="mb-2">
                <label class="small">Email</label>
                <input type="email" name="email_cliente" value="<%= emailSalvata %>" class="form-control form-control-sm bg-dark text-white border-secondary" required>
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