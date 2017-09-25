package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;

import java.lang.reflect.Field;

public class RecipeBookTest extends TestCase {

	private RecipeBook rb;

	protected void setUp() throws Exception {
		rb = new RecipeBook();
		super.setUp();
	}

	public Recipe[] getArray(String field){
		Recipe[] result = null;
		try {
			Field afield = RecipeBook.class.getDeclaredField(field);
			afield.setAccessible(true);
			result = (Recipe[]) afield.get(rb);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Calvin
	public void testRecipeBook() {
		assertNotNull("RecipeBook not created.", rb);
	}

	//Calvin
	public void testGetRecipes() {
		assertEquals("The getter for RecipeBook is incorrect.",getArray("recipeArray"),rb.getRecipes());
	}

	//Calvin
	public void testAddRecipe() {
		fail("Not yet implemented");
	}

	//Calvin
	public void testDeleteRecipe() {
		fail("Not yet implemented");
	}

	//Calvin
	public void testEditRecipe() {
		fail("Not yet implemented");
	}

}
