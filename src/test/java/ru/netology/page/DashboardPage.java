package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item div");
    private ElementsCollection actionButton = $$("[data-test-id='action-deposit'] .button__text");
    private SelenideElement refreshButton = $("[data-test-id='action-reload'] .button__text");


       public TransferPage chooseCardTo(int indexCardTo) {
        actionButton.get(indexCardTo).click();
        return new TransferPage();
    }

    public int getBalance(int index) {
        refreshButton.click();
        String[] card = cards.get(index).toString().split("");
        int balance = valueOf(card[6]);
        return balance;
    }

    public void assertBalance(int index, int expectedBalance) {
        int actualBalance = getBalance(index);
        assertEquals(expectedBalance, actualBalance);
    }
}