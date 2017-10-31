package edu.ncsu.csc326.coffeemaker1;

import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Recipe;
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
		assertTrue("Recipe not added.",contains);
	}
	
	public void testDeleteRecipe() {
		Recipe temp;
		boolean contains = false;
		cm.addRecipe(r1);
		contains = false;
		//R1 will be the recipe in slot 0, because it was the first (and only) one added.
		String name = cm.deleteRecipe(0);
		Recipe[] array_deleted = cm.getRecipes();
		for (Recipe recipe : array_deleted) {
			if (recipe != null && recipe.getName() != "") {
				contains = true;
			}
		}
		assertFalse("Recipe was not deleted.",contains);
		assertTrue("Wrong name returned while deleting recipe.",r1.getName().equals(name));
		
		assertNull("Null was not returned while deleting a recipe that did not exist.",cm.deleteRecipe(1));
		assertEquals("Incorrect return value while deleting a recipe that was already deleted.",
				"", cm.deleteRecipe(0)); //TODO This recipe was already deleted. Should it be null instead of ""?
	}
	
	public void testDeleteRecipeName() {
		cm.addRecipe(r1);
		//R1 will be the recipe in slot 0, because it was the first (and only) one added.
		String name = cm.deleteRecipe(0);
		assertTrue("Wrong name returned while deleting recipe.",r1.getName().equals(name));
	}
	
	public void testDeleteNullRecipe() {
		cm.addRecipe(r1);
		//R1 will be the recipe in slot 0, because it was the first (and only) one added.
		cm.deleteRecipe(0);
		
		assertNull("Null was not returned while deleting a recipe that did not exist.",cm.deleteRecipe(1));
	}
	
	public void testDeleteDeletedRecipe() {
		cm.addRecipe(r1);
		//R1 will be the recipe in slot 0, because it was the first (and only) one added.
		cm.deleteRecipe(0);
		assertEquals("Incorrect return value while deleting a recipe that was already deleted.",
				"", cm.deleteRecipe(0)); //TODO This recipe was already deleted. Should it be null instead of ""?
	}
	
	public void testEditRecipeReturnOldName() {
		cm.addRecipe(r1);
		String name = cm.editRecipe(0, r2);
		assertEquals("",r1.getName(),name);
	}
	
	public void testEditRecipeNewRecipeIsPlaced() {
		cm.addRecipe(r1);
		cm.editRecipe(0, r2);
		Recipe[] arr = cm.getRecipes();
		Recipe temp = arr[0];
		assertEquals(r2.getName(), temp.getName());
	}
	
	public void testAddInventory() {
		boolean exceptionFlag = true;
		try {
			cm.addInventory("4","7","0","9");
		} catch (InventoryException e) {
			exceptionFlag = false;
		}
		assertTrue("Exception thrown while adding valid numbers of ingedients.",exceptionFlag);
	}
	
	public void testAddInventoryException() {
		boolean exceptionFlag = false;
		try {
			cm.addInventory("4", "-1", "asdf", "3");
		} catch (InventoryException e) {
			exceptionFlag = true;
		}
		assertTrue("Exception not thrown while adding invalid number of ingedients.",exceptionFlag);
	}
	
	public void testCheckInventory() {
		assertEquals("","Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",cm.checkInventory());
		try {
			cm.addInventory("2", "2", "2", "2"); //TODO Believes sugar is not positive. Definitely is.
		} catch (InventoryException e) {
//			e.printStackTrace();
			assertTrue("Excpetion was thrown and should not have been.",false);
		}
		assertEquals("Amount of ingedient was not correctly added to the inventory.",
				"Coffee: 17\nMilk: 17\nSugar: 17\nChocolate: 17\n",cm.checkInventory());
	}
	
	public void testMakeCoffeeSuccess() {
		cm.addRecipe(r1);
		//Successful Transaction
		assertEquals("Successful transaction was not completed.",25, cm.makeCoffee(0, 75));
	}
	
	public void testMakeCoffeeNotEnoughPaid() {
		cm.addRecipe(r1);
		//User doesn't pay with enough money for a recipe.
		assertEquals("User was able to pay with not enough money.",1,cm.makeCoffee(0, 1));
	}
	
	public void testMakeCoffeeThatDoesntExist() {
		assertEquals("User was able to pay for deleted recipe.",75,cm.makeCoffee(0, 75));
	}
	
	public void testMakeCoffeeWithoutIngediants() {
		cm.addRecipe(r1);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		cm.makeCoffee(0, 75);
		//User attempts to pay, but not enough chocolate to make coffee.
		assertEquals("User was able to pay when there wasn't enough chocolate to make the mocha.",
				75, cm.makeCoffee(0, 75));
	}
	
	public void testGetRecipes() {
		//Make sure there are no initial recipes.
		boolean recipeExists = false;
		for (Recipe rec : cm.getRecipes()) {
			if(null != rec) {
				recipeExists = true;
			}
		}
		assertFalse("Recipe exists before any were added.",recipeExists);
		//Add a recipe and confirm it's gotten.
		cm.addRecipe(r1);
		boolean recipe1Exists = false;
		for (Recipe rec : cm.getRecipes()) {
			if(null != rec) {
				if (rec == r1){
					recipe1Exists = true;
				}
			}
		}
		assertTrue("Recipe was not added.",recipe1Exists);
		recipe1Exists = false;
		boolean recipe2Exists = false;
		cm.addRecipe(r2);
		for (Recipe rec : cm.getRecipes()) {
			if(null != rec) {
				if (rec == r1){
					recipe1Exists = true;
				}
				else if (rec == r2){
					recipe2Exists = true;
				}
			}
		}
		assertTrue("Recipe that was added earlier does not exist.",recipe1Exists);
		assertTrue("Newly added recipe does not exist.",recipe2Exists);
		//Make sure deleted recipe is gone.
		cm.deleteRecipe(0);
		recipe1Exists = false;
		recipe2Exists = false;
		for (Recipe rec : cm.getRecipes()) {
			if(null != rec) {
				if (rec == r1){
					recipe1Exists = true;
				}
				else if (rec == r2){
					recipe2Exists = true;
				}
			}
		}
		assertFalse("Newly deleted recipe exists.",recipe1Exists);
		assertTrue("Recipe that was here earlier does not exist.",recipe2Exists);
	}
}
