/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 23.7.2003
 *
 */
package familytree.model;

/**
 * @author mmantyla
 *
 * 
 */
/**
 * Relation class represents a relationships between two people.
 * Currently we have only two types of relationships: spouse and parent-child
 * @author mmantyla
 *
 * 
 */
public abstract class Relation {

	public boolean isParent(Person person) {
		return false;
	}
	
	/**
	 * Get the type of the relationship.
	 * 
     * @param person The person whose relationship to the other person is returned.
     * E.g if person=child -> return "parent"
     * @return
     */
    public abstract String getRelationType(Person person);
    
    /**
     * Get the person's partner in the relationship
     * @param person the person whose partner we are seeking
     * @return 
     */
    public abstract Person getPartner(Person person);
    
    /**
     * Get the first Person in the Relation.
     * @return
     */
    public abstract Person getPerson1();
    
    public abstract boolean equals(Object o);
    
    /**
     * Get the second Person in the Relation.
     * @return
     */
    public abstract Person getPerson2();
    
    public static Relation createRelation(int id1, String relationType, int id2){
    	return null;
    }

	/**
	 * Check whether the given two persons exists in this relation
	 */
	public boolean equalsPersons(Person person1, Person person2){
		return ((person1.equals(getPerson1()) && person2.equals(getPerson2())) ||
			(person1.equals(getPerson2()) && person2.equals(getPerson1())));
		
	}
	/**
	 * Check whether this relation holds the same people
	 */
	public boolean equalsPersons(Relation relation){
		return equalsPersons(relation.getPerson1(), relation.getPerson2());
	}

    public static final String HUSBAND = 	"Husband";
    public static final String WIFE = 		"Wife";
    public static final String SON = 		"Son";
    public static final String DAUGHTER = 	"Daughter";
    public static final String FATHER = 	"Father";
    public static final String MOTHER =		"Mother";
    public static final String PARENT = 	"Parent";
    public static final String CHILD = 		"Child";
    public static final String SPOUSE = 	"Spouse";
}
