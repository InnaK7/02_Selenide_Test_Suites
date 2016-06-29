package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;

import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage;
import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage.*;

public class OperationsAtActiveTest extends BaseTest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testAddAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a");

        page.add("b");
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a", "b");

        page.delete("a");
        page.assertVisibleTasks("b");
    }

    @Test
    public void testCompleteAllAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a", "b");

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtActive() {
        page.givenAtActive(
                page.aTask("a", TaskType.ACTIVE),
                page.aTask("b", TaskType.COMPLETED));

        page.clearCompleted();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtActive() {
        page.givenAtActive(
                page.aTask("a", TaskType.COMPLETED),
                page.aTask("b", TaskType.COMPLETED));


        page.toggleAll();
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a");

        page.startEditing("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditWithTabAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a", "b");

        page.startEditing("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditClickOutsideAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a", "b");

        page.startEditing("a", "a edited");
        page.newTask.click();
        page.assertVisibleTasks("a edited", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testDeleteClearNameAtActive() {
        page.givenAtActive(TaskType.ACTIVE, "a", "b");

        page.startEditing("a", "").pressEnter();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }
}
