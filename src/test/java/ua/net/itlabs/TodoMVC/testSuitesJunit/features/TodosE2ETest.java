package ua.net.itlabs.TodoMVC.testSuitesJunit.features;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.net.itlabs.TodoMVC.BaseTest;
import ua.net.itlabs.TodoMVC.testSuitesJunit.categories.Smoke;

import static ua.net.itlabs.TodoMVC.testSuitesJunit.pages.TodoMVC.*;

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
