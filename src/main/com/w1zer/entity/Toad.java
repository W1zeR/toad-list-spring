package main.com.w1zer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class Toad {
    private final Long id;

    private final String name;

    private final String type;

    private final Long weight;

    private final BigDecimal length;

    private final Date birthday;

    private final String description;

    private final Long idProfile;
}
