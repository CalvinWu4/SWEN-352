package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;

/**
 * 
 * @author Sarah Heckman
 *
 * Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest extends TestCase {
	
	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	protected void setUp() throws Exception {
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		super.setUp();
	}
	
	public void testAddRecipe() {
		Recipe temp;
		boolean contains = false;
		cm.addRecipe(r1);
		Recipe[] array = cm.getRecipes();
		for (int i = 0; i < array.length; i++) {
			temp = array[i];
			if (r1 == temp) {
				contains = true;
			}
		}
		assertTrue(contains);
	}
	
	public void testDeleteRecipe() {
		Recipe temp;
		boolean contains = false;

		cm.addRecipe(r1);
		Recipe[] array_added = cm.getRecipes();
		
		for (Recipe recipe : array_added) {
			if (recipe == null || recipe.getName() == "") {
				continue;
			}
			else	
			{
				contains = true;
			}
		}
		assertTrue(contains);
		contains = false;
		String name = cm.deleteRecipe(0);
		Recipe[] array_deleted = cm.getRecipes();
		for (Recipe recipe : array_added) {
			if (recipe != null && recipe.getName() != "") {
				contains = true;
			}
		}
		assertFalse(contains);
		assertTrue(r1.getName().equals(name));
		
		assertNull(cm.deleteRecipe(1));
		assertEquals("", cm.deleteRecipe(0)); //TODO This recipe was already deleted. Should it be null instead of ""?
	}
	
	public void testEditRecipe() {
		cm.addRecipe(r1);
		String name = cm.editRecipe(0, r2);
		assertEquals("",r1.getName(),name);
		Recipe[] arr = cm.getRecipes();
		Recipe temp = arr[0];
		assertEquals(r2.getName(), temp.getName());
	}
	
	public void testAddInventory() {
		try {
			cm.addInventory("4","7","0","9");
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	public void testAddInventoryException() {
		try {
			cm.addInventory("4", "-1", "asdf", "3");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			//success if thrown
		}
	}
	
	public void testCheckInventory() {
		fail("Not yet implemented");
	}
	
	public void testMakeCoffee() {
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}
	
	public void testGetRecipes() {
		fail("Not yet implemented");
	}

}
