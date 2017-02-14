<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>ShowLog</title>
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
			s
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/homeAdmin" />"><span
						class="glyphicon glyphicon-home"></span> Accueil</a></li>
				<li><a href="<c:url value="/adminProfil" />"><span
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
				<h4>
					Administration <span class="glyphicon glyphicon-cog"></span>
				</h4>
				<br>
				<hr>
				<h5>
					Gestion des clients<span class="glyphicon glyphicon-usd"></span>
				</h5>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/newClient"/>">Nouveau client?</a></li>
					<li><a href="<c:url value="/showAllClient"/>">Gérer un
							compte existant?</a></li>
				</ul>
				<br>
				<hr>
				<h5>
					Gestion des administrateur<span class="glyphicon glyphicon-user"></span>
				</h5>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/newAdmin"/>">Créer
							compte Administrateur</a></li>
				</ul>
				<br>
				<hr>
				<h5>
					Analyse des logs<span class="glyphicon glyphicon-wrench"></span>
				</h5>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/showLog"/>">Voir les Logs</a></li>
				</ul>
			</div>
			<div class="col-sm-9">
				<h4>
					<small>Voir les logs</small>
				</h4>
				<br> <br>
				<hr>
				<br>
				<div class="form-group">
					<label for="log">Log:</label>
					<textarea
						class="form-control"
						rows="5"
						id="log"
						name="log"></textarea>
				</div>
			</div>
		</div>
	</div>
</body>
</html>