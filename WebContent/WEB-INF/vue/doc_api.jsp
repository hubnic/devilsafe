<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
<title>Documentation API</title>
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/librairie/bootstrap-3.3.7/css/bootstrap.css" />">
<script src="<c:url value="/librairie/jquery/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/librairie/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>Explication de l'API</h4>
    </div>


    <div class="col-sm-9">
      <h2>Documentation de l'API</h2>

      <p>&nbsp;</p>
<h2><strong>Cr&eacute;er une pr&eacute;autorisation</strong></h2>
<p><span style="font-weight: 400;">Cet appel peut &ecirc;tre utilis&eacute; pour cr&eacute;er une pr&eacute;autorisation de paiement sur le compte du client pass&eacute; en param&egrave;tre. Retourne les informations relatives &agrave; cette pr&eacute;autorisation.</span></p>
<p><span style="font-weight: 400;">POST /api/:clientID/preauth</span><span style="font-weight: 400;"><br /><br /></span></p>
<h3><strong>Param&egrave;tres</strong></h3>
<table>
<tbody>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><strong>Ent&ecirc;te</strong></p>
</td>
<td style="height: 35px;">&nbsp;<strong>Type</strong></td>
<td style="height: 35px;">&nbsp;<strong>Description</strong></td>
</tr>
<tr style="height: 13px;">
<td style="height: 13px;">&nbsp;key</td>
<td style="height: 13px;">&nbsp;string</td>
<td style="height: 13px;">&nbsp;Cl&eacute; publique permettant l'identification pour utilisation de l'API</td>
</tr>
<tr style="height: 13.0938px;">
<td style="height: 13.0938px;">&nbsp;Content-Type</td>
<td style="height: 13.0938px;">&nbsp;string</td>
<td style="height: 13.0938px;">&nbsp;application/json</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><strong>Param&egrave;tre</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Type</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">credit_id</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">num&eacute;ro de carte de cr&eacute;dit de utilis&eacute; pour la transaction</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">credit_expiration</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">expiration de la carte de cr&eacute;dit</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">credit_nom</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">Nom du d&eacute;tenteur de la carte</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">credit_prenom</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">Pr&eacute;nom du d&eacute;tenteur de la carte</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">credit_cvs</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string &nbsp;</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">CVS de la carte de s&eacute;curit&eacute;</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">source_id</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">Identifiant de la source de la transaction</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">montant</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">double</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">Montant de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p><span style="font-weight: 400;"><br /><br /></span></p>
<p>&nbsp;</p>
<h3><strong>R&eacute;ponse</strong><strong><br /></strong><span style="font-weight: 400;">La r&eacute;ponse de l&rsquo;api:</span></h3>
<p><span style="font-weight: 400;">HTTP Status Code: 201 CREATED</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_ID"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"xxxxxxx"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"CREATED",</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_expiration&rdquo;: &rdquo;</span><span style="font-weight: 400;">yyyy-mm-dd hh:mm:ss"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation a &eacute;t&eacute; cr&eacute;&eacute;e avec succ&egrave;s&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 403 FORBIDDEN</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre cr&eacute;&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 400 BAD REQUEST</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre cr&eacute;&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<h3><strong>Attributs de la r&eacute;ponse</strong></h3>
<table>
<tbody>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preath_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Num&eacute;ro d&rsquo;identification de la pr&eacute;autorisation, en cas de succ&egrave;s</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_status</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">{CREATED, FAILURE}</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_expiration</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Date d&rsquo;expiration de la pr&eacute;autorisation en cas de r&eacute;ussite</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">detail_transaction</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Texte des d&eacute;tails de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p><br /><br /><br /></p>
<h2><strong>Modifier une pr&eacute;autorisation</strong></h2>
<p><span style="font-weight: 400;">Cet appel peut &ecirc;tre utilis&eacute; pour modifier une pr&eacute;autorisation de paiement sur le compte du client pass&eacute; en param&egrave;tre, par exemple pour l&rsquo;annuler. Retourne les informations relatives &agrave; cette pr&eacute;autorisation.</span></p>
<p><span style="font-weight: 400;">PATCH /api/preauth/:preauth_id</span><span style="font-weight: 400;"><br /><br /></span></p>
<h3><strong>Param&egrave;tres</strong></h3>
<table>
<tbody>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><strong>Ent&ecirc;te</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Type</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p>key</p>
</td>
<td style="height: 35px;">
<p>string</p>
</td>
<td style="height: 35px;">
<p>Cl&eacute; publique permettant l'identification pour utilisation de l'API</p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;"><strong>&nbsp;</strong>Content-Type</td>
<td style="height: 35px;"><strong>&nbsp;</strong>string</td>
<td style="height: 35px;">application/json</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><strong>Param&egrave;tre</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Type</strong></p>
</td>
<td style="height: 35px;">
<p><strong>Description</strong></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">preauth_ID</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">Identifiant de la pr&eacute;autorisation</span></p>
</td>
</tr>
<tr style="height: 35px;">
<td style="height: 35px;">
<p><span style="font-weight: 400;">public_api_key</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 35px;">
<p><span style="font-weight: 400;">La cl&eacute; d&rsquo;API qui permet d&rsquo;autoriser son utilisateur</span></p>
</td>
</tr>
<tr style="height: 37.625px;">
<td style="height: 37.625px;">
<p><span style="font-weight: 400;">preauth_status</span></p>
</td>
<td style="height: 37.625px;">
<p><span style="font-weight: 400;">string</span></p>
</td>
<td style="height: 37.625px;">
<p><span style="font-weight: 400;">Status de la pr&eacute;autorisation{CANCELED,EXECUTED}</span></p>
</td>
</tr>
</tbody>
</table>
<p><span style="font-weight: 400;"><br /><br /></span></p>
<p>&nbsp;</p>
<h3><strong>R&eacute;ponse</strong><strong><br /></strong><span style="font-weight: 400;">La r&eacute;ponse de l&rsquo;api:</span></h3>
<p><span style="font-weight: 400;">HTTP Status Code: 200</span></p>
<p><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_ID"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"xxxxxxx"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"CANCELED"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation a &eacute;t&eacute; CANCELL&Eacute;E&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 403 FORBIDDEN</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre cr&eacute;&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 400 BAD REQUEST</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre modifi&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p><br /><br /><br /></p>
<h3><strong>Attributs de la r&eacute;ponse</strong></h3>
<table>
<tbody>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preath_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Num&eacute;ro d&rsquo;identification de la pr&eacute;autorisation</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_status</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">{FAILURE, CANCELED}</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">detail_transaction</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Texte des d&eacute;tails de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p><br /><br /><br /></p>
<h2><strong>Ex&eacute;cuter une pr&eacute;autorisation (paiement)</strong></h2>
<p><span style="font-weight: 400;">Cet appel peut &ecirc;tre utilis&eacute; pour ex&eacute;cuter la pr&eacute;autorisation de paiement sur le compte du client pass&eacute; en param&egrave;tre, donc verser les fonds pour ex&eacute;cuter la transaction.. Retourne les informations relatives &agrave; la transaction</span></p>
<p><span style="font-weight: 400;">PATCH &nbsp;/api/preauth/:preauth_id</span><span style="font-weight: 400;"><br /><br /></span></p>
<h3><strong>Param&egrave;tres</strong></h3>
<table>
<tbody>
<tr>
<td><strong>Ent&ecirc;te</strong></td>
<td><strong>type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td>key</td>
<td>string</td>
<td>Cl&eacute; d'identification public de l'aPI</td>
</tr>
<tr>
<td>
<p>Content-Type/json</p>
</td>
<td>
<p>string</p>
</td>
<td>
<p>application/json</p>
</td>
</tr>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">public_api_key</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">La cl&eacute; d&rsquo;API qui permet d&rsquo;autoriser son utilisation</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Identifiant de la pr&eacute;autorisation</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">timestamp</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Temps au moment de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p><span style="font-weight: 400;"><br /><br /></span></p>
<p>&nbsp;</p>
<h3><strong>R&eacute;ponse</strong><strong><br /></strong><span style="font-weight: 400;">La r&eacute;ponse de l&rsquo;api:</span></h3>
<p><span style="font-weight: 400;">HTTP Status Code: 200</span></p>
<p><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_ID"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"xxxxxxx"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"EXECUTED"</span><span style="font-weight: 400;">,</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation a &eacute;t&eacute; ex&eacute;cut&eacute;e &nbsp;avec succ&egrave;s&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 403 FORBIDDEN</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre ex&eacute;cut&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 400 BAD REQUEST</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"preauth_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La pr&eacute;autorisation n&rsquo;a pas pu &ecirc;tre cr&eacute;&eacute;e&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<h3><strong>Attributs de la r&eacute;ponse</strong></h3>
<table>
<tbody>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Num&eacute;ro d&rsquo;identification de la pr&eacute;autorisation</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">preauth_status</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">{FAILURE&nbsp;EXECUTED}</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">detail_transaction</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Texte des d&eacute;tails de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p><br /><br /><br /><br /><br /></p>
<h2><strong>Virement &agrave; un compte</strong></h2>
<p><span style="font-weight: 400;">Cet appel peut &ecirc;tre utilis&eacute; pour effectuer un virement depuis la banque 1 et la passerelle pour un virement de fonds. L&rsquo;appel doit contenir la cl&eacute; d&rsquo;API pr&eacute;alablement fournie et compte et le montant du virement. Les d&eacute;tails de la transaction sont envoy&eacute;s en retour</span></p>
<p><span style="font-weight: 400;">POST /api/virement/</span><span style="font-weight: 400;"><br /><br /></span></p>
<h3><strong>Param&egrave;tres</strong></h3>
<table>
<tbody>
<tr>
<td><strong>Ent&ecirc;te</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td>key</td>
<td>string</td>
<td>Cl&eacute; publique d'identification de l'utilisateur</td>
</tr>
<tr>
<td>
<p>Content-Type</p>
</td>
<td>
<p>string</p>
</td>
<td>
<p>application/json</p>
</td>
</tr>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">compte_dst_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">num&eacute;ro de compte ou les fond seront vers&eacute;s</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">src_ID</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">compte source de la transaction</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">montant</span></p>
</td>
<td>
<p><span style="font-weight: 400;">double</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Montant de la transaction</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">description</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Description textuelle&nbsp;</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">public_api_key</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">La cl&eacute; d&rsquo;API qui permet d&rsquo;autoriser son utilisateur</span></p>
</td>
</tr>
</tbody>
</table>
<p><span style="font-weight: 400;"><br /><br /></span></p>
<p>&nbsp;</p>
<h3><strong>R&eacute;ponse</strong><strong><br /></strong><span style="font-weight: 400;">La r&eacute;ponse de l&rsquo;api:</span></h3>
<p><span style="font-weight: 400;">HTTP Status Code: 200</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"transaction_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"SUCCEED"</span><span style="font-weight: 400;">,<br /></span><span style="font-weight: 400;">&nbsp; &nbsp;"timestamp": "yyyy-mm-dd hh:mm:ss",<br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;La virement a &eacute;t&eacute; fait avec succ&egrave;s&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p><span style="font-weight: 400;">HTTP Status Code: 403 FORBIDDEN</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"transaction_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;Le virement n&rsquo;a pas pu &ecirc;tre fait&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<p><span style="font-weight: 400;">HTTP Status Code: 400 BAD REQUEST</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">{</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"transaction_status"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">"FAILURE"</span><span style="font-weight: 400;">,</span> <span style="font-weight: 400;">//success, failure</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;"> &nbsp;&nbsp;&nbsp;</span><span style="font-weight: 400;">"detail_transaction"</span><span style="font-weight: 400;">: </span><span style="font-weight: 400;">&ldquo;Le virement n&rsquo;a pas pu &ecirc;tre fait, mauvaise requ&ecirc;te&rdquo;</span><span style="font-weight: 400;"><br /></span><span style="font-weight: 400;">}</span></p>
<p>&nbsp;</p>
<h3><strong>Attributs de la r&eacute;ponse</strong></h3>
<table>
<tbody>
<tr>
<td>
<p><strong>Param&egrave;tre</strong></p>
</td>
<td>
<p><strong>Type</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">timestamp</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Temps au moment de la transaction</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">transaction_status</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">{SUCCEED, FAILURE}</span></p>
</td>
</tr>
<tr>
<td>
<p><span style="font-weight: 400;">detail_transaction</span></p>
</td>
<td>
<p><span style="font-weight: 400;">string</span></p>
</td>
<td>
<p><span style="font-weight: 400;">Texte des d&eacute;tails de la transaction</span></p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
</div>

<footer class="container-fluid">
  <p>Banque du Demon</p>
</footer>
</body>
