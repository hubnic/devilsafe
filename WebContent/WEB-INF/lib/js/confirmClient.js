function check(){
	return checkAccount() && confirmTransfertIn();
}

function confirmChangePass()
{
		return confirm("Est-vous sur de vouloir modifier votre mot de passe ?");
		/*if (retour == true) {
		  document.getElementById("changePwdClient").submit();
		}*/
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
		
		return retour;
		/*if (retour == true) {
		  document.getElementById("formTransfertIn").submit();
		}*/
}

function confirmRembourseCC()
{
		var compteOut = document.getElementById("compteOut").value;
		var montant = document.getElementById("montantRemboursement").value;
	
		var retour = confirm("Voulez-vous procéder au remboursement de votre carte de credit selon les informations suivantes : \n" 
				+ "Compte emetteur : "+ compteOut +"\n"
				+ "Pour un montant de "+ montant +"$ \n"+"?");
		
		return retour;
		/*if (retour == true) {
		  document.getElementById("formRemboursementCC").submit();
		}*/
}


function checkAccount(){
    var emetteur = document.getElementById('compteOut');
    var receveur = document.getElementById('compteIn');
    var message = document.getElementById('confirmMessage');
    var badColor = "#ff6666";
    message.style.color = badColor;
    
    if(emetteur.value == receveur.value){
        //pass2.parentElement.parentElement.className = "form-group row has-error";
        message.innerHTML = "Vous ne pouvez pas transférer d'argent sur le même compte."
    	return false;
    } else {
        message.innerHTML = "";
        return true;
    }
    
}

