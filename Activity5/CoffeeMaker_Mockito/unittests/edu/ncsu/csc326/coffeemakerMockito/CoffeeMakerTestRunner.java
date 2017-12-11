package edu.ncsu.csc326.coffeemakerMockito;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class CoffeeMakerTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CoffeeMakerTest.testMakeCoffee.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

