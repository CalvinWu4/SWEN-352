package edu.ncsu.csc326.coffeemaker1;

import junit.framework.TestCase;

import java.lang.reflect.Field;

import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.RecipeBook;

public class RecipeBookTest extends TestCase {

	private RecipeBook recipeBook;
	private Recipe recipe;

	protected void setUp() throws Exception {
		recipeBook = new RecipeBook();
		recipe = new Recipe();
		recipe.setName("test");
		recipe.setPrice("1");
		recipe.setAmtSugar("1");
		recipe.setAmtChocolate("1");
		recipe.setAmtCoffee("1");
		recipe.setAmtMilk("1");
		super.setUp();
	}

	public Recipe[] getArray(String field){
		Recipe[] result = null;
		try {
			Field afield = RecipeBook.class.getDeclaredField(field);
			afield.setAccessible(true);
			result = (Recipe[]) afield.get(recipeBook);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Calvin
	public void testRecipeBook() {
		assertNotNull("RecipeBook not created.", recipeBook);
	}

	//Calvin
	public void testGetRecipes() {
		assertEquals("The getter for RecipeBook is incorrect.",getArray("recipeArray"), recipeBook.getRecipes());
	}

	//Calvin
	public void testAddRecipe() {
		recipeBook.addRecipe(recipe);
		System.out.println(	recipeBook.getRecipes()[0]);
		assertEquals("Recipe cannot be added", recipeBook.getRecipes()[0], recipe);
	}

	public void testAddDupRecipe() {
		recipeBook.addRecipe(recipe);
		RecipeBook before = recipeBook;
		recipeBook.addRecipe(recipe);
		assertEquals("Duplicate recipe was added when it shouldn't have been.", recipeBook, before);
		}

	public void testAddRecipeToFullBook() {
		recipeBook.addRecipe(recipe);
		recipe.setName("test2");
		recipeBook.addRecipe(recipe);
		recipe.setName("test3");
		recipeBook.addRecipe(recipe);
		recipe.setName("test4");
		RecipeBook before = recipeBook;
		assertEquals("Recipe was added to a full recipebook.", recipeBook, before);
	}

	//Calvin
	public void testDeleteNullRecipe() {
		RecipeBook before = recipeBook;
		recipeBook.deleteRecipe(0);
		assertEquals("Null recipe cannot be deleted", recipeBook, before);
	}

	//Calvin
	public void testDeleteRecipe() {
		RecipeBook before = recipeBook;
		recipeBook.addRecipe(recipe);
		recipeBook.deleteRecipe(0);
		assertEquals("Recipe was no deleted", recipeBook, before);
	}

	//Calvin
	public void testgetDeletedRecipeName() {
		recipeBook.addRecipe(recipe);
		assertEquals("Wrong deleted recipe index returned", recipeBook.deleteRecipe(0),
				"test");
	}

	//Calvin
	public void testEditRecipe() {
		fail("Not yet implemented");
	}

}
