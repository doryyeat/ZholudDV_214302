package com.bookland.controller;

import com.bookland.controller.main.Main;
import com.bookland.model.Users;
import com.bookland.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Main {

    @GetMapping
    public String reg(Model model) {
        AddAttributes(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password) {
        if (usersRepo.findAll().isEmpty() || usersRepo.findAll().size() == 0) {
            usersRepo.save(new Users(username, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributes(model);
            return "reg";
        }

        usersRepo.save(new Users(username, password, Role.CLIENT));

        return "redirect:/login";
    }
}
