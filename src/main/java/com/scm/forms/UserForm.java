package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message="UserName is required")
    @Size(min=3, message="Min 3 characters are required")
    private String name;

    @NotBlank(message="Email Address is required")
    @Email(message="Invalid Email Address")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min=6, message="Min 6 Chracters are required")
    private String password;
    @NotBlank(message="About is required")
    private String about;
    @Size(min=8, max=12, message="Invalid Phone Number")
    @NotBlank(message="Phone Number is required")
    private String phoneNumber;

}
