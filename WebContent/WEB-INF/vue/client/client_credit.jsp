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
											value="${accountName}"
											name="idCompte"
											readonly>
									</div>
									<div class="form-group">
										<label for="usr">N° Compte :</label> <input
											type="text"
											class="form-control"
											id="idCompte"
											value="${accountId}"
											name="idCompte"
											readonly>
									</div>
									<div class="form-group">
										<label for="usr">Montant à rembourser ($) :</label> <input
											type="number"
											class="form-control"
											id="montant"
											value="${montant}"
											name="montant"
											readonly>
									</div>
								</div>
								<div class="col-sm-8">
									<form>
										<hr>
										<h4>1. Veuillez choisir le compte emetteur pour le remboursement</h4>
										<hr>
										<div class="form-group">
											<label
												for="compteOut">Emetteur</label> <select
												class="form-control"
												id="compteOut"
												name="compteOut"
												required>
												<option>Choisir Compte</option>
												<option>Compte 1</option>
												<option>Compte 2</option>
												<option>Compte 2</option>
											</select>
										</div>
										<hr>
										<h4>2. Inscrire le montant désiré</h4>
										<div class="form-group">
											<input
												type="number"
												class="form-control"
												id="montant"
												name="montant"
												placeholder="Indiquer le montant désiré"
												required>
										</div>
										<h4>3. Veuillez valider le remboursement en inscrivant
											votre mot de passe</h4>
										<div class="form-group">
											<label for="pwd">Password:</label> <input
												type="password"
												class="form-control"
												id="pwd"
												required>
										</div>
										<button
											type="submit"
											class="btn btn-primary">Effectuer le remboursement</button>
										<input
											type="hidden"
											name="${_csrf.parameterName}"
											value="${_csrf.token}" />
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