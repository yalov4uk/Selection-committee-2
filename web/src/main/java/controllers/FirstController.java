package controllers;

import beans.User;
import interfaces.ICRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.CRUDService;

/**
 * Created by valera on 5/7/17.
 */
@RestController
public class FirstController {

    @RequestMapping("/")
    public String index() {
        ICRUDService crudService = new CRUDService();
        return crudService.getAll(User.class).toString();
    }
}
