package edu.ncsu.csc326.coffeemaker;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;
import edu.ncsu.csc326.coffeemaker.Recipe;
import edu.ncsu.csc326.coffeemaker.RecipeBook;

public class RecipeBookTest extends TestCase {

	public ArrayList<Recipe> arrayOfRecipes;
	
	/** 
     * Sets up the test fixture. 
     * (Called before every test case).
     */ 
	protected void setUp() throws Exception {
		
		Recipe r1;
		Recipe r2;
		Recipe r3;
		Recipe r4;
		
		arrayOfRecipes = new ArrayList<Recipe>();
	
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");

		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");

		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");

		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		arrayOfRecipes.add(r1);
		arrayOfRecipes.add(r2);
		arrayOfRecipes.add(r3);
		arrayOfRecipes.add(r4);
		super.setUp();
	}
	
	/**
	 * Tears down the test fixture.
	 * (Called after every test case).
	 */
	public void tearDown() throws Exception {
		arrayOfRecipes = null;
		super.tearDown();
	}
	
	/**
	 * Test if a recipe gets added in the recipe book.
	 */
	public void testAddRecipe() {
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		assertEquals("Recipe sizes are different", recipeBook.getRecipes().length, arrayOfRecipes.size());
	}
	
	/**
	 * Test if more recipes can be added in the recipe book which exceeds the max limit. 	
	 */
	public void testAddMoreThanMax() throws Exception {
		Recipe r5;
		RecipeBook recipeBook = new RecipeBook();
		
		r5 = new Recipe();
		r5.setName("Bananas");
		r5.setAmtChocolate("1");
		r5.setAmtCoffee("0");
		r5.setAmtMilk("2");
		r5.setAmtSugar("0");
		r5.setPrice("4");
	
		arrayOfRecipes.add(r5);
		
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		assertNotSame("Recipe book allows more recipes than the max", recipeBook.getRecipes().length, arrayOfRecipes.size());
	}
	
	/**
	 * Test if a recipe is added in the recipe book.
	 */
	public void testIsRecipeAdded() {
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}		
		assertTrue("Recipe book should contain Mocha Recipe", Arrays.asList(recipeBook.getRecipes()).contains(arrayOfRecipes.get(1)));
	}
	
	/**
	 * Test if a null recipe can be added in the recipe book.
	 */
	public void testAddNULLRecipeToEmptyBook() {
		
		RecipeBook recipeBook = new RecipeBook();
		
		boolean bool = recipeBook.addRecipe(null);
		assertEquals("Should be no recipes in the book", null, recipeBook.getRecipes()[0]);
		assertEquals("Should be no recipes in the book", null, recipeBook.getRecipes()[1]);
		assertEquals("Should be no recipes in the book", null, recipeBook.getRecipes()[2]);
		assertEquals("Should be no recipes in the book", null, recipeBook.getRecipes()[3]);
		assertTrue("'NULL' recipe should not be added to the recipe book, but is added", bool == false);
	}
	
	/**
	 * Test if a recipe that already exists in the recipe book can be added.
	 * @throws Exception
	 */
	public void testAddExistingRecipe() throws Exception {		
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		
		boolean wasAdded = recipeBook.addRecipe(arrayOfRecipes.get(1));
		
		assertEquals("The recipe already exists and should not have been added", wasAdded, false);
	}
	
	
	/**
	 * Test if a recipe is removed from the recipe book.
	 */
	public void testIsRecipeRemoved() {
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		recipeBook.deleteRecipe(2);
			
		assertTrue("Recipe book should NOT contain Latte Recipe", !Arrays.asList(recipeBook.getRecipes()).contains(arrayOfRecipes.get(2)));
	}
	
	/**
	 * Test to delete a recipe not in the recipe book.
	 */
	public void testDeleteRecipeNotInBook(){
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		String nameOfRecipe = recipeBook.deleteRecipe(4);
	
		assertTrue("Should return NULL but does not", nameOfRecipe == null);
	}
	
	/**
	 * Test to edit a recipe that does NOT exist in the recipe book (>= index 4).
	 * @throws Exception
	 */
	public void testEditRecipeNotInBookPositive() throws Exception {
		Recipe rnew;
		rnew = new Recipe();
		rnew.setName("Bananas");
		rnew.setAmtChocolate("1");
		rnew.setAmtCoffee("0");
		rnew.setAmtMilk("2");
		rnew.setAmtSugar("0");
		rnew.setPrice("4");
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}

		String nameOfRecipe = recipeBook.editRecipe(4, rnew);
	
		assertTrue("Should return NULL but does not", nameOfRecipe == null);
	}
	
	/**
	 * Test to edit a recipe that does exist in the recipe book.
	 * @throws Exception
	 */
	public void testEditRecipeInBook() throws Exception {
		Recipe rnew;

		rnew = new Recipe();
		rnew.setName("Bananas");
		rnew.setAmtChocolate("1");
		rnew.setAmtCoffee("0");
		rnew.setAmtMilk("2");
		rnew.setAmtSugar("0");
		rnew.setPrice("4");
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}

		String nameOfRecipe = recipeBook.editRecipe(3, rnew);
	
		assertTrue("Should return the name of the old recipe", nameOfRecipe.equals("Hot Chocolate"));	
		assertTrue("The name of the new recipe should not be changed", rnew.getName().equals("Bananas"));
	}
	
	/**
	 * Test to edit a recipe that does NOT exist in the recipe book (<= index -1).
	 * @throws Exception
	 */
	public void testEditRecipeNotInBookNegative() throws Exception {
		Recipe rnew;

		rnew = new Recipe();
		rnew.setName("Bananas");
		rnew.setAmtChocolate("1");
		rnew.setAmtCoffee("0");
		rnew.setAmtMilk("2");
		rnew.setAmtSugar("0");
		rnew.setPrice("4");
		
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}

		String nameOfRecipe = recipeBook.editRecipe(-1, rnew);
		
		assertTrue("Should return NULL but does not", nameOfRecipe == null);
	}
	
	/**
	 * Test to edit a recipe that does NOT exist with a new recipe name. 
	 * @throws Exception
	 */
	public void testEditNULLRecipeInBook() throws Exception {
		RecipeBook recipeBook = new RecipeBook();
		
		Recipe rnew;
		
		rnew = new Recipe();
		rnew.setName("Bananas");
		rnew.setAmtChocolate("1");
		rnew.setAmtCoffee("0");
		rnew.setAmtMilk("2");
		rnew.setAmtSugar("0");
		rnew.setPrice("4");
		
		String str = recipeBook.editRecipe(1, rnew);
		assertTrue("NULL recipe should not be edited", str == null);
	}
	
	/**
	 * Test to edit a recipe that does exist with NULL.
	 * @throws Exception
	 */
	public void testEditRecipeWithNULLInBook() throws Exception {
		RecipeBook recipeBook = new RecipeBook();
		for (Recipe recipe: arrayOfRecipes) {
			recipeBook.addRecipe(recipe);
		}
		
		String str = recipeBook.editRecipe(1, null);
		assertTrue("NULL recipe should not be added to the recipe book", recipeBook.getRecipes()[1] != null);
		assertTrue("Expected a null string returned to indicate not added", str == null);
	}
	
}