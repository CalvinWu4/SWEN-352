/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 23.7.2003
 *
 */
package familytree.model;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

/**
 * Person class represents a single person in the FamilyTree program
 */
public class Person {
	private String firstName;
	private String lastName;
	private boolean female; 
	public static final String GENDER_MALE = "male";
	public static final String GENDER_FEMALE = "female";
	private int id; //ID for identifying people with same name
	private static int current_id_max=0;
	//Vector containing this persons relationships
	private Vector vecRelations = new Vector(); 
	
	//TODO In future we need to start using our own date class
	//SUN's Calendar class has undocumented "feature" that cause problems
	private GregorianCalendar dateOfBirth;
	private GregorianCalendar dateOfDeath;
	
	public Person (String firstName, String lastName, boolean female){
		this.firstName = firstName;
		this.lastName = lastName; 	
		this.female = female;
		id = returnIncrementId();
		dateOfBirth = new GregorianCalendar();
		dateOfBirth.clear();
		dateOfDeath = new GregorianCalendar();
		dateOfDeath.clear();
	}
	/**
	 * Private constructor that is used when person are restored from disk
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param female
	 */
	private Person (int id, String firstName, String lastName, boolean female)
	{
		this(firstName, lastName, female);
		/*this.firstName = firstName;
		this.lastName = lastName; 	
		this.female = female;*/
		this.id = id; 
	}
	
	/**
	 * Method used to restore from disk. 
	 * Not to be used when new persons are created.
	 * This way we can set the id number
     */
    public static Person restorePerson(
									int id, 
									String firstName, 
									String lastName, 
									boolean female){
										
		return new Person(id, firstName, lastName, female);								
	}
	

	/**
	 * Guarntees that each person has different id
	 * @return
	 */
   	private static synchronized int returnIncrementId(){
   		current_id_max++;
   		return current_id_max;
   	}
   
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String string) {
        firstName = string;
    }
    
    public void setLastName(String string) {
        lastName = string;
    }
    public int getId(){
    	return id;
    }
   
   
   
	public void addSpouse(Person spouse)throws AddRelationException{
		Relation relationship;
		if (this.female)
			relationship = new RelationSpouse(spouse, this);
		else
			relationship = new RelationSpouse(this, spouse);  
		this.addRelation(relationship); 
    	spouse.addRelation(relationship);
	}
   
    public void addChild (Person child) throws AddRelationException {
    	Relation relationship = new RelationParentChild(child, this);
    	this.addRelation(relationship); 
    	child.addRelation(relationship);
    }
    
    private void addRelation (Relation relationship) throws AddRelationException {
    	if(illegalRelation(relationship))
    		throw new AddRelationException("Relation exists or is illegal");	
   		vecRelations.add(relationship);
    }
    /**
     * Remoeves the relation between this person and 
     * person given as parameter
     * @param person
     * @return
     */
    public boolean removeRelation (Person person){
    	Relation relation = getRelation(person);
    	if (relation == null)
    		return false;
   		this.removeRelationFromVector(relation);
   		person.removeRelationFromVector(relation); 		
    	return true;
    }
    private void removeRelationFromVector(Relation relation)
    {
    	vecRelations.remove(relation);
    }
   
    
    /**
     * Is the new relation illegal
     * Is there allready some kind of relationship between the two people
     * @param relation Relation that holds the people together
     * @return true if the two people already are in 
     * 		   any relationship with each other
     */
    private boolean illegalRelation(Relation relationToBeAdded) {
        //Cannot have relationhips with yourself
        if (relationToBeAdded.getPerson1() == relationToBeAdded.getPerson2())
            return true;
        // Loop through this persons relationships			
        for (Iterator iter = vecRelations.iterator(); iter.hasNext();) {
            Relation relation = (Relation) iter.next();
            // We allready have some kind of relation between the persons
            if (relationToBeAdded.equalsPersons(relation))
                return true;
            // Get direct reference to the person this person will have relation
            Person relativeToBe; // The person we will have relation with	
            if (relationToBeAdded.getPerson1() == this)
                relativeToBe = relationToBeAdded.getPerson2();
            else if (relationToBeAdded.getPerson2() == this)
                relativeToBe = relationToBeAdded.getPerson1();
            else // this person does not partipate in this relation
                return true; // Should not be reached
            //Rule addition:
            //this person cannot have relation with it's siblings (=sister/brother)
            if (relation instanceof RelationParentChild) {
                RelationParentChild rpc = (RelationParentChild) relation;
                if (rpc.getChild() == this) { //Found Parent
                    Person parent = rpc.getParent();
                    for (Iterator iter2 = parent.getRelationships().iterator();
                        iter2.hasNext();
                        ) {
                        Relation parentsRelation = (Relation) iter2.next();
                        if (parentsRelation instanceof RelationParentChild) {
                            RelationParentChild childRelation =
                                (RelationParentChild) parentsRelation;
                            if (parent == childRelation.getParent()) {
                                //Found a child 
                                Person child = childRelation.getChild();
                                if (child == relativeToBe) {
                                    //Found sibling illegal relation
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * Get the relation between the persons if one exists
     * @param relative
     * @return null if there is no relation between the people
     * Feature Envy
     */
    public Relation getRelation(Person relative){
    	for (Iterator iterator = vecRelations.iterator(); iterator.hasNext();) {
        	Relation relation = (Relation) iterator.next();
    		if ((relation.getPerson1() == this && 
    			 relation.getPerson2() == relative) ||
    			(relation.getPerson2() == this &&
    		     relation.getPerson1() == relative))
    			return relation;
       }
    	return null;	
    }
    /**
     * Get children of this person
     * @return Vector containing the children
     */
    public Vector getChildren() {
        Vector children = new Vector();
        for (Iterator iterator = vecRelations.iterator(); iterator.hasNext();) {
            Relation relation = (Relation) iterator.next();
            if (relation.isParent(this))
                children.add(((RelationParentChild) relation).getChild());
        }
        return children;
    }
	public Vector getRelationships() {
		return vecRelations;
	}
	
	public String getGender(){
		if (female)
			return GENDER_FEMALE;
		else
			return GENDER_MALE;	
	}
	/**
	 * Get gender as int 1 is female 0 is male
	 * @return
	 */
	public int getGenderAsInt(){
		if (female)
			return 1;
		else
			return 0;	
	}
	
    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean b) {
        female = b;
    }

	public String toString(){
		return firstName+" "+lastName;
	}
   
    public GregorianCalendar getDateOfBirth() {
    	//Ugly hack to fix feature/bug by SUN, which causes
		//all calender fields to get set, when Calender.get(Field) is called
        return (GregorianCalendar) dateOfBirth.clone();
    }

    public GregorianCalendar getDateOfDeath() {
    	//Ugly hack to fix feature/bug by SUN, which causes
		//all calender fields to get set, when Calender.get(Field) is called
        return (GregorianCalendar) dateOfDeath.clone();
    }

    public void setDateOfBirth(GregorianCalendar calendar) {
        dateOfBirth = calendar;
    }

    public void setDateOfDeath(GregorianCalendar calendar) {
        dateOfDeath = calendar;
    }
    /**
     * Does the given birth day match with this persons birthday
     * 
     * @param moment The moment that is checked as birthday.
     * It is possible to specify only only month, year or day in the moment variable
     * This way we can check whether his person was born in July for instance
     * 
     * @return Whether the given moment includes the birthday 
     */
    public boolean dateOfBirthEquals(Calendar moment) {
        if (moment == null)
            return false;
        //Ugly hack to fix feature/bug by SUN, which causes
        //all calender fields to get set, when Calender.get(Field) is called	
        Calendar day1 = (Calendar) moment.clone();
        Calendar day2 = (Calendar) moment.clone();
        Calendar day3 = (Calendar) moment.clone();
        Calendar bday1 = getDateOfBirth();
        Calendar bday2 = getDateOfBirth();
        Calendar bday3 = getDateOfBirth();
        return (        
        //Check day of month
        (!day1.isSet(Calendar.DAY_OF_MONTH)
            || (bday1.get(Calendar.DAY_OF_MONTH) == day1.get(Calendar.DAY_OF_MONTH)))
            &&         
        //Check month
        (!day2.isSet(Calendar.MONTH)
            || (bday2.get(Calendar.MONTH) == day2.get(Calendar.MONTH)))
            &&         
        //Check year
        (!day3.isSet(Calendar.YEAR)
            || (bday3.get(Calendar.YEAR) == day3.get(Calendar.YEAR))));
    }
	public boolean dateOfDeathEquals(Calendar day) {
		if (day == null)
			return false;
		//Ugly hack to fix feature/bug by SUN, which causes
		//all calender fields to get set, when Calender.get(Field) is called	
		Calendar day1 = (Calendar)day.clone();
		Calendar day2 = (Calendar)day.clone();
		Calendar day3 = (Calendar)day.clone();
		Calendar bday1 = getDateOfDeath();
		Calendar bday2 = getDateOfDeath();
		Calendar bday3 = getDateOfDeath();	
			
		return (
			//Check day of month
			(!day1.isSet(Calendar.DAY_OF_MONTH) || 
			(bday1.get(Calendar.DAY_OF_MONTH) == day1.get(Calendar.DAY_OF_MONTH))) &&
			//Check month
			(!day2.isSet(Calendar.MONTH) ||
			(bday2.get(Calendar.MONTH) == day2.get(Calendar.MONTH)))&&
			//Check year
			(!day3.isSet(Calendar.YEAR)  || 
			(bday3.get(Calendar.YEAR) == day3.get(Calendar.YEAR))));
	}
}
