<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it" class="h-100">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Area Riservata - Idea Bistrot</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" />
        
    </head>
    <body class="text-white bg-bistrot-dark">
    
        <%@ include file="/WEB-INF/navbarlogin.txt" %>
	
        <section class="page-section flex-grow-1 d-flex align-items-center" id="login-admin">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase text-white">Area Admin</h2>
                    <h3 class="section-subheading text-muted mb-5">Accesso riservato alla gestione del locale</h3>
                </div>

                <div class="row justify-content-center">
                    <div class="col-12 col-sm-8 col-md-6 col-lg-4">
                        <form action="${pageContext.request.contextPath}/login" method="POST" class="p-4 border border-warning rounded bg-dark">
                            <div class="mb-4">
                                <label class="text-white small mb-2">Indirizzo Email</label>
                                <input type="email" name="email" class="form-control bg-dark text-white border-secondary py-2" required>
                            </div>
                            <div class="mb-4">
                                <label class="text-white small mb-2">Password</label>
                                <input type="password" name="password" class="form-control bg-dark text-white border-secondary py-2" required>
                            </div>
                            <button type="submit" class="btn btn-warning w-100 fw-bold py-2 text-uppercase mt-2">
                                Accedi al Sistema
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <%@ include file="/WEB-INF/footer.txt" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>