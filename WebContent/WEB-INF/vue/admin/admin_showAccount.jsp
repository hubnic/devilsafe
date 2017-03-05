<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Infos Client</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/js/valideLogin.js" />"></script>
<script src="<c:url value="/librairie/js/confirmWindow.js" />"></script>
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
		<a class="navbar-brand" href="#">Votre Espace D</a>
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
				<br><hr>
					<form
							method="post"
							id="formAddAccountClient"
							name ="addAccountClient"
							action="<c:url value="/addAccountClient" />"
							>
							<h3>Ajouter un compte bancaire</h3>
							<hr>
							<div class="form-group">
								<label for="typeCompte">1. Choisir le type de compte</label> <select
									class="form-control"
									id="typeCompte"
									name="typeCompte"
									required>
									<option>Choisir compte</option>
									<option>DEBIT</option>
									<option>CREDIT</option>
								</select>
							</div>
							<h3>2. Inscrire le montant de base</h3>
							<div class="form-group">
								<input
									type=number step=0.01
									class="form-control"
									id="montant"
									name="montant"
									placeholder="500.00"
									required>
							</div>
							<input
								type="hidden"
								name="idClient"
								value="${client.identifiant}" />
							<button
								onclick="confirmAddAccount()"
								class="btn btn-danger">Ajouter le compte</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
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
    </div>
    
    
    
    <div class="col-sm-8 text-left"> 
				<div class="col-sm-9">
						<div class="container">
							<h3>
							<span class="glyphicon glyphicon-user"></span>
								Information personnelles de : ${client.nom} ${client.prenom} 
							</h3>
							<br>
						
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group row">
										<label
											for="example-email-input"
											class="col-2 col-form-label">Identifant :</label>
										<div class="col-10">
											<input
												class="form-control"
												type="text"
												value="${client.identifiant}"
												id="nom"
												readonly>
										</div>
									</div>
									<div class="form-group row">
										<label
											for="example-email-input"
											class="col-2 col-form-label">Nom :</label>
										<div class="col-10">
											<input
												class="form-control"
												type="text"
												value="${client.nom}"
												id="nom"
												readonly>
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
												value="${client.prenom}"
												id="prenom"
												readonly>
										</div>
									</div>
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-4">
								<div class="form-group row">
										<label
											for="tel"
											class="col-2 col-form-label">Telephone</label>
										<div class="col-10">
											<input
												class="form-control"
												type="tel"
												value="${client.telephone}"
												id="tel"
												readonly>
										</div>
									</div>
										<div class="form-group row">
											<label
												for="courriel"
												class="col-2 col-form-label">Email</label>
											<div class="col-10">
												<input
													class="form-control"
													type="email"
													value="${client.courriel}"
													id="courriel"
													readonly>
											</div>
										</div>
										<div class="form-group row">
											<label
												for="tel"
												class="col-2 col-form-label">Adresse</label>
											<div class="col-10">
												<input
													class="form-control"
													type="tel"
													value="${client.adresse}"
													id="tel"
													readonly>
											</div>
										</div>
								</div>
							</div>
						</div>
						<br>
	<hr>
						<h3><span class="glyphicon glyphicon-usd"></span> Etat financier de : ${client.nom} ${client.prenom}  </h3>
						<br>
						<c:forEach items="${comptes}" var="comptes">
					
					
					<div class="container">
						<div class="row">
						<div class="col-sm-10">
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
												<th>Clôturer</th>
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
											<td class="col-xs-2">
												<form method="post" id="delAccount ${comptes.idCompte}" action="<c:url value="/delAccount" />"role="form">
													<input type="hidden" class="form-control" id="idClient" name="idClient" value="${client.identifiant}" readonly>
													<input type="hidden" class="form-control" id="idCompte" name="idCompte" value="${comptes.idCompte}" readonly>
													<input type="hidden" class="form-control" id="typeCompte" name="typeCompte" value="${comptes.type}" readonly>
													<button onclick="confirmDelAccount(${comptes.idCompte})"> 
													<span class="glyphicon glyphicon-trash"></span>
													</button>
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												</form>
											</td>
												
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
													<th>Nom transaction</th>
													<th>Montant $</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${comptes.transactions}" var="transactions">
												<tr>
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
							</div>
							</div>
						</c:forEach>
						<hr>
					</div>
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
