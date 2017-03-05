<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="fr">
<head>
<title>Reinitialiser MDP</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<body background="<c:url value="/librairie/images/key.png" />">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-lock"></span>Banque du Demon
					</div>
					<div class="panel-body">
					<c:if test="${succes == true}">
							<div class="alert alert-info">
								<p>${description}</p>
							</div>
						</c:if>
						<h5>Veuillez reinitialiser votre mot de passe : ${idUser} </h5>
						<form method="post" id="reset" action="<c:url value="/reinitialiserMdp" />" role="form">
						<div class="form-group">
								<label for="pass1">Inscrire le PIN de reinitalisation recu par courriel :</label> <input
									type="number"
									class="form-control"
									id="pin"
									value="${pin}"
									name="pin"
									placeholder="PIn de reinitialisation"
									required>
							</div>
							<div class="form-group">
								<label for="pass1">Mot de passe :</label> <input
									type="password"
									class="form-control"
									id="pass1"
									name="pass1"
									value="${pass1}"
									placeholder="Saisir votre nouveau mot de passe"
									required>
							</div>
							<div class="form-group">
								<label for="pass2">Valider Mot de passe :</label> <input
									type="password"
									class="form-control"
									id="pass2"
									name="pass2"
									value="${pass2}"
									placeholder="Valider votre nouveau mot de passe"
									required>
							</div>
							<input type="hidden" id="idt" name="idt" value="${idUser}" />
							<button type="submit" class="btn btn-default">Reinitialiser MDP</button>
							<input
								type="hidden"
								name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<c:if test="${succes == false}">
							<div class="alert alert-alert">
								<p>${description}</p>
							</div>
						</c:if>
						<br><hr>
						<a href="<c:url value="/" />">Retour au Portail?</a> 
						<br>
						
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
