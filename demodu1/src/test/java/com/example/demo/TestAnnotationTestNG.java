package com.example.demo;

import org.testng.annotations.*;

public class TestAnnotationTestNG {

    @Test
    public void testCase1(){
        System.out.println("in test case 1");
    }

    @Test
    public void testCase2(){
        System.out.println("in test case 2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("in beforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("in afterMethod");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("in beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("in afterClass");
    }
}
