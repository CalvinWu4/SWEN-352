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
public class RelationParentChild extends Relation {
	private Person child;
	private Person parent;

	public RelationParentChild (Person child, Person parent){
		this.child = child;
		this.parent = parent; 	
	}
	
	public boolean isParent(Person person){
		return person == parent;
	}
    
    public Person getChild() {
        return child;
    }

    public Person getParent() {
        return parent;
    }
	public boolean equals(Object obj){
		if (obj instanceof RelationParentChild) {
			RelationParentChild relation = (RelationParentChild) obj;
			return (this.getChild() == relation.getChild() && 
					this.getParent() == relation.getParent());
		}
		return false;
	}
	
    /**
     * Get the type of the relation as String.
     * 
     * @param person The person whose relation to the other person is returned.
     * E.g if person=child -> return "parent"
     * 
     * @return The relation type as String
     */
    public String getRelationType(Person person) {
        if (person.equals(child)) {
            if (parent.isFemale())
                return MOTHER;
            else
                return FATHER;
        } else if (person.equals(parent)) {
            if (child.isFemale())
                return DAUGHTER;
            else
                return SON;
        } else {
            //TODO: Fix this to use exceptions
            return "An error has occured";
        }
    }
	
	public Person getPartner(Person person){
		if (person.equals(child))
			return parent;
		else if (person.equals(parent))
			return child;
		else
			return null;//TODO throw something		
	}
	public Person getPerson1(){
		return getParent();
	}
	public Person getPerson2(){
		return getChild();
	}
}
