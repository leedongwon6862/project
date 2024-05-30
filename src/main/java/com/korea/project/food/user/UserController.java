package com.korea.project.food.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/signup")
    public String signup(UserForm userForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm , BindingResult bindingResult){
        if(bindingResult.hasErrors ()){
            return "signup_form";
        }

       userService.createUser(userForm.getLoginId (), userForm.getPassword (), userForm.getEmail ());
        return "redirect:/store/list";
    }

    @GetMapping("/login")
    public String login(UserForm userForm){
        return "login_form";
    }


    @GetMapping("/logout")
    public String logout(UserForm userForm){
        return "redirect:/";
    }




}
