package ru.netology.page;

import ru.netology.data.User;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement loginFieldError = $("[data-test-id='login'] .input__sub");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement passwordFieldError = $("[data-test-id='password'] .input__sub");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");

    private final SelenideElement notificationError = $("[data-test-id='error-notification']");
    private final SelenideElement notificationErrorTitle = $("[data-test-id='error-notification'] .notification__title");
    private final SelenideElement notificationErrorContent = $("[data-test-id='error-notification'] .notification__content");

    private void fillForm(User user) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        loginField.sendKeys(Keys.DELETE);
        loginField.setValue(user.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        passwordField.sendKeys(Keys.DELETE);
        passwordField.setValue(user.getPassword());
    }

    public VerificationPage getVerificationPage(User user) {
        fillForm(user);
        loginButton.click();
        return new VerificationPage();
    }

    public void isLoginError() {
        notificationError.shouldBe(Condition.visible);
        notificationErrorTitle.shouldHave(Condition.text("Ошибка"));
        notificationErrorContent.shouldHave(Condition.text("Ошибка! \nНеверно указан логин или пароль"));
    }
}