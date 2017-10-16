/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 21.8.2003
 *
 */
package familytree.io;

import java.io.IOException;
import java.util.Iterator;

import junit.framework.TestCase;
import familytree.model.AddRelationException;
import familytree.model.Person;
import familytree.view.PersonTableModel;
/**
 * @author mmantyla
 *
 * 
 */
public class DiskManagerTest extends TestCase {
	
	public void testWriteRoyalFamilyToDisk(){
		//http://www.ne.jp/asahi/y.s/madness/london/familytree.htm
		PersonTableModel personTableModel = new PersonTableModel();
		
		Person kingGeargeVi = new Person("George VI", "King", false);
		Person queenMotherElizabeth = new Person("Elizabeth", "The Queen Mother", true);
		
		Person philip = new Person("Philip", "Duke of Edinburgh", false);
		Person queenElizabeth = new Person("Elizabeth", "The Queen", true);
		
		Person charles = new Person("Charles", "Prince Of Wales", false);
		Person diana = new Person("Diana", "The Princess Of Wales", true);
		
		Person anne = new Person("Anne", "The Princess Royal", true);
		
		Person wiliam = new Person ("Wiliam", "Prince", false);
		Person harry = new Person ("Harry", "Prince", false);
		
		personTableModel.addPerson(kingGeargeVi);
		personTableModel.addPerson(queenMotherElizabeth);
		personTableModel.addPerson(philip);
				
		personTableModel.addPerson(queenElizabeth);
		personTableModel.addPerson(charles);
		personTableModel.addPerson(diana);

		personTableModel.addPerson(anne);
		personTableModel.addPerson(wiliam);
		personTableModel.addPerson(harry);
		try {
			kingGeargeVi.addSpouse(queenMotherElizabeth);
			kingGeargeVi.addChild(queenElizabeth);
			queenMotherElizabeth.addChild(queenElizabeth);
			
			queenElizabeth.addSpouse(philip);
			queenElizabeth.addChild(charles);
			queenElizabeth.addChild(anne);
			philip.addChild(charles);
			philip.addChild(anne);
			
			charles.addChild(wiliam);
			charles.addChild(harry);
			diana.addChild(wiliam);
			diana.addChild(harry);
			charles.addSpouse(diana);
			DiskManager dm = DiskManager.getDiskManager();
			dm.writeToDisk(personTableModel);
		}catch (AddRelationException e){
    		e.printStackTrace();
    		fail("AddRelationExpetion");
    	}catch (IOException e){
    		e.printStackTrace();
    		fail("IOException");
    	}
						
	}
	
	public void testReadWriteDisk(){
		/*
		PersonTableModel personTableModel = new PersonTableModel();
		Person grandMother = new Person("Grand", "Mother", true);
    	
    	Person mother1 = new Person ("Mother1", "Venäläinen", true);
     	Person mother2 = new Person ("Mother2", "Suomalainen", true);
    	Person mother3 = new Person ("Mother3", "Ruotsalainen", true);
    	Person father1 = new Person ("Father1", "Venäläinen", false);
    	Person father2 = new Person ("Father2", "Suomalainen", false);
    	Person father3 = new Person ("Father3", "Ruotsalainen", false);
    	
    	
    	Person child11 = new Person ("Child Daughter1", "Venäläinen", true);
    	Person child12 = new Person ("Child Daughter2", "Venäläinen", true);
    	Person child21 = new Person ("Child Son1", "Suomalainen", false);
    	Person child22 = new Person ("Child Son2", "Suomalainen", false);
    	Person child23 = new Person ("Child Son3", "Suomalainen", false);
    	Person child24_only_husbands = new Person ("Child Son4", "Suomalainen isä", false);    	
    	
    	personTableModel.addPerson(grandMother);
    	personTableModel.addPerson(mother1);
    	personTableModel.addPerson(mother2);
    	personTableModel.addPerson(mother3);
    	personTableModel.addPerson(father1);
    	personTableModel.addPerson(father2);
    	personTableModel.addPerson(father3);
    	personTableModel.addPerson(child11);
    	personTableModel.addPerson(child12);
    	personTableModel.addPerson(child21);
    	personTableModel.addPerson(child22);
    	personTableModel.addPerson(child23);
    	personTableModel.addPerson(child24_only_husbands);
    	
    	try{
    	grandMother.addChild(mother1);
    	
    	grandMother.addChild(mother2);
    	grandMother.addChild(mother3);
    	
    	
    	
    	mother1.addChild(child11);
    	mother1.addChild(child12);
    	father1.addChild(child11);
    	father1.addChild(child12);
    	father1.addSpouse(mother1);
    	
    	mother2.addChild(child21);
    	mother2.addChild(child22);
    	mother2.addChild(child23);
    	father2.addChild(child21);
    	father2.addChild(child22);
    	father2.addChild(child23);
    	father2.addChild(child24_only_husbands);
    	father2.addSpouse(mother2);
    	
    	} catch (AddRelationException e){
    		e.printStackTrace();
    		fail("AddRelationExpetion");
    	}
    	
    	//assertEquals(1, grandMother.getChildren().size());
    	assertEquals(3, grandMother.getChildren().size());
    	assertEquals(2, mother1.getChildren().size());
    	assertEquals(3, mother2.getChildren().size());
    	assertEquals(0, mother3.getChildren().size());
    	
    	PersonTableModel personTableModel2 = new PersonTableModel();
    	DiskManager dm = DiskManager.getDiskManager();
    	try {
    		dm.writeToDisk(personTableModel);
    		dm.readFromDisk(personTableModel2); 
    	}catch (IOException e){
    		e.printStackTrace();
    		fail("IOException");
    	}
    	boolean visitedGrandMa = false;
    	boolean visitedMrSuoma = false;
    	for(Iterator iter = personTableModel2.getPersons().iterator(); iter.hasNext();){
    		Person person = (Person) iter.next();
    		
    		if (person.getFirstName().equals("Grand")){
    			assertEquals(3, person.getChildren().size());
    			assertEquals("Error not female Grandma",true, person.isFemale());
    			assertEquals("GrandMa already visited",visitedGrandMa, false);
    			visitedGrandMa = true;
    		}
    		else if (person.getFirstName().equals("Father2")&& person.getLastName().equals("Suomalainen")&& 
    					person.isFemale() == false){
    			assertEquals(5, person.getRelationships().size());
    			assertEquals("Father has wrong firstName", person.getFirstName().equals("Father2"), true);
    			assertEquals("MrSuomalainen already visited",visitedMrSuoma, false);
    			visitedMrSuoma = true;
    		}			
    	}
    	assertEquals("Grandma not visited", visitedGrandMa, true);
    	assertEquals("MrSuomalainen not visited", visitedGrandMa, true);
*/    	
	}
	

}
