package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.TransferPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;

public class TransferTest {

    DataHelper user;
    DashboardPage dashboardPage;

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");

        LoginPage login = new LoginPage();
        user = new DataHelper();
        VerificationPage verificationPage = login.validLogin(user);
        dashboardPage = verificationPage.validVerify(user);
    }

    @Test
    public void shouldTransfer100() {

        TransferPage transferPage = dashboardPage.chooseCardTo(0);
        transferPage.transfer(user, 100, 1);
        dashboardPage.assertBalance(0, 10_100);
        dashboardPage.assertBalance(1, 9_900);

        dashboardPage.chooseCardTo(1);
        transferPage.transfer(user, 100, 0);
        dashboardPage.assertBalance(0, 10_000);
        dashboardPage.assertBalance(1, 10_000);
    }
}