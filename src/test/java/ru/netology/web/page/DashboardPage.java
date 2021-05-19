package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private SelenideElement changeFirstCard = $$("[data-test-id=action-deposit]").first();
    private SelenideElement changeSecondCard = $$("[data-test-id=action-deposit]").last();
    private SelenideElement firstBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage(){
        heading.shouldHave(visible);
    }
    public TransferPage firstCard (){
        changeFirstCard.click();
        return new TransferPage();
    }
    public TransferPage secondCard (){
        changeSecondCard.click();
        return new TransferPage();
    }
    public int getCurrentFirstCardBalance() {
        val text = firstBalance.text();
        return extractBalance(text);
    }
    public int getCurrentSecondCardBalance() {
        val text = secondBalance.text();
        return extractBalance(text);
    }
    private int extractBalance(String text){
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
