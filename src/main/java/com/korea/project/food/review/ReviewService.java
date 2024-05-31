package com.korea.project.food.review;


import com.korea.project.food.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.sampled.ReverbType;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review save(Store store,String title , String content) {
        Review review = new Review ();
        review.setStore (store);
        review.setTitle (title);
        review.setContent (content);
        review.setCreateDate (LocalDateTime.now ());
        reviewRepository.save(review);
        return review;  // 저장을 한값을 내보내줘야 하니깐 .

    }

    public Review getReview(Long id) {
        return reviewRepository.findById (id).orElseThrow ();
    }

    public void update(Review review, String title, String content) {
        review.setTitle (title);
        review.setContent (content);
        reviewRepository.save (review);
    }
}
