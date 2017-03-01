<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Apercu Details</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>Votre Espace D</h4>
      <br>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="<c:url value="/apercu"/>"><span class="glyphicon glyphicon-piggy-bank"></span> Mes comptes</a></li>
		<li><a href="<c:url value="/transfertIn"/>"><span class="glyphicon glyphicon-transfer"></span> Transfert vers un compte interne</a></li>
		<li><a href="<c:url value="/transfertOut"/>"><span class="glyphicon glyphicon-transfer"></span> Transfert vers un compte externe</a></li>
		<li><a href="<c:url value="/credit"/>"><span class="glyphicon glyphicon-credit-card"></span> Rembourser carte de credit</a></li>
		<li><a href="<c:url value="/profil" />"><span class="glyphicon glyphicon-user"></span> Mon profil</a></li>
		<li><a href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
      </ul><br>
    </div>

    <div class="col-sm-9">
      <h2>Détails de vos comptes</h2>
		<img width="40" height="40" src="data:image/png;base64,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" />
		<hr>
		
      <c:forEach items="${comptes}" var="comptes">
					<div class="container">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"></h4>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>N° du compte</th>
												<th>Nom du compte</th>
												<th>Solde</th>
												<th>Voir</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="col-xs-2">${comptes.idBanque}${comptes.idCompte}</td>
												<td class="col-xs-2">${comptes.type}</td>
												<td class="col-xs-2">${comptes.solde}</td>
												<td class="col-xs-2"><a
													data-toggle="collapse"
													data-parent="#accordion"
													href="#${comptes.idCompte}"> <span
														class="glyphicon glyphicon-eye-open"></span>
												</a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="${comptes.idCompte}" class="panel-collapse collapse">
									<div class="panel-body">
										<h2>${account}</h2>
										<p>Voici le détails de votre compte :</p>
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Date</th>
													<th>#Transaction</th>
													<th>Nom transaction</th>
													<th>Montant $</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${comptes.transactions}" var="transactions">
												<tr>
													<td>${transactions.idTransaction}</td>
													<td>${transactions.date}</td>
													<td>${transactions.description}</td>
													<td>${transactions.montant}</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								
							</div>
							</div>		
							</div>					
						</c:forEach>

    </div>
  </div>
</div>

<footer class="container-fluid">
  <p>Banque du Demon</p>
</footer>
</body>