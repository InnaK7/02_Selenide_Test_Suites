package ua.net.itlabs.gmailSendAndSearchTask;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import ua.net.itlabs.gmailSendAndSearchTask.pages.Gmail;
import ua.net.itlabs.gmailSendAndSearchTask.pages.Mails;
import ua.net.itlabs.gmailSendAndSearchTask.pages.Menu;

import java.time.LocalTime;
import java.util.Date;


public class GmailTest {

    {
        Configuration.timeout = 15000;
    }

    @Test
    public void testLoginAndSendSearchEmail() {

        Gmail.visit();
        Gmail.logIn(TestData.EMAIL, TestData.PASSWORD);

        String subject = "test" + LocalTime.now();

        Mails.send(TestData.EMAIL, subject);
        Menu.refresh();

        Mails.assertNthMail(0, subject);

        Menu.goToSent();
        Mails.assertNthMail(0, subject);

        Menu.goToInbox();

        Mails.searchBySubject(subject);

        Mails.assertMails(subject);

    }
}
