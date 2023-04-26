package main.com.w1zer.model;

public class ProfileGetDto {

    private final Long id;
    private final String login;

    public ProfileGetDto(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
