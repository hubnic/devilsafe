function confirmChangePass()
{
		var retour = confirm("Est-vous sur de vouloir modifier votre mot de passe ?");
		if (retour == true) {
		  document.getElementById("changePwdClient").submit();
		}
}

function confirmTransfertIn()
{
		var compteOut = document.getElementById("compteOut").value;
		var compteIn = document.getElementById("compteIn").value;
		var montant = document.getElementById("montant").value;
	
		var retour = confirm("Est-vous certain d'effectuer le transfert : \n" 
				+ "du compte emetteur : "+ compteOut +"\n"
				+ "vers le Compte receveur : "+ compteIn +"\n"
				+ "pour un montant de : "+ montant +"\n"+"?");
		if (retour == true) {
		  document.getElementById("formTransfertIn").submit();
		}
}

function confirmRembourseCC()
{
		var compteOut = document.getElementById("compteOut").value;
		var montant = document.getElementById("montant").value;
	
		var retour = confirm("Voulez-vous procéder au remboursement de votre carte de credit selon les informations suivantes : \n" 
				+ "Compte emetteur : "+ compteOut +"\n"
				+ "Pour un montant de "+ montant +"$ \n"+"?");
		if (retour == true) {
		  document.getElementById("formRemboursementCC").submit();
		}
}

