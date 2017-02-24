<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Ajouter Admin</title>
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
      <h4>Ajouter un compte administrateur</h4>
				<br>
				<hr>
				<br>
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
				<form
					method="post"
					id="addAdmin"
					action="<c:url value="/addAdmin" />"
					role="form">
					<div class="col-sm-5">
						<div class="form-group row">
							<label
								for="example-email-input"
								class="col-2 col-form-label">Nom :</label>
							<div class="col-10">
								<input
									class="form-control"
									type="text"
									value="Dup"
									id="nom"
									name = "nom"
									required>
							</div>
						</div>
						<div class="form-group row">
							<label
								for="example-email-input"
								class="col-2 col-form-label">Prenom :</label>
							<div class="col-10">
								<input
									class="form-control"
									type="text"
									value="Jean"
									id="prenom"
									name ="prenom"
									required>
							</div>
						</div>
						<div class="form-group row">
							<label
								for="example-email-input"
								class="col-2 col-form-label">Role :</label>
							<div class="col-10">
								<select
									class="form-control"
									id="role"
									name="role">
									<option>Choisir Role</option>
									<option>ADMIN</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-1"></div>
					<div class="col-sm-4">
						<div class="form-group row">
							<label
								for="pass1"
								class="col-2 col-form-label">Mot de passe</label>
							<div class="col-10">
								<input
									class="form-control"
									type="password"
									value="12345"
									id="pass1"
									name="pass1"
									onkeyup="checkPass();"
									required
									>
							</div>
						</div>
						<div class="form-group row">
							<label
								for="pass2"
								class="col-2 col-form-label">Confirmer Mot de passe </label>
							<div class="col-10">
								<input
									class="form-control"
									type="password"
									value="12345"
									id="pass2"
									name="pass2"
									onkeyup="checkPass();"
									required>
								<span id="confirmMessage" class="confirmMessage"></span>
							</div>
						</div>
						<div class="form-group row">
							<div class="offset-sm-2 col-sm-10">
								<button
									type="submit"
									class="btn btn-primary">Créer compte Administrateur</button>
							</div>
						</div>
					</div>
					<input
						type="hidden"
						name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
    </div>
    <div class="col-sm-2 sidenav">
      
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Banque du Demon</p>
</footer>

</body>
</html>