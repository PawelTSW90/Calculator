package com.example.calculator;

import android.widget.EditText;

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
    private EditText txt;
    @Before
    public void setup()
    {
        this.calculating = new Calculating(storage);
        this.storage = new StorageRefactor(txt);
    }


    @Test
    public void countResultTest(){
        storage.setStorage("2,3+2,7=");
        Assert.assertEquals("5", calculating.countResult(storage));

    }
    @Test
    public void countResultTest2(){
        storage.setStorage("-1-2-3=");
        Assert.assertEquals("-6", calculating.countResult(storage));

    }
}