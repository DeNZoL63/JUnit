package com.haulmont;

import com.haulmont.categories.StringTestCategory;
import com.haulmont.example.StringService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

public class StringServiceTest {
    private StringService stringService;

    @Before
    public void setUp() {
        stringService = new StringService();
    }

    @Test
    @Category(StringTestCategory.class)
    public void testConcatPositive() {
        String s1 = "hello";
        String s2 = " world!";
        assertEquals("hello world!", stringService.concat(s1, s2));
    }

}
