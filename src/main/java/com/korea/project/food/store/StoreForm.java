package com.korea.project.food.store;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreForm {
    @NotEmpty(message = "음식점 이름은 필수 항목입니다.")
    private String name;
}
