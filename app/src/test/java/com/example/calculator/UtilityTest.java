package com.example.calculator;

import org.junit.Assert;
import org.junit.Test;

public class UtilityTest {

    @Test
    public void isLowPriorityTest(){
        String tmp = "+";
        Assert.assertTrue(Utility.isLowPriority(tmp));
    }
    @Test
    public void whatSignTest(){
String tmp = "+";
Assert.assertEquals(0, Utility.whatSign(tmp));
tmp = "-";
Assert.assertEquals(1, Utility.whatSign(tmp));
tmp = "×";
Assert.assertEquals(2, Utility.whatSign(tmp));
tmp = "÷";
Assert.assertEquals(3, Utility.whatSign(tmp));
tmp = "3";
Assert.assertEquals(4, Utility.whatSign(tmp));



    }
@Test
    public void isDoubleTest(){
        String tmp = "5";
        Assert.assertTrue(Utility.isDouble(tmp));
        tmp = ",";
        Assert.assertFalse(Utility.isDouble(tmp));

    }
    @Test
    public void containArithmeticSymbolTest(){
        String tmp = "+";
        Assert.assertTrue(Utility.containArithmeticSymbol(tmp));
        tmp = "2";
        Assert.assertFalse(Utility.containArithmeticSymbol(tmp));

    }


}
