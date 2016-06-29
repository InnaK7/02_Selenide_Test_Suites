package com.tasj.gmailSendAndSearchTask.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Menu {

    public static void goToInbox() {
        $("a[title^='Inbox']").click();
    }

    public static void goToSent() {
        $(byText("Sent Mail")).click();
    }

    public static void refresh() {
        $(".nu").click();
    }
}
