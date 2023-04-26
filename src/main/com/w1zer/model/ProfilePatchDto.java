package main.com.w1zer.model;

public class ProfilePatchDto {
    private final String login;
    private final String password;

    public ProfilePatchDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
