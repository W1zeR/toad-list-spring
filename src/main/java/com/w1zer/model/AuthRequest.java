package com.w1zer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.w1zer.constants.ValidationConstants.LOGIN_MAX_SIZE;
import static com.w1zer.constants.ValidationConstants.LOGIN_MIN_SIZE;
import static com.w1zer.constants.ValidationConstants.LOGIN_NOT_BLANK_MESSAGE;
import static com.w1zer.constants.ValidationConstants.LOGIN_SIZE_MESSAGE;
import static com.w1zer.constants.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.w1zer.constants.ValidationConstants.PASSWORD_MIN_SIZE;
import static com.w1zer.constants.ValidationConstants.PASSWORD_NOT_BLANK_MESSAGE;
import static com.w1zer.constants.ValidationConstants.PASSWORD_SIZE_MESSAGE;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = LOGIN_NOT_BLANK_MESSAGE)
    @Size(min = LOGIN_MIN_SIZE, max = LOGIN_MAX_SIZE, message = LOGIN_SIZE_MESSAGE)
    private final String login;

    @NotBlank(message = PASSWORD_NOT_BLANK_MESSAGE)
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = PASSWORD_SIZE_MESSAGE)
    private final String password;
}
