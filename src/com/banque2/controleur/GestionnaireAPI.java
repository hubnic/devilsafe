package com.banque2.controleur;

/**
 * Created by tinic on 2/20/17.
 */

import com.banque2.modele.PojoCarte;
import com.banque2.modele.PojoCompte;
import com.banque2.modele.PojoPreautorisation;
import com.banque2.services.ServiceDaoApi;
import com.banque2.services.ServiceDaoClient;
import com.banque2.services.ServiceDaoCompte;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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

    @Autowired
    private ServiceDaoClient serviceDaoClient;

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

            if (serviceDaoApi.getPreautorisation(i) != null) {
                preauthNode.put("preauth_id", i);preauthNode.put("preauth_status", "FAILURE" );
                preauthNode.put("detail_transaction", "Preautorisation deja existante a l'id suivant:"+i);
                i++;
                return ResponseEntity.status(HttpStatus.CONFLICT).header("salut", "réponse").body(preauthNode.toString());
            } else {
                try {
                    PojoPreautorisation preauth = new PojoPreautorisation();
                    PojoCarte carte = new PojoCarte();

                    //ASSIGNATION DES PARAMÈTRES JSON -> POJO
                    //assignation du numéro de carte de crédit
                    preauth.setCredit_id(rootNode.path("credit_id").getTextValue());
                    carte.setNumCarte(preauth.getCredit_id());

                    //assignation de la date d'expiration
                    preauth.setCredit_expiration(rootNode.path("credit_expiration").getTextValue());
                    carte.setDateExp(preauth.getCredit_expiration());

                    //assignation du nom de la carte de crédit
                    preauth.setCredit_nom(rootNode.path("credit_nom").getTextValue());
                    carte.setNom(preauth.getCredit_nom());

                    //assignation du prénom de la carte
                    preauth.setCredit_prenom(rootNode.path("credit_prenom").getTextValue());
                    carte.setPrenom(preauth.getCredit_prenom());

                    //assignation numero cvs
                    preauth.setCredit_cvs(rootNode.path("credit_cvs").getIntValue());
                    carte.setCrypto(preauth.getCredit_cvs());

                    preauth.setSource_id(rootNode.path("source_id").getTextValue());
                    preauth.setMontant(rootNode.path("montant").getDoubleValue());
                    preauth.setPreauth_id(i);
                    preauth.setPreauthStatus("CREATED");


                    if (serviceDaoApi.checkIfCCexit(carte)){
                        //on créée la préautorisation
                        int preauthId=serviceDaoApi.createPreautorisation(preauth);
                        //ICI ON AJOUTE LE MONTANT AU CREDIT
                        serviceDaoClient.creerPreauth(preauth, carte);
                        //ENVOI REPONSE
                        preauthNode.put("preauth_id", preauthId);
                        preauthNode.put("preauth_status", "CREATED");
                        preauthNode.put("preauth_expiration", serviceDaoApi.getPreautorisation(preauthId).getPreauth_date().toString());
                        preauthNode.put("detail_transaction", "Preautorisation creee avec succes");
                        i++;
                        return ResponseEntity.ok().header("salut", "réponse").body(preauthNode.toString());
                    }
                    else{
                        preauthNode.put("preauth_status", "FAILURE");
                        preauthNode.put("detail_transaction", "La carte de credit n'existe pas");
                        return ResponseEntity.ok().header("salut", "réponse").body(preauthNode.toString());
                    }
                }
                 catch(Exception e){
                    // REPONSE EN CAS D'ECHEC
                    preauthNode.put("preauth_status", "FAILURE");
                    preauthNode.put("detail_transaction", "La preautorisation n'a pu etre creee");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauthNode.toString());
                }


            }
    }



    @RequestMapping(value = "/preauth/{id}", method = RequestMethod.PATCH, headers={"key=1234"})
    public ResponseEntity<String> ModifierPreauth(@PathVariable("id") int id, @RequestBody String body2 ) throws IOException, InterruptedException {

        //PREAUTH RÉFÉRENCE BD
        PojoPreautorisation preauth_bd = serviceDaoApi.getPreautorisation(id);

        System.out.print("je suis sorti du get1");
        System.out.print(preauth_bd.getPreauth_id());
        System.out.print("je suis sorti du get2");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body2);

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/

        ObjectNode preauth_modif_node = mapper.createObjectNode();

        PojoPreautorisation preauth_modif = new PojoPreautorisation();

        //ASSIGNATION DES PARAMÈTRES JSON -> POJO
        preauth_modif.setCredit_id(rootNode.path("credit_id").getTextValue());
        preauth_modif.setCredit_expiration(rootNode.path("credit_expiration").getTextValue());
        preauth_modif.setCredit_nom(rootNode.path("credit_nom").getTextValue());
        preauth_modif.setCredit_prenom(rootNode.path("credit_prenom").getTextValue());
        preauth_modif.setCredit_cvs(rootNode.path("credit_cvs").getIntValue());
        preauth_modif.setSource_id(rootNode.path("source_id").getTextValue());
        preauth_modif.setMontant(rootNode.path("montant").getDoubleValue());
        preauth_modif.setPreauth_id(id);
        preauth_modif.setPreauthStatus(rootNode.path("preauth_status").getTextValue());


        if(preauth_bd!=null){
            if(preauth_bd.getPreauthStatus().equals("CREATED")){
                if(isPreauthExpired(preauth_bd)==false) {
                    if (preauth_modif.getPreauthStatus().equals("CREATED")) {
                        try {
                            preauth_modif_node.put("preauth_status", "CREATED");
                            preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getCredit_expiration());
                            preauth_modif_node.put("detail_transaction", "La preautorisation est deja cree");
                            return ResponseEntity.status(HttpStatus.CREATED).header("salut", "réponse").body(preauth_modif_node.toString());
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                        }
                    } else if (preauth_modif.getPreauthStatus().equals("CANCELED")) {
                        try {
                            preauth_modif.setPreauthStatus("CANCELED");
                            serviceDaoApi.modifierPreautorizationStatus(preauth_modif);
                            preauth_modif_node.put("preauth_status", "CANCELED");
                            preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getCredit_expiration());
                            preauth_modif_node.put("detail_transaction", "La preautorisation a ete cancelee");
                            return ResponseEntity.status(HttpStatus.OK).header("salut", "CANCELED").body(preauth_modif_node.toString());
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                        }
                    } else if (preauth_modif.getPreauthStatus().equals("EXECUTED")) {
                        try {
                            preauth_modif.setPreauthStatus("EXECUTED");
                            serviceDaoApi.modifierPreautorizationStatus(preauth_modif);
                            preauth_modif_node.put("preauth_status", "EXECUTED");
                            preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getCredit_expiration());
                            preauth_modif_node.put("detail_transaction", "La preautorisation a ete execute");
                            return ResponseEntity.status(HttpStatus.ACCEPTED).header("salut", "EXECUTED").body(preauth_modif_node.toString());
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("BD", "PAS DE STATUS1").body(preauth_modif_node.toString());
                    }
                }
                else{
                    try {
                        preauth_modif.setPreauthStatus("CANCELED");
                        serviceDaoApi.modifierPreautorizationStatus(preauth_modif);
                        preauth_modif_node.put("preauth_status", "CANCELED");
                        preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getPreauth_date().plusMinutes(20).toString());
                        preauth_modif_node.put("detail_transaction", "La preautorisation est expirée");
                        return ResponseEntity.status(HttpStatus.OK).header("salut", "CANCELED").body(preauth_modif_node.toString());
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                    }
                }
            }
            else if(serviceDaoApi.getPreautorisation(id).getPreauthStatus().equals("CANCELED")){

                try {
                preauth_modif_node.put("preauth_status","CANCELED");
                preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getCredit_expiration());
                preauth_modif_node.put("detail_transaction", "La preautorisation a deja ete cancelee");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("BD", "CANCELED").body(preauth_modif_node.toString());
                }catch(Exception e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                }
            }
            else if(serviceDaoApi.getPreautorisation(id).getPreauthStatus().equals("EXECUTED")){
                try {
                    preauth_modif_node.put("preauth_status","EXECUTED");
                    preauth_modif_node.put("preauth_expiration", serviceDaoApi.getPreautorisation(id).getCredit_expiration());
                    preauth_modif_node.put("detail_transaction", "La preautorisation a deja ete execute");
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("BD", "EXECUTED").body(preauth_modif_node.toString());
                }catch(Exception e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("salut", "ERROR").body(preauth_modif_node.toString());
                }
            }else{
                preauth_modif_node.put("preauth_status1",serviceDaoApi.getPreautorisation(id).getPreauthStatus());
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("BD", "PAS DE STATUS2").body(preauth_modif_node.toString());
            }
        }
        else{
            preauth_modif_node.put("detail_transaction", "Cette preautorisation n'est pas presente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("BD", "VIDE").body(preauth_modif_node.toString());

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
            compte.setSolde((float)rootNode.path("montant").getDoubleValue());


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

    private boolean isPreauthExpired(PojoPreautorisation preauth){

        ZonedDateTime oldtime = preauth.getPreauth_date().atZone(ZoneOffset.UTC);
        LocalDateTime newtime = oldtime.toLocalDateTime();
        if(newtime.plusMinutes(20).isAfter(LocalDateTime.now())){

            System.out.print("\ndate locale: " + LocalDateTime.now()+"\n");
            System.out.print("\ndate expiration: " + newtime.plusMinutes(20)+"\n");
            return false;
        }
        return true;
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
