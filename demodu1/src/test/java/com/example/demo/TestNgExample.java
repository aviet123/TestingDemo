package com.example.demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TestNgExample {

    @Test(priority = 3)
    void setup(){
        System.out.println("open browser");
    }
    @Test(priority = 2)
    void login(){
        System.out.println("the login test");
    }
    @Test(priority = 1)
    void breakdown(){
        System.out.println("browser is breakdown");
    }
    @Test(priority = 4)
    void done(){
        System.out.println("congratulation c0620k1");
    }

    @Test
    @Parameters({"value", "isEven"})
    public void givenNumberFromXML_ifEvenCheckOK_thenCorrect(int value, boolean isEven) {
        Assert.assertEquals(isEven, value % 2 == 0);
    }
    @DataProvider(name = "numbers")
    public static Object[][] evenNumbers(){
        return new Object[][]{{1,false},{2,true}, {4,true}};
    }

    @Test(dataProvider = "numbers")
    public void givenNumberFromDataProvider_ifEvenCheckOk_thenCorrect(Integer number, boolean expected){
        Assert.assertEquals(expected, number % 2 == 0);
    }

    String email = "dinhv2388@gmail.com";

    @Test
    public void givenEmail_ifValid_thenTrue(){
        boolean isValid = email.contains("@");
        Assert.assertTrue(isValid);
    }

    @Test(dependsOnMethods = {"givenEmail_ifValid_thenTrue"})
    public void givenValidEmail_whenLoggedIn_thenTrue(){
        System.out.println("Email valid is true"+ email);
    }



}
