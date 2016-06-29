package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;
import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage;
import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage.*;

public class GeneralTest extends BaseTest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testTasksLifeCycle() {

        page.givenAtAll();
        page.add("a");
        //complete
        page.toggle("a");

        page.filterActive();
        page.assertItemsLeft(0);
        page.assertNoVisibleTasks();

        page.filterCompleted();
        //reopen
        page.toggle("a");
        page.assertNoVisibleTasks();

        page.filterActive();
        page.startEditing("a", "a edited").pressEnter();
        //complete
        page.toggle("a edited");
        page.assertNoVisibleTasks();

        page.filterAll();
        page.delete("a edited");
        page.assertNoTasks();
    }

    @Test
    public void testFilteringFromCompletedToAll() {
        page.givenAtCompleted(
                page.aTask("a", TaskType.COMPLETED),
                page.aTask("b", TaskType.ACTIVE));

        page.filterAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testFilteringFromAllToCompleted() {
        page.givenAtAll(
                page.aTask("a", TaskType.COMPLETED),
                page.aTask("b", TaskType.ACTIVE));

        page.filterCompleted();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }
}
