package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.servise.RoleService;
import ru.itmentor.spring.boot_security.demo.servise.UserService;
import ru.itmentor.spring.boot_security.demo.models.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "all-users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(User user, @RequestParam(value = "nameRoles", required = false) String[] roles) {
        userService.getUserAndRoles(user, roles);
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/updateInfo/{id}")
    public String showUpdateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.findUserById(id));
        return "update";
    }

    @PostMapping("/userUpdate")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "nameRoles", required = false) String[] roles) {

        userService.getUserAndRoles(user, roles);
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}





 /*
    Аннотация @RequestParam в методе saveUser() контроллера UserController используется для получения параметра запроса с именем "nameRoles".
    Этот параметр представляет собой массив имен ролей, выбранных пользователем в форме.
    Атрибут value указывает имя параметра запроса, который необходимо получить. В данном случае это "nameRoles".
    Атрибут required указывает, является ли параметр обязательным. В данном случае он имеет значение false, что означает,
    что параметр не является обязательным.
    Если параметр запроса "nameRoles" присутствует в запросе, он будет привязан к массиву имен ролей.
    Если параметр отсутствует, массив имен ролей будет иметь значение null.
    В данном случае массив имен ролей используется для назначения ролей пользователю перед сохранением пользователя в базе данных.
     */