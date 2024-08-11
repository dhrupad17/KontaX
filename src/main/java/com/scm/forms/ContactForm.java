package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message="Name is Required")
    private String name;
    @Email(message="Invalid Email Address")
    private String email;
    @NotBlank(message="Phone Number is Required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number")
    private String phoneNumber;
    @NotBlank(message="Address is Required")
    private String address;
    private String description;
    private boolean favourite;
    private String websiteLink;
    private String linkedlnLink;
    private MultipartFile profileImage;
}
