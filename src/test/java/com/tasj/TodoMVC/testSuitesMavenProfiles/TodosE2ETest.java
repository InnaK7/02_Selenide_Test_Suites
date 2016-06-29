package com.tasj.TodoMVC.testSuitesMavenProfiles;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import com.tasj.TodoMVC.BaseTest;
import com.tasj.TodoMVC.testSuitesMavenProfiles.categories.Smoke;

import static com.tasj.TodoMVC.testSuitesMavenProfiles.pages.TodoMVC.*;

public class TodosE2ETest extends BaseTest {

    @Category(Smoke.class)
    @Test
    public void testTasksLifeCycle() {

        givenAtAll();
        add("a");
        startEditing("a", "a edited").pressEnter();
        toggle("a edited");

        filterActive();
        assertItemsLeft(0);
        assertNoVisibleTasks();

        filterCompleted();
        assertVisibleTasks("a edited");

        filterAll();
        delete("a edited");
        assertNoTasks();
    }
}
