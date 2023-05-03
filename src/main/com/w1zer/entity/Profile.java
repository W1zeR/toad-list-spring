package main.com.w1zer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Profile {
    private final Long id;

    private final String login;

    private final String password;
}
