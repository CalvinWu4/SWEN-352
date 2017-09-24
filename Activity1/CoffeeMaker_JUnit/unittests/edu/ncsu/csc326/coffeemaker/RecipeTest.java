package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;

import java.lang.reflect.Field;

public class RecipeTest extends TestCase {


	private Recipe recipe;
	private int positive_amount = 1;
	private int negative_amount = -1;
	private int zero_amount = 0;

	public void setUp() throws Exception {
		recipe = new Recipe();
		super.setUp();
	}

	// Helper function for getting private field values from Recipe
	public int getInt(String field){
		int result = 999;
		try {
			Field afield = Recipe.class.getDeclaredField(field);
			afield.setAccessible(true);
			result = afield.getInt(recipe);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void testHashCode() {
		fail("Not yet implemented");
	}

	public void testRecipe() {
		fail("Not yet implemented");
	}

	public void testGetAmtChocolate() {
		fail("Not yet implemented");
	}

	public void testSetAmtChocolate() {
		fail("Not yet implemented");
	}

	public void testGetAmtCoffee() {
		fail("Not yet implemented");
	}

	public void testSetAmtCoffee() {
		fail("Not yet implemented");
	}

	public void testGetAmtMilk() {
		fail("Not yet implemented");
	}

	public void testSetAmtMilk() {
		fail("Not yet implemented");
	}

	//Calvin

	public void testGetAmtSugar() {
		assertEquals("Getter must return the correct sugar amount.", recipe.getAmtSugar(),
				getInt("amtSugar"));
	}

	public void testSetAmtSugar() throws RecipeException {
		recipe.setAmtSugar(String.valueOf(positive_amount));
		assertEquals("Sugar amount must be the same as set value", positive_amount, getInt("amtSugar"));
		try{
			recipe.setAmtSugar(String.valueOf(negative_amount));
			fail("Sugar amount cannot be negative");
		}
		catch(RecipeException e){
		}
		try{
			recipe.setAmtSugar(String.valueOf(zero_amount));
			fail("Sugar amount cannot be zero");
		}
		catch(RecipeException e){
		}
		assertTrue("Sugar amount must be positive", getInt("amtSugar") > 0);
	}

	//Calvin
	public void testGetName() {
		String getName = "";
		String setName = "Recipe 1";
		try {
			getName = Recipe.class.getDeclaredField("name").toString();

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		getName = setName;
		assertEquals("Getter must return the correct recipe name.", getName, setName);
	}

	//Calvin
	public void testSetName() {
		String getName = "";
		String setName = "Recipe 1";
//		recipe.setName(null);
//		assertEquals("Name value must match the set value.", name, recipe);
//		String before = name;
//		assertEquals("Name value must ignore the null value.", name, recipe.name);

	}

	//Calvin
	public void testGetPrice() {
		fail("Not yet implemented");
	}

	//Calvin
	public void testSetPrice() {
		fail("Not yet implemented");
	}

	//Calvin
	public void testToString() {
		fail("Not yet implemented");
	}

	//Calvin
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
