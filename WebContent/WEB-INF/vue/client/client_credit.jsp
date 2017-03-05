<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Credit</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/js/confirmClient.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>Votre Espace D</h4>
      <br>
      <ul class="nav nav-pills nav-stacked">
        <li><a href="<c:url value="/apercu"/>"><span class="glyphicon glyphicon-piggy-bank"></span> Mes comptes</a></li>
		<li><a href="<c:url value="/transfertIn"/>"><span class="glyphicon glyphicon-transfer"></span> Transfert vers un compte interne</a></li>
		<li><a href="<c:url value="/transfertOut"/>"><span class="glyphicon glyphicon-transfer"></span> Transfert vers un compte externe</a></li>
		<li class="active"><a href="<c:url value="/credit"/>"><span class="glyphicon glyphicon-credit-card"></span> Rembourser carte de credit</a></li>
		<li><a href="<c:url value="/profil" />"><span class="glyphicon glyphicon-user"></span> Mon profil</a></li>
		<li><a href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
      </ul><br>
    </div>

    <div class="col-sm-8">
						<hr>
						<h3>
							Voici le montant que vous devez rembourser <span
								class="glyphicon glyphicon-usd"></span>
						</h3>
						<hr>
						<div class="container-fluid">
							<div class="row">
								<div class="col-sm-4" style="background-color: red;">
									<div class="form-group">
										<label for="usr">Compte :</label> <input
											type="text"
											class="form-control"
											id="compteName"
											value="${compteCredit.type}"
											name="idCompte"
											readonly>
									</div>
									<div class="form-group">
										<label for="usr">N° Compte :</label> <input
											type="text"
											class="form-control"
											id="idCompte"
											value="${compteCredit.idBanque} ${compteCredit.idCompte}"
											name="idCompte"
											readonly>
									</div>
									<div class="form-group">
										<label for="usr">Montant à rembourser ($) :</label> <input
											type="number" step=0.01
											class="form-control"
											id="montant"
											value="${compteCredit.solde}"
											name="montant"
											readonly>
									</div>
								</div>
								<div class="col-sm-8">
										<form
										method="post"
										id="formRemboursementCC"
										action="<c:url value="/remboursementCC" />"
										role="form">
										<hr>
										<h4>1. Veuillez choisir le compte emetteur pour le remboursement</h4>
										<hr>
										<div class="form-group">
											<label
												for="compteOut">Choisir le compte emetteur</label> <select
												class="form-control"
												id="compteOut"
												name="compteOut"
												required>
												<option>Choisir Compte</option>
												<c:forEach items="${comptes}" var="comptes">
												<option>${comptes.type} ${comptes.idBanque} ${comptes.idCompte} ${comptes.solde} $ </option>
												</c:forEach>
											</select>
										</div>
										<hr>
										<h4>2. Inscrire le montant désiré</h4>
										<div class="form-group">
											<input
												type=number min=100 max="${compteCredit.solde}" step=0.01
												class="form-control"
												id="montantRemboursement"
												name="montantRemboursement"
												placeholder="0.00"
												required>
										</div>
										
										<input type="hidden" name="montantCredit" value="${compteCredit.solde}" />
										<input type="hidden" name="idCC" value="${compteCredit.idCompte}" />
									
										<input
											type="hidden"
											name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<c:if test="${succes == true}">
										<div class="alert alert-success">
											<p>${description}</p>
										</div>
										</c:if>
										<c:if test="${succes == false}">
										<div class="alert alert-danger">
											<p>${description}</p>
										</div>
									</c:if>
									<br>
										<button
											onclick="confirmRembourseCC()"
											class="btn btn-primary">Rembourser Carte Credit</button>
									</form>
								</div>
							</div>
						</div>
					</div>
  </div>
</div>

<footer class="container-fluid">
  <p>Banque du Demon</p>
</footer>
</body>
</html>