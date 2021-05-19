package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement replenishment = $("h1");
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement numberOfCardField = $("[data-test-id='from'] input");
    private SelenideElement actionTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement actionCancel = $("[data-test-id='action-cancel']");

    public TransferPage() {
        replenishment.shouldHave(visible);
    }

    public TransferPage dataFilling (DataHelper.CardInfo info, int amountToTransfer) {
        amountField.setValue(String.valueOf(amountToTransfer));
        numberOfCardField.clear();
        numberOfCardField.setValue(info.getCardNumber());
        actionTransfer.click();
        return new TransferPage();
    }
    public TransferPage cancelDataFilling () {
        actionCancel.click();
        return new TransferPage();
    }
    public static int balanceMinusAmount(int balance, int amount) {
        int balanceMinus = balance - amount;
        if (balanceMinus < 0) {
            return balance;

        }
        return balanceMinus;
    }
    public static int balancePlusAmount(int balance, int amount){
        return balance + amount;
    }
}