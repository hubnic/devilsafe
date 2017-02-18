<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Infos Client</title>
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
				<div class="col-sm-10">
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
								</div>
								<div class="col-sm-1"></div>
								<div class="col-sm-4">
									<form
										method="post"
										id="changePwd"
										action="<c:url value="/changePwd" />"
										role="form">
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
										<div class="form-group row">
											<div class="offset-sm-2 col-sm-10">
												<button
													type="submit"
													class="btn btn-warning">Modifier</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<br>
						<hr>
						<h3>
							Voici les comptes <span class="glyphicon glyphicon-usd"></span>
						</h3>
						<div
							class="panel-group"
							id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"></h4>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nom du compte</th>
												<th>N° du compte</th>
												<th>Solde</th>
												<th>Voir</th>
												<th>Clôturer</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${account}Debit</td>
												<td>${accountId}123</td>
												<td>${accountBalance}213$</td>
												<td class="col-xs-2"><a
													data-toggle="collapse"
													data-parent="#accordion"
													href="#collapse1"> <span
														class="glyphicon glyphicon-eye-open"></span>
												</a></td>
												<td class="col-xs-2"><a
													data-toggle="collapse"
													data-parent="#accordion"
													href="#collapse1"> <span
														class="glyphicon glyphicon-trash"></span>
												</a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div
									id="collapse1"
									class="panel-collapse collapse">
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
												<tr>
													<td>11-01-2017</td>
													<td>Bestbuy</td>
													<td>300</td>
												</tr>
												<tr>
													<td>11-01-2017</td>
													<td>Walmart</td>
													<td>66</td>
												</tr>
												<tr>
													<td>11-01-2017</td>
													<td>Canadian tire</td>
													<td>43</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<hr>
							<br>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"></h4>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nom du compte</th>
												<th>N° du compte</th>
												<th>Solde</th>
												<th>Voir</th>
												<th>Clôturer</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${account}Debit</td>
												<td>${accountId}123</td>
												<td>${accountBalance}213$</td>
												<td class="col-xs-2"><a
													data-toggle="collapse"
													data-parent="#accordion"
													href="#collapse1"> <span
														class="glyphicon glyphicon-eye-open"></span>
												</a></td>
												<td class="col-xs-2"><a
													data-toggle="collapse"
													data-parent="#accordion"
													href="#collapse2"> <span
														class="glyphicon glyphicon-trash"></span>
												</a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div
									id="collapse2"
									class="panel-collapse collapse">
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
												<tr>
													<td>11-01-2017</td>
													<td>Bestbuy</td>
													<td>300</td>
												</tr>
												<tr>
													<td>11-01-2017</td>
													<td>Walmart</td>
													<td>66</td>
												</tr>
												<tr>
													<td>11-01-2017</td>
													<td>Canadian tire</td>
													<td>43</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<hr>
						</div>
						<h3>
							Ajouter un compte <span
								class="glyphicon glyphicon-plus"></span>
						</h3>
						<form
							method="post"
							id="formTransfertIn"
							action="<c:url value="/transfertIn" />"
							role="form">
							<h3>1. Choisir le type de compte à ajouter</h3>
							<hr>
							<div class="form-group">
								<label for="compteIn">Choisir le type de compte</label> <select
									class="form-control"
									id="compteIn"
									name="compteIn">
									<option>Choisir compte</option>
									<option>Débit</option>
									<option>Crédit</option>
								</select>
							</div>
							<h3>2. Inscrire le montant de base</h3>
							<div class="form-group">
								<input
									type="number"
									class="form-control"
									id="montant"
									name="montant"
									placeholder="256">
							</div>
							<button
								type="submit"
								class="btn btn-danger">Ajouter le compte</button>
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
</body>
</html>