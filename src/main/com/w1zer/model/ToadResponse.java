package main.com.w1zer.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
public class ToadResponse {
    private final Long id;

    private final String name;

    private final String type;

    private final Long weight;

    private final BigDecimal length;

    private final Date birthday;

    private final String description;

    private final Long idProfile;
}
