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
public class RelationSpouse extends Relation {
	public Person husband;
	public Person wife;
	
	public RelationSpouse (Person husband, Person wife)
	{
		this.husband = husband;
		this.wife = wife;
	}
	
	public String getRelationType(Person person){
        if (person.equals(husband))
			return WIFE;
		else if (person.equals(wife))
			return HUSBAND;
		else
			//TODO: Fix this to use exceptions
			return "An error has occured";		
	}
	
	public Person getPartner(Person person){
		if (person.equals(husband))
			return wife;
		else if (person.equals(wife))
			return husband;
		else
			return null;//TODO throw something		
	}
    public Person getHusband() {
        return husband;
    }

    public Person getWife() {
        return wife;
    }
	public Person getPerson1(){
		return getWife();
	}
	public Person getPerson2(){
		return getHusband();
	}
	public boolean equals(Object o){
		if (o instanceof RelationSpouse){
			RelationSpouse relation = (RelationSpouse) o;
			return (this.getHusband().equals(relation.getHusband())&&
					this.getWife().equals(relation.getWife()));
			
		}
		return false;
	}
}
