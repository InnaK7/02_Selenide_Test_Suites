package com.tasj.TodoMVC.testSuitesMavenProfiles;

import com.tasj.TodoMVC.BaseTest;
import com.tasj.TodoMVC.testSuitesMavenProfiles.categories.Buggy;
import com.tasj.TodoMVC.testSuitesMavenProfiles.categories.Smoke;
import com.tasj.TodoMVC.testSuitesMavenProfiles.pages.TodoMVC;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TodosOperationsAtAllTest extends BaseTest {

    @Category({Buggy.class, Smoke.class})
    @Test
    public void testEditAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.ACTIVE, "a");

        TodoMVC.startEditing("a", "a edited").pressEnter();
        TodoMVC.assertTasks("a edited");
        TodoMVC.assertItemsLeft(2);
    }

    @Category(Smoke.class)
    @Test
    public void testCompleteAllAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.toggleAll();
        TodoMVC.assertTasks("a", "b");
        TodoMVC.assertItemsLeft(0);
    }

    @Category(Smoke.class)
    @Test
    public void testClearCompletedAtAll() {
        TodoMVC.givenAtAll(
                TodoMVC.aTask("a", TodoMVC.TaskType.ACTIVE),
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED),
                TodoMVC.aTask("c", TodoMVC.TaskType.COMPLETED));

        TodoMVC.clearCompleted();
        TodoMVC.assertTasks("a");
        TodoMVC.assertItemsLeft(1);
    }

    @Category(Smoke.class)
    @Test
    public void testReopenAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.COMPLETED, "a");

        TodoMVC.toggle("a");
        TodoMVC.assertTasks("a");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.COMPLETED, "a", "b");

        TodoMVC.toggleAll();
        TodoMVC.assertTasks("a", "b");
        TodoMVC.assertItemsLeft(2);
    }

    //Additional Edit Operations
    @Test
    public void testCancelEditAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.ACTIVE, "a", "b");

        TodoMVC.startEditing("a", "a edited").pressEscape();
        TodoMVC.assertTasks("a", "b");
        TodoMVC.assertItemsLeft(2);
    }

    @Test
    public void testConfirmEditWithTabAtAll() {
        TodoMVC.givenAtAll(
                TodoMVC.aTask("a", TodoMVC.TaskType.ACTIVE),
                TodoMVC.aTask("b", TodoMVC.TaskType.COMPLETED));

        TodoMVC.startEditing("a", "a edited").pressTab();
        TodoMVC.assertTasks("a edited", "b");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditClickOutsideAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.ACTIVE, "a");

        TodoMVC.startEditing("a", "a edited");
        TodoMVC.newTask.click();
        TodoMVC.assertTasks("a edited");
        TodoMVC.assertItemsLeft(1);
    }

    @Test
    public void testDeleteClearNameAtAll() {
        TodoMVC.givenAtAll(TodoMVC.TaskType.ACTIVE, "a");

        TodoMVC.startEditing("a", "").pressEnter();
        TodoMVC.assertNoTasks();
    }


}
