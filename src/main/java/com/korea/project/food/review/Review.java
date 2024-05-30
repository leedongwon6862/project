package com.korea.project.food.review;

import com.korea.project.food.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity //기능을 만들어야하는걸 정의해주려고
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title;
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Store store;

}
