package com.mvp.question.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditUserRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    private String fullname;
    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(min = 6, max = 40)
    private String confirm_password;

}
