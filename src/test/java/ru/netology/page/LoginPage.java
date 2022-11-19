package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final SelenideElement loginField = $("[data-test-id='login'] input");
    private static final SelenideElement loginFieldError = $("[data-test-id='login'] .input__sub");
    private static final SelenideElement passwordField = $("[data-test-id='password'] input");
    private static final SelenideElement passwordFieldError = $("[data-test-id='password'] .input__sub");
    private static final SelenideElement loginButton = $("[data-test-id='action-login']");

    private static final SelenideElement notificationError = $("[data-test-id='error-notification']");
    private static final SelenideElement notificationErrorTitle = $("[data-test-id='error-notification'] .notification__title");
    private static final SelenideElement notificationErrorContent = $("[data-test-id='error-notification'] .notification__content");

    private static void fillForm(User user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
    }

    public static VerificationPage getVerificationPage(User user) {
        fillForm(user);
        loginButton.click();
        return new VerificationPage();
    }

    public static void isLoginError() {
        notificationError.shouldBe(Condition.visible);
        notificationErrorTitle.shouldHave(Condition.text("Ошибка"));
        notificationErrorContent.shouldHave(Condition.text("Ошибка! \nНеверно указан логин или пароль"));
    }
}