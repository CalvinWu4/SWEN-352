/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 29.7.2003
 *
 */
package familytree.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import junit.framework.*;
import familytree.model.Person;

/**
 * @author mmantyla
 *
 * 
 */
public class PersonTableModelTest extends TestCase {
	
	private Person personMikki;
	private Person personMinni; 
    private Person personMortti;
    private Person personVertti;
	private PersonTableModel ptm;
	public static void main (java.lang.String[] args) {
        junit.textui.TestRunner.run(new TestSuite(PersonTableModelTest.class));
    }
    
    public void setUp(){
    	personMikki = new Person ("Mikki", "Hiiri", false);
    	personMinni = new Person ("Minni", "Hiiri", true);
    	personMortti = new Person ("Mortti", "Hiiri", false);
    	personVertti = new Person ("Vertti", "Hiiri", false);
    	
    	ptm = new PersonTableModel();
    	assertEquals(0, ptm.getRowCount() );  
    }
    public void testSeekPersons(){
    	ptm.addPerson(personMikki);
    	ptm.addPerson(personMinni);
    	ptm.addPerson(personMortti);
    	ptm.addPerson(personVertti);
    	
    	Vector v = ptm.searchPersons("Mikki", null, -1, null, null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMikki));
    	v.clear();
    	
    	v = ptm.searchPersons("Minni", null, -1, null, null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMinni));
    	v.clear();
    	
    	v = ptm.searchPersons(null, "Hiiri", -1, null, null);
    	assertEquals(4, v.size());
    	assertTrue(v.contains(personMikki));
    	assertTrue(v.contains(personMinni));
    	assertTrue(v.contains(personMortti));
    	assertTrue(v.contains(personVertti));
    	v.clear();
    	
    	GregorianCalendar bday = new GregorianCalendar(1977, 10, 9);
    	GregorianCalendar dday = new GregorianCalendar(2132, 8, 17);
    	
    	personMortti.setDateOfBirth(bday);
    	personMortti.setDateOfDeath(dday);
    	
    	v = ptm.searchPersons(null, null, -1, null, new GregorianCalendar(2100, 10, 10));
    	assertEquals(0, v.size());
    	v.clear();
    	
    	//FindBugs marks 17 as a bug, but it is purposeful so it won't be changed.
    	v = ptm.searchPersons(null, null, -1, null, new GregorianCalendar(2132, 17, 9));
    	assertEquals(0, v.size());
    	v.clear();
    	
    	v = ptm.searchPersons(null, null, -1, null, new GregorianCalendar(2132, 8, 17));
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMortti));
    	v.clear();
    	
    	v = ptm.searchPersons(null, null, -1, new GregorianCalendar(1977, 10, 9), null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMortti));
    	v.clear();
    	
    	GregorianCalendar date =  new GregorianCalendar();
    	date.clear();
    	date.set(Calendar.MONTH, 10);
    	v = ptm.searchPersons(null, null, -1, date, null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMortti));
    	v.clear();
    	
    	date =  new GregorianCalendar();
    	date.clear();
    	date.set(Calendar.DAY_OF_MONTH, 9);
    	v = ptm.searchPersons(null, null, -1, date, null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMortti));
    	v.clear();
    	
    	date =  new GregorianCalendar();
    	date.clear();
    	date.set(Calendar.YEAR, 1977);
    	v = ptm.searchPersons(null, null, -1, date, null);
    	assertEquals(1, v.size());
    	assertTrue(v.contains(personMortti));
    	v.clear();
    	
    }
    public void testTableModelBasic(){
    	ptm.addPerson(personMikki);
    	ptm.addPerson(personMikki);
    	ptm.addPerson(personMikki);
    	
    	ptm.addPerson(personMinni);
    	
    	ptm.addPerson(personMortti);
    	
    	assertEquals(5, ptm.getRowCount());
    	
    	assertEquals(Person.GENDER_FEMALE, ptm.getValueAt(3, 2));
    	assertEquals("Mikki", ptm.getValueAt(1, 0));
    	assertEquals("Mortti", ptm.getValueAt(4, 0));
    
    	ptm.createPerson("Hessu", "Hopo", "male");
    	ptm.deletePerson(0);
    	ptm.deletePerson(1);
    	ptm.applyChangesToPerson("MikkiMod", "Hiiri", "female", 0);
    	
    	assertEquals(4, ptm.getRowCount());
    	assertEquals("Hopo", ptm.getValueAt(3, 1));
    	assertEquals(Person.GENDER_FEMALE, ptm.getValueAt(0,2));
    	assertEquals("MikkiMod", ptm.getValueAt(0,0));	
    }
   
    
}
