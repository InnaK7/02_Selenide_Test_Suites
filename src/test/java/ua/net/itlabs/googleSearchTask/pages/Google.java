package ua.net.itlabs.googleSearchTask.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Google {
    public static SelenideElement search = $(By.name("q"));
    public static ElementsCollection results = $$(".srg .g");

    public static void visit() {
        open("http://google.com/ncr");
    }

    public static void search(String searchText) {
        search.setValue(searchText).pressEnter();
    }

    public static void assertCountResults(int count) {
        results.shouldHave(size(count));
    }

    public static void assertResultText(int index, String resultText) {
        results.get(index).shouldHave(text(resultText));
    }

    public static void followResultLink(int index) {
        results.get(index).find(".r").click();
    }

    public static void assertSeleniumPage() {
        $(".downloadBox a").shouldHave(exactText("Download Selenium"));
        assert(url().equals("http://www.seleniumhq.org/"));
    }
}
