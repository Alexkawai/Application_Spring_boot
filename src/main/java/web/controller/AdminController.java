package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserDetailsServiceImpl;

import java.util.List;

@Controller
public class AdminController {

    UserDetailsServiceImpl service;
    @Autowired
    public void setDao(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/admin")
    public String printUser(ModelMap model) {
        List<User> users= service.allUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }
    @PostMapping(value = "/admin/edit")
    public String edit( User user) {
        service.edit(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, ModelMap model) {
        service.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/add")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        return "add";
    }
    @PostMapping(value = "/admin/add")
    public String create( User user) {

        service.save(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/user")
    public String takeUser(ModelMap model) {
        UserDetails user = service.loadUserByUsername(getPrincipalName());
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        //model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(  Model model) {

       //model.addAttribute("user", newUser);
        return "redirect:/user";
    }
    public String getPrincipalName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        return username;
    }
}
