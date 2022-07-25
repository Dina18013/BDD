package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.TransferPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @BeforeEach
    public void auth() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode= DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }


    @Test
    public void shouldTransfer300To() {
        DashboardPage dashboardPage = new DashboardPage();
        TransferPage transferPage = dashboardPage.chooseCardTo(0);

        transferPage.transfer(300, "5559 0000 0000 0002");

        int cardToBalance = dashboardPage.getFirstCardBalance();
        int cardFromBalance = dashboardPage.getSecondCardBalance();

        int cardToExpectedBalance = 10300;
        int cardFromExpectedBalance = 9700;

        assertEquals(cardToExpectedBalance, cardToBalance);
        assertEquals(cardFromExpectedBalance, cardFromBalance);
    }


    @Test
    public void shouldTransfer300Back() {
        DashboardPage dashboardPage = new DashboardPage();
        TransferPage transferPage = dashboardPage.chooseCardTo(0);

        transferPage.transfer(300, "5559 0000 0000 0001");

        int cardToBalance = dashboardPage.getFirstCardBalance();
        int cardFromBalance = dashboardPage.getSecondCardBalance();

        int cardToExpectedBalance = 1000;
        int cardFromExpectedBalance = 1000;

        assertEquals(cardToExpectedBalance, cardToBalance);
        assertEquals(cardFromExpectedBalance, cardFromBalance);
    }
}