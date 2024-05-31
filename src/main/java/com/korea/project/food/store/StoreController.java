package com.korea.project.food.store;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/create") //화면을 보여주는것 // 가게등록
    public String create(StoreForm storeForm) {
        return "store_form";
    }

    @PostMapping("/create") //요청을 하면 처리하는것
    public String create(@Valid StoreForm storeform, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors ()) {
            return "store_form";
        }
        String url = storeService.temp_save (file);
        storeService.save (storeform.getName (), storeform.getContent (), storeform.getLocation (),url); // 날자와 id는 안적어두된다. service 에 적어놨으니깐 ,id는 엔티티에 적어놨으니
        return "redirect:/";
    }

    @GetMapping("/list") //메인화면
    public String list(Model model) {
        List<Store> storeList = storeService.getStoreList ();  //create 에서 만든 list 를 다보여주려고
        model.addAttribute ("storeList", storeList);
        return "main";

    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Store store = storeService.getStore (id);
        if (store == null) {
            return "redirect:/error";
        }
        model.addAttribute ("store", store);
        return "store_detail";
    }

  @GetMapping("/search")
    public String search(@RequestParam("name") String name,Model model){
        List<Store> searchStore = storeService.findBySeacrh (name);
        model.addAttribute ("searchStore",searchStore);

        return "search_store";
  }
}
