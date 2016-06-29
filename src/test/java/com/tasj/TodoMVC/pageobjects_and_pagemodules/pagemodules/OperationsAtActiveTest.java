package com.tasj.TodoMVC.pageobjects_and_pagemodules.pagemodules;

import com.tasj.TodoMVC.BaseTest;
import com.tasj.TodoMVC.pageobjects_and_pagemodules.pagemodules.pages.TodoMVC;
import org.junit.Test;

public class OperationsAtActiveTest extends BaseTest {

    @Test
    public void testAddAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a");

        TodoMVC.add("b");
        TodoMVC.assertVisibleTasks("a", "b");
        TodoMVC.assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.delete("a");
        TodoMVC.assertVisibleTasks("b");
    }

    @Test
    public void testCompleteAllAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.toggleAll();
        TodoMVC.assertNoVisibleTasks();
        TodoMVC.assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtActive() {
        TodoMVC.givenAtActive(
                TodoMVC.aTask("a", TodoMVC.TaskType.ACTIVE),
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED));

        TodoMVC.clearCompleted();
        TodoMVC.assertVisibleTasks("a");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtActive() {
        TodoMVC.givenAtActive(
                TodoMVC.aTask("a", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED));


        TodoMVC.toggleAll();
        TodoMVC.assertVisibleTasks("a", "b");
        TodoMVC.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a");

        TodoMVC.startEditing("a", "a edited").pressEscape();
        TodoMVC.assertVisibleTasks("a");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditWithTabAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.startEditing("a", "a edited").pressTab();
        TodoMVC.assertVisibleTasks("a edited", "b");
        TodoMVC.assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditClickOutsideAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.startEditing("a", "a edited");
        TodoMVC.newTask.click();
        TodoMVC.assertVisibleTasks("a edited", "b");
        TodoMVC.assertItemsLeft(2);
    }

    @Test
    public void testDeleteClearNameAtActive() {
        TodoMVC.givenAtActive(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.startEditing("a", "").pressEnter();
        TodoMVC.assertVisibleTasks("b");
        TodoMVC.assertItemsLeft(1);
    }
}
