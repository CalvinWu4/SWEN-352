package edu.ncsu.csc326.coffeemaker;

import java.lang.reflect.Field;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
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
	}

	public void testGetChocolate() {
		assertEquals("The inventory did not start with the default amount of chocolate.",default_amount,inventory.getChocolate());
	}

	public void testSetPositiveChocolate() {
		//Test with positive number
		int new_amount = 10;
		inventory.setChocolate(new_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("chocolate");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertEquals("The amount of chocolate in the inventory is incorrect.",new_amount,testAmount);
	}
	
	public void testSetNegativeChocolate(){
		//Test with negative number.
		Field field;
		int testAmount = 0;
		int negative_amount = -1;
		inventory.setChocolate(negative_amount);
		
		try {
			field = inventory.getClass().getDeclaredField("chocolate");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		assertEquals("Amount of chocolate was set while trying to add a negative amount.",default_amount, testAmount);
	}

	public void testAddPositiveChocolate() {
		int add = 10;
		inventory.setChocolate(default_amount);
		try {
			inventory.addChocolate(String.valueOf(add));
		} catch (InventoryException e) {
			assertTrue("Inventory Exception was thrown.",false);
		}
		assertEquals("Amount of chocolate not increased.",default_amount + add, inventory.getChocolate());
	}
	
	public void testAddNegativeChocolate() {
		inventory.setChocolate(default_amount);
		boolean NegFlag = false;
		String neg = "-1";
		try {
			inventory.addChocolate(neg);
		}
		catch (InventoryException e) {
			NegFlag = true;
		}
		assertTrue("Exception not thrown for adding a negative amount of chocolate",NegFlag);
	}
	
	public void testAddNotANumberChocolate() {
		inventory.setChocolate(default_amount);
		boolean NanFlag = false;
		String neg = "a";
		try {
			inventory.addChocolate(neg);
		}
		catch (InventoryException e) {
			NanFlag = true;
		}
		assertTrue("Exception not thrown for adding a letters worth of chocolate.",NanFlag);
	}

	public void testGetCoffee() {
		assertEquals("Incorrect default amount of coffee.", default_amount, inventory.getCoffee());
	}

	public void testSetPositiveCoffee() {
		int new_amount = 10;
		inventory.setCoffee(new_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("coffee");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertEquals("Amount of coffee not set correctly.", new_amount, testAmount);
	}
	
	public void testSetNegativeCoffee() {
		Field field;
		int newAmount = -1;
		inventory.setCoffee(newAmount);
		int testAmount = 0;
		
		try {
			field = inventory.getClass().getDeclaredField("coffee");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertEquals("Negative amount of coffee set.", default_amount, testAmount);
	}

	public void testAddCoffeePositive() {
		int add =  5; //How much Coffee is being added
		inventory.setCoffee(default_amount);
		try {
			inventory.addCoffee(String.valueOf(add));
		} catch(InventoryException e) {
			//TODO Auto created Catch
		}
		assertEquals("Coffee amount not correct", default_amount+add, inventory.getCoffee());
	}
	
	public void testAddCoffeeLetter() {
		boolean LetterFlag = false; //Checks if letter tries to be added
		inventory.setCoffee(default_amount);
		try {
			inventory.addCoffee("a");
		} catch(InventoryException e) {
			LetterFlag = true;
		}
		assertTrue("Exception not thrown for adding a letter instead of a positive number.", LetterFlag);
	
	}
	public void testAddCoffeeNegative() {
		int add = -20;
		boolean NegativeFlag = false; //Checks if negative number tries to be added
		inventory.setCoffee(default_amount);
		try {
			inventory.addCoffee(String.valueOf(add));
		} catch(InventoryException e) {
			NegativeFlag = true;
		}
		assertTrue("Exception not thrown for adding a negative number instead of a positive number.", NegativeFlag);
	}
	
	public void testGetMilk() {
		assertEquals("Wrong amount of Milk.", default_amount, inventory.getMilk());
	}
	
	public void testSetMilkPos() {
		int pos_amount = 10;
		inventory.setMilk(pos_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("milk");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertEquals("Amount of coffee not set correctly.", pos_amount, testAmount);
	}
	
	public void testSetMilkNeg() {
		int neg_amount = -10;
		inventory.setMilk(neg_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("milk");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertNotSame("Negative amount of coffee set.", neg_amount, testAmount);
	}

	public void testAddMilkPositive() {
		int add =  5; //How much Coffee is being added
		inventory.setMilk(default_amount);
		try {
			inventory.addMilk(String.valueOf(add));
		} catch(InventoryException e) {
			//TODO Auto created Catch
		}
		assertEquals("Milk amount not correct", default_amount+add, inventory.getMilk());
	}
	
	public void testAddMilkLetter() {
		boolean LetterFlag = false; //Checks if letter tries to be added
		inventory.setMilk(default_amount);
		try {
			inventory.addMilk("a");
		} catch(InventoryException e) {
			LetterFlag = true;
		}
		assertTrue("Exception not thrown for adding a letter instead of a positive number.", LetterFlag);
	
	}
	public void testAddMilkNegative() {
		int add = -20;
		boolean NegativeFlag = false; //Checks if negative number tries to be added
		inventory.setMilk(default_amount);
		try {
			inventory.addMilk(String.valueOf(add));
		} catch(InventoryException e) {
			NegativeFlag = true;
		}
		assertTrue("Exception not thrown for adding a negative number instead of a positive number.", NegativeFlag);
	}

	public void testGetSugar() {
		assertEquals("Wrong amount of Sugar.", default_amount, inventory.getSugar());
	}

	public void testSetSugarPos() {
		int pos_amount = 10;
		inventory.setSugar(pos_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("sugar");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertEquals("Amount of coffee not set correctly.", pos_amount, testAmount);
	}
	
	public void testSetSugarNeg() {
		int neg_amount = -10;
		inventory.setSugar(neg_amount);
		
		Field field;
		int testAmount = 0;
		try {
			field = inventory.getClass().getDeclaredField("sugar");
			field.setAccessible(true);
			testAmount = field.getInt(inventory);
		} catch (NoSuchFieldException | SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}
		
		assertNotSame("Negative amount of coffee set.", neg_amount, testAmount);
	}

	public void testAddSugarPositive() {
		int add =  5; //How much Coffee is being added
		inventory.setSugar(default_amount);
		try {
			inventory.addSugar(String.valueOf(add));
		} catch(InventoryException e) {
			//TODO Auto created Catch
		}
		assertEquals("Sugar amount not correct", default_amount+add, inventory.getSugar());
	}
	
	public void testAddSugarLetter() {
		boolean LetterFlag = false; //Checks if letter tries to be added
		inventory.setSugar(default_amount);
		try {
			inventory.addSugar("a");
		} catch(InventoryException e) {
			LetterFlag = true;
		}
		assertTrue("Exception not thrown for adding a letter instead of a positive number.", LetterFlag);
	
	}
	public void testAddSugarNegative() {
		int add = -10;
		boolean NegativeFlag = false; //Checks if negative number tries to be added
		inventory.setSugar(default_amount);
		try {
			inventory.addSugar(String.valueOf(add));
		} catch(InventoryException e) {
			NegativeFlag = true;
		}
		assertTrue("Exception not thrown for adding a negative number instead of a positive number.", NegativeFlag);
	}

	public void testEnoughIngredients() {
		Recipe R = new Recipe();
		boolean NewR = true;
		boolean isEnough = true;
		inventory.setChocolate(default_amount);
		inventory.setCoffee(default_amount);
		inventory.setMilk(default_amount);
		inventory.setSugar(default_amount);
			try {
				R.setName("recipe");
				R.setPrice("2040");
				R.setAmtCoffee(String.valueOf(default_amount));
				R.setAmtMilk(String.valueOf(default_amount));
				R.setAmtSugar(String.valueOf(default_amount));
				R.setAmtChocolate(String.valueOf(default_amount));
			} catch (RecipeException e) {
				NewR = false;
			}
			assertTrue(inventory.getCoffee() >= R.getAmtCoffee());
			assertTrue(inventory.getMilk() >= R.getAmtMilk());
			assertTrue(inventory.getSugar() >= R.getAmtSugar());
			assertTrue(inventory.getChocolate() >= R.getAmtChocolate());
			assertTrue("You don;t have enough ingredients", inventory.enoughIngredients(R) == true);
			try {
				R.setAmtCoffee(String.valueOf(default_amount+1));
				R.setAmtMilk(String.valueOf(default_amount+1));
				R.setAmtSugar(String.valueOf(default_amount+1));
				R.setAmtChocolate(String.valueOf(default_amount+1));
			} catch (RecipeException e) {
				NewR = false;
			}
			assertFalse(inventory.getCoffee() >= R.getAmtCoffee());
			assertFalse(inventory.getMilk() >= R.getAmtMilk());
			assertFalse(inventory.getSugar() >= R.getAmtSugar());
			assertFalse(inventory.getChocolate() >= R.getAmtChocolate());	

	}

	public void testUseIngredients() {
		Recipe R = new Recipe();
		boolean NewR = true;
		boolean isEnough = true;
		inventory.setChocolate(default_amount);
		inventory.setCoffee(default_amount);
		inventory.setMilk(default_amount);
		inventory.setSugar(default_amount);
			try {
				R.setName("recipe");
				R.setPrice("2040");
				R.setAmtCoffee(String.valueOf(default_amount));
				R.setAmtMilk(String.valueOf(default_amount));
				R.setAmtSugar(String.valueOf(default_amount));
				R.setAmtChocolate(String.valueOf(default_amount));
			} catch (RecipeException e) {
				NewR = false;
			}
		assertTrue(inventory.useIngredients(R));	
		try {
			R.setAmtCoffee(String.valueOf(default_amount-1));
			R.setAmtMilk(String.valueOf(default_amount-1));
			R.setAmtSugar(String.valueOf(default_amount-1));
			R.setAmtChocolate(String.valueOf(default_amount-1));
		} catch (RecipeException e) {
			NewR = false;
		}
		assertFalse(inventory.useIngredients(R));
	}

	public void testToString() {
		assertEquals(inventory.toString(), ("Coffee: " +
		inventory.getCoffee() + "\n" + "Milk: " +
		inventory.getMilk() + "\n" + "Sugar: " +
		inventory.getSugar() + "\n" + "Chocolate: " +
    	inventory.getChocolate() + "\n")); 
	}

}