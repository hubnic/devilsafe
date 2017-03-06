<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Mot de passe oublie</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<body background="<c:url value="/librairie/images/cle.png" />">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-lock"></span>Banque du Demon
					</div>
					<div class="panel-body">
						<h3>Mot de passe oublie?</h3>
						<form method="post" id="login" action="<c:url value="/mdpOublie" />" role="form">
							<div class="form-group">
								<label for="idt">Inscrire Identifiant:</label> <input
									type="text"
									class="form-control"
									id="idt"
									name="idt"
									placeholder="Inscrire votre identifiant"
									required>
							</div>
							<div class="form-group">
								<label for="pswd">Inscrire @ courriel :</label> <input
									type="email"
									class="form-control"
									id="courriel"
									name="courriel"
									placeholder="Inscrire votre courriel"
									required>
							</div>
							<button type="submit" class="btn btn-default">Demander une reinitialisation</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<hr>
						<c:if test="${succes == false}">
							<div class="alert alert-danger">
								<p>${description}</p>
							</div>
						</c:if>
						<br>
						<a href="<c:url value="/" />">Retour au Portail?</a> 
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container text-center">
	</div>
	<footer class="container-fluid text-center">
		<p>Banque du demon</p>
	</footer>
</body>
</html>
