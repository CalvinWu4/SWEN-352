package edu.ncsu.csc326.coffeemaker;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		//suite.addTestSuite(CoffeeMakerTest.class);
		suite.addTestSuite(InventoryTest.class);
		//suite.addTestSuite(MainTest.class);
		suite.addTestSuite(RecipeBookTest.class);
		suite.addTestSuite(RecipeTest.class);
		//$JUnit-END$
		return suite;
	}

}
