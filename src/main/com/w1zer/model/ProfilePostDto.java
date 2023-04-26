package main.com.w1zer.model;

public class ProfilePostDto {
    private final String login;
    private final String password;

    public ProfilePostDto(String login, String password) {
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
