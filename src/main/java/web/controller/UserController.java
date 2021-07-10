package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.dao.Dao;
import web.model.User;

@Controller
public class UserController {

    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }
    @GetMapping(value = "/")
    public String printUser(ModelMap model) {
        User user = dao.getById(1L);

        //User user = new User("Vasya","Petechkin","vasya.petechkin@yandex.ru");
        model.addAttribute("user", user);
        return "index";
    }
}
