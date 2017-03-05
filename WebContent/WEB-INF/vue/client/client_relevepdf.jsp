<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Releve PDF</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>
<div class="container">
  <h2>Releve du compte : ${compte.type} ${compte.idBanque}${compte.idCompte}</h2>
  <p></p>            
  <table class="table table-bordered">
    <thead>
      <tr>
     	<th>#Transaction</th>
		<th>Date</th>
		<th>Nom transaction</th>
		<th>Montant $</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach items="${compte.transactions}" var="transactions">
		<tr>
			<td>${transactions.idTransaction}</td>
			<td>${transactions.date}</td>
			<td>${transactions.description}</td>
			<td>${transactions.montant}</td>
		</tr>
	</c:forEach>
    </tbody>
</table>
<h2>Le solde du compte est de : ${compte.solde} $</h2>
<input type="button" value="Imprimer ce releve" onClick="window.print()">
</div>



</body>
</html>