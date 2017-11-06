/*
 * This file was automatically generated by EvoSuite
 * Mon Oct 23 01:05:16 EDT 2017
 */

package edu.ncsu.csc326.coffeemaker;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

@RunWith(EvoRunner.class) @EvoRunnerParameters(useVNET = true, separateClassLoader = true, useJEE = true) 
public class CoffeeMaker_ESTest extends CoffeeMaker_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      int int0 = coffeeMaker0.makeCoffee(0, 1);
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      int int0 = coffeeMaker0.makeCoffee(0, 1);
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      recipe0.setName("H");
      coffeeMaker0.addRecipe(recipe0);
      coffeeMaker0.editRecipe(0, recipe0);
      assertEquals("", recipe0.getName());
      assertEquals("", recipe0.toString());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      String string0 = coffeeMaker0.editRecipe(0, recipe0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      recipe0.setName("O");
      String string0 = coffeeMaker0.deleteRecipe(0);
      assertEquals("O", string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      String string0 = coffeeMaker0.deleteRecipe(0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      boolean boolean0 = coffeeMaker0.addRecipe(recipe0);
      boolean boolean1 = coffeeMaker0.addRecipe(recipe0);
      assertFalse(boolean1 == boolean0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      // Undeclared exception!
      try { 
        coffeeMaker0.makeCoffee((-1), 0);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -1
         //
         verifyException("edu.ncsu.csc326.coffeemaker.CoffeeMaker", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      // Undeclared exception!
      try { 
        coffeeMaker0.editRecipe(0, (Recipe) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("edu.ncsu.csc326.coffeemaker.RecipeBook", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      // Undeclared exception!
      try { 
        coffeeMaker0.editRecipe((-1), recipe0);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -1
         //
         verifyException("edu.ncsu.csc326.coffeemaker.RecipeBook", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      // Undeclared exception!
      try { 
        coffeeMaker0.deleteRecipe(4);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 4
         //
         verifyException("edu.ncsu.csc326.coffeemaker.RecipeBook", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      // Undeclared exception!
      try { 
        coffeeMaker0.addRecipe((Recipe) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("edu.ncsu.csc326.coffeemaker.RecipeBook", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe[] recipeArray0 = coffeeMaker0.getRecipes();
      assertNotNull(recipeArray0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      int int0 = coffeeMaker0.makeCoffee(0, (-1));
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      try { 
        coffeeMaker0.addInventory("", "", "", "");
        fail("Expecting exception: InventoryException");
      
      } catch(InventoryException e) {
         //
         // Units of coffee must be a positive integer
         //
         verifyException("edu.ncsu.csc326.coffeemaker.Inventory", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      String string0 = coffeeMaker0.deleteRecipe(0);
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      Recipe recipe0 = new Recipe();
      coffeeMaker0.addRecipe(recipe0);
      int int0 = coffeeMaker0.makeCoffee(0, 0);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      String string0 = coffeeMaker0.checkInventory();
      assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      CoffeeMaker coffeeMaker0 = new CoffeeMaker();
      String string0 = coffeeMaker0.editRecipe(0, (Recipe) null);
      assertNull(string0);
  }
}