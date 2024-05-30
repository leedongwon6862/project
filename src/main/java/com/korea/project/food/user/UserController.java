package com.korea.project.food.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

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

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetail principal, Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        SiteUser siteUser = this.userService.findByUserName(principal.getUsername ());
        model.addAttribute("siteUser", siteUser);
        if(!model.containsAttribute("url") || model.getAttribute("url")==null)
            model.addAttribute("url",siteUser.getProfile_image());
        return "profile";
    }


    @PostMapping("/imageform")
    public String imageform(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        String url = null;
        if(file.getContentType().contains("image"))
            url = userService.temp_save(file);
        redirectAttributes.addFlashAttribute("url",url);

        return "redirect:/user/profile";
    }

    @PostMapping("/imagesaveform")
    public String imagesaveform(Principal principal, @RequestParam(value = "url",defaultValue = "")String url){
        if(url.isBlank())
            return "redirect:/user/profile";
        SiteUser siteUser = userService.getUser(principal.getName());
        userService.save(siteUser,url);
        return "redirect:/user/profile";
    }


}
