package com.korea.project.food.store;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;  // 레파지토리에서 컨트롤러로 바로 불러오면 위험하기때문에


    public void save(String name) {
        Store store = new Store ();
        store.setName (name);
        store.setCreateDate (LocalDateTime.now ());

        storeRepository.save (store);   // 레파지토리에  저장해야하기 때문에.
    }

    public List<Store> getStoreList() {
        return storeRepository.findAll ();
    }
}
