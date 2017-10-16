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
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import familytree.model.Person;

/**
 * Model that contains the data about persons 
 * shown in the table.
 */
public class PersonTableModel extends AbstractTableModel {
    Vector persons = new Vector();
    private static final int NUMBER_OF_COLUMNS = 3;
    private final String[] columnNames = { "First Name", "Last Name", "Gender" };
    
    
    public int getRowCount() {
        return persons.size();
    }
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return NUMBER_OF_COLUMNS;
    }
    public String getColumnName(int column){
    	if (column > NUMBER_OF_COLUMNS -1)
    		return super.getColumnName(column);
    	else
    		return columnNames[column];
    }
     
    /**
     * Get the value of particular cell so that JTable can display it.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = (Person) persons.elementAt(rowIndex);
        if (columnIndex == 2){
        	return person.getGender();
        }
        else if (columnIndex == 1)
        	return person.getLastName();
        else if (columnIndex == 0)
        	return person.getFirstName();
        else 
        	return null;
    }
    public Person getPersonAt(int rowIndex) {
        return (Person) persons.elementAt(rowIndex);
    }
    /**
     * Add new person entry in to the table and notify listeners
     * @param person Person who is inserted in the table
     */
    public Person addPerson(Person person){
    	persons.add(person);
    	fireTableRowsInserted(persons.size(), persons.size());
    	return person;
    }
    
    /**
     * Create new Person
     * 
     * @param firstName
     * @param lastName
     * @param Gender
     */
    public Person createPerson(String firstName, String lastName, String gender){
    	return addPerson(new Person(firstName, 
    						 lastName, 
    						 gender.equalsIgnoreCase("female")));	
    }
    /**
     * Delete the selected person
     * @param rowIndex
     */
    public void deletePerson(int rowIndex) {
        persons.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
        
    }
    /**
     * Update the selected persons data
     * @param firstName New first name
     * @param lastName New last name
     * @param gender New gender
     * @param rowIndex The row that contains the person to be updated
     */
    public void applyChangesToPerson(
        String firstName,
        String lastName,
        String gender,
        int rowIndex) {
        Person person = (Person) persons.elementAt(rowIndex);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setFemale(gender.equalsIgnoreCase("female"));
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    /**
     * Update the selected persons data
     */
    public Vector getPersons() {
        return persons;
    }
	public Person getPersonWithId(int id){
		for(Iterator iter = persons.iterator(); iter.hasNext();){
			Person person = (Person) iter.next();
			if (person.getId() == id)
				return person;
		}
		return null;
	}
	
	/*
	 * Show persons that match the arguments 
	 * We must store also the old version in order to keep all code working
	 * People can store they queries
	 */
	public void showMatchingPersons(String firstName, 
									String lastName, 
									int gender) {
		showMatchingPersons(firstName, lastName, gender, null);
	}
	/*
	 *  Show persons that match the arguments
	 */
	public void showMatchingPersons(String firstName, 
									String lastName, 
									int gender, 
									GregorianCalendar dateOfBirth) {
		showMatchingPersons(firstName, lastName, gender, dateOfBirth, null);
	}
	/*
	 *  Show persons that match the arguments
	 */
	public void showMatchingPersons(String firstName, 
									String lastName, 
									int gender, 
									GregorianCalendar dateOfBirth,
									GregorianCalendar dateOfDeath) {
		searchPersons(firstName, lastName, gender, dateOfBirth, dateOfDeath);
	}
	
	
    /**
     * Get the persons that match the arguments
     * @return Vector contain the matching persons
     */
    protected Vector searchPersons(
        String firstName,
        String lastName,
        int gender,
        Calendar dateOfBirth,
        Calendar dateOfDeath) {
        Vector mathingPersons = new Vector();
        // Loop throught the persons and check if they match
        for (Iterator iter = persons.iterator(); iter.hasNext();) {
            Person person = (Person) iter.next();
            if (personMatch(person,
                firstName,
                lastName,
                gender,
                dateOfBirth,
                dateOfDeath)) {
                mathingPersons.add(person);
            }
        }
        return mathingPersons;
    }

    /**
     * Does the person given as the first argument match 
     * with the search criteria given as arguments
     */
    private boolean personMatch(
        Person person,
        String firstName,
        String lastName,
        int gender,
        Calendar dateOfBirth,
        Calendar dateOfDeath) {
        return        
        //Compare firstname
         (firstName == null || person.getFirstName().equals(firstName)) &&        
        //Compare lastname
         (lastName == null || person.getLastName().equals(lastName)) &&        
        //Compare gender
         (gender == person.getGenderAsInt() || gender == -1) &&        
        //Compare day of birth
         (dateOfBirth == null || person.dateOfBirthEquals(dateOfBirth)) &&        
        //Compare day of death
         (dateOfDeath == null || person.dateOfDeathEquals(dateOfDeath));
    }
}
