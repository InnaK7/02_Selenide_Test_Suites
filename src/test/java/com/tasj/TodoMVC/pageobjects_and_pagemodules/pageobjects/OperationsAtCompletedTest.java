package com.tasj.TodoMVC.pageobjects_and_pagemodules.pageobjects;

import com.tasj.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages.TodoMVCPage;
import org.junit.Test;
import com.tasj.TodoMVC.BaseTest;

public class OperationsAtCompletedTest extends BaseTest {

    TodoMVCPage page = new TodoMVCPage();

    @Test
    public void testAddAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.ACTIVE));

        page.add("b");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(2);
    }

    @Test
    public void testEditAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED),
                page.aTask("b", TodoMVCPage.TaskType.ACTIVE));

        page.startEditing("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED));

        page.delete("a");
        page.assertNoVisibleTasks();
    }

    @Test
    public void testCompleteAllAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.ACTIVE),
                page.aTask("b", TodoMVCPage.TaskType.ACTIVE),
                page.aTask("c", TodoMVCPage.TaskType.COMPLETED));

        page.toggleAll();
        page.assertVisibleTasks("a", "b", "c");
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED),
                page.aTask("b", TodoMVCPage.TaskType.COMPLETED));

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testReopenAllAtCompleted() {
        page.givenAtCompleted(
                page.aTask("b", TodoMVCPage.TaskType.COMPLETED),
                page.aTask("c", TodoMVCPage.TaskType.COMPLETED));

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED));

        page.startEditing("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditWithTabAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED));

        page.startEditing("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditClickOutsideAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED),
                page.aTask("b", TodoMVCPage.TaskType.ACTIVE));

        page.startEditing("a", "a edited");
        page.newTask.click();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDeleteClearNameAtCompleted() {
        page.givenAtCompleted(
                page.aTask("a", TodoMVCPage.TaskType.COMPLETED),
                page.aTask("b", TodoMVCPage.TaskType.ACTIVE));

        page.startEditing("a", "").pressEnter();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }
}
