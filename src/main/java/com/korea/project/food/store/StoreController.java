package com.korea.project.food.store;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/create") //화면을 보여주는것
    public String create(StoreForm storeForm){
        return "store_form";
    }

    @PostMapping("/create") //요청을 하면 처리하는것
    public String create(@Valid StoreForm storeform , BindingResult bindingResult){
        if(bindingResult.hasErrors ()){
            return "store_form";
        }
        storeService.save(storeform.getName ()); // 날자와 id는 안적어두된다. service 에 적어놨으니깐 ,id는 엔티티에 적어놨으니
            return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Store> storeList = storeService.getStoreList();
        model.addAttribute ("storeList" , storeList);
        return "main";

    }

}
