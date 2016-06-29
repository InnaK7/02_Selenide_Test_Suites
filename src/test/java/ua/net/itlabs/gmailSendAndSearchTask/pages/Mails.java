package ua.net.itlabs.gmailSendAndSearchTask.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Mails {

    public static ElementsCollection emails = $$("[role='main'] .zA");

    public static void send(String to, String emailSubject) {
        $(byText("COMPOSE")).click();
        $(By.name("to")).setValue(to).pressEnter();
        $(By.name("subjectbox")).setValue(emailSubject);
        $(byText("Send")).click();

    }

    public static void assertNthMail(int index, String subject) {
        emails.get(index).$$(".y6").get(0).shouldHave(exactText(subject));
    }

    public static void assertMails(String... subjects) {
        emails.shouldHave(texts(subjects));

    }

    public static void searchBySubject(String subject) {
        $(By.name("q")).setValue("subject:" + subject).pressEnter();
    }

}
