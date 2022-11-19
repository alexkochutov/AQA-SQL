package ru.netology.data;

public class DataHelper {
    public static User getValidUserFirst() {
        return new User("10de7665-6eea-42e2-b405-05ae9c3d72e4", "vasya", "qwerty123", "active");
    }

    public static User getValidUserFirstWrongPassword() {
        return new User("10de7665-6eea-42e2-b405-05ae9c3d72e4", "vasya", "123qwe!@#", "active");
    }

    public static String getWrongCode() {
        return "123456";
    }
}