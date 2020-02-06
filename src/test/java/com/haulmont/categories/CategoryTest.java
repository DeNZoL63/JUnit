package com.haulmont.categories;

import com.haulmont.NumberServiceTest;
import com.haulmont.StringServiceTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory({StringTestCategory.class, NumberTestCategory.class})
@Suite.SuiteClasses({NumberServiceTest.class, StringServiceTest.class})
public class CategoryTest {
}
