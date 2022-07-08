package ru.netology.page;

import ru.netology.data.DataHelper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyContinueButton = $("[data-test-id='action-verify']");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(DataHelper user) {
        codeField.setValue(user.getVerificationCode());
        verifyContinueButton.click();
        return new DashboardPage();
    }
}
