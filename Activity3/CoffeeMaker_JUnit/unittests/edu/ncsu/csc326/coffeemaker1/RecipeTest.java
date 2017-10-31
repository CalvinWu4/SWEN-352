package edu.ncsu.csc326.coffeemaker1;

import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;

import java.lang.reflect.Field;

public class RecipeTest extends TestCase {


	private Recipe recipe;
	private Recipe recipe2;

	private static final int default_amount = 0;
	private static final String default_name = "";

	private static final int positive_amount = 1;
	private static final int negative_amount = 0;
	private static final int zero_amount = -1;
	private static final String test_string = "test";


	public void setUp() throws Exception {
		recipe = new Recipe();
		recipe2 = new Recipe();
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

	/*public void testHashCode() {
		fail("Not yet implemented");
	}*/

	public void testEqualObjects() throws RecipeException {
		recipe.setName("Mocha");
		recipe.setAmtChocolate("1");
		recipe.setAmtCoffee("3");
		recipe.setAmtMilk("3");
		recipe.setAmtSugar("1");

		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("1");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("3");
		recipe2.setAmtSugar("1");

		assertTrue("The two objects didn't match",recipe.equals(recipe2));
	}

	public void testUnequalObjects() throws RecipeException {
		recipe.setName("Americano");
		recipe.setAmtCoffee("3");

		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("1");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("3");
		recipe2.setAmtSugar("1");

		assertFalse("The two unequal objects match",recipe.equals(recipe2));
	}

	public void testRecipeConstructor() throws RecipeException {
		//Testing the creation of a new recipe object
		assertEquals("Constructor set the incorrect name.",
				getString("name"),default_name);
		assertEquals("Constructor set the incorrect amount of chocolate.",
				getInt("amtChocolate"),default_amount);
		assertEquals("Constructor set the incorrect amount of milk.",
				getInt("amtMilk"),default_amount);
		assertEquals("Constructor set the incorrect amount of sugar.",
				getInt("amtSugar"),default_amount);
		assertEquals("Constructor set the incorrect price.",
				getInt("price"),default_amount);
	}

	public void testGetAmtChocolate() {
		assertEquals("Getter must return the correct Chocolate amount.", recipe.getAmtChocolate(),
				getInt("amtChocolate"));
	}

	public void testSetAmtChocolate() throws RecipeException {
		//Test with positive number
		String new_amount = "1";
		recipe.setAmtChocolate(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtChocolate");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of chocolate in the recipe is incorrect.",new_amount,testAmount);
	}

	public void testSetZeroChocolate() throws RecipeException {
		//Test with positive number
		String new_amount = "0";
		recipe.setAmtChocolate(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtChocolate");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of chocolate in the recipe is incorrect.",new_amount,testAmount);
	}

	// Throws an exception for negative numbers.
//
//	public void testSetNegAmtChocolate() throws RecipeException {
//		//Test with positive number
//		String new_amount = "-10";
//		recipe.setAmtChocolate(new_amount);
//
//		Field field;
//		String testAmount = "0";
//		try {
//			field = recipe.getClass().getDeclaredField("amtChocolate");
//			field.setAccessible(true);
//			testAmount = "" + field.getInt(recipe);
//		} catch (NoSuchFieldException | SecurityException e2) {
//			e2.printStackTrace();
//			//Stop test if exception is thrown, as there is an error with the test.
//			assertTrue("Caught an No Such Field or Security exception.",false);
//		} catch (IllegalArgumentException | IllegalAccessException e1) {
//			e1.printStackTrace();
//			//Stop test if exception is thrown, as there is an error with the test.
//			assertTrue("Caught an illegal argument or illegal access exception.",false);
//		}
//
//		assertNotSame("The amount of chocolate should not be negative.",new_amount,testAmount);
//	}

	public void testGetAmtCoffee() {
		assertEquals("Getter must return the correct coffee amount.", recipe.getAmtCoffee(),
				getInt("amtCoffee"));
	}

	public void testSetAmtCoffee() throws RecipeException {
		//Test with positive number
		String new_amount = "1";
		recipe.setAmtCoffee(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtCoffee");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of coffee in the recipe is incorrect.",new_amount,testAmount);
	}
	public void testSetZeroCoffee() throws RecipeException {
		//Test with positive number
		String new_amount = "0";
		recipe.setAmtCoffee(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtCoffee");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of coffee in the recipe is incorrect.",new_amount,testAmount);
	}

	public void testGetAmtMilkNonInt() {
		assertEquals("Getter must return the correct milk amount.", recipe.getAmtMilk(),
				getInt("amtMilk"));
	}

	public void testSetAmtMilk() throws RecipeException {
		//Test with positive number
		String new_amount = "1";
		recipe.setAmtMilk(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtMilk");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of milk in the recipe is incorrect.",new_amount,testAmount);
	}
	public void testSetZeroMilk() throws RecipeException {
		//Test with positive number
		String new_amount = "0";
		recipe.setAmtMilk(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtMilk");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of milk in the recipe is incorrect.",new_amount,testAmount);
	}

	//Calvin
	public void testGetAmtSugar() {
		assertEquals("Getter must return the correct sugar amount.", recipe.getAmtSugar(),
				getInt("amtSugar"));
	}

	public void testSetAmtSugar() throws RecipeException {
		//Test with positive number
		String new_amount = "1";
		recipe.setAmtSugar(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtSugar");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of sugar in the recipe is incorrect.",new_amount,testAmount);
	}
	public void testSetZeroSugar() throws RecipeException {
		//Test with positive number
		String new_amount = "0";
		recipe.setAmtSugar(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("amtSugar");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The amount of sugar in the recipe is incorrect.",new_amount,testAmount);
	}


	//Calvin
	public void testGetName() {
		assertEquals("Must return the correct recipe name.", recipe.getName(), getString("name"));
	}

	//Calvin
	public void testSetName() {
		String before = getString("name");
		recipe.setName(null);
		assertEquals(before, getString("name"));
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
		//Test with positive number
		String new_amount = "1";
		recipe.setPrice(new_amount);

		Field field;
		String testAmount = "0";
		try {
			field = recipe.getClass().getDeclaredField("price");
			field.setAccessible(true);
			testAmount = "" + field.getInt(recipe);
		} catch (NoSuchFieldException | SecurityException e2) {
			e2.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an No Such Field or Security exception.",false);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
			//Stop test if exception is thrown, as there is an error with the test.
			assertTrue("Caught an illegal argument or illegal access exception.",false);
		}

		assertEquals("The price of the recipe is incorrect.",new_amount,testAmount);
	}

	//Calvin
	public void testToString() {
		assertEquals("Must return the correct recipe name.", recipe.toString(), getString("name"));
	}

}
