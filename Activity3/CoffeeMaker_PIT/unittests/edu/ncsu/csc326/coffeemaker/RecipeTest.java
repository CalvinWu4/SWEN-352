package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;

public class RecipeTest extends TestCase {

	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;
	
	/**
	 * Sets up the test fixture.
	 * (Called before every test case).
	 */
	protected void setUp() throws Exception {
		
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		r2 = new Recipe();
		r2.setName("Coffee");
		r2.setAmtChocolate("0");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("50");
		
		r3 = new Recipe();
		r3.setName("Espresso");
		r3.setAmtChocolate("1");
		r3.setAmtCoffee("5");
		r3.setAmtMilk("2");
		r3.setAmtSugar("2");
		r3.setPrice("75");
		
		r4 = new Recipe();
		r4.setName(null);
		r4.setAmtChocolate("0");
		r4.setAmtCoffee("3");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("50");
		super.setUp();
	}
	
	/**
	 * Tears down the test fixture.
	 * (Called after every test case).
	 */
	protected void tearDown() throws Exception {
		r1 = null;
		r2 = null;
		r3 = null;
		r4 = null;
		super.tearDown();
	}
	
	/**
	 * Test the getter for amount of chocolate in the recipe.
	 */
	public void testGetAmtChocolate() {
		assertEquals(r1.getAmtChocolate(), 0);
	}
	
	/**
	 * Test the setter for amount of chocolate is positive in the recipe.
	 */
	public void testSetAmtChocolatePositive() {
		try {
			r1.setAmtChocolate("2");
			assertEquals(r1.getAmtChocolate(), 2);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of chocolate is zero in the recipe.
	 */
	public void testSetAmtChocolateZero() {
		try {
			r1.setAmtChocolate("2");
			r1.setAmtChocolate("0");
			assertEquals(r1.getAmtChocolate(), 0);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of chocolate is negative in the recipe.
	 */
	public void testSetAmtChocolateNegative() {
		try {
			r1.setAmtChocolate("-2");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of chocolate is a string in the recipe.
	 */
	public void testSetAmtChocolateString() {
		try {
			r1.setAmtChocolate("Two");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of chocolate is empty in the recipe. 
	 */
	public void testSetAmtChocolateEmpty() {
		try {
			r1.setAmtChocolate("");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the getter for amount of coffee in the recipe. 
	 */
	public void testGetAmtCoffee() {
		assertEquals(r1.getAmtCoffee(), 3);
	}
	
	/**
	 * Test the setter for amount of coffee is positive in the recipe.
	 */
	public void testSetAmtCoffeePositive() {
		try {
			r1.setAmtCoffee("1");
			assertEquals(r1.getAmtCoffee(), 1);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of coffee is zero in the recipe.
	 */
	public void testSetAmtCoffeeZero() {
		try {
			r1.setAmtCoffee("0");
			assertEquals(r1.getAmtCoffee(), 0);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of coffee is negative in the recipe.
	 */
	public void testSetAmtCoffeeNegative() {
		try {
			r1.setAmtCoffee("-1");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of coffee is a string in the recipe.
	 */
	public void testSetAmtCoffeeString() {
		try {
			r1.setAmtCoffee("One");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of coffee is empty in the recipe.
	 */
	public void testSetAmtCoffeeEmpty() {
		try {
			r1.setAmtCoffee("");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the getter for amount of milk in the recipe.
	 */
	public void testGetAmtMilk() {
		assertEquals(r1.getAmtMilk(), 1);
	}
	
	/**
	 * Test the setter for amount of milk is positive in the recipe.
	 */
	public void testSetAmtMilkPositive() {
		try {
			r1.setAmtMilk("3");
			assertEquals(r1.getAmtMilk(), 3);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of milk is zero in the recipe.
	 */
	public void testSetAmtMilkZero() {
		try {
			r1.setAmtMilk("0");
			assertEquals(r1.getAmtMilk(), 0);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of milk is negative in the recipe.
	 */
	public void testSetAmtMilkNegative() {
		try {
			r1.setAmtMilk("-3");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of milk is a string in the recipe.
	 */
	public void testSetAmtMilkString() {
		try {
			r1.setAmtMilk("Three");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of milk is empty in the recipe.
	 */
	public void testSetAmtMilkEmpty() {
		try {
			r1.setAmtMilk("");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the getter for amount of sugar in the recipe.
	 */
	public void testGetAmtSugar() {
		assertEquals(r1.getAmtSugar(), 1);
	}
	
	/**
	 * Test the setter for amount of sugar is positive in the recipe.
	 */
	public void testSetAmtSugarPositive() {
		try {
			r1.setAmtSugar("3");
			assertEquals(r1.getAmtSugar(), 3);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of sugar is zero in the recipe.
	 */
	public void testSetAmtSugarZero() {
		try {
			r1.setAmtSugar("0");
			assertEquals(r1.getAmtSugar(), 0);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for amount of sugar is negative in the recipe.
	 */
	public void testSetAmtSugarNegative() {
		try {
			r1.setAmtSugar("-3");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of sugar is a string in the recipe. 
	 */
	public void testSetAmtSugarString() {
		try {
			r1.setAmtSugar("Three");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for amount of sugar is empty in the recipe.
	 */
	public void testSetAmtSugarEmpty() {
		try {
			r1.setAmtSugar("");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the getter for recipe name.
	 */
	public void testGetName() {
		assertEquals(r1.getName(), "Coffee");
	}
	
	/**
	 * Test the setter for recipe name.
	 */
	public void testSetName() {
		r1.setName("Espresso");
		assertEquals(r1.getName(), "Espresso");
	}
	
	/**
	 * Test the setter for recipe name is empty.
	 */
	public void testSetNameEmpty() {
		r1.setName("");
		assertEquals(r1.getName(), "");
	}
	
	/**
	 * Test the setter for recipe name is null.
	 */
	public void testSetNameNull() {
		r1.setName(null);
		assertEquals(r1.getName(), null);
	}
	
	/**
	 * Test the getter for getting the recipe's price.
	 */
	public void testGetPrice() {
		assertEquals(r1.getPrice(), 50);
	}
	
	/**
	 * Test the setter for recipe price is positive.
	 */
	public void testSetPricePositive() {
		try {
			r1.setPrice("35");
			assertEquals(r1.getPrice(), 35);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for recipe price is zero.
	 */
	public void testSetPriceZero() {
		try {
			r1.setPrice("0");
			assertEquals(r1.getPrice(), 0);
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}
	
	/**
	 * Test the setter for recipe price is negative.
	 */
	public void testSetAmtPriceNegative() {
		try {
			r1.setPrice("-50");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for recipe price is a string.
	 */
	public void testSetAmtPriceLetter() {
		try {
			r1.setPrice("Fifty");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the setter for recipe price is empty.
	 */
	public void testSetAmtPriceEmpty() {
		try {
			r1.setPrice("");
			fail("RecipeException should be thrown");
		} catch (RecipeException e) {
			// Success if thrown
		}
	}
	
	/**
	 * Test the behavior of the overridden toString() method to display recipe.
	 */
	public void testToString() {
		assertEquals(r1.toString(), "Coffee");
	}
	
	/**
	 * Test if two recipes are the same.
	 */
	public void testEqualsSame() {
		assertTrue(r1.equals(r1));
	}
	
	/**
	 * Test if two recipes are identical.
	 */
	public void testEqualsIdentical() {
		assertTrue(r1.equals(r2));
	}
	
	/**
	 * Test if a class equals to a different class.
	 */
	public void testEqualsDifferentClass() {
		assertFalse(r1.equals("Coffee"));
	}
	
	/**
	 * Test if a recipe object equals null.
	 */
	public void testEqualsNull() {
		assertFalse(r1.equals(null));
	}
	
	/**
	 * Test if this recipe object's name is equal to the other recipe object's null name.
	 */
	public void testEqualsOtherNameNull() {
		assertFalse(r1.equals(r4));
	}
	
	/**
	 * Test if this recipe object's null name is equal to the other recipe object's name. 
	 */
	public void testEqualsThisNameNull() {
		assertFalse(r4.equals(r1));
		// Null name should result false
	}
	
	/**
	 * Test the hash code for a recipe.
	 */
	public void testHashCode() {
		assertEquals(r1.hashCode(), (31 * 1 + r1.getName().hashCode()));
	}
	
	/**
	 * Test the hash code for a null recipe.
	 */
	public void testHashCodeNull() {
		assertEquals(r4.hashCode(), 31);
	}
}