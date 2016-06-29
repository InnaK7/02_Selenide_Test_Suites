package com.tasj.TodoMVC.fullCoverageWithFT;

import com.codeborne.selenide.*;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import com.tasj.TodoMVC.BaseTest;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TodoMVCTest extends BaseTest {

    @Test
    public void testTasksLifeCycle() {

        givenAtAll();
        add("a");
        //complete
        toggle("a");

        filterActive();
        assertItemsLeft(0);
        assertNoVisibleTasks();

        filterCompleted();
        //reopen
        toggle("a");
        assertNoVisibleTasks();

        filterActive();
        startEditing("a", "a edited").pressEnter();
        //complete
        toggle("a edited");
        assertNoVisibleTasks();

        filterAll();
        delete("a edited");
        assertNoTasks();
    }

    @Test
    public void testAddAtActive() {
        givenAtActive(TaskType.ACTIVE, "a");

        add("b");
        assertVisibleTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testAddAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.ACTIVE));

        add("b");
        assertNoVisibleTasks();
        assertItemsLeft(2);
    }

    @Test
    public void testEditAtAll() {
        givenAtAll(TaskType.ACTIVE, "a");

        startEditing("a", "a edited").pressEnter();
        assertTasks("a edited");
        assertItemsLeft(1);
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
    public void testDeleteAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        delete("a");
        assertVisibleTasks("b");
    }

    @Test
    public void testDeleteAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        delete("a");
        assertNoVisibleTasks();
    }

    @Test
    public void testCompleteAllAtAll() {
        givenAtAll(TaskType.ACTIVE, "a", "b");

        toggleAll();
        assertTasks("a", "b");
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        toggleAll();
        assertNoVisibleTasks();
        assertItemsLeft(0);
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
    public void testClearCompletedAtAll() {
        givenAtAll(
                aTask("a", TaskType.ACTIVE),
                aTask("b", TaskType.COMPLETED),
                aTask("c", TaskType.COMPLETED));

        clearCompleted();
        assertTasks("a");
        assertItemsLeft(1);
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
    public void testClearCompletedAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.COMPLETED));

        clearCompleted();
        assertNoVisibleTasks();
    }

    @Test
    public void testReopenAtAll() {
        givenAtAll(TaskType.COMPLETED, "a");

        toggle("a");
        assertTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testReopenAllAtAll() {
        givenAtAll(TaskType.COMPLETED, "a", "b");

        toggleAll();
        assertTasks("a", "b");
        assertItemsLeft(2);
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
    public void testCancelEditAtAll() {
        givenAtAll(TaskType.ACTIVE, "a", "b");

        startEditing("a", "a edited").pressEscape();
        assertTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtActive() {
        givenAtActive(TaskType.ACTIVE, "a");

        startEditing("a", "a edited").pressEscape();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelEditAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        startEditing("a", "a edited").pressEscape();
        assertVisibleTasks("a");
        assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditWithTabAtAll() {
        givenAtAll(
                aTask("a", TaskType.ACTIVE),
                aTask("b", TaskType.COMPLETED));

        startEditing("a", "a edited").pressTab();
        assertTasks("a edited", "b");
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
    public void testConfirmEditWithTabAtCompleted() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED));

        startEditing("a", "a edited").pressTab();
        assertVisibleTasks("a edited");
        assertItemsLeft(0);
    }

    @Test
    public void testConfirmEditClickOutsideAtAll() {
        givenAtAll(TaskType.ACTIVE, "a");

        startEditing("a", "a edited");
        newTask.click();
        assertTasks("a edited");
        assertItemsLeft(1);
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
    public void testDeleteClearNameAtAll() {
        givenAtAll(TaskType.ACTIVE, "a");

        startEditing("a", "").pressEnter();
        assertNoTasks();
    }

    @Test
    public void testDeleteClearNameAtActive() {
        givenAtActive(TaskType.ACTIVE, "a", "b");

        startEditing("a", "").pressEnter();
        assertVisibleTasks("b");
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

    @Test
    public void testFilteringFromCompletedToAll() {
        givenAtCompleted(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        filterAll();
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testFilteringFromAllToCompleted() {
        givenAtAll(
                aTask("a", TaskType.COMPLETED),
                aTask("b", TaskType.ACTIVE));

        filterCompleted();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }


    ElementsCollection tasks = $$("#todo-list>li");
    SelenideElement newTask = $("#new-todo");

    @Step
    private void add(String... taskTexts) {
        for (String text : taskTexts) {
            newTask.shouldBe(enabled).setValue(text).pressEnter();
        }
    }

    @Step
    private SelenideElement startEditing(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    private void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    private void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    private void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    private void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    private void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @Step
    private void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    private void assertNoTasks() {
        tasks.shouldBe(empty);
    }

    @Step
    private void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    private void assertItemsLeft(int count) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(count)));
    }

    //====================================================================================
    // Precondition Helpers
    //====================================================================================

    public class Task {

        public TaskType taskType;
        public String taskText;

        public Task(String taskText, TaskType taskType) {
            this.taskText = taskText;
            this.taskType = taskType;
        }
    }

    public Task aTask(String taskText, TaskType taskType) {
        return new Task(taskText, taskType);
    }

    public enum TaskType {
        ACTIVE("false"), COMPLETED("true");

        private String flag;

        TaskType(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public enum FilterType {
        ALL("#/"), Active("#/active"), Completed("#/completed");

        private String url;

        FilterType(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public void ensurePageOpenedAndSetUpData(FilterType filterType, StringBuilder jsCommand) {

        String requiredUrl = "https://todomvc4tasj.herokuapp.com/" + filterType.getUrl();

        if(!url().equals(requiredUrl))
            open(requiredUrl);

        executeJavaScript(jsCommand.toString());
        executeJavaScript("location.reload()");
    }

    public StringBuilder createJSCommandString(Task... todos) {

        StringBuilder jsCommand = new StringBuilder();
        jsCommand.append("localStorage.setItem(\"todos-troopjs\", \"[");
        for (Task todo : todos) {
            jsCommand.append("{\\\"completed\\\":").append(todo.taskType.getFlag()).append(", \\\"title\\\":\\\"").append(todo.taskText).append("\\\"}, ");
        }
        if (todos.length != 0)
            jsCommand.deleteCharAt(jsCommand.length() - 2);
        jsCommand.append("]\")");

        return jsCommand;
    }

    private Task[] convertToTaskArray(TaskType taskType, String... taskText) {
        Task[] todos = new Task[taskText.length];
        for (int i = 0; i < taskText.length; i++) {
            todos[i] = aTask(taskText[i], taskType);
        }
        return todos;
    }

    private void givenHelper(FilterType filterType, Task... todos) {
        ensurePageOpenedAndSetUpData(filterType, createJSCommandString(todos));
    }

    private void givenAtAll(Task... todos) {
        givenHelper(FilterType.ALL, todos);
    }

    private void givenAtAll(TaskType taskType, String... taskText) {
        givenHelper(FilterType.ALL, convertToTaskArray(taskType, taskText));
    }

    private void givenAtActive(Task... todos) {
        givenHelper(FilterType.Active, todos);
        filterActive();
    }

    private void givenAtActive(TaskType taskType, String... taskText) {
        givenHelper(FilterType.Active, convertToTaskArray(taskType, taskText));
        filterActive();
    }

    private void givenAtCompleted(Task... todos) {
        givenHelper(FilterType.Completed, todos);
        filterCompleted();
    }
}
