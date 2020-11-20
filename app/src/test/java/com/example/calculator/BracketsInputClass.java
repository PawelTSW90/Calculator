package com.example.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BracketsInputClass {
    BracketsInput brackets;
    StorageRefactor storage;




    @Before

    public void setup(){
        storage = new StorageRefactor(null);
        brackets = new BracketsInput(null, storage);
    }
    @Test
    public void bracketType(){
        storage.setStorage("5+(");
        Assert.assertEquals("closeBracket", brackets.bracketType());
        storage.setStorage("5+(3*3)+");
        Assert.assertEquals("openBracket", brackets.bracketType());

    }
}
