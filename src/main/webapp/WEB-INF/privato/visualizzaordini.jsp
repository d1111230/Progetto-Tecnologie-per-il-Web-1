<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.ristorante.beans.Ordine" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Admin - Registro Ordini</title>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <style>
        body { background-color: #212529; padding-top: 130px; color: white; }
        .table-admin { background-color: #2c3034; border: 1px solid #ffc800; }
    </style>
</head>
<body class="admin-page">

    <%@ include file="/WEB-INF/privato/navbar-admin.txt" %>

    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="fas fa-clipboard-list text-warning me-2"></i> Registro Ordini Ricevuti</h2>
            <a href="${pageContext.request.contextPath}/privato/AreaPrivata" class="btn btn-outline-warning btn-sm">
                <i class="fas fa-arrow-left"></i> Torna al Pannello
            </a>
        </div>

        <div class="card table-admin p-4 shadow">
            <table class="table table-dark table-hover align-middle">
                <thead>
                    <tr class="text-warning text-uppercase">
                        <th>Data Invio</th>
                        <th>Cliente</th>
                        <th>Nominativo Ritiro</th>
                        <th>Orario Ritiro</th>
                        <th>Piatto Ordinato</th>
                        <th>Quantità</th>
                        <th>Totale Conto</th>
                        <th>Stato</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Ordine> ordini = (List<Ordine>) request.getAttribute("listaOrdini");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        
                        if (ordini != null && !ordini.isEmpty()) {
                            for (Ordine o : ordini) {
                    %>
                    <tr>
                        <td class="small text-muted"><%= sdf.format(o.getDataOrdine()) %></td>
                        <td><%= o.getEmailCliente() %></td>
                        <td><%= o.getNomeRitiro() %></td>
                        <td><i class="far fa-clock text-warning me-1"></i> <%= o.getOraRitiro() %></td>
                        <td class="text-info fw-bold"><%= o.getNomePiatto() %></td>
                        <td class="text-center"><span class="badge bg-secondary"><%= o.getQuantita() %>x</span></td>
                        <td class="fw-bold text-success"><%= String.format("%.2f", o.getTotaleNetto()) %> €</td>
                        <td><span class="badge bg-warning text-dark"><%= o.getStato() %></span></td>
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="8" class="text-center text-muted">Nessun record presente.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="/WEB-INF/footer.txt" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>