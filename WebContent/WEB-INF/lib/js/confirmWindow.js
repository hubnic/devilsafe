function check(){
	return checkPass() && confirmAddClient();
}


function confirmAddClient()
{
	var nom = document.getElementById("nom").value;
	var prenom = document.getElementById("prenom").value;
	var date = document.getElementById("date").value;
	var telephone = document.getElementById("telephone").value;
	var adresse = document.getElementById("adresse").value;
	var courriel = document.getElementById("courriel").value;

	var retour = confirm("Est-vous sur de procéder à l'ajout du client : s" + "\n" +nom + "\n" +prenom + "\n" +date + "\n" +telephone + "\n" +adresse + "\n" +courriel+" ?"  );
	
	return retour;
	/*if (retour == true) {
	  document.getElementById("newClient").submit();
	}*/
}

function confirmAddAdmin()
{
	var nom = document.getElementById("nom").value;
	var prenom = document.getElementById("prenom").value;

	var retour = confirm("Est-vous sur de procéder à l'ajout de l'administrateur :" + "\n" +nom + "\n" +prenom +" ?"  );
	if (retour == true) {
	  document.getElementById("formAddAdmin").submit();
	}
}

function confirmAddAccount()
{
	var typeCompte = document.getElementById("typeCompte");
	var montant = document.getElementById("montant").value;
	var compte = typeCompte.options[typeCompte.selectedIndex].innerHTML
	
	var retour = confirm("Est-vous sur de procéder à l'ajout du compte :" + "\n" +compte + "\n" +montant  +" ?"  );
	if (retour == true) {
		return true;
	}else{
		return false;
	}
}

function confirmAddCC(id)
{
	var retour = confirm("Est-vous sur d'ajouter la carte de crédit au compte 666-"+id+" ?"  );
	if (retour == true) {
	  document.getElementById("addCreditCard"+id).submit();
	}
}

function confirmDelAccount(id)
{
	var retour = confirm("Est-vous sur de supprimer le compte 666-"+id+" ?" );
	if (retour == true) {
	  document.getElementById("delAccount"+id).submit();
	}
}

function confirmDelClient(id)
{
	var retour = confirm("Est-vous sur de supprimer l'utilisateur : "+id+" ?" );
	if (retour == true) {
	  document.getElementById("delClient"+id).submit();
	}
}



function confirmChangePassAdmin()
{
	var retour = confirm("Est-vous sur de vouloir modifier votre mot de passe ?");
	if (retour == true) {
	  document.getElementById("changePwdAdmin").submit();
	}
}

function checkPass()
{
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    var message = document.getElementById('confirmMessage');
    var badColor = "#ff6666";
    message.style.color = badColor;
    
    if(pass1.value.length <8){
        pass1.parentElement.parentElement.className = "form-group row has-error";
    	message.innerHTML = "Le mot de passe doit contenir au moins 8 caracteres"  
    	return false;
    }
    
    if(pass1.value !== pass2.value){
        pass2.parentElement.parentElement.className = "form-group row has-error";
        message.innerHTML = "Vous devez saisir un mot de passe identique"
    	return false;
    }
    
    if(pass1.value.length >=8 && pass1.value == pass2.value){
        pass1.parentElement.parentElement.className = "form-group row has-success";
        pass2.parentElement.parentElement.className = "form-group row has-success";
        message.innerHTML = ""
    }
    return true;

}

