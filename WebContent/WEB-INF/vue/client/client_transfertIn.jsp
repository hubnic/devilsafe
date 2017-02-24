<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transfert In</title>
<link
	rel="stylesheet"
	href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link
	rel="stylesheet"
	href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/js/valideLogin.js" />"></script>
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script
	src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a
					class="navbar-brand"
					href="#">Votre Espace D</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/apercu" />"><span
						class="glyphicon glyphicon-home"></span> Accueil</a></li>
				<li><a href="<c:url value="/profil" />"><span
						class="glyphicon glyphicon-user"></span> Mon profil</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/deconnexion" />"><span
						class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h5>Transfert interne</h5>
				<br> <br>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/apercu"/>">Mes comptes</a></li>
					<li><a href="<c:url value="/transfertIn"/>">Transfert
							d'argent vers un compte interne</a></li>
					<li><a href="<c:url value="/transfertOut"/>">Transfert
							d'argent vers un compte externe</a></li>
					<li><a href="<c:url value="/credit"/>">Rembourser ma carte
							de credit</a></li>
				</ul>
				<br>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-sm-8">
						<hr>
						<h3>
							Tranfert d'argent entre vos comptes<span
								class="glyphicon glyphicon-usd"></span>
						</h3>
						<form
							method="post"
							id="formTransfertIn"
							action="<c:url value="/transfertIn" />"
							role="form">
							<h3>1. Choisir le compte emetteur</h3>
							<div class="form-group">
								<label for="compteOut">Emetteur</label> <select
									class="form-control"
									id="compteOut"
									name="cpmpteOut">
									<option>Choisir compte emetteur</option>
									<option>Compte 1</option>
									<option>Compte 2</option>
									<option>Compte 3</option>
								</select>
							</div>
							<hr>
							<h3>2. Indiquer les informations sur le compte receveur</h3>
							<div class="form-group">
								<label for="compteIn">Compte receveur</label> <select
									class="form-control"
									id="compteIn"
									name="compteIn">
									<option>Choisir compte receveur</option>
									<option>Compte 1</option>
									<option>Compte 2</option>
									<option>Compte 3</option>
								</select>
							</div>
							<h3>3. Inscrire le montant désiré</h3>
							<div class="form-group">
								<input
									type="number"
									class="form-control"
									id="montant"
									name="montant"
									placeholder="256">
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
								class="btn btn-danger">Effectuer le transfert</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<hr>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="container-fluid">
		<p>Banque 2</p>
	</footer>
</body>
</html>