package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @Test
    void shouldTransferOnFirstCard() {
        int amount = 5000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val initialFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val initialSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val changeFirstCard = dashboardPage.firstCard();
        changeFirstCard.dataFilling(DataHelper.getSecondCardInfo(), amount);
        val balanceAfterTransferFirstCard = TransferPage.balancePlusAmount(initialFirstCardBalance, amount);
        val balanceAfterTransferSecondCard = TransferPage.balanceMinusAmount(initialSecondCardBalance, amount);
        val currentFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val currentSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        assertEquals(balanceAfterTransferFirstCard, currentFirstCardBalance);
        assertEquals(balanceAfterTransferSecondCard, currentSecondCardBalance);


    }
    @Test
    void shouldTransferOnSecondCard() {
        int amount = 5000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val initialFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val initialSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val changeSecondCard = dashboardPage.secondCard();
        changeSecondCard.dataFilling(DataHelper.getFirstCardInfo(), amount);
        val balanceAfterTransferSecondCard = TransferPage.balancePlusAmount(initialSecondCardBalance, amount);
        val balanceAfterTransferFirstCard = TransferPage.balanceMinusAmount(initialFirstCardBalance, amount);
        val currentSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val currentFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        assertEquals(balanceAfterTransferFirstCard, currentFirstCardBalance);
        assertEquals(balanceAfterTransferSecondCard, currentSecondCardBalance);


    }

}