package com.korea.project.food.user;


import com.korea.project.food.DataNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

    public SiteUser createUser(String loginId , String password , String email) {
        SiteUser siteUser = new SiteUser ();
        siteUser.setLoginId (loginId);
        siteUser.setPassword (passwordEncoder.encode (password));
        siteUser.setEmail (email);
        siteUser.setCreateDate (LocalDateTime.now ());

        userRepository.save (siteUser);

        return siteUser;

    }
    public SiteUser findByUserName(String name) {
        Optional<SiteUser> siteUser = this.userRepository.findByloginId (name);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException ("siteuser not found");
        }
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

    public void save(SiteUser siteUser, String url) {
        try {
            String path = resourceLoader.getResource("classpath:/static").getFile().getPath();
            if(siteUser.getProfile_image()!=null) {
                File oldFile = new File(path+siteUser.getProfile_image());
                if(oldFile.exists())
                    oldFile.delete();
            }
            siteUser.setProfile_image(url);
            userRepository.save(siteUser);
        } catch (IOException ignored) {

        }

    }
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByloginId (username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
