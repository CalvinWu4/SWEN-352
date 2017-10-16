/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * 
 * Created on 23.7.2003
 *
 */
package familytree.model;

import java.util.Iterator;
import java.util.Vector;

import junit.framework.*;

/**
 * @author mmantyla
 *
 * 
 */
public class PersonTest extends TestCase {
	public static void main (java.lang.String[] args) {
        junit.textui.TestRunner.run(new TestSuite(PersonTest.class));
    }
    
    public void testParentChildRelationship() {
    	Person grandMother = new Person ("Grand", "Mother", true);
    	
    	Person mother1 = new Person ("1st", "Mother1", true);
     	Person mother2 = new Person ("2nd", "Mother2", true);
    	Person mother3 = new Person ("3rd", "Mother3", true);
    	
    	Person child11 = new Person ("", "", true);
    	Person child12 = new Person ("", "", true);
    	Person child21 = new Person ("", "", true);
    	Person child22 = new Person ("", "", true);
    	Person child23 = new Person ("", "", true);   	
    	try{
    	
	    	grandMother.addChild(mother1);
	    	assertEquals(1, grandMother.getChildren().size());
	    	grandMother.addChild(mother2);
	    	grandMother.addChild(mother3);
	    	
	    	assertEquals(3, grandMother.getChildren().size());
	    	
	    	mother1.addChild(child11);
	    	mother1.addChild(child12);
	    	assertEquals(2, mother1.getChildren().size());
	    	
	    	mother2.addChild(child21);
	    	mother2.addChild(child22);
	    	mother2.addChild(child23);
	    	assertEquals(3, mother2.getChildren().size());
	    	assertEquals(0, mother3.getChildren().size());
	    	
	    	assertEquals(3, mother2.getChildren().size());
    	}catch (AddRelationException e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
   
    public void testRelationships()
    {
    	Person mother1 = new Person ("Minni", "Hiiri", true);
     	Person father1 = new Person ("Mikki", "Hiiri", false);
    	Person son1 = new Person ("Mortti", "Hiiri", false);	
    	Person daughter1 = new Person ("Liinu", "Hiiri", true);
    	try {
	    	mother1.addChild(son1);
	    	mother1.addChild(daughter1);
	    	father1.addChild(son1);
	    	father1.addChild(daughter1);
	    	father1.addSpouse(mother1);
    	}catch (AddRelationException e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    	//Exists already
    	try {
	    	mother1.addChild(son1);
	    	fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		mother1.addSpouse(father1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
		//Relation with itself
		try {
    		mother1.addChild(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		mother1.addSpouse(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	//Relation already through other type of realtionships
    	try {
    		mother1.addSpouse(son1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		son1.addSpouse(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		son1.addChild(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		father1.addChild(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	try {
    		daughter1.addChild(mother1);
    		fail("We should have AddRelationException"); 
    	}catch (AddRelationException e){}
    	
    	Vector relationshipsMother = mother1.getRelationships();
    	for (Iterator iteratorMom = relationshipsMother.iterator(); iteratorMom.hasNext();){
    		Relation relationship = (Relation) iteratorMom.next();
    		
    	}
    }
    public void testComplexRelationPreventer(){
    	Person isanisa = new Person ("IsanIsa", "", false);
    	Person isanaiti = new Person  ("Isanaiti", "", true);
    	Person aidinnisa = new Person ("AidinIsa", "", false);
    	Person aidinnaiti = new Person  ("Aidinnaiti", "", true);
    	
    	Person isa = new Person ("isa", "", false);
    	Person aiti = new Person ("aiti", "", true);
    	
    	Person ma = new Person ("ma", "", false);
    	Person sisko = new Person ("sisko", "", true);
    	Person veli = new Person ("veli", "", true);
    	
    	try{
    		isa.addSpouse(aiti);
    		isa.addChild(ma);
    		isa.addChild(sisko);
    		isa.addChild(veli);
    		
    		aiti.addChild(ma);
    		aiti.addChild(sisko);
    		aiti.addChild(veli);

    	}catch (AddRelationException e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    	try{
    		ma.addSpouse(sisko);
    		fail("Should not be reached, Cannot marry sibling");
    	}catch (AddRelationException e){}
    	
    }
}
