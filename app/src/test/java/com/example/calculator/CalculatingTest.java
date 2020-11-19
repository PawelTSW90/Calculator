package com.example.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CalculatingTest {
    private Calculating calculating;
    private StorageRefactor storage;

    @Before
    public void setup() {
        storage = new StorageRefactor(null);
        calculating = new Calculating(storage);


    }

    @Test
    public void checkBracketsTest() {
        storage.setStorage("5+3-(5+3)-(");
        Assert.assertFalse(calculating.checkBrackets());
        storage.setStorage("5+3-(5+3)");
        Assert.assertTrue(calculating.checkBrackets());
    }

    @Test
    public void countResultTest() {
        storage.setStorage("2,3+2,7-1-2-3+5÷(4×7-(8+9))=");
        Assert.assertEquals("-0,5454545455", calculating.countResult(storage));

    }

    @Test
    public void WrongFormatCheckerTest() {
        String tmp = "5,789789078907";
        Assert.assertEquals(3, calculating.wrongFormatChecker(tmp));
        tmp = "1234567812345678+2";
        Assert.assertEquals(2, calculating.wrongFormatChecker(tmp));
        tmp = "(,43";
        Assert.assertEquals(1, calculating.wrongFormatChecker(tmp));
        tmp = "12345123451+23451234512+3451234512345+12345123451+234512345+123451234+51234512+3451234512+3451234+5123451+234512345+1234512+34512345";
        Assert.assertEquals(4, calculating.wrongFormatChecker(tmp));


    }


}