package com.example.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StorageRefactorTest {
    StorageRefactor storage;
    MainActivity activity;


@Before

    public void setup(){
    storage = new StorageRefactor(null);
    activity = new MainActivity();


}
@Test
public void refactorStorageTest(){
    List<String> expectedList = Arrays.asList("5", "6", "2", "×", "+", "5", "7", "-", "4", "÷", "+");
   storage.setStorage("5+6×2+(5-7)÷4=");
    Assert.assertEquals(expectedList,storage.refactorStorage());


}





}
