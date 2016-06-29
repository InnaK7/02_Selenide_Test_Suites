package com.tasj.TodoMVC.testSuitesJunit.features;

import com.tasj.TodoMVC.testSuitesJunit.pages.TodoMVC;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import com.tasj.TodoMVC.BaseTest;
import com.tasj.TodoMVC.testSuitesJunit.categories.Smoke;

public class TodosE2ETest extends BaseTest {

    @Category(Smoke.class)
    @Test
    public void testTasksLifeCycle() {

        TodoMVC.givenAtAll();
        TodoMVC.add("a");
        TodoMVC.startEditing("a", "a edited").pressEnter();
        TodoMVC.toggle("a edited");

        TodoMVC.filterActive();
        TodoMVC.assertItemsLeft(0);
        TodoMVC.assertNoVisibleTasks();

        TodoMVC.filterCompleted();
        TodoMVC.assertVisibleTasks("a edited");

        TodoMVC.filterAll();
        TodoMVC.delete("a edited");
        TodoMVC.assertNoTasks();
    }
}
