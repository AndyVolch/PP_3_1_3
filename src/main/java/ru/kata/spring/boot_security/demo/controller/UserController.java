package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/admin")
    public String getAllUsers(Model model){
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("allUsers", listOfUsers);
        return "users";
    }
    @RequestMapping("/user")
    public String getUser(Model model, Principal principal){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
    @RequestMapping("/admin/addUser")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user_form";
    }
    @RequestMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @RequestMapping("/admin/updateUser")
    public String updateUser(@RequestParam("UserID") Long id, Model model){
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);
        return "user_form";
    }
    @RequestMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam("UserID") Long id){
        userService.deleteUserByID(id);
        return "redirect:/admin";
    }
}
