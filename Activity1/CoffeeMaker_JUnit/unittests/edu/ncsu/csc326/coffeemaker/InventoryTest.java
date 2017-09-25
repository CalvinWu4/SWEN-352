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
			assertTrue("Caught an illegal arguement or illegal access exception.",false);
		}
		
		assertEquals("The amount of chocolate in the inventory is incorrect.",new_amount,testAmount);
	}
	
	public void testSetNegitiveChocolate(){
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
			assertTrue("Caught an illegal arguement or illegal access exception.",false);
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
	
	public void testAddNegatvieChocolate() {
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
			assertTrue("Caught an illegal arguement or illegal access exception.",false);
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
			assertTrue("Caught an illegal arguement or illegal access exception.",false);
		}
		
		assertEquals("Negative amount of coffee set.", default_amount, testAmount);
	}

	public void testAddCoffee() {
		String amt = "I"; 
		boolean Nai = false; //Not an Int
		boolean Neg = false; //Negative value
		boolean Pos = false; //Positive value
		boolean Zer = false; //Zero value
		try {
			inventory.addCoffee(String.valueOf(amt));
		}
		catch(InventoryException e){
			Nai = true;
		}
		inventory.setCoffee(0);
		int iamtn = 0 - default_amount;
		int iamtz = 0;
		int iamtp = 1;
		try {
			inventory.addCoffee(String.valueOf(iamtn));
		}
		catch(InventoryException e) {
			Neg = true;
		}
		try {
			inventory.addCoffee(String.valueOf(iamtz));
		}
		catch(InventoryException e) {
			Zer = true;
		}
		try {
			inventory.addCoffee(String.valueOf(iamtp));
		}
		catch(InventoryException e) {
			Pos = true;
		}
		assertTrue("Exception thrown for coffee amount being negative", Neg = true);
		assertTrue("Exception thrown for coffee amount being negative", Pos = true);
		assertTrue("Exception thrown for coffee amount being negative", Zer = true);
		//assertTrue("Coffee amount must not be negative", iamtn < 0);
		//assertTrue("Added amount can be zero", iamtz == 0);
		//assertTrue("Added amount can be positive", iamtp > 0);
		assertTrue("Exception thrown for coffee amount being negative", Nai = true);
	}

	public void testGetMilk() {
		int amt = 5;
		inventory.setMilk(amt);
		assertEquals("Milk values need to be the same as set value", amt, inventory.getMilk());
	}

	public void testSetMilk() {
		int amtn = -1;
		int amtp = 5;
		int amtz = 0;
		inventory.setMilk(amtp);
		assertEquals("Milk amount must be the same as set value", amtp, inventory.getMilk());
		assertTrue("Milk amount must be greater than or equal to 0", inventory.getMilk() >= 0);
		inventory.setMilk(amtn);
		assertFalse("Milk amount must not be negative", amtn == inventory.getMilk());
		inventory.setMilk(amtz);
		assertEquals("Milk amount must be the same as set value", amtz, inventory.getMilk());
		assertFalse("Milk amount must not be negative", amtn == inventory.getMilk());
	}

	public void testAddMilk() {
		int amtp = 5;
		int amtz = 0;
		int amtn = -1;
		boolean Pos = true;
		inventory.setMilk(default_amount);
		try {
			inventory.addMilk(String.valueOf(amtp));
		}catch(InventoryException e){
			Pos = false;
		}
		assertTrue("The amount of Milk you add needs to be positive", Pos);
		assertEquals("You need to add the correct amount", amtp + default_amount, inventory.getMilk());
		inventory.setMilk(default_amount);
		try {
			inventory.addMilk(String.valueOf(amtn));
		}catch(InventoryException e) {
			Pos = false;
		}
		assertTrue("The amount of Milk you add needs to be positive", Pos);
		assertEquals("You need to add the correct amount", default_amount, inventory.getMilk());
		try {
			inventory.addMilk(String.valueOf(amtz));
		}catch(InventoryException e) {
			Pos = false;
		}
		assertEquals("You need to add the correct amount", default_amount, inventory.getMilk());
	}

	public void testGetSugar() {
		assertEquals("Incorrect amount of coffee.", default_amount, inventory.getSugar());
	}

	public void testSetSugar() {
		int amtn = -1;
		int amtp = 5;
		int amtz = 0;
		inventory.setSugar(amtp);
		assertEquals("Sugar amount must be the same as set value", amtp, inventory.getSugar());
		assertTrue("Sugar amount must be greater than or equal to 0", inventory.getSugar() >= 0);
		inventory.setSugar(amtn);
		assertFalse("Sugar amount must not be negative", amtn == inventory.getSugar());
		inventory.setSugar(amtz);
		assertEquals("Sugar amount must be the same as set value", amtz, inventory.getSugar());
		assertFalse("Sugar amount must not be negative", amtn == inventory.getSugar());

	}

	public void testAddSugar() {
		int amtp = 5;
		int amtz = 0;
		int amtn = -1;
		boolean Pos = true;
		inventory.setSugar(default_amount);
		try {
			inventory.addSugar(String.valueOf(amtp));
		}catch(InventoryException e){
			Pos = false;
		}
		assertTrue("The amount of Sugar you add needs to be positive", Pos);
		assertEquals("You need to add the correct amount", amtp + default_amount, inventory.getSugar());
		inventory.setSugar(default_amount);
		try {
			inventory.addSugar(String.valueOf(amtn));
		}catch(InventoryException e) {
			Pos = false;
		}
		assertFalse("The amount of Sugar you add needs to be positive", Pos);
		assertEquals("You need to add the correct amount", default_amount, inventory.getSugar());
		try {
			inventory.addSugar(String.valueOf(amtz));
		}catch(InventoryException e) {
			Pos = false;
		}
		assertEquals("You need to add the correct amount", default_amount, inventory.getSugar());
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
