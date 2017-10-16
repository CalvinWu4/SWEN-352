/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 1.8.2003
 *
 */
package familytree.view;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import familytree.model.Person;
import familytree.model.Relation;

/**
 * @author mmantyla
 *
 * 
 */
public class RelationTableModel extends AbstractTableModel {
    private Person currentPerson; // The persons whose relationship we are currently viewing
    Vector relationships = new Vector(); 
    private static final int NUMBER_OF_COLUMNS = 4;
    private final String[] columnNames= {"First Name", "Last Name", "Gender","Relationship Type"}; 
    
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
     	return relationships.size(); 
    }
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return NUMBER_OF_COLUMNS;
    }
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Relation relation = (Relation) relationships.elementAt(rowIndex);
        

String value ="";
        if (columnIndex == 3){value =  relation.getRelationType(currentPerson);}else if (columnIndex == 0){
        	value =  relation.getPartner(currentPerson).getFirstName();
        }
        else if (columnIndex == 1){
        	value =  relation.getPartner(currentPerson).getLastName();}
        else if (columnIndex == 2)
	    {
        	value =  relation.getPartner(currentPerson).getGender();
        		
        }return value;
    }
    
    public Person getPersonAt(int rowIndex){
    	Relation relation = (Relation) relationships.elementAt(rowIndex);
    	return relation.getPartner(currentPerson);
    }
    
    public String getColumnName(int column){
    	if (column > NUMBER_OF_COLUMNS -1)
    		return super.getColumnName(column);
    	else
    		return columnNames[column];
    }
    public void setCurrentPerson(Person person)
    {
    	currentPerson = person;
    	relationships = person.getRelationships();
    	fireTableRowsInserted(0, relationships.size());
    }
  
    public void personAdded() {
    	fireTableRowsInserted(relationships.size(), relationships.size());
    }
    public Person getCurrentPerson() {
        return currentPerson;
    }
    public void personRemoved() {
    	fireTableRowsDeleted(0, relationships.size()+1);
    	fireTableRowsInserted(0, relationships.size());
    }

}
