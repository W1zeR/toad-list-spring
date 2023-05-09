package com.w1zer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private final Long id;

    private final String login;
}
