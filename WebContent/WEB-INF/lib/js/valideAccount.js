
function checkAccount(){
    var emetteur = document.getElementById('compteOut');
    var receveur = document.getElementById('compteIn');
    var message = document.getElementById('confirmMessage');
    var badColor = "#ff6666";
    message.style.color = badColor;
    
    if(emetteur.value == receveur.value){
        //pass2.parentElement.parentElement.className = "form-group row has-error";
        message.innerHTML = "Vous ne pouvez pas transférer d'argent sur le même compte."
    } else {
        message.innerHTML = "";
    }
    
}