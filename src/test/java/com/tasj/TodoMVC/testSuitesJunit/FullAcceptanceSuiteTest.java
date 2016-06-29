package com.tasj.TodoMVC.testSuitesJunit;

import com.tasj.TodoMVC.testSuitesJunit.categories.Buggy;
import com.tasj.TodoMVC.testSuitesJunit.features.TodosE2ETest;
import com.tasj.TodoMVC.testSuitesJunit.features.TodosOperationsAtAllTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({TodosOperationsAtAllTest.class, TodosE2ETest.class})
@Categories.ExcludeCategory(Buggy.class)
public class FullAcceptanceSuiteTest {
}
