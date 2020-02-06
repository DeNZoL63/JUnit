package com.haulmont;

import com.haulmont.example.NumberService;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class TheoriesTest {

    private NumberService numberService;

    @Before
    public void setUp() {
        numberService = new NumberService();
    }

    @DataPoints
    public static Object[][] data = new Object[][]{
            {1, false},
            {2, true},
            {4, true},
            {591, false}
    };

    @Theory
    public void testIsEven(Object... data) {
        boolean even = numberService.isEven((Integer) data[0]);
        assertEquals(even, data[1]);
    }
}
