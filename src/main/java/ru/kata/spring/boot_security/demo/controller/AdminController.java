package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) { this.userService = userService; }

    @RequestMapping()
    public String getAllUsers(Model model, Principal principal){
        List<User> listOfUsers = userService.getAllUsers();
        User loggedUser = userService.getUserByName(principal.getName());
        model.addAttribute("allUsers", listOfUsers);
        model.addAttribute("loggedUser", loggedUser);
        return "users";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model, Principal principal){
        User user = new User();
        User loggedUser = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("loggedUser", loggedUser);
        return "user_form";
    }
    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user){
        System.out.println(user.getRoles());
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("UserID") Long id, Model model){
        User user = userService.getUserByID(id);
        model.addAttribute("user", user);
        return "user_form";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("UserID") Long id){
        userService.deleteUserByID(id);
        return "redirect:/admin";
    }
    @GetMapping("/users")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserByID(id);
    }
}
