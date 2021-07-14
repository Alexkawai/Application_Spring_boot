package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.dao.Dao;
import web.model.User;
import web.service.ServiceInterface;

import java.util.List;

@Controller
public class UserController {

    ServiceInterface service;
    @Autowired
    public void setDao(ServiceInterface service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String printUser(ModelMap model) {
        List<User> users= service.allUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }
    @PostMapping(value = "/edit")
    public String edit( User user) {
        service.edit(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, ModelMap model) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "add";
    }
    @PostMapping(value = "/add")
    public String create( User user) {
        service.save(user);
        return "redirect:/";
    }
}
