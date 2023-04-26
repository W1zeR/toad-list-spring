package main.com.w1zer.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Toad {
    private final Long id;
    private final String name;
    private final String type;
    private final Long weight;
    private final BigDecimal length;
    private final Date birthday;
    private final String description;
    private final Long idProfile;

    public Toad(Long id, String name, String type, Long weight, BigDecimal length, Date birthday, String description,
                Long idProfile) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.length = length;
        this.birthday = birthday;
        this.description = description;
        this.idProfile = idProfile;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toad toad = (Toad) o;
        return Objects.equals(id, toad.id) && Objects.equals(weight, toad.weight) &&
                Objects.equals(idProfile, toad.idProfile) && name.equals(toad.name) &&
                Objects.equals(type, toad.type) && Objects.equals(length, toad.length) &&
                Objects.equals(birthday, toad.birthday) && Objects.equals(description, toad.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, weight, length, birthday, description, idProfile);
    }
}
