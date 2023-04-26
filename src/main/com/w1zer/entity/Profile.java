package main.com.w1zer.entity;

import java.util.Objects;

public class Profile {
    private final Long id;
    private final String login;
    private final String password;

    public Profile(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && login.equals(profile.login) && password.equals(profile.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}
