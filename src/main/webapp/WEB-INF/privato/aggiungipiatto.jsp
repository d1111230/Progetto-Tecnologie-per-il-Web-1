<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Admin - Aggiungi Piatto</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <style>
        body { background-color: #212529; padding-top: 130px; color: white; }
        .form-admin { background-color: #2c3034; border: 1px solid #ffc800; max-width: 600px; margin: 0 auto; }
    </style>
</head>
<body class="admin-page">

    <%@ include file="/WEB-INF/privato/navbar-admin.txt" %>

    <div class="container">
        <div class="card form-admin p-4 shadow">
            <h2 class="text-warning text-center mb-4"><i class="fas fa-plus-circle"></i> Aggiungi Nuovo Piatto</h2>
            
            <form action="${pageContext.request.contextPath}/privato/AggiungiPiatto" method="POST">
                
                <div class="mb-3">
                    <label for="nome" class="form-label text-warning fw-bold">Nome del Piatto (Chiave Primaria)</label>
                    <input type="text" class="form-control bg-dark text-white border-secondary" id="nome" name="nome" required placeholder="Es: Margherita">
                </div>

                <div class="mb-3">
                    <label for="descrizione" class="form-label text-warning fw-bold">Descrizione</label>
                    <textarea class="form-control bg-dark text-white border-secondary" id="descrizione" name="descrizione" rows="3" placeholder="Ingredienti del piatto..."></textarea>
                </div>

                <div class="mb-3">
                    <label for="prezzo" class="form-label text-warning fw-bold">Prezzo (€)</label>
                    <input type="number" step="0.01" class="form-control bg-dark text-white border-secondary" id="prezzo" name="prezzo" required placeholder="Es: 7.50">
                </div>

                <div class="mb-4">
                    <label for="categoria" class="form-label text-warning fw-bold">Categoria</label>
                    <select class="form-select bg-dark text-white border-secondary" id="categoria" name="categoria" required>
                        <option value="" disabled selected>Seleziona una categoria</option>
                        <option value="Antipasti">Antipasti</option>
                        <option value="Primi">Primi</option>
                        <option value="Secondi">Secondi</option>
                        <option value="Pizze">Pizze</option>
                        <option value="Dolci">Dolci</option>
                        <option value="Bevande">Bevande</option>
                    </select>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/privato/GestioneMenu" class="btn btn-outline-secondary text-white">Annulla</a>
                    <button type="submit" class="btn btn-warning fw-bold text-dark">Salva Piatto</button>
                </div>
                
            </form>
        </div>
    </div>

    <%@ include file="/WEB-INF/footer.txt" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>