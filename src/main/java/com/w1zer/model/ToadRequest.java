package com.w1zer.model;

import com.w1zer.validation.ProfileExists;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
public class ToadRequest {
    @NotBlank(message = "Name can't be blank")
    @Size(max = 50, message = "Name must be shorter than 50 characters")
    private final String name;

    @Size(max = 50, message = "Type must be shorter than 50 characters")
    private final String type;

    @Positive(message = "Weight must be positive number")
    private final Long weight;

    @Digits(integer = 5, fraction = 2, message = "Length must have up to 5 digits and 2 fractional digits")
    @Positive(message = "Length must be positive number")
    private final BigDecimal length;

    @PastOrPresent(message = "Birthday must be in the past or present")
    private final Date birthday;

    @Size(max = 150, message = "Description must be shorter than 150 characters")
    private final String description;

    @NotNull(message = "idProfile can't be null")
    @Positive(message = "idProfile must be positive number")
    @ProfileExists(message = "Profile with idProfile not exists")
    private final Long idProfile;
}
