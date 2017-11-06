package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class InventoryTest extends TestCase {
	private Inventory inv;
	private Recipe recipe;
	private CoffeeMaker cm;

	/**
	 * Sets up the test fixture.
	 * (Called before every test case).
	 */
	protected void setUp() throws Exception {
		inv = new Inventory();
		recipe = new Recipe();
		cm = new CoffeeMaker();
		super.setUp();
	}
	
	/**
	 * Tears down the test fixture.
	 * (Called after every test case).
	 */
	protected void tearDown() throws Exception {
		inv = null;
		recipe = null;
		cm = null;
		super.tearDown();
	}

	/**
	 * Test if add inventory can have null amounts passed in.
	 * @throws InventoryException
	 */
	public void testAddInventoryNull() throws InventoryException {
		try {
			cm.addInventory(null, null, null, null);
		} catch(InventoryException e) {
			assertNotSame("Units must be a positive integer", e);
		}
	}
	
	/**
	 * Test if the amounts were successfully added in the inventory.
	 * @throws InventoryException
	 */
	public void testAddInventory() throws InventoryException {
			cm.addInventory("100", "100", "50", "200");
			assertEquals(cm.checkInventory(), inv.toString());
	}
	
	/**
	 * Test the getter for the amount of chocolate in the inventory.
	 */
	public void testGetChocolate() {
		assertEquals(15, inv.getChocolate());
	}
	
	/**
	 * Test the setter for amount of chocolate in the inventory.
	 */
	public void testSetChocolateZero(){
		inv.setChocolate(0);
		assertEquals(0, inv.getChocolate());
	}
	
	/**
	 * Test the setter for amount of chocolate is negative in the inventory.
	 */
	public void testSetChocolateNegative() {
		inv.setChocolate(-1);
		assertEquals(15, inv.getChocolate());
	}
	
	/**
	 * Test the setter for amount of chocolate is positive in the inventory.
	 */
	public void testSetChocolatePositive() {
		inv.setChocolate(1);
		assertEquals(1, inv.getChocolate());
	}
	
	/**
	 * Test if the amount of chocolate added is empty in the inventory. 
	 */
	public void testAddChocolateEmpty() {
		try {
			inv.addChocolate("");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertNotSame("Exception should be thrown", e);
		}
	}
	
	/**
	 * Test if the amount is a string value for adding chocolate in the inventory.
	 */
	public void testAddChocolateString() {
		try {
			inv.addChocolate("String");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of chocolate must be a positive integer", e.getMessage());
		}
	}

	/**
	 * Test if the amount of chocolate being added is negative in the inventory.
	 */
	public void testAddChocolateNegative() {
		try {
			inv.addChocolate("-1");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of chocolate must be a positive integer", e.getMessage());
		}
	}

	/**
	 * Test if the amount of chocolate being added is zero in the inventory.
	 */
	public void testAddChocolateZero() {
		try {
			inv.addChocolate("0");
			assertEquals(15, inv.getChocolate());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test if the amount of chocolate being added is positive in the inventory.
	 */
	public void testAddChocolatePositive() {
		try {
			inv.addChocolate("1");
			assertEquals(16, inv.getChocolate());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test the getter for the amount of coffee in the inventory.
	 */
	public void testGetCoffee() {
		assertEquals(15, inv.getCoffee());
	}
	
	/**
	 * Test the setter for the amount of coffee is negative in the inventory.
	 */
	public void testSetCoffeeNegative() {
		inv.setCoffee(-1);
		assertEquals(15, inv.getCoffee());
	}
	
	/**
	 * Test the setter for the amount of coffee is zero in the inventory.
	 */
	public void testSetCoffeeZero() {
		inv.setCoffee(0);
		assertEquals(0, inv.getCoffee());
	}
	
	/**
	 * Test the setter for the amount of coffee is positive in the inventory.
	 */
	public void testSetCoffeePositive() {
		inv.setCoffee(1);
		assertEquals(1, inv.getCoffee());
	}
	
	/**
	 * Test if the amount of coffee being added is empty in the inventory.
	 */
	public void testAddCoffeeEmpty() {
		try {
			inv.addCoffee("");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertNotSame("Exception should be thrown", e);
		}
	}
	
	/**
	 * Test if the amount of coffee being added is a string in the inventory.
	 */
	public void testAddCoffeeString() {
		try {
			inv.addCoffee("String");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of coffee must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of coffee being added is negative in the inventory.
	 */
	public void testAddCoffeeNegative() {
		try {
			inv.addCoffee("-1");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of coffee must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of coffee being added is zero in the inventory.
	 */
	public void testAddCoffeeZero() {
		try {
			inv.addCoffee("0");
			assertEquals(15, inv.getCoffee());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test if the amount of coffee being added is positive in the inventory.
	 */
	public void testAddCoffeePositive() {
		try {
			inv.addCoffee("1");
			assertEquals(16, inv.getCoffee());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test the getter for the amount of milk in the inventory.
	 */
	public void testGetMilk() {
		assertEquals(15, inv.getMilk());
	}
	
	/**
	 * Test the setter for the amount of milk is negative in the inventory.
	 */
	public void testSetMilkNegative() {
		inv.setMilk(-1);
		assertEquals(15, inv.getMilk());
	}
	
	/**
	 * Test the setter for the amount of milk is zero in the inventory.
	 */
	public void testSetMilkZero() {
		inv.setMilk(0);
		assertEquals(0, inv.getMilk());
	}
	
	/**
	 * Test the setter for the amount of milk is positive in the inventory.
	 */
	public void testSetMilkPositive() {
		inv.setMilk(1);
		assertEquals(1, inv.getMilk());
	}
	
	/**
	 * Test if the amount of milk being added is empty in the inventory.
	 */
	public void testAddMilkEmpty() {
		try {
			inv.addMilk("");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertNotSame("Exception should be thrown", e);
		}
	}
	
	/**
	 * Test if the amount of milk being added is a string in the inventory.
	 */
	public void testAddMilkString() {
		try {
			inv.addMilk("String");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of milk must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of milk being added is negative in the inventory.
	 */
	public void testAddMilkNegative() {
		try {
			inv.addMilk("-1");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of milk must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of milk being added is zero in the inventory.
	 */
	public void testAddMilkZero() {
		try {
			inv.addMilk("0");
			assertEquals(15, inv.getMilk());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test if the amount of milk being added is positive in the inventory.
	 */
	public void testAddMilkPositive() {
		try {
			inv.addMilk("1");
			assertEquals(16, inv.getMilk());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test the getter for the amount of sugar in the inventory.
	 */
	public void testGetSugar() {
		assertEquals(15, inv.getSugar());
	}
	
	/**
	 * Test the setter for the amount of sugar is zero in the inventory.
	 */
	public void testSetSugarZero() {
		inv.setSugar(0);
		assertEquals(0, inv.getSugar());
	}
	
	/**
	 * Test the setter for the amount of sugar is negative in the inventory.
	 */
	public void testSetSugarNegative() {
		inv.setSugar(-1);
		assertEquals(15, inv.getSugar());
	}

	/**
	 * Test the setter for the amount of sugar is positive in the inventory.
	 */
	public void testSetSugarPositive() {
		inv.setSugar(1);
		assertEquals(1, inv.getSugar());
	}
	
	/**
	 * Test if the amount of sugar being added is empty in the inventory.
	 */
	public void testAddSugarEmpty() {
		try {
			inv.addSugar("");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertNotSame("Exception should be thrown", e);
		}
	}
	
	/**
	 * Test if the amount of sugar being added is a string in the inventory.
	 */
	public void testAddSugarString() {
		try {
			inv.addSugar("String");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of sugar must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of sugar being added is negative in the inventory.
	 */
	public void testAddSugarNegative() {
		try {
			inv.addSugar("-1");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of sugar must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of sugar being added is positive in the inventory.
	 */
	public void testAddSugarPositive() {
		try {
			inv.addSugar("1");
			assertEquals(16, inv.getSugar());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	public void testAddSugarZero() {
		try {
			inv.addSugar("0");
			assertEquals(15, inv.getSugar());
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	/**
	 * Test if the amount of chocolate being used is positive in the inventory.
	 * @throws RecipeException
	 */
	public void testUseChocolatePositive() throws RecipeException {
		recipe.setAmtChocolate("5");
		inv.useIngredients(recipe);
		assertEquals(15-5, inv.getChocolate());
	}
	
	/**
	 * Test if the amount of chocolate being used is negative in the inventory.
	 * @throws RecipeException
	 */
	public void testUseChocolateNegative() throws RecipeException {
		try {
			recipe.setAmtChocolate("-1");	
			inv.useIngredients(recipe);	
		} catch(RecipeException e) {
			assertEquals("Units of chocolate must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of milk being used is positive in the inventory.
	 * @throws RecipeException
	 */
	public void testUseMilkPositive() throws RecipeException {
		recipe.setAmtMilk("10");
		inv.useIngredients(recipe);
		assertEquals(15-10, inv.getMilk());
	}
	
	/**
	 * Test to see if too much milk can be user.
	 * @throws RecipeException
	 */
	public void testUseTooMuchMilk() throws RecipeException {
		recipe.setAmtMilk("20");
		boolean result = inv.useIngredients(recipe);
		assertFalse(result);
	}
	
	/**
	 * Test to see if too much milk can be user.
	 * @throws RecipeException
	 */
	public void testUseEnoughMilk() throws RecipeException {
		recipe.setAmtMilk("10");
		boolean result = inv.useIngredients(recipe);
		assertTrue(result);
	}
	
	/**
	 * Test if the amount of milk being used is negative in the inventory.
	 * @throws RecipeException
	 */
	public void testUseMilkNegative() throws RecipeException {
		try {
			recipe.setAmtMilk("-1");	
			inv.useIngredients(recipe);	
		} catch(RecipeException e) {
			assertEquals("Units of milk must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of sugar being used is positive in the inventory.
	 * @throws RecipeException
	 */
	public void testUseSugarPositive() throws RecipeException {
		recipe.setAmtSugar("10");
		inv.useIngredients(recipe);
		assertEquals(15-10, inv.getSugar());
	}

	/**
	 * Test if the amount of sugar being used is negative in the inventory.
	 * @throws RecipeException
	 */
	public void testUseSugarNegative() throws RecipeException {
		try {
			recipe.setAmtSugar("-1");	
			inv.useIngredients(recipe);	
		} catch(RecipeException e) {
			assertEquals("Units of sugar must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test if the amount of coffee being used is positive in the inventory.
	 * @throws RecipeException
	 */
	public void testUseCoffeePositive() throws RecipeException {
		recipe.setAmtCoffee("6");
		inv.useIngredients(recipe);
		assertEquals(15-6, inv.getCoffee());
	}
	
	/**
	 * Test if the amount of coffee being used is negative in the inventory.
	 * @throws RecipeException
	 */
	public void testUseCoffeeNegative() throws RecipeException {
		try {
			recipe.setAmtCoffee("-1");
			inv.useIngredients(recipe);
		} catch (RecipeException e) {
			assertEquals("Units of coffee must be a positive integer", e.getMessage());
		}
	}
	
	/**
	 * Test to see if the recipe tries to use more coffee than the inventory has.
	 * @throws RecipeException
	 */
	public void testNotEnoughCoffee() throws RecipeException {
		recipe.setAmtCoffee("16");    	
		assertFalse("Can't use ingredients because not enough coffee", inv.useIngredients(recipe));
	}
	
	/**
	 * Test to see if the recipe tries to use more milk than the inventory has.
	 * @throws RecipeException
	 */
	public void testNotEnoughMilk() throws RecipeException {
		recipe.setAmtMilk("16");    	
		assertFalse("Can't use ingredients because not enough milk", inv.useIngredients(recipe));
	}
	
	/**
	 * Test to see if the recipe tries to use more sugar than the inventory has.
	 * @throws RecipeException
	 */
	public void testNotEnoughSugar() throws RecipeException {
		recipe.setAmtSugar("16");    	
		assertFalse("Can't use ingredients because not enough sugar", inv.useIngredients(recipe));
	}
	
	/**
	 * Test to see if recipe tries to use more chocolate than the inventory has.
	 * @throws RecipeException
	 */
	public void testNotEnoughChocolate() throws RecipeException {
		recipe.setAmtChocolate("16");    	
		assertFalse("Can't use ingredients because not enough chocolate", inv.useIngredients(recipe));
	}

	/**
	 * Test to see if there is enough ingredients in the inventory for the recipe.
	 * Should fail since the recipe requires more than available in the inventory.
	 */
	public void testNotEnoughIngredients() {
		try {
			recipe.setName("Recipe with not enough inv ingredients");
			recipe.setAmtSugar("250");
			recipe.setAmtCoffee("200");
			recipe.setAmtMilk("300");
			recipe.setAmtChocolate("150");

			boolean checkEnough = inv.enoughIngredients(recipe);
			assertFalse(checkEnough);
			
		} catch(RecipeException e) {
			fail("RecipeException should be thrown");
		}
	}
	
	/**
	 * Test to see if there is enough ingredients in the inventory for the recipe.
	 * Should fail since the recipe requires more than available in the inventory.
	 */
	public void testBarelyEnoughSugar() {
		try {
			recipe.setName("Recipe with barely enough inv ingredients");
			recipe.setAmtSugar("15");
			recipe.setAmtCoffee("15");
			recipe.setAmtMilk("15");
			recipe.setAmtChocolate("15");

			boolean checkEnough = inv.enoughIngredients(recipe);
			assertTrue(checkEnough);
			
		} catch(RecipeException e) {
			fail("RecipeException should be thrown");
		}
	}
	
	/**
	 * Test to see if there is enough ingredients in the inventory for the recipe.
	 * Should pass since the recipe doesn't require more than available in the inventory.
	 */
	public void testEnoughIngredients() {
		try {
			recipe.setName("Recipe with enough inventory ingredients");
			recipe.setAmtSugar("10");
			recipe.setAmtChocolate("4");
			recipe.setAmtMilk("5");
			recipe.setAmtCoffee("12");

			boolean checkEnough = inv.enoughIngredients(recipe);
			assertTrue(checkEnough);
			
		} catch (RecipeException e) {
			fail("RecipeException should not be thrown");
		}
	}

	/**
	 * Tests the behavior of the overridden toString() method to display inventory.
	 */
	public void testToString() {
		String actual = inv.toString();
		String expected = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		assertEquals("toString() not valid", actual, expected);
	}
}