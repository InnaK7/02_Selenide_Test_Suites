package com.tasj.TodoMVC.testSuitesJunit;

import com.tasj.TodoMVC.testSuitesJunit.categories.Buggy;
import com.tasj.TodoMVC.testSuitesJunit.features.TodosE2ETest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.tasj.TodoMVC.testSuitesJunit.features.TodosOperationsAtAllTest;

@RunWith(Categories.class)
@Suite.SuiteClasses({TodosOperationsAtAllTest.class, TodosE2ETest.class})
@Categories.IncludeCategory(Buggy.class)

public class BuggySuiteTest {
}
