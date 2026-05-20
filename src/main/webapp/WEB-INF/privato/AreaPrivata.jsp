<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it" class="h-100">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Area Admin - Idea Bistrot</title>
    <%-- 
       Uso ${pageContext.request.contextPath} per evitare che il browser cerchi il CSS 
       dentro la cartella '/privato/'. In questo modo il server calcola la radice del progetto
       (/ProgettoTecnologie) e la grafica di Bootstrap si carica correttamente.
    --%>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    
    <style>
        body { background-color: #212529; padding-top: 150px; }
        .card-admin { background-color: #2c3034; border: 1px solid #ffc800; transition: 0.3s; margin-bottom: 20px;}
        .card-admin:hover { transform: translateY(-5px); box-shadow: 0 10px 20px rgba(255, 200, 0, 0.2); }
    </style>
</head>
<body class="admin-page bg-dark text-white">

    <%@ include file="/WEB-INF/privato/navbar-admin.txt" %>

    <section class="flex-grow-5">
        <div class="container">
            <div class="text-center mb-5">
                <h2 class="section-heading text-uppercase">Pannello Gestione</h2>
                <h3 class="section-subheading text-muted">Bentornato. Cosa vuoi fare oggi?</h3>
            </div>

            <div class="row g-4 justify-content-center">
                <div class="col-md-4">
                    <div class="card h-100 card-admin text-center p-4">
                        <div class="card-body">
                            <i class="fas fa-book-open fa-3x text-warning mb-3"></i>
                            <h4 class="card-title text-uppercase">Gestione Menù</h4>
                            <p class="card-text text-muted small">Aggiungi, modifica o elimina i piatti dal menù.</p>
                            <a href="GestioneMenu" class="btn btn-warning fw-bold w-100">ENTRA</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card h-100 card-admin text-center p-4">
                        <div class="card-body">
                            <i class="fas fa-clipboard-list fa-3x text-warning mb-3"></i>
                            <h4 class="card-title text-uppercase">Ordini Ricevuti</h4>
                            <p class="card-text text-muted small">Visualizza gli ordini dei clienti e l'orario di ritiro.</p>
                            <a href="VisualizzaOrdini" class="btn btn-warning fw-bold w-100">ENTRA</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="card h-100 card-admin text-center p-4">
                        <div class="card-body">
                            <i class="fas fa-user fa-3x text-warning mb-3"></i>
                            <h4 class="card-title text-uppercase">Gestione Personale</h4>
                            <p class="card-text text-muted small">Visualizza e gestisci il personale</p>
                            <a href="AggiungiPersonale" class="btn btn-warning fw-bold w-100">ENTRA</a>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </section>

    <%@ include file="/WEB-INF/footer.txt" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    
</body>
</body>
</html>