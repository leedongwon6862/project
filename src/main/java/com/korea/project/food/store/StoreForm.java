package com.korea.project.food.store;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreForm {  //@RequestParam 으로  일일히 적어주기 싫어서.


    private String name;
    private String location;
    private String content;
}
