package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
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
		assertNotNull("Inventory not created.",inventory);
		assertEquals("Inventory does not start off with the correct amout of coffee.",default_amount,inventory.getCoffee());
		assertEquals("Inventory does not start off with the correct amout of milk.",default_amount,inventory.getMilk());
		assertEquals("Inventory does not start off with the correct amout of sugar.",default_amount,inventory.getSugar());
		assertEquals("Inventory does not start off with the correct amout of chocolate.",default_amount,inventory.getChocolate());
	}

	public void testGetChocolate() {
		assertEquals("The inventory did not start with the default amount of chocolate.",default_amount,inventory.getChocolate());
		int new_amount = 10;
		inventory.setChocolate(new_amount);
		assertEquals("The new amount of chocolate in the inventory is incorrect.",new_amount,inventory.getChocolate());
	}

	public void testSetChocolate() {
		//Test with positive number
		int new_amount = 10;
		inventory.setChocolate(new_amount);
		assertEquals("The amount of chocolate in the inventory is incorrect.",new_amount,inventory.getChocolate());
		//Reset the amount of chocolate
		inventory.setChocolate(default_amount); 
		//Test with negative number.
		int negative_amount = -1;
		inventory.setChocolate(negative_amount);
		assertEquals("Amount of chocolate was set while trying to add a negative amount.",default_amount, inventory.getChocolate());
		
	}

	public void testAddChocolate() {
		int add = 10;
		inventory.setChocolate(default_amount);
		boolean NanFlag = false;
		boolean NegFlag = false;
		try {
			inventory.addChocolate(String.valueOf(add));
			inventory.addChocolate("a");
		} catch (InventoryException e) {
			NanFlag = true; //Signal it reached here.
		}
		assertEquals("Amount of chocolate not increased.",default_amount + add, inventory.getChocolate());
		String neg = "-1";
		try {
			inventory.addChocolate(neg);
		}
		catch (InventoryException e) {
			NegFlag = true;
		}
		assertTrue("Exception not thrown for adding a letters worth of chocolate.",NanFlag);
		assertTrue("Exception not thrown for adding a negative amount of chocolate",NegFlag);
	}

	public void testGetCoffee() {
		assertEquals("Incorrect amount of coffee.", default_amount, inventory.getCoffee());
	}

	public void testSetCoffee() {
		int new_amount = 10;
		inventory.setCoffee(new_amount);
		assertEquals("Amount of coffee not set correctly.", new_amount, inventory.getCoffee());
		inventory.setCoffee(-1);
		assertEquals("Negative amount of coffee set.", new_amount, inventory.getCoffee());
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
