package com.tasj.gmailSendAndSearchTask;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import com.tasj.gmailSendAndSearchTask.pages.Gmail;
import com.tasj.gmailSendAndSearchTask.pages.Mails;
import com.tasj.gmailSendAndSearchTask.pages.Menu;

import java.time.LocalTime;


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
