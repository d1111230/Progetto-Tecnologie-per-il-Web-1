<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Admin - Aggiungi Personale</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <style>
        body { background-color: #212529; padding-top: 130px; color: white; }
        .form-admin { background-color: #2c3034; border: 1px solid #ffc800; max-width: 500px; margin: 0 auto; }
    </style>
</head>
<body class="admin-page">

    <%@ include file="/WEB-INF/privato/navbar-admin.txt" %>

    <div class="container">
    	<div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-users text-warning me-2"></i> Gestione del personale</h2>
            <a href="${pageContext.request.contextPath}/privato/AreaPrivata" class="btn btn-outline-warning btn-sm">
                <i class="fas fa-arrow-left"></i> Torna al Pannello
            </a>
        </div>
    
        <div class="card form-admin p-4 shadow">
            <h2 class="text-warning text-center mb-4"><i class="fas fa-user-plus"></i> Nuovo Account Staff</h2>
            
            <form action="${pageContext.request.contextPath}/privato/AggiungiPersonale" method="POST">
                
                <div class="mb-3">
                    <label for="username" class="form-label text-warning fw-bold">Email</label>
                    <input type="email" class="form-control bg-dark text-white border-secondary" id="username" name="username" required placeholder="Es: admin@ristorante.com">
                </div>

                <div class="mb-4">
                    <label for="password" class="form-label text-warning fw-bold">Password</label>
                    <input type="password" class="form-control bg-dark text-white border-secondary" id="password" name="password" required placeholder="Inserisci una password sicura">
                </div>

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/privato/AreaPrivata" class="btn btn-outline-secondary text-white">Annulla</a>
                    <button type="submit" class="btn btn-warning fw-bold text-dark">Crea Account</button>
                </div>
                
            </form>
        </div>
    </div>

    <%@ include file="/WEB-INF/footer.txt" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>