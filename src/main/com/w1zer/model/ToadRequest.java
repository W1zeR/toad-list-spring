package main.com.w1zer.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
public class ToadRequest {
    @NotBlank(message = "Name can't be blank")
    private final String name;

    private final String type;

    private final Long weight;

    private final BigDecimal length;

    @PastOrPresent(message = "Birthday must be in the past or present")
    private final Date birthday;

    private final String description;

    @NotNull(message = "idProfile can't be null")
    @Positive(message = "idProfile must be positive number")
    private final Long idProfile;
}
