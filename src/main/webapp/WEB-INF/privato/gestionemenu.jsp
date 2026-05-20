<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.ristorante.beans.Piatto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Admin - Gestione Menù</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <style>
        body { background-color: #212529; padding-top: 130px; color: white; }
        .table-admin { background-color: #2c3034; border: 1px solid #ffc800; }
    </style>
</head>
<body class="admin-page">

    <%-- Sfruttiamo l'include della barra amministratore con il tasto ESCI --%>
    <%@ include file="/WEB-INF/privato/navbar-admin.txt" %>

    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-utensils text-warning me-2"></i> Gestione Piatti del Menù</h2>
            <a href="${pageContext.request.contextPath}/privato/AreaPrivata" class="btn btn-outline-warning btn-sm">
                <i class="fas fa-arrow-left"></i> Torna al Pannello
            </a>
        </div>

        <div class="card table-admin p-4 shadow">
            <table class="table table-dark table-hover align-middle">
            <a href="${pageContext.request.contextPath}/privato/AggiungiPiatto" class="btn btn-warning fw-bold mb-3">
    					<i class="fas fa-plus"></i> Aggiungi Piatto
					</a>
        <thead>
            <tr class="text-warning text-uppercase">
                <th>Nome Piatto</th>
                <th>Descrizione</th>
                <th>Prezzo</th>
                <th>Categoria</th>
                <th class="text-center">Azioni</th>
            </tr>
        </thead>
        <tbody>
           <% 
    		// 1. Recuperiamo l'attributo con lo stesso nome usato nella Servlet
    		List<Piatto> listaPiatti = (List<Piatto>) request.getAttribute("listaPiatti");
    
    		// 2. Controlliamo che la lista non sia nulla e contenga record
    		if (listaPiatti != null && !listaPiatti.isEmpty()) {
        		for (Piatto p : listaPiatti) {
		%>
		<tr>
   			<td class="fw-bold"><%= p.getNome() %></td>
    		<td><%= p.getDescrizione() %></td>
    		<td><%= String.format("%.2f", p.getPrezzo()) %> €</td>
    		<td><span class="badge bg-secondary"><%= p.getCategoria() %></span></td>
    		<td class="text-center">
        		<a href="${pageContext.request.contextPath}/privato/EliminaPiatto?nome=<%= p.getNome() %>"
           			class="btn btn-danger btn-sm"
           			onclick="return confirm('Sei sicuro di voler eliminare questo piatto dal menù?');">
            		<i class="fas fa-trash-alt"></i> Elimina
        		</a>
    		</td>
		</tr>
		<% 
        	}
    	} else {
		%>
		<tr>
    		<td colspan="5" class="text-center text-muted">Nessun piatto presente nel menù digitale.</td>
		</tr>
		<% 
    		} 
		%>
        </tbody>
        </div>
    </div>

    <%@ include file="/WEB-INF/footer.txt" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>