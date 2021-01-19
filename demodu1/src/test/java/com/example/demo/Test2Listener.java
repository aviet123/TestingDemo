package com.example.demo;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

public class Test2Listener extends TestListenerAdapter {


    public void onTestFailure(ITestResult result){
        log(result.getName()+"--Test Method failed\n");
    }

    private void log(String s) {
        System.out.println(s);
    }

    public void onTestSkipped(ITestResult result){
        log(result.getName()+"--Test Method Skipped\n");
    }

    public void onTestSuccess(ITestResult result){
        log(result.getName()+"--Test Method Success\n");
    }
}
