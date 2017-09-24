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

	// Helper function for getting int values from private fields in Recipe
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

	// Helper function for getting String values from private fields in Recipe
	public String getString(String field) {
		String result = "test";
		try {
			Field afield = Recipe.class.getDeclaredField(field);
			afield.setAccessible(true);
			result = (String) afield.get(recipe);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void testHashCode() {
		fail("Not yet implemented");
	}

	public void testEqualsObject() {
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
	}

	//Calvin
	public void testGetName() {
		assertEquals("Must return the correct recipe name.", recipe.getName(), getString("name"));
	}

	//Calvin
	public void testSetName() {
		recipe.setName("test");
		assertEquals("Name must be the same as set value", "test", getString("name"));
	}

	//Calvin
	public void testGetPrice() {
		assertEquals("Getter must return the correct price.", recipe.getPrice(),
				getInt("price"));
	}

	//Calvin
	public void testSetPrice() throws RecipeException {
		recipe.setPrice(String.valueOf(positive_amount));
		assertEquals("Price must be the same as set value", positive_amount, getInt("price"));
		try{
			recipe.setPrice(String.valueOf(negative_amount));
			fail("Price cannot be negative");
		}
		catch(RecipeException e){
		}
		try{
			recipe.setPrice(String.valueOf(zero_amount));
			fail("Price cannot be zero");
		}
		catch(RecipeException e){
		}
	}

	//Calvin
	public void testToString() {
		assertEquals("Must return the correct recipe name.", recipe.toString(), getString("name"));
	}

}
