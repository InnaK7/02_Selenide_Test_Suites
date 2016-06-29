package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;

import static ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules.pages.TodoMVC.*;

public class GeneralTest extends BaseTest {

    @Test
    public void testTasksLifeCycle() {

        givenAtAll();
        add("a");
        //complete
        toggle("a");

        filterActive();
        assertItemsLeft(0);
        assertNoVisibleTasks();

        filterCompleted();
        //reopen
        toggle("a");
        assertNoVisibleTasks();

        filterActive();
        startEditing("a", "a edited").pressEnter();
        //complete
        toggle("a edited");
        assertNoVisibleTasks();

        filterAll();
        delete("a edited");
        assertNoTasks();
    }

    @Test
    public void testFilteringFromCompletedToAll() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        filterAll();
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testFilteringFromAllToCompleted() {
        givenAtAll(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        filterCompleted();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }
}
