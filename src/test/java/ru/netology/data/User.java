package ru.netology.data;

public class User {
    private String id;
    private String login;
    private String password;
    private String status;

    private User() {};

    public User(String id, String login, String password, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}