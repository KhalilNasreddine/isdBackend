package com.book.verse.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "Firstname should not be empty")
    @NotBlank(message = "Firstname should not be empty")
    private String firstname;
    @NotEmpty(message = "Lastname should not be empty")
    @NotBlank(message = "Lastname should not be empty")
    private String lastname;
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email should not be empty")
    @NotBlank(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, message = "Password length should be minimum 8 characters")
    private String password;
    @NotEmpty(message = "Image should not be empty")
    @NotBlank(message = "Image should not be empty")
    private String profileimage;


}
