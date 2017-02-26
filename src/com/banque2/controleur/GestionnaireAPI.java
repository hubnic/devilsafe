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


        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        map = mapper.readValue(body, new TypeReference<Map<String, String>>() {});

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/

        if (CREATION == 1) {
            ObjectNode preauth = mapper.createObjectNode();
            preauth.put("preauth_id", i);
            preauth.put("preauth_status", "CREATED");
            preauth.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            preauth.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(preauth.toString());
        } else {
            ObjectNode preauth = mapper.createObjectNode();
            preauth.put("preauth_status", "FAILURE");
            preauth.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauth.toString());
        }
    }



    @RequestMapping(value = "/preauth/{id}", method = RequestMethod.PATCH, headers={"key=1234"})
    public ResponseEntity<String> ModifierPreauth(@PathVariable("id") int id, @RequestBody String body2 ) throws IOException {

        Map<String, Object> map2 = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body2);
        JsonNode idNode = rootNode.path("credit_id");
        map2 = mapper.readValue(body2, new TypeReference<Map<String, String>>(){});

        /* CRÉATION DE LA PRÉAUTORIZATION ICI*/

        if(id == 1){
            ObjectNode preauth_modif = mapper.createObjectNode();
            preauth_modif.put("preauth_id", idNode);
            preauth_modif.put("preauth_status","CREATED");
            preauth_modif.put("preauth_expiration", new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60000)).toString());
            preauth_modif.put("detail_transaction", "Preautorisation creee avec succes");
            i++;
            return ResponseEntity.ok().header("salut", "réponse").body(preauth_modif.toString());
        }
        else{
            ObjectNode preauth_modif = mapper.createObjectNode();
            preauth_modif.put("preauth_status","FAILURE");
            preauth_modif.put("detail_transaction", "La preautorisation n'a pu etre creee");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("status", "pas bon").body(preauth_modif.toString());
        }
    }

    @RequestMapping(value = "/virement", method = RequestMethod.POST, headers={"key=1234"})
    public ResponseEntity<String> creerVirement(@RequestBody String body ) {

        body.toString();
        return ResponseEntity.ok().header("salut", "salut").body(body);
    }
/**
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Stormtrooper getTrooper(@PathVariable("id") String id) throws NotFoundException {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Stormtrooper createTrooper(@RequestBody Stormtrooper trooper) {
        return trooperDao.addStormtrooper(trooper);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public Stormtrooper updateTrooper(@PathVariable("id") String id, @RequestBody Stormtrooper updatedTrooper) throws NotFoundException {
        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTrooper(@PathVariable("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }



    //read json file data to String
    byte[] jsonData = Files.readAllBytes(Paths.get("employee.txt"));

    //create ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    //read JSON like DOM Parser
    JsonNode rootNode = objectMapper.readTree(jsonData);
    JsonNode idNode = rootNode.path("id");
**/

}
