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

    DataHelper authInfo;
    DashboardPage dashboardPage;


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
    public void shouldTransferFromSecondToFirstCard() {
        DashboardPage dashboardPage = new DashboardPage();
        TransferPage transferPage = dashboardPage.chooseCardTo(0);

        transferPage.transfer(authInfo, 3_000, 1);

        dashboardPage.assertBalance(0, 13_000);
        dashboardPage.assertBalance(1, 7_000);

        dashboardPage.chooseCardTo(1);
        transferPage.transfer(authInfo, 3_000, 0);

        dashboardPage.assertBalance(0, 10_000);
        dashboardPage.assertBalance(1, 10_000);
    }

    @Test
    public void shouldTransferFromFirstToSecondCard() {
        DashboardPage dashboardPage = new DashboardPage();
        TransferPage transferPage = dashboardPage.chooseCardTo(1);

        transferPage.transfer(authInfo, 3_000, 0);

        dashboardPage.assertBalance(1, 13_000);
        dashboardPage.assertBalance(0, 7_000);

        dashboardPage.chooseCardTo(0);
        transferPage.transfer(authInfo, 3_000, 1);

        dashboardPage.assertBalance(1, 10_000);
        dashboardPage.assertBalance(0, 10_000);
    }
}

