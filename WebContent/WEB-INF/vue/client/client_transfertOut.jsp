<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<li class="active"><a href="<c:url value="/transfertOut"/>"><span class="glyphicon glyphicon-transfer"></span> Transfert vers un compte externe</a></li>
		<li><a href="<c:url value="/credit"/>"><span class="glyphicon glyphicon-credit-card"></span> Rembourser carte de credit</a></li>
		<li><a href="<c:url value="/profil" />"><span class="glyphicon glyphicon-user"></span> Mon profil</a></li>
		<li><a href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
      </ul><br>
    </div>

    <div class="col-sm-8">
						<hr>
						<h3>
							Tranfert vers un compte externe<span
								class="glyphicon glyphicon-usd"></span>
						</h3>
						<hr>
						<form
							method="post"
							id="formTransfertOut"
							action="<c:url value="/transfertOut" />"
							role="form">
							<h3>1. Choisir le compte emetteur</h3>
							<div class="form-group">
								<label for="compteOut">Emetteur</label> <select
									class="form-control"
									id="compteOut"
									name="compteOut">
									<option>Choisir compte</option>
									<option>Compte 1</option>
									<option>Compte 2</option>
									<option>Compte 3</option>
								</select>
							</div>
							<hr>
							<h3>2. Informations sur le compte receveur externe</h3>
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label for="nom">Nom :</label> <input
											type="text"
											class="form-control"
											id="nom"
											name="nom"
											placeholder="Dup">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="prenom">Prenom :</label> <input
											type="text"
											class="form-control"
											id="prenom"
											name="prenom"
											placeholder="Maurice">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="idCompte">Numero de compte :</label> <input
											type="number"
											class="form-control"
											id="idCompte"
											name="idCompte"
											placeholder="1234-56788">
									</div>
								</div>
							</div>
							<h3>3. Inscrire le montant désiré</h3>
							<div class="form-group">
								<input
									type="number"
									class="form-control"
									id="montant"
									name="montant"
									placeholder="256$">
							</div>
							<h3>4. Valider le transfert en inscrivant votre mot de passe</h3>
							<div class="form-group">
								<label for="pwd">Password:</label> <input
									type="password"
									class="form-control"
									id="pwd"
									name="pwd"
									value="1234567">
							</div>
							<button
								type="submit"
								class="btn btn-primary">Effectuer le transfert</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<hr>
					</div>
  </div>
</div>

<footer class="container-fluid">
  <p>Banque du Demon</p>
</footer>
</body>
</html>