package com.example.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatingTest{
    private Calculating calculating;
    private StorageRefactor storage;
    @Before
    public void setup()
    {
        this.calculating = new Calculating(storage);
        this.storage = storage;
    }

    @Test
    public void wrongFormatCheckerTest() {
        /*
        String testExpression2 = "2+4,,4+1";
        Assert.assertTrue(this.calculating.wrongFormatChecker(testExpression2));
        String testExpression = "2+4,6,8,9+1";
        Assert.assertTrue(this.calculating.wrongFormatChecker(testExpression));

*/
    }
    @Test
    public void countResultTest(){
        StorageRefactor storage = new StorageRefactor(null);
        storage.setStorage("2,3+2,7=");
        Assert.assertTrue(calculating.countResult(storage).equals("5"));

    }
}