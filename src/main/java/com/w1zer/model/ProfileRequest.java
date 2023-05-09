package com.w1zer.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProfileRequest {
    @NotBlank(message = "Login can't be blank")
    @Size(min = 3, max = 50, message = "Login must contain from 3 to 50 characters")
    private final String login;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 5, max = 50, message = "Password must contain from 5 to 50 characters")
    private final String password;
}
