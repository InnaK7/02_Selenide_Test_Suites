package com.tasj.googleSearchTask;

import org.junit.Test;
import com.tasj.googleSearchTask.pages.Google;

public class GoogleSearchTest {

    @Test
    public void testSearchAndFollowLink() {

        Google.visit();
        search("Selenium automates browsers");

        assertCountResults(10);
        assertResultText(0, "Selenium automates browsers");

        followResultLink(0);
        assertSeleniumPage();
    }
}
