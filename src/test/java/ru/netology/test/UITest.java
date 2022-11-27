package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import ru.netology.data.User;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;

import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        LoginPage loginPage =  new LoginPage();
        loginPage.getVerificationPage(user);
        String code = DBHelper.getCode(user);
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.getDashboardPage(code);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.isVisible();
    }

    //
    //  Set of negative test scenarios with invalid Username, Password and Verification code
    //

    @Test
    void shouldReturnErrorWithValidUserNameAndWrongPassword() {
        User user = DataHelper.getValidUserFirstWrongPassword();
        LoginPage loginPage =  new LoginPage();
        loginPage.getVerificationPage(user);
        loginPage.isLoginError();
    }

    @Test
    void shouldReturnErrorWithValidUserDataAndWrongCode() {
        User user = DataHelper.getValidUserFirst();
        LoginPage loginPage =  new LoginPage();
        loginPage.getVerificationPage(user);
        String code = DataHelper.getWrongCode();
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.getDashboardPage(code);
        verificationPage.isVerificationError();
    }

    @Test
    void shouldReturnErrorValidUserDataAndExceedRetryPassword() {
        User user = DataHelper.getValidUserFirstWrongPassword();
        LoginPage loginPage =  new LoginPage();
        loginPage.getVerificationPage(user);
        loginPage.getVerificationPage(user);
        loginPage.getVerificationPage(user);
        assertEquals("blocked", DBHelper.getUserStatus(user));
    }

    @AfterAll
    static void flushTables() {
        DBHelper.flushTables();
    }
}