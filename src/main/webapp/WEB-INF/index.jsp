<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="Idea Bistrot - Il gusto della tradizione" />
        <meta name="author" content="" />
        <title>Idea Bistrot</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    </head>
    
    <%
        // 1. Leggiamo l'attributo impostato dalla Servlet nel forward interno
        String ordineStatus = (String) request.getAttribute("ordine");
        boolean mostraModal = (ordineStatus != null && "successo".equalsIgnoreCase(ordineStatus.trim()));
    %>
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        // 2. Se l'attributo Java dice che l'ordine ha avuto successo, forziamo l'apertura
        <% if (mostraModal) { %>
            var modalElement = document.getElementById('modalSuccesso');
            if (modalElement) {
                var mioModal = new bootstrap.Modal(modalElement);
                mioModal.show();
            }
        <% } %>
    });
    </script>
    
    <body id="page-top" class="bg-bistrot-dark">
        
        <%@ include file="/WEB-INF/navbarindex.txt" %>

	<header class="masthead">
		<div class="container">
			<div
				class="masthead-subheading text-gold animate__animated animate__fadeInDown">Benvenuti da</div>
			<div
				class="masthead-heading text-uppercase animate__animated animate__fadeInUp">Idea Bistrot</div>
			<a
				class="btn btn-success btn-xl text-uppercase animate__animated animate__zoomIn"
				href="mostraMenu">Scopri il Menù</a>
			</div>
	</header>

	<section class="page-section" id="services">
            <div class="container">
                <div class="text-center mb-5">
                    <h2 class="section-heading text-uppercase text-gold">I Nostri Servizi</h2>
                    <h3 class="section-subheading text-muted">Qualità e passione in ogni dettaglio.</h3>
                </div>
                <div class="row text-center">
                    <div class="col-md-4 mb-4">
                        <span class="fa-stack mb-3 bistrot-stack">
                            <i class="fas fa-circle fa-stack-2x text-dark border border-warning rounded-circle bistrot-cerchio"></i>
                            <i class="fas fa-utensils fa-stack-1x text-warning bistrot-icona"></i>
                        </span>
                        <h4 class="my-3 text-white">Ristorante</h4>
                        <p class="text-muted px-3">Ingredienti freschi e selezionati per un'esperienza gastronomica indimenticabile.</p>
                    </div>
                    <div class="col-md-4 mb-4">
                        <span class="fa-stack mb-3 bistrot-stack">
                            <i class="fas fa-circle fa-stack-2x text-dark border border-warning rounded-circle bistrot-cerchio"></i>
                            <i class="fas fa-pizza-slice fa-stack-1x text-warning bistrot-icona"></i>
                        </span>
                        <h4 class="my-3 text-white">Pizzeria</h4>
                        <p class="text-muted px-3">Pizza a lunga lievitazione cotta in forno a legna come vuole la tradizione.</p>
                    </div>
                    <div class="col-md-4 mb-4">
                        <span class="fa-stack mb-3 bistrot-stack">
                            <i class="fas fa-circle fa-stack-2x text-dark border border-warning rounded-circle bistrot-cerchio"></i>
                            <i class="fas fa-truck fa-stack-1x text-warning bistrot-icona"></i>
                        </span>
                        <h4 class="my-3 text-white">Asporto</h4>
                        <p class="text-muted px-3">Goditi i nostri piatti comodamente a casa tua con il servizio take-away.</p>
                    </div>
                </div>
            </div>
        </section>
        
        <section>
        		<div class="container my-1">
    <div class="p-5 text-center box-fame-scuro rounded">
        <i class="fas fa-hamburger text-warning fa-4x mb-3 animate__animated animate__bounce animate__infinite animate__slower"></i>
        <h2 class="fw-bold text-uppercase">Hai Fame?</h2>
        <p class="col-lg-6 mx-auto text-muted">
            Sfoglia il nostro menu digitale, componi il tuo carrello in pochi click e vieni a ritirare i tuoi piatti preferiti quando vuoi tu.
        </p>
        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center mt-3">
            <a href="mostraMenu" class="btn btn-warning btn-lg px-4 gap-3 fw-bold">ORDINA ORA L'ASPORTO</a>
        </div>
    </div>
</div>
        </section>


	<section class="page-section" id="about">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Il Nostro Staff</h2>
            <h3 class="section-subheading text-muted">Le persone che rendono speciale ogni vostra visita.</h3>
        </div>
        <ul class="timeline">
            
            <li>
                <div class="timeline-image">
                    <img class="rounded-circle img-fluid" src="assets/img/about/proprietario.jpeg" alt="Proprietario" />
                </div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>Il Proprietario</h4>
                        <h4 class="subheading">Antonio Rossi</h4>
                    </div>
                    <div class="timeline-body">
                        <p class="text-muted">L'anima di Idea Bistrot. Antonio ha creato questo spazio per condividere la sua visione di cucina autentica e ospitalità d'eccellenza, curando ogni dettaglio del brand.</p>
                    </div>
                </div>
            </li>

            <li class="timeline-inverted">
                <div class="timeline-image">
                    <img class="rounded-circle img-fluid" src="assets/img/about/chef.jpeg" alt="Executive Chef" />
                </div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>Executive Chef</h4>
                        <h4 class="subheading">Marco Rossi</h4>
                    </div>
                    <div class="timeline-body">
                        <p class="text-muted">Il cuore pulsante della cucina. Marco guida la nostra brigata con passione, selezionando solo le migliori materie prime per creare piatti che uniscono tradizione e innovazione.</p>
                    </div>
                </div>
            </li>

            <li>
                <div class="timeline-image">
                    <img class="rounded-circle img-fluid" src="assets/img/about/sommelier.jpeg" alt="Sommelier" />
                </div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>Sommelier</h4>
                        <h4 class="subheading">Luca Verdi</h4>
                    </div>
                    <div class="timeline-body">
                        <p class="text-muted">Esperto conoscitore dei vitigni locali e internazionali, Luca è il custode della nostra cantina e saprà consigliarvi l'abbinamento perfetto per ogni vostra scelta dal menù.</p>
                    </div>
                </div>
            </li>

            <li class="timeline-inverted">
                <div class="timeline-image">
                    <img class="rounded-circle img-fluid" src="assets/img/about/maitre.jpeg" alt="Responsabile Sala" />
                </div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>Responsabile di Sala</h4>
                        <h4 class="subheading">Giulia Bianchi</h4>
                    </div>
                    <div class="timeline-body">
                        <p class="text-muted">L'accoglienza è la sua missione. Giulia coordina il servizio con eleganza e attenzione, assicurandosi che ogni ospite viva un'esperienza impeccabile dal benvenuto al saluto finale.</p>
                    </div>
                </div>
            </li>

            <li class="timeline-inverted">
                <div class="timeline-image">
                    <h4>
                        Vieni
                        <br />
                        a trovarci
                        <br />
                        a Tavola!
                    </h4>
                </div>
            </li>
        </ul>
    </div>
</section>
        

        <section class="page-section sezione-contatti-sfondo" id="contact">
    <div class="container my-5 p-4 bg-dark text-white rounded border border-warning">
    <div class="row g-4 align-items-center">
        <div class="col-md-6">
            <h3 class="text-warning text-uppercase fw-bold mb-3">Idea Bistrot</h3>
            <p class="mb-2"><i class="fas fa-map-marker-alt text-warning me-2"></i> Via Roma, 123 - Milano</p>
            <p class="mb-4"><i class="fas fa-phone text-warning me-2"></i> +39 02 1234567</p>
            
            <h5 class="text-warning text-uppercase fw-bold mb-2">Orari d'Asporto</h5>
            <ul class="list-unstyled text-muted small">
                <li><strong class="text-white">Mar - Ven:</strong> 12:00 - 15:00 / 19:00 - 23:00</li>
                <li><strong class="text-white">Sab - Dom:</strong> 19:00 - 23:30</li>
                <li><strong class="text-white">Lunedì:</strong> Chiuso per riposo settimanale</li>
            </ul>
        </div>
        <div class="col-md-6">
        	<div class="rounded d-flex align-items-center justify-content-center text-center p-5 box-mappa-bistrot"><div>
                    <i class="fas fa-map-marked-alt text-warning fa-2x mb-2"></i>
                    <h5 class="text-white m-0">Vieni a trovarci!</h5>
                    <p class="text-white small m-0">Parcheggio riservato ai clienti per il ritiro</p>
                </div>
            </div>
        </div>
    </div>
</div>
</section>

        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start">Copyright &copy; Idea Bistrot 2026</div>
                    <div class="col-lg-4 my-3 my-lg-0">
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
                    </div>
                    <div class="col-lg-4 text-start">
   						 <a class="text-white text-decoration-none me-2" href="${pageContext.request.contextPath}/richiestaLogin">Login</a>
   						 <a class="text-white text-decoration-none me-2" href="#!">Privacy Policy</a>
    					 <a class="text-white text-decoration-none" href="#!">Terms of Use</a>
					</div>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        
        
        <div class="modal fade" id="modalSuccesso" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content bg-dark text-white border-warning">
      <div class="modal-header border-secondary">
        <h5 class="modal-title text-warning text-uppercase">Idea Bistrot</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center py-4">
        <i class="fas fa-check-circle text-warning fa-4x mb-3 animate__animated animate__pulse animate__infinite"></i>
        <h4 class="mb-2">Ordine Ricevuto!</h4>
        <p>Il tuo ordine è stato eseguito con successo.<br>Ti aspettiamo per il ritiro all'orario indicato.</p>
      </div>
      <div class="modal-footer border-secondary">
        <button type="button" class="btn btn-warning fw-bold w-100" data-bs-dismiss="modal">PERFETTO</button>
      </div>
    </div>
  </div>
</div>
        
        
    </body>
</html>