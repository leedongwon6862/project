package com.korea.project.food.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser createUser(String loginId , String password , String email) {
        SiteUser siteUser = new SiteUser ();
        siteUser.setLoginId (loginId);
        siteUser.setPassword (passwordEncoder.encode (password));
        siteUser.setEmail (email);
        siteUser.setCreateDate (LocalDateTime.now ());

        userRepository.save (siteUser);

        return siteUser;

    }
}
