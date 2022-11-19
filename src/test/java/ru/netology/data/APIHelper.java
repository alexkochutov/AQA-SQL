package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIHelper {
    private static final String URL_LOGIN = "/api/auth";
    private static final String URL_VERIFY = "/api/auth/verification";
    private static final String URL_CARDS = "/api/cards";
    private static final String URL_TRANSFER = "/api/transfer";

    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    public static void logIn(User user) {
        Requests.LoginRequest loginRequest = new Requests.LoginRequest(user.getLogin(), user.getPassword());
        given()
                .spec(spec)
                .body(loginRequest)
        .when()
                .post(URL_LOGIN)
        .then()
                .statusCode(200);
    }

    public static String getToken(User user, String code) {
        Requests.TokenRequest tokenRequest = new Requests.TokenRequest(user.getLogin(), code);
        var result = given()
                .spec(spec)
                .body(tokenRequest)
        .when()
                .post(URL_VERIFY)
        .then()
                .statusCode(200);
        return result.extract().path("token");
    }

    public static List<Card> getCards(String token) {
        List<Card> cards = given()
                .spec(spec)
                .header("Authorization", "Bearer " + token)
        .when()
                .get(URL_CARDS)
        .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", Card.class);
        return cards;
    }

    public static String makeTransfer(String cardFrom, String cardTo, String amount, String token) {
        Requests.TransferRequest transferRequest = new Requests.TransferRequest(cardFrom, cardTo, amount);
        var result = given()
                .baseUri("http://localhost:9999")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(transferRequest)
        .when()
                .post(URL_TRANSFER)
        .then()
                .statusCode(200);
        return result.extract().path("status");
    }
}