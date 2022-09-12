package com.mvp.question.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    private String fullname;
    @NotBlank
    @Size(min = 11, max = 11, message = "phone must be 11 digit")
    private String phone;
    @NotBlank
    @Email
    private String email;

    private Set<String> role;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}