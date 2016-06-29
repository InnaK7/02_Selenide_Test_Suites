package com.tasj.TodoMVC.testSuitesMavenProfiles.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TodoMVC {

    public static ElementsCollection tasks = $$("#todo-list>li");
    public static  SelenideElement newTask = $("#new-todo");

    @Step
    public static void add(String... taskTexts) {
        for (String text : taskTexts) {
            newTask.shouldBe(enabled).setValue(text).pressEnter();
        }
    }

    @Step
    public static SelenideElement startEditing(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    //asserts
    @Step
    public static void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void assertNoTasks() {
        tasks.shouldBe(empty);
    }

    @Step
    public static void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public static void assertItemsLeft(int count) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(count)));
    }

    //====================================================================================
    // Precondition Helpers
    //====================================================================================

    public static class Task {

        public TaskType taskType;
        public String taskText;

        public Task(String taskText, TaskType taskType) {
            this.taskText = taskText;
            this.taskType = taskType;
        }
    }

    public static Task aTask(String taskText, TaskType taskType) {
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

    public static void ensurePageOpenedAndSetUpData(StringBuilder jsCommand) {

        if (!url().equals("https://todomvc4tasj.herokuapp.com/"))
            open("https://todomvc4tasj.herokuapp.com/");
        executeJavaScript(jsCommand.toString());
        refresh();
    }

    public static StringBuilder createJSCommandString(Task... todos) {

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

    public static Task[] convertToTaskArray(TaskType taskType, String... taskText) {
        Task[] todos = new Task[taskText.length];
        for (int i = 0; i < taskText.length; i++) {
            todos[i] = aTask(taskText[i], taskType);
        }
        return todos;
    }

    public static void givenHelper(Task... todos) {

        ensurePageOpenedAndSetUpData(createJSCommandString(todos));
    }

    public static void givenAtAll(Task... todos) {
        givenHelper(todos);
    }

    public static void givenAtAll(TaskType taskType, String... taskText) {
        givenHelper(convertToTaskArray(taskType, taskText));
    }

    public static void givenAtActive(Task... todos) {
        givenHelper(todos);
        filterActive();
    }

    public static void givenAtActive(TaskType taskType, String... taskText) {
        givenHelper(convertToTaskArray(taskType, taskText));
        filterActive();
    }

    public static void givenAtCompleted(Task... todos) {
        givenHelper(todos);
        filterCompleted();
    }
}
