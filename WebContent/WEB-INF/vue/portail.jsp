<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Portail</title>
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
<body background="<c:url value="/librairie/images/fond.png" />">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-lock"></span>Banque du Demon
					</div>
					<div class="panel-body">
						<h3>Mon compte</h3>
						<form
							method="post"
							id="login"
							action="<c:url value="/portail" />"
							role="form">
							<div class="form-group">
								<label for="idt">Identifiant:</label> <input
									type="text"
									class="form-control"
									id="idt"
									name="idt"
									placeholder="Identifiant"
									required>
							</div>
							<div class="form-group">
								<label for="pswd">Mot de passe:</label> <input
									type="password"
									class="form-control"
									id="pswd"
									name="pswd"
									placeholder="Mot de passe"
									required>
							</div>
							<button
								type="submit"
								class="btn btn-default">Connexion</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Problème d'authentification.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>Deconnexion avec succès.</p>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<hr>
	<div class="container text-center">
		<div class="row">
			<div class="col-sm-3">
				<p>ADS</p>
			</div>
			<div class="col-sm-6">
				<p>ADS</p>
			</div>
			<div class="col-sm-3">
				<p>ADS</p>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center">
		<p>Banque du demon</p>
	</footer>
</body>
</html>
