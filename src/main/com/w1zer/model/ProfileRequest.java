package main.com.w1zer.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ProfileRequest {
    @NotBlank(message = "Login can't be blank")
    private final String login;

    @NotBlank(message = "Password can't be blank")
    private final String password;
}
