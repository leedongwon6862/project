package com.korea.project.food.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {


    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String loginId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    @Email(message = "이메일의 형식을 확인해주세요.")
    private String email;


}
