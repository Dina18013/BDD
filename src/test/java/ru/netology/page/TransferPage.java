package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

    private SelenideElement sum = $x(".//span[@data-test-id='amount']//input");
    private SelenideElement from = $x(".//span[@data-test-id='from']//input");
    private SelenideElement topUp = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public void transfer(int amount, String cardFrom) {
        sum.setValue(String.valueOf(amount));
        from.setValue(cardFrom);
        topUp.click();
    }
}
