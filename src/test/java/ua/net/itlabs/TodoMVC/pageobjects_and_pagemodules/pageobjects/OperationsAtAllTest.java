package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;

import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage;
import ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage.*;

public class OperationsAtAllTest extends BaseTest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testEditAtAll() {
        page.givenAtAll(TaskType.ACTIVE, "a");

        page.startEditing("a", "a edited").pressEnter();
        page.assertTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAllAtAll() {
        page.givenAtAll(TaskType.ACTIVE, "a", "b");

        page.toggleAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtAll() {
        page.givenAtAll(
                page.aTask("a", TaskType.ACTIVE),
                page.aTask("b", TaskType.COMPLETED),
                page.aTask("c", TaskType.COMPLETED));

        page.clearCompleted();
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAtAll() {
        page.givenAtAll(TaskType.COMPLETED, "a");

        page.toggle("a");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        page.givenAtAll(TaskType.COMPLETED, "a", "b");

        page.toggleAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtAll() {
        page.givenAtAll(TaskType.ACTIVE, "a", "b");

        page.startEditing("a", "a edited").pressEscape();
        page.assertTasks("a", "b");
        page.assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditWithTabAtAll() {
        page.givenAtAll(
                page.aTask("a", TaskType.ACTIVE),
                page.aTask("b", TaskType.COMPLETED));

        page.startEditing("a", "a edited").pressTab();
        page.assertTasks("a edited", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditClickOutsideAtAll() {
        page.givenAtAll(TaskType.ACTIVE, "a");

        page.startEditing("a", "a edited");
        page.newTask.click();
        page.assertTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteClearNameAtAll() {
        page.givenAtAll(TaskType.ACTIVE, "a");

        page.startEditing("a", "").pressEnter();
        page.assertNoTasks();
    }
}
