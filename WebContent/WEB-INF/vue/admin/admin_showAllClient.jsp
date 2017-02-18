<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>All Client</title>
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
					<li><a href="<c:url value="/newAdmin"/>">Créer compte
							Administrateur</a></li>
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
				<h4>Liste des clients</h4>
				<br>
				<hr>
				<br>

					<div class="col-sm-10">
						<div class="container">
							<div class="row">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4>Membre Deamon</h4>
									</div>
									<table class="table table-fixed">
										<thead>
											<tr>
												<th class="col-xs-2">#client</th>
												<th class="col-xs-5">Nom et Prénom</th>
												<th class="col-xs-2">Accéder au compte</th>
											</tr>
										</thead>
										<tbody>
				
										<c:forEach items="${clients}" var="clients">
										
												<tr>
												
												<td class="col-xs-2"><c:out value="${clients.identifiant}" /></td>
												<td class="col-xs-5"><c:out value="${clients.nom} ${clients.prenom}" /></td>
												<td class="col-xs-2">
												<form method="post" id="id" action="<c:url value="/showAccount" />"role="form">
													<input type="hidden" class="form-control" id="id" name="id" value="${clients.identifiant}" readonly>
													<button type="submit"> 
													<span class="glyphicon glyphicon-eye-open"></span>
													</button>
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												</form>
												</td>
											
												</tr>
											
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>


			</div>
		</div>
	</div>
</body>
</html>