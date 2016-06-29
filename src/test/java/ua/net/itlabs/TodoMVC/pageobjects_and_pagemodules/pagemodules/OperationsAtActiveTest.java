package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;

import static ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules.pages.TodoMVC.*;

public class OperationsAtActiveTest extends BaseTest {

    @Test
    public void testAddAtActive() {
        givenAtActive(TaskType.ACTIVE, "a");

        add("b");
        assertVisibleTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        delete("a");
        assertVisibleTasks("b");
    }

    @Test
    public void testCompleteAllAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        toggleAll();
        assertNoVisibleTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtActive() {
        givenAtActive(
                aTask("a", TaskType.ACTIVE),
                aTask("b", TaskType.COMPLETED));

        clearCompleted();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtActive() {
        givenAtActive(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.COMPLETED));


        toggleAll();
        assertVisibleTasks("a", "b");
        assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtActive() {
        givenAtActive(TaskType.ACTIVE, "a");

        startEditing("a", "a edited").pressEscape();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditWithTabAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        startEditing("a", "a edited").pressTab();
        assertVisibleTasks("a edited", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditClickOutsideAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        startEditing("a", "a edited");
        newTask.click();
        assertVisibleTasks("a edited", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteClearNameAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        startEditing("a", "").pressEnter();
        assertVisibleTasks("b");
        assertItemsLeft(1);
    }
}
