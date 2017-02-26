package com.banque2.controleur;

/**
 * Created by tinic on 2/20/17.
 */

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banque2.services.ServiceDaoApi;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.GET)
    public String listTroopers() {
        return "salut";
    }


    @RequestMapping(value = "/preauth", method = RequestMethod.POST, headers={"key=1234"})
    public ResponseEntity<String> creerPreauth(@RequestBody  String body ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body);
        JsonNode idNode = rootNode.path("credit_id");

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/

        ObjectNode preauth = mapper.createObjectNode();
        if (CREATION == 1) {
            preauth.put("preauth_id", i);
            preauth.put("preauth_status", "CREATED");
            preauth.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            preauth.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(preauth.toString());
        } else {
            preauth.put("preauth_status", "FAILURE");
            preauth.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauth.toString());
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


        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        map = mapper.readValue(body, new TypeReference<Map<String, String>>() {});

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/
        ObjectNode virement = mapper.createObjectNode();
        if (CREATION == 1) {

            virement.put("preauth_id", i);
            virement.put("preauth_status", "CREATED");
            virement.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            virement.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(virement.toString());
        } else {
            virement.put("preauth_status", "FAILURE");
            virement.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(virement.toString());
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
