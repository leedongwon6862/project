package com.korea.project.food.review;

import com.korea.project.food.store.Store;
import com.korea.project.food.store.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private  final ReviewService reviewService;
    private final StoreService storeService;


    @GetMapping("/create/{id}") // 이리뷰가 store 에 속해야 하므로 id 가 필요하다.
    public String create(ReviewForm reviewForm ,@PathVariable("id") Long id){
        return "review_form";

    }

    @PostMapping("/create/{id}")
    public String create(@Valid ReviewForm reviewForm , BindingResult bindingResult , @PathVariable("id") Long id ){
        Store store =  storeService.getStore (id); //  그 해당하는 store 를 가져와야하기때문
        if (bindingResult.hasErrors ()){
             return "review_form";
         }

        Review review = reviewService.save(store,reviewForm.getTitle (), reviewForm.getContent ());
        return "redirect:/store/detail/%d".formatted (review.getStore ().getId ());
    }

    @GetMapping("/{storeId}/edit/{id}")
    public String reviewEdit(@PathVariable("id") Long id, ReviewForm reviewForm, Model model) {
        Review review = reviewService.getReview (id);
        reviewForm.setTitle (review.getTitle ());
        reviewForm.setContent (review.getContent ());
        model.addAttribute ("store", review.getStore ());
        model.addAttribute ("reviewForm", reviewForm);
        model.addAttribute ("review", review);
        return "review_edit";
    }

    @PostMapping("/{storeId}/edit/{id}")
    public String reviewEdit(@PathVariable("id") Long id, @Valid ReviewForm reviewForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors ())
            return "review_form";
        Review review = reviewService.getReview (id);
        if (review == null)
            return "redirect:/error";

        reviewService.update (review, reviewForm.getTitle (), reviewForm.getContent ());

        return "redirect:/store/detail/%d".formatted (review.getStore ().getId ());

    }


}


