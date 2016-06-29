package ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules;

import org.junit.Test;
import ua.net.itlabs.TodoMVC.BaseTest;

import static ua.net.itlabs.TodoMVC.pageobjects_and_pagemodules.pagemodules.pages.TodoMVC.*;

public class OperationsAtCompletedTest extends BaseTest {

    @Test
    public void testAddAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.ACTIVE));

        add("b");
        assertNoVisibleTasks();
        assertItemsLeft(2);
    }

    @Test
    public void testEditAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        startEditing("a", "a edited").pressEnter();
        assertVisibleTasks("a edited");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        delete("a");
        assertNoVisibleTasks();
    }

    @Test
    public void testCompleteAllAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.ACTIVE),
                aTask("b", TaskType.ACTIVE),
                aTask("c", TaskType.COMPLETED));

        toggleAll();
        assertVisibleTasks("a", "b", "c");
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.COMPLETED));

        clearCompleted();
        assertNoVisibleTasks();
    }

    @Test
    public void testReopenAllAtCompleted() {
        givenAtCompleted(
                aTask("b", TaskType.COMPLETED),
                aTask("c", TaskType.COMPLETED));

        toggleAll();
        assertNoVisibleTasks();
        assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        startEditing("a", "a edited").pressEscape();
        assertVisibleTasks("a");
        assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditWithTabAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        startEditing("a", "a edited").pressTab();
        assertVisibleTasks("a edited");
        assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditClickOutsideAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        startEditing("a", "a edited");
        newTask.click();
        assertVisibleTasks("a edited");
        assertItemsLeft(1);
    }

    @Test
    public void testDeleteClearNameAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        startEditing("a", "").pressEnter();
        assertNoVisibleTasks();
        assertItemsLeft(1);
    }
}
