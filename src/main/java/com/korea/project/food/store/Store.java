package com.korea.project.food.store;


import com.korea.project.food.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String content;
    private LocalDateTime createDate;
    private  String url; //사진넣기.


    @OneToMany(mappedBy ="store" , cascade = CascadeType.REMOVE) // store 를 지우면 store 에 관한 리뷰까지 다지워지게끔
    private List<Review> reviewList;
}
