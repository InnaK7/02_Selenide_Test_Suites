package ua.net.itlabs.TodoMVC.testSuitesJunit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.net.itlabs.TodoMVC.testSuitesJunit.categories.Buggy;
import ua.net.itlabs.TodoMVC.testSuitesJunit.features.TodosE2ETest;
import ua.net.itlabs.TodoMVC.testSuitesJunit.features.TodosOperationsAtAllTest;

@RunWith(Categories.class)
@Suite.SuiteClasses({TodosOperationsAtAllTest.class, TodosE2ETest.class})
@Categories.IncludeCategory(Buggy.class)

public class BuggySuiteTest {
}
