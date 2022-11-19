package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.LoginPage;

import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.page.DashboardPage.isVisible;
import static ru.netology.page.LoginPage.*;
import static ru.netology.page.VerificationPage.getDashboardPage;
import static ru.netology.page.VerificationPage.isVerificationError;

public class UITest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999", LoginPage.class);
    }

    //
    //  Positive test scenario with valid Username, Password and Verification code
    //

    @Test
    void shouldLoginWithValidUserData() {
        User user = DataHelper.getValidUserFirst();
        getVerificationPage(user);
        String code = DBHelper.getCode(user);
        getDashboardPage(code);
        isVisible();
    }

    //
    //  Set of negative test scenarios with invalid Username, Password and Verification code
    //

    @Test
    void shouldReturnErrorWithValidUserNameAndWrongPassword() {
        User user = DataHelper.getValidUserFirstWrongPassword();
        getVerificationPage(user);
        isLoginError();
    }

    @Test
    void shouldReturnErrorWithValidUserDataAndWrongCode() {
        User user = DataHelper.getValidUserFirst();
        getVerificationPage(user);
        String code = DataHelper.getWrongCode();
        getDashboardPage(code);
        isVerificationError();
    }

    @Test
    void shouldReturnErrorValidUserDataAndExceedRetryPassword() {
        User user = DataHelper.getValidUserFirstWrongPassword();
        getVerificationPage(user);
        getVerificationPage(user);
        getVerificationPage(user);
        assertEquals("blocked", DBHelper.getUserStatus(user));
    }

    @Test
    void shouldReturnToken() {
        User user = DataHelper.getValidUserFirst();
        APIHelper.logIn(user);
        String code = DBHelper.getCode(user);
        String token = APIHelper.getToken(user, code);
        List<Card> cardList = APIHelper.getCards(token);
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).setOpenedNumber(DBHelper.getCardNumber(cardList.get(i).getId()));
        }
        String actual = APIHelper.makeTransfer(cardList.get(0).getOpenedNumber(), cardList.get(1).getOpenedNumber(), "1000", token);
        System.out.println(actual);
    }

    @AfterAll
    static void flushTables() {
        DBHelper.flushTables();
    }
}