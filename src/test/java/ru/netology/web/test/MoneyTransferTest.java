package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

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
        val firstCardBalanceAfterTransfer = dashboardPage.getCurrentFirstCardBalance();
        val secondCardBalanceAfterTransfer = dashboardPage.getCurrentSecondCardBalance();
        assertEquals(firstCardBalanceAfterTransfer, initialFirstCardBalance + amount);
        assertEquals(secondCardBalanceAfterTransfer, initialSecondCardBalance - amount);


    }

    @Test
    void shouldTransferOnSecondCard() {
        int amount = 2000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val initialFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val initialSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val changeSecondCard = dashboardPage.secondCard();
        changeSecondCard.dataFilling(DataHelper.getFirstCardInfo(), amount);
        val secondCardBalanceAfterTransfer = dashboardPage.getCurrentSecondCardBalance();
        val firstCardBalanceAfterTransfer = dashboardPage.getCurrentFirstCardBalance();
        assertEquals(firstCardBalanceAfterTransfer, initialFirstCardBalance - amount);
        assertEquals(secondCardBalanceAfterTransfer, initialSecondCardBalance + amount);


    }
    @Test
    void shouldTransferCancel() {
        int amount = 500;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val initialFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val initialSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val changeFirstCard = dashboardPage.firstCard();
        changeFirstCard.cancelDataFilling();
        val firstCardBalanceAfterCancel = dashboardPage.getCurrentFirstCardBalance();
        val secondCardBalanceAfterCancel = dashboardPage.getCurrentSecondCardBalance();
        assertEquals(firstCardBalanceAfterCancel, initialFirstCardBalance);
        assertEquals(secondCardBalanceAfterCancel, initialSecondCardBalance);

    }

    @Test
    void shouldTransferUnderOverAmount() {
        int amount = 20000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val initialFirstCardBalance = dashboardPage.getCurrentFirstCardBalance();
        val initialSecondCardBalance = dashboardPage.getCurrentSecondCardBalance();
        val changeFirstCard = dashboardPage.firstCard();
        changeFirstCard.dataFilling(DataHelper.getSecondCardInfo(), amount);
        val firstCardBalanceAfterTransfer = dashboardPage.getCurrentFirstCardBalance();
        val secondCardBalanceAfterTransfer = dashboardPage.getCurrentSecondCardBalance();
        assertEquals(firstCardBalanceAfterTransfer, initialFirstCardBalance + amount);
        assertEquals(secondCardBalanceAfterTransfer, initialSecondCardBalance - amount);

    }




}