package com.tasj.TodoMVC.pageobjects_and_pagemodules.pagemodules;

import com.tasj.TodoMVC.pageobjects_and_pagemodules.pagemodules.pages.TodoMVC;
import org.junit.Test;
import com.tasj.TodoMVC.BaseTest;

public class OperationsAtCompletedTest extends BaseTest {

    @Test
    public void testAddAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.ACTIVE));

        TodoMVC.add("b");
        TodoMVC.assertNoVisibleTasks();
        TodoMVC.assertItemsLeft(2);
    }

    @Test
    public void testEditAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("b", TodoMVC.TaskType.ACTIVE));

        TodoMVC.startEditing("a", "a edited").pressEnter();
        TodoMVC.assertVisibleTasks("a edited");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testDeleteAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED));

        TodoMVC.delete("a");
        TodoMVC.assertNoVisibleTasks();
    }

    @Test
    public void testCompleteAllAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.ACTIVE),
                TodoMVC.aTask("b", TodoMVC.TaskType.ACTIVE),
                TodoMVC.aTask("c", TodoMVC.TaskType.COMPLETED));

        TodoMVC.toggleAll();
        TodoMVC.assertVisibleTasks("a", "b", "c");
        TodoMVC.assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED));

        TodoMVC.clearCompleted();
        TodoMVC.assertNoVisibleTasks();
    }

    @Test
    public void testReopenAllAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("c", TodoMVC.TaskType.COMPLETED));

        TodoMVC.toggleAll();
        TodoMVC.assertNoVisibleTasks();
        TodoMVC.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED));

        TodoMVC.startEditing("a", "a edited").pressEscape();
        TodoMVC.assertVisibleTasks("a");
        TodoMVC.assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditWithTabAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED));

        TodoMVC.startEditing("a", "a edited").pressTab();
        TodoMVC.assertVisibleTasks("a edited");
        TodoMVC.assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditClickOutsideAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("b", TodoMVC.TaskType.ACTIVE));

        TodoMVC.startEditing("a", "a edited");
        TodoMVC.newTask.click();
        TodoMVC.assertVisibleTasks("a edited");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testDeleteClearNameAtCompleted() {
        TodoMVC.givenAtCompleted(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("b", TodoMVC.TaskType.ACTIVE));

        TodoMVC.startEditing("a", "").pressEnter();
        TodoMVC.assertNoVisibleTasks();
        TodoMVC.assertItemsLeft(1);
    }
}
