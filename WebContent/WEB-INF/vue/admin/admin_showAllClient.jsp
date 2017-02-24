<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Membre Client</title>
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
				<li><a href="<c:url value="/homeAdmin" />"><span
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
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
     <h4>Administration <span class="glyphicon glyphicon-cog"></span></h4>
				<br>
				<hr>
				<h5>Gestion des clients</h5>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/newClient"/>">Nouveau client?</a></li>
					<li><a href="<c:url value="/showAllClient"/>">Gérer un compte existant?</a></li>
				</ul>
				<br>
				<hr>
				<h5>
					<span class="glyphicon glyphicon-user"></span> Gestion des administrateur
				</h5>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/newAdmin"/>">Créer compte Administrateur</a></li>
				</ul>
    </div>
    <div class="col-sm-8 text-left"> 
      <h1>Liste des membres</h1>
      <c:if test="${succes == true}">
							<div class="alert alert-success">
								<p>${description}</p>
							</div>
				</c:if>
				
							<div class="row">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4>Membre Deamon</h4>
									</div>
									<table class="table table-fixed">
										<thead>
											<tr>
												<th class="col-xs-1">#client</th>
												<th class="col-xs-1">Nom et Prénom</th>
												<th class="col-xs-1">Courriel</th>
												<th class="col-xs-1">Accéder au compte</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${clients}" var="clients">
												<tr>
												<td class="col-xs-1"><c:out value="${clients.identifiant}" /></td>
												<td class="col-xs-1"><c:out value="${clients.nom} ${clients.prenom}" /></td>
												<td class="col-xs-1"><c:out value="${clients.courriel}" /></td>
												<td class="col-xs-1">
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
    <div class="col-sm-2 sidenav">
      <div class="well">
      <h4>Zone de Recherche</h4>
     <form method="post" id="search" action="<c:url value="/searchClientByElm" />"role="form">
									<div class="row">
									<div class="col-sm-10">
											<select	class="form-control" id="critere" name="critere">
												<option>CHOISIR CRITERE</option>
												<option>ID CLIENT</option>
												<option>NOM CLIENT</option>
												<option>PRENOM CLIENT</option>
												<option>COURRIEL CLIENT</option>
												<option>SANS CRITERES</option>
											</select>
									  </div>
									  </div>
									  <br>
									  <div class="row">
									 	<div class="col-sm-10">
									  	<input class="form-control" type="text" id="critereValue" name="critereValue" placeholder="Indiquer Recherche"></div>
									  </div>
									  `<br>
									    <div class="row">
									 	<div class="col-sm-10">
									  	 <button type="submit" class="btn btn-info">Rechercher</button></div>
									  	   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									  </div>
									</form>
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Banque du Demon</p>
</footer>

</body>
</html>