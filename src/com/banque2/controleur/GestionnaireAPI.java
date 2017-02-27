package com.banque2.controleur;

/**
 * Created by tinic on 2/20/17.
 */

import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoPreautorisation;
import com.banque2.services.ServiceDaoApi;
import com.banque2.services.ServiceDaoCompte;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;

@RestController
@RequestMapping(value = "/api", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
public class GestionnaireAPI {

    int i= 0;
    final int CREATION = 0;
    final int MODIF =1;
    
    @Autowired
	private ServiceDaoApi serviceDaoApi;

    @Autowired
    private ServiceDaoCompte serviceDaoCompte;

    @RequestMapping(method = RequestMethod.GET)
    public String listTroopers() {
        return "salut";
    }


    @RequestMapping(value = "/preauth", method = RequestMethod.POST, headers={"key=1234"})
    public ResponseEntity<String> creerPreauth(@RequestBody  String body ) throws IOException {

        // CREATION MAPPER POUR ACCEDER AUX DONNEES
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body);

        //CREATION REPONSE
        ObjectNode preauthNode = mapper.createObjectNode();


        try {
            //CREATION POJO preautorisation
            PojoPreautorisation preauth = new PojoPreautorisation();

            //ASSIGNATION DES PARAMÈTRES JSON -> POJO
            preauth.setCredit_id(rootNode.path("credit_id").getTextValue());
            preauth.setCredit_expiration(rootNode.path("credit_expiration").getTextValue());
            preauth.setCredit_nom(rootNode.path("credit_nom").getTextValue());
            preauth.setCredit_prenom(rootNode.path("credit_prenom").getTextValue());
            preauth.setCredit_cvs(rootNode.path("credit_cvs").getIntValue());
            preauth.setSource_id(rootNode.path("source_id").getTextValue());
            preauth.setMontant(rootNode.path("montant").getDoubleValue());
            preauth.setPreauth_id(i);

            serviceDaoApi.createPreautorisation(preauth);

            //ENVOI REPONSE
            preauthNode.put("preauth_id", i);
            preauthNode.put("preauth_status", "CREATED");
            preauthNode.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            preauthNode.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(preauthNode.toString());
        }
        catch (Exception e){
            // REPONSE EN CAS D'ECHEC
            preauthNode.put("preauth_status", "FAILURE");
            preauthNode.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauthNode.toString());
        }

    }



    @RequestMapping(value = "/preauth/{id}", method = RequestMethod.PATCH, headers={"key=1234"})
    public ResponseEntity<String> ModifierPreauth(@PathVariable("id") int id, @RequestBody String body2 ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body2);
        JsonNode idNode = rootNode.path("credit_id");

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/

        ObjectNode preauth_modif = mapper.createObjectNode();
        if(id == 1){
            preauth_modif.put("preauth_id", idNode);
            preauth_modif.put("preauth_status","CREATED");
            preauth_modif.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            preauth_modif.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(preauth_modif.toString());
        }
        else{
            preauth_modif.put("preauth_status","FAILURE");
            preauth_modif.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauth_modif.toString());
        }
    }

    @RequestMapping(value = "/virement", method = RequestMethod.POST, headers={"key=1234"})
    public ResponseEntity<String> creerVirement(@RequestBody  String body ) throws IOException {


        // CREATION MAPPER POUR ACCEDER AUX DONNEES
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body);

        //CREATION REPONSE
        ObjectNode compteNode = mapper.createObjectNode();


        try {
            //CREATION POJO preautorisation
            PojoCompte compte = new PojoCompte();

            //ASSIGNATION DES PARAMÈTRES JSON -> POJO
            compte.setIdCompte(rootNode.path("compte_dest_ID").getIntValue());
            compte.setSolde(rootNode.path("montant").getDoubleValue());


            serviceDaoCompte.ajoutMontant(
                    compte.getIdCompte(),
                    compte.getSolde());

            //ENVOI REPONSE

            compteNode.put("transaction_status", "SUCCEED");
            compteNode.put("timestamp", new Date(Calendar.getInstance().getTimeInMillis()).toString());
            compteNode.put("detail_transaction", "virement fait avec succès");

            return ResponseEntity.ok().header("salut", "réponse").body(compteNode.toString());
        }
        catch (Exception e){
            // REPONSE EN CAS D'ECHEC
            compteNode.put("transaction_status", "FAILURE");
            compteNode.put("detail_transaction", "Le virement n'a pu etre fait");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(compteNode.toString());
        }

    }




/**
    //read json file data to String
    byte[] jsonData = Files.readAllBytes(Paths.get("employee.txt"));

    //create ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    //read JSON like DOM Parser
    JsonNode rootNode = objectMapper.readTree(jsonData);
    JsonNode idNode = rootNode.path("id");
**/

}
