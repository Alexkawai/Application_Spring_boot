package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.dao.Dao;
import web.model.User;

import java.util.List;

@Controller
public class UserController {

    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/")
    public String printUser(ModelMap model) {
        List<User> users= dao.allUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        User user = dao.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }
    @PostMapping(value = "/edit")
    public String edit( User user) {
        dao.edit(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, ModelMap model) {
        dao.delete(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "add";
    }
    @PostMapping(value = "/add")
    public String create( User user) {
        dao.save(user);
        return "redirect:/";
    }
}
