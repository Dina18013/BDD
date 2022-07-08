package ru.netology.page;

import ru.netology.data.DataHelper;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement fieldLogin = $("[data-test-id='login'] input");
    private SelenideElement fieldPassword = $("[data-test-id='password'] input");
    private SelenideElement continueButton = $("[data-test-id='action-login'] input");

    public VerificationPage validLogin(DataHelper user) {
        fieldLogin.setValue(user.getLogin());
        fieldPassword.setValue(user.getPassword());
        continueButton.click();
        return new VerificationPage();
    }
}
