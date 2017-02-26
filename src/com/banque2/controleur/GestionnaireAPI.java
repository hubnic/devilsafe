package com.banque2.controleur;

/**
 * Created by tinic on 2/20/17.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banque2.modele.PojoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
public class GestionnaireAPI {

    @RequestMapping(method = RequestMethod.GET)
    public String listTroopers() {
        return "salut";
    }

    @RequestMapping(value = "/preauth/{id}", method = RequestMethod.POST, headers={"key=1234"})
    public @ResponseBody
    ResponseEntity updateTrooper(@PathVariable("id") int id) {
        if(id == 1){
            return ResponseEntity.ok().header("nope", "marche pas").build();
        }
        else{
            return new ResponseEntity((HttpStatus.BAD_REQUEST));
        }
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
**/

}
