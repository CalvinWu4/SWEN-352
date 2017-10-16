/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 12.8.2003
 *
 */
package familytree.io;

import java.util.*;
import java.io.*;
import familytree.view.*;
import familytree.model.*;

/**
 * This class writes and reads person and relation data from the disk
 */
public class DiskManager {
    private static DiskManager diskManager;
    private static final String FILE_PERSONS = "FamilyTreePersons.fml";
    private static final String FILE_RELATIONS = "FamilyTreeRelations.fml";
    private static final String ERROR_MSG_ADD_RELATION_FAILED =
        "AddRelation failed when reading file.\n The file is likely corrupt";
    private static final String ERROR_MSG_NONE_EXISTING_PERSON_IN_RELATION =
        "Found none-existing person in relation when reading file.\n "
            + "The file is likely corrupt";
    private DiskManager() {}
    public static DiskManager getDiskManager() {
        if (diskManager == null)
            diskManager = new DiskManager();
        return diskManager;
    }
	/**
	 * Read familytree data from disk
	 * 
	 * Read stored data of people and their relationships from disk
	 * 
	 * @param personTableModel The table data model that is populated by the method
	 * @throws IOException In case IO-fails
	 */
	public void readFromDisk(PersonTableModel personTableModel)
	    throws IOException {
	    // Open files so we can read the data
	    LineNumberReader personReader =
	        new LineNumberReader(new FileReader(FILE_PERSONS));
	    LineNumberReader relativeReader =
	        new LineNumberReader(new FileReader(FILE_RELATIONS));
	    /*This loop reads the persons from the disk
	     Single person is stored in the disk in the form: 
	     "<id>,<firstName>,<lastName>,<female>;\n" */
	    String linePerson = personReader.readLine();
	    while (linePerson != null) {
	        // Read single person from disk
	        int index_1 = linePerson.indexOf(",");
	        int id = Integer.parseInt(linePerson.substring(0, index_1));
	        int index_2 = linePerson.indexOf(",", index_1 + 1);
	        String firstName = linePerson.substring(index_1 + 1, index_2);
	        int index_3 = linePerson.indexOf(",", index_2 + 1);
	        String lastName = linePerson.substring(index_2 + 1, index_3);
	        int index_4 = linePerson.indexOf(";", index_3 + 1);
	        String strFemaleTrue = linePerson.substring(index_3 + 1, index_4);
	        boolean female = Boolean.valueOf(strFemaleTrue).booleanValue();
	        // Restore the person and add it to table model
	        Person person =
	            Person.restorePerson(id, firstName, lastName, female);
	        personTableModel.addPerson(person);
	        linePerson = personReader.readLine();
	    }
	    /* This loop reads relationships from the disk
	     	Single relation is writen to disk in the form
	    	"person_id-relationtype-person_id" */
	    String lineRelation = relativeReader.readLine();
	    while (lineRelation != null) {
	        //Read single relation from disk
	        int index_1 = lineRelation.indexOf("-");
	        int id1 = Integer.parseInt(lineRelation.substring(0, index_1));
	        Person person1 = personTableModel.getPersonWithId(id1);
	        if (person1 == null)
	            throw new IOException(ERROR_MSG_NONE_EXISTING_PERSON_IN_RELATION);
	        int index_2 = lineRelation.indexOf("-", index_1 + 1);
	        String relationType = lineRelation.substring(index_1 + 1, index_2);
	        int index_3 = lineRelation.indexOf(";");
	        int id2 =
	            Integer.parseInt(lineRelation.substring(index_2 + 1, index_3));
	        Person person2 = personTableModel.getPersonWithId(id2);
	        if (person2 == null)
	            throw new IOException(ERROR_MSG_NONE_EXISTING_PERSON_IN_RELATION);
	        // Restore relations as classes
	        try {
	            if (relationType.equals(Relation.DAUGHTER)
	                || relationType.equals(Relation.SON)) {
	                person1.addChild(person2);
	            } else if (
	                relationType.equals(Relation.FATHER)
	                    || relationType.equals(Relation.MOTHER)) {
	                person2.addChild(person1);
	            } else if (
	                relationType.equals(Relation.WIFE)
	                    || relationType.equals(Relation.HUSBAND)) {
	                person1.addSpouse(person2);
	            }
	        } catch (AddRelationException e) {
	            throw new IOException(ERROR_MSG_ADD_RELATION_FAILED);
	        }
	        lineRelation = relativeReader.readLine();
	    }
	}
	/**
	 * Write the family tree data to the disk
	 * 
	 * Write the people and their relationships to disk
	 * 
	 * @param personTableModel This data model contais the people we will write to disk
	 * @throws IOException
	 */
	public void writeToDisk(PersonTableModel personTableModel) throws IOException {
	    // Open files for writing
	    FileWriter fileWriterPerson = new FileWriter(FILE_PERSONS);
	    FileWriter fileWriterRelations = new FileWriter(FILE_RELATIONS);
	    // Create temp variables
	    Vector persons = personTableModel.getPersons();
	    Vector relations = new Vector();
	    Vector relationsToBeRemoved = new Vector();
	    //Write persons to disk according to format:
	    //"<id>,<firstName>,<lastName>,<female>;\n"
	    for (Iterator iter = persons.iterator(); iter.hasNext();) {
	        Person person = (Person) iter.next();
	        fileWriterPerson.write(person.getId() + ",");
	        fileWriterPerson.write(person.getFirstName() + ",");
	        fileWriterPerson.write(person.getLastName() + ",");
	        fileWriterPerson.write(person.isFemale() + ";\n");
	        relations.addAll(person.getRelationships());
	    }
	    //Find duplicate Relationships 
	    for (int i = 0; relations.size() > i; i++) {
	        Relation rel1 = (Relation) relations.elementAt(i);
	        for (int j = i + 1; relations.size() > j; j++) {
	            Relation rel2 = (Relation) relations.elementAt(j);
	            if (rel2.equals(rel1)) {
	                relationsToBeRemoved.add(rel2);
	            }
	        }
	    }
	    //Remove duplicate relationships
	    for (Iterator iter = relationsToBeRemoved.iterator(); iter.hasNext();) {
	        relations.remove(iter.next());
	    }
	    //Write relations to disk to format:
	    //"person_id-relationtype-person_id"
	    for (Iterator iter = relations.iterator(); iter.hasNext();) {
	        Relation relation = (Relation) iter.next();
	        fileWriterRelations.write(relation.getPerson1().getId() + "-");
	        fileWriterRelations.write(
	            relation.getRelationType(relation.getPerson1()) + "-");
	        fileWriterRelations.write(relation.getPerson2().getId() + ";\n");
	    }
	    //Clean up and close the files and streams
	        fileWriterPerson.flush();
	        fileWriterPerson.close();
	        fileWriterRelations.flush();
	        fileWriterRelations.close();
	    }
	}