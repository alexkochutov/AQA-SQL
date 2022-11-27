package ru.netology.page;

import lombok.NoArgsConstructor;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class VerificationPage {
    private static SelenideElement codeField = $("[data-test-id='code'] input");
    private static SelenideElement codeFieldError = $("[data-test-id='code'] .input__sub");
    private static SelenideElement verifyButton = $("[data-test-id='action-verify']");

    private static final SelenideElement notificationError = $("[data-test-id='error-notification']");
    private static final SelenideElement notificationErrorTitle = $("[data-test-id='error-notification'] .notification__title");
    private static final SelenideElement notificationErrorContent = $("[data-test-id='error-notification'] .notification__content");

    private void fillForm(String code) {
        codeField.setValue(code);
    }

    public DashboardPage getDashboardPage(String code) {
        fillForm(code);
        verifyButton.click();
        return new DashboardPage();
    }

    public void isVerificationError() {
        notificationError.shouldBe(Condition.visible);
        notificationErrorTitle.shouldHave(Condition.text("Ошибка"));
        notificationErrorContent.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}