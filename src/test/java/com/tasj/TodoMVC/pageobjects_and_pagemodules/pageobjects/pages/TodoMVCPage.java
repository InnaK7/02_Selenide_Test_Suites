package com.tasj.TodoMVC.pageobjects_and_pagemodules.pageobjects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TodoMVCPage {

    public ElementsCollection tasks = $$("#todo-list>li");
    public SelenideElement newTask = $("#new-todo");

    @Step
    public void add(String... taskTexts) {
        for (String text : taskTexts) {
            newTask.shouldBe(enabled).setValue(text).pressEnter();
        }
    }

    @Step
    public SelenideElement startEditing(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();

    }

    @Step
    public void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @Step
    public void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public void assertNoTasks() {
        tasks.shouldBe(empty);
    }

    @Step
    public void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public void assertItemsLeft(int count) {
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

        public String flag;

        TaskType(String flag) {
            this.flag = flag;
        }

        public String getFlag() {
            return flag;
        }
    }

    public void ensurePageOpenedAndSetUpData(StringBuilder jsCommand) {

        if (!url().equals("https://todomvc4tasj.herokuapp.com/"))
            open("https://todomvc4tasj.herokuapp.com/");
        executeJavaScript(jsCommand.toString());
        refresh();
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

    public Task[] convertToTaskArray(TaskType taskType, String... taskText) {
        Task[] todos = new Task[taskText.length];
        for (int i = 0; i < taskText.length; i++) {
            todos[i] = aTask(taskText[i], taskType);
        }
        return todos;
    }

    public void givenHelper(Task... todos) {

        ensurePageOpenedAndSetUpData(createJSCommandString(todos));
    }

    public void givenAtAll(Task... todos) {
        givenHelper(todos);
    }

    public void givenAtAll(TaskType taskType, String... taskText) {
        givenHelper(convertToTaskArray(taskType, taskText));
    }

    public void givenAtActive(Task... todos) {
        givenHelper(todos);
        filterActive();
    }

    public void givenAtActive(TaskType taskType, String... taskText) {
        givenHelper(convertToTaskArray(taskType, taskText));
        filterActive();
    }

    public void givenAtCompleted(Task... todos) {
        givenHelper(todos);
        filterCompleted();
    }
}
