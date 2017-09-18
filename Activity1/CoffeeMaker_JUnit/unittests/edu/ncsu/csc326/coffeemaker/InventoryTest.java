package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {
	
	private Inventory inventory;
	private int default_amount;

	protected void setUp() throws Exception {
		inventory = new Inventory();
		default_amount = 15;
		super.setUp();
	}

	public void testInventory() {
		assertNotNull(inventory);
		assertEquals(default_amount,inventory.getCoffee());
		assertEquals(default_amount,inventory.getMilk());
		assertEquals(default_amount,inventory.getSugar());
		assertEquals(default_amount,inventory.getChocolate());
	}

	public void testGetChocolate() {
		assertEquals("The inventory did not start with the default amount of chocolate.",default_amount,inventory.getChocolate());
		int new_amount = 10;
		inventory.setChocolate(new_amount);
		assertEquals("The amount of chocolate in the inventory is incorrect.",new_amount,inventory.getChocolate());
		assertFalse("The amount of chocoalte in the inventory did not change.",inventory.getChocolate() == default_amount);
	}

	public void testSetChocolate() {
		int new_amount = 10;
		inventory.setChocolate(new_amount);
		assertEquals("The amount of chocolate in the inventory is incorrect.",new_amount,inventory.getChocolate());
		assertFalse("The amount of chocoalte in the inventory did not change.",inventory.getChocolate() == default_amount);
	}

	public void testAddChocolate() {
		fail("Not yet implemented");
	}

	public void testGetCoffee() {
		fail("Not yet implemented");
	}

	public void testSetCoffee() {
		fail("Not yet implemented");
	}

	public void testAddCoffee() {
		fail("Not yet implemented");
	}

	public void testGetMilk() {
		fail("Not yet implemented");
	}

	public void testSetMilk() {
		fail("Not yet implemented");
	}

	public void testAddMilk() {
		fail("Not yet implemented");
	}

	public void testGetSugar() {
		fail("Not yet implemented");
	}

	public void testSetSugar() {
		fail("Not yet implemented");
	}

	public void testAddSugar() {
		fail("Not yet implemented");
	}

	public void testEnoughIngredients() {
		fail("Not yet implemented");
	}

	public void testUseIngredients() {
		fail("Not yet implemented");
	}

	public void testToString() {
		fail("Not yet implemented");
	}

}
