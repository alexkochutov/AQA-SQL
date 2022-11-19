package ru.netology.data;

public class Card {
    private String id;
    private String number;
    private String balance;
    private String openedNumber;

    private Card() {}

    public Card(String id, String number, String balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getBalance() {
        return balance;
    }

    public String getOpenedNumber() {
        return openedNumber;
    }

    public void setOpenedNumber(String number) {
        this.openedNumber = number;
    }
}