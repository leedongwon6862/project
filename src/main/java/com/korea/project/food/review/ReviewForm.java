package com.korea.project.food.review;

import com.korea.project.food.store.Store;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {

    private Store store;
    @NotEmpty(message = "제목은 필수 입력 항목입니다.")
    private String title;
    @NotEmpty(message = "내용은 필수 입력 항목입니다.")
    private String content;


}
