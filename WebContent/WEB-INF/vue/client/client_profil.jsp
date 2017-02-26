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
		<li><a href="<c:url value="/credit"/>"><span class="glyphicon glyphicon-credit-card"></span> Rembourser carte de credit</a></li>
		<li class="active"><a href="<c:url value="/profil" />"><span class="glyphicon glyphicon-user"></span> Mon profil</a></li>
		<li><a href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
      </ul><br>
    </div>

    <div class="col-sm-9">
				<div class="container">
					<h1>
						Voici vos informations personnelles <span
							class="glyphicon glyphicon-user"></span>
					</h1>
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
										value="${userName}"
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
										value="${userFirstName}"
										id="prenom"
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
										value="${courriel}"
										id="courriel"
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
										value="${tel}"
										id="tel"
										readonly>
								</div>
							</div>
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-4">
							<h4>Modifier votre mot de passe</h4>
							<hr>
							<form
								method="post"
								id="changePwd"
								action="<c:url value="/changePwd" />"
								role="form">
								<div class="form-group row">
									<label
										for="oldPass"
										class="col-2 col-form-label">Ancien mot de passe</label>
									<div class="col-10">
										<input
											class="form-control"
											type="password"
											id="oldPass"
											name="oldPass"
											required>
									</div>
								</div>
								<div class="form-group row">
									<label
										for="newPass"
										class="col-2 col-form-label">Nouveau Mot de passe</label>
									<div class="col-10">
										<input
											class="form-control"
											type="password"
											id="newPass"
											name="newPass"
											required>
									</div>
								</div>
								<div class="form-group row">
									<label
										for="newPass2"
										class="col-2 col-form-label">Confirmation</label>
									<div class="col-10">
										<input
											class="form-control"
											type="password"
											id="newPass2"
											name="newPass2"
											required>
									</div>
								</div>
								<div class="form-group row">
									<div class="offset-sm-2 col-sm-10">
										<button
											type="submit"
											class="btn btn-warning">Changer mot de passe</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<hr>
			</div>
  </div>
</div>

<footer class="container-fluid">
  <p>Banque du Demon</p>
</footer>
</body>