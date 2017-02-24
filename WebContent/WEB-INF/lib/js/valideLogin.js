function checkPass()
{
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    var message = document.getElementById('confirmMessage');
    var badColor = "#ff6666";
    
    if(pass1.value.length <8){
        pass1.parentElement.parentElement.className = "form-group row has-error";
        message.style.color = badColor;
    	message.innerHTML = "Le mot de passe doit contenir au moins 8 caracteres"    	
    }
    
    if(pass1.value !== pass2.value){
        pass2.parentElement.parentElement.className = "form-group row has-error";
        message.innerHTML = "Vous devez saisir un mot de passe identique"
    }
    
    if(pass1.value.length >=8 && pass1.value == pass2.value){
        pass1.parentElement.parentElement.className = "form-group row has-success";
        pass2.parentElement.parentElement.className = "form-group row has-success";
        message.innerHTML = ""
    }

}