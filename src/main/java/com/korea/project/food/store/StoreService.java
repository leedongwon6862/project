package com.korea.project.food.store;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;  // 레파지토리에서 컨트롤러로 바로 불러오면 위험하기때문에
    private final ResourceLoader resourceLoader;

    public void save(String name , String content , String location, String url) {
        Store store = new Store ();
        store.setName (name);
        store.setContent (content);
        store.setLocation (location);
        store.setUrl (url);
        store.setCreateDate (LocalDateTime.now ());

        storeRepository.save (store);   // 레파지토리에  저장해야하기 때문에.
    }

    public List<Store> getStoreList() {
        return storeRepository.findAll ();
    }

    public Store getStore(Long id) {
        return storeRepository.findById(id).orElseThrow ();
    }
    public String temp_save(MultipartFile file) {
        if (!file.isEmpty())
            try {
                String path = resourceLoader.getResource("classpath:/static").getFile().getPath();
                File fileFolder = new File ( path + "/image");
                if (!fileFolder.exists())
                    fileFolder.mkdirs();
                String filePath = "/image/" + UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
                file.transferTo(Paths.get(path + filePath));
                return filePath;
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        return null;
    }
}
