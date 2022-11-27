package ru.netology.page;

import lombok.NoArgsConstructor;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class DashboardPage {
    private static final SelenideElement heading = $("[data-test-id='dashboard']");

    public void isVisible() {
        heading.shouldBe(Condition.visible);
    }
}