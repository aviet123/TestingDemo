package com.example.demo;

import com.example.demo.model.MessageUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
    String message = ".com";
    MessageUtil messageUtil = new MessageUtil(message);


    @Test
    public void testPrintMessage(){
        System.out.println("inside testPrintMessage()");
        message = ".com";
        Assert.assertEquals(message,messageUtil.printMessage());
    }

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "tutorialspoint"+".com";
        Assert.assertEquals(message,messageUtil.salutationMessage());
    }

    @Test(dependsOnMethods = {"testPrintMessage"})
    public void testExitMessage(){
        System.out.println("Inside testExitMessage()");
        message = "www."+"tutorialspoint"+".com";
        Assert.assertEquals(message,messageUtil.exitMessage());
    }

    @org.junit.Test
    public void testCompare(){
        String str = "alo";
        org.junit.Assert.assertEquals("alo",str);
    }

}
