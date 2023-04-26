package main.com.w1zer.model;

import java.math.BigDecimal;
import java.sql.Date;

public class ToadPostDto {
    private final String name;
    private final String type;
    private final Long weight;
    private final BigDecimal length;
    private final Date birthday;
    private final String description;
    private final Long idProfile;

    public ToadPostDto(String name, String type, Long weight, BigDecimal length, Date birthday, String description, Long idProfile) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.length = length;
        this.birthday = birthday;
        this.description = description;
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getWeight() {
        return weight;
    }

    public BigDecimal getLength() {
        return length;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getDescription() {
        return description;
    }

    public Long getIdProfile() {
        return idProfile;
    }
}