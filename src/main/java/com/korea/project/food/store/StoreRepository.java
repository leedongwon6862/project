package com.korea.project.food.store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store ,Long> {
    List<Store> findByNameContaining(String name);
}
