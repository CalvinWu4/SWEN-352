/*
 * FamilyTree - Family tree modeling software 
 * created for research purposes
 * 
 * Created on 28.7.2003
 *
 */
package familytree.view;

/**
 * @author mmantyla
 *
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import familytree.io.DiskManager;
import familytree.model.AddRelationException;
import familytree.model.Person;
import familytree.model.Relation;
import familytree.model.RelationParentChild;
import familytree.model.RelationSpouse;

/**
 * This class is the main GUI class of the application
 * It contains the tables text-fields, buttons and other GUI
 * components shown in the application
 */
public class FamilyFrame extends JFrame{
    
    private static FamilyFrame familyFrame;
    
    private boolean DEBUG = true;
	private JTable personTable;
	private PersonTableModel personTableModel;
	
	private JTable relationTable;
	private RelationTableModel relationTableModel;
	
   	private JTextField jtfFirstName = new JTextField();
   	private JTextField jtfLastName = new JTextField();
   	String[] genderStrings = {"Male", "Female"};
   	private JComboBox jcbGender = new JComboBox(genderStrings);
   	private JButton jbAddPerson = new JButton("Add");
   	private JButton jbApplyPerson = new JButton("Apply");
   	private JButton jbDeletePerson = new JButton("Delete");
   	
   	// PersonTable shares the person vector with these person combo boxes
   	private JComboBox jcbFullNameRelative;
   	private JComboBox jcbFullNameRelativeToBeAdded;
   //	private JTextField jtfRelativeGender = new JTextField();
   	String[] relationhipsStrings = {Relation.PARENT, Relation.CHILD, Relation.SPOUSE};
   	private JComboBox jcbRelationship = new JComboBox(relationhipsStrings);
   	private JButton jbAddRelation = new JButton("Add");
   	private JButton jbApplyRelation = new JButton("Apply");
   	private JButton jbDeleteRelation = new JButton("Delete");
   	
   	//Singleton
   	public static FamilyFrame getFamilyFrame(){
   		if (familyFrame == null)
   			familyFrame = new FamilyFrame();
   		return familyFrame;	
   	}
   	
    /**
     * Constructor of the main GUI class in the application
     * This method creates GUI components for the application
     */
    private FamilyFrame() {
        super("Family Tree Professional");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel personPanel = new JPanel(false);
        JPanel relativePanel = new JPanel(false);
        personPanel.setLayout(new BorderLayout());
        relativePanel.setLayout(new BorderLayout());
        // Two tabs are used. personPanel for showing all the persons
        // relativePanel for showing all the relations
        tabbedPane.addTab("Persons", null, personPanel, "Shows the persons");
        tabbedPane.setSelectedIndex(0);
        tabbedPane.addTab("Relationships", null,
            relativePanel,"Show the relationships");
        //Table for Person PersonTable
        personTableModel = new PersonTableModel();
        personTable = new JTable(personTableModel);
        personTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        //Table for Relations RelationTable
        relationTableModel = new RelationTableModel();
        relationTable = new JTable(relationTableModel);
        relationTable.setPreferredScrollableViewportSize(
            new Dimension(500, 70));
        //Create the scroll pane and add the PersonTable to it. 
        JScrollPane scrollPane = new JScrollPane(personTable);
        personPanel.add(scrollPane, BorderLayout.CENTER);
        //Create the scroll pane and add the RelativeTable to it. 
        JScrollPane scrollPane2 = new JScrollPane(relationTable);
        relativePanel.add(scrollPane2, BorderLayout.CENTER);
        //Create the panel where person data can be edited 
        //Add it personPanel
        JPanel personPanelEdit = new JPanel();
        personPanelEdit.setLayout(new BorderLayout());
        //Text fields for editing person
        JPanel personFieldPanel = new JPanel();
        personFieldPanel.setLayout(new GridLayout(0, 3));
        personFieldPanel.add(jtfFirstName);
        personFieldPanel.add(jtfLastName);
        personFieldPanel.add(jcbGender);
        personPanelEdit.add(personFieldPanel, BorderLayout.NORTH);
        //Buttons for editing person
        JPanel buttonPanelPerson = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanelPerson.add(jbAddPerson);
        buttonPanelPerson.add(jbApplyPerson);
        buttonPanelPerson.add(jbDeletePerson);
        personPanelEdit.add(buttonPanelPerson, BorderLayout.SOUTH);
        personPanel.add(personPanelEdit, BorderLayout.SOUTH);
        //Create the panel where relationships can be edited.
        //Add it to relativePanel
        JPanel relativePanelEdit = new JPanel();
        relativePanelEdit.setLayout(new BorderLayout(0, 1));
        JPanel relativeFieldPanel = new JPanel();
        relativeFieldPanel.setLayout(new GridLayout(0, 3));
  
        relativePanel.add(createTopRelativePanel(), BorderLayout.NORTH);
        relativePanelEdit.add(relativeFieldPanel, BorderLayout.NORTH);
        //Buttons for editing relationships
        JPanel buttonPanelRelative =
            new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanelRelative.add(jbAddRelation);
        buttonPanelRelative.add(jbApplyRelation);
        buttonPanelRelative.add(jbDeleteRelation);
        buttonPanelRelative.add(jcbRelationship);
        relativePanelEdit.add(buttonPanelRelative, BorderLayout.CENTER);
        //RelativeInfoPanel, where the person's info 
        //who is edited/added/deleted as relative is added 
        JPanel relativeInfoPanel = new JPanel();
        relativeInfoPanel.setLayout(new GridLayout(0, 3));
        jcbFullNameRelativeToBeAdded =
            new JComboBox(
                new DefaultComboBoxModel(personTableModel.getPersons()));
        relativeInfoPanel.add(jcbFullNameRelativeToBeAdded);
        relativeInfoPanel.add(jcbRelationship);
        relativePanelEdit.add(relativeInfoPanel, BorderLayout.SOUTH);
        relativePanel.add(relativePanelEdit, BorderLayout.SOUTH);
        //Add the tabbed pane to this window.
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        addListeners();
        readDataFromDisk();
    }

	private JPanel createTopRelativePanel(){
		jcbFullNameRelative =
            new JComboBox(
                new DefaultComboBoxModel(personTableModel.getPersons()));
        JLabel label = new JLabel("Relatives:");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,0));
        panel.add(jcbFullNameRelative);
        panel.add(label);
        return panel;
                
	}

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
	
	/**
     * Add listeners to GUI-components whose events we which to follow
     */
    private void addListeners(){ 
		personTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                personTableClicked();
                }
            });
            
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	writeDataToDisk();
       			dispose();
                System.exit(0);
            }
        });
        jbAddPerson.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		addPersonClicked();
        	}
        	    
        });
        jbApplyPerson.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		applyPersonClicked();
        	}
        	    
        });
        jbDeletePerson.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		deletePersonClicked();
        	}
        	    
        });
        jcbFullNameRelativeToBeAdded.addItemListener(new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
        		relativeToBeAddedChanged(e);
        	}
        	
        });
        jcbFullNameRelative.addItemListener(new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
        		relativeChanged(e);
        	}
        	
        });
        jbAddRelation.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		addRelationClicked(
        			(Person)jcbFullNameRelative.getSelectedItem(),
    				(Person)jcbFullNameRelativeToBeAdded.getSelectedItem(),
    				(String)jcbRelationship.getSelectedItem());
        	}
        
        });
        jbApplyRelation.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		applyRelationClicked(
        			(Person)jcbFullNameRelative.getSelectedItem(),
    				(Person) jcbFullNameRelativeToBeAdded.getSelectedItem(),
    				(String)jcbRelationship.getSelectedItem());
        	}
        
        });
        jbDeleteRelation.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
        		removeRelationClicked(
        			(Person)jcbFullNameRelative.getSelectedItem(),
    				(Person) jcbFullNameRelativeToBeAdded.getSelectedItem());
        	}
        
        });
        relationTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                relationTableClicked();
                }
        });   
	}
	
	private void relationTableClicked(){
		int selectedRow = relationTable.getSelectedRow();
		if (selectedRow == -1)
			return;
		Person person = relationTableModel.getPersonAt(selectedRow);
		//Set the relative in the lower bar
		jcbFullNameRelativeToBeAdded.setSelectedItem(person);
		Relation relation = person.getRelation(relationTableModel.getCurrentPerson());
		if (relation instanceof RelationParentChild){
			RelationParentChild rbc = (RelationParentChild) relation;
			if (person == rbc.getChild())
				jcbRelationship.setSelectedItem(Relation.CHILD);
			else
				jcbRelationship.setSelectedItem(Relation.PARENT);				
		}
		else if (relation instanceof RelationSpouse){
			jcbRelationship.setSelectedItem(Relation.SPOUSE);
		}	
	}
	
	
	private void removeRelationClicked(Person person1, Person person2){
		if(!person1.removeRelation(person2)) {
			JOptionPane.showMessageDialog(this, 
				"No Relation between "+person1.toString()
				+" and "+person2.toString(),
 				"Applying relation failed", JOptionPane.ERROR_MESSAGE);
 			return;	
		}
		//Inform the table that relationship was removed
		relationTableModel.personRemoved();
	}
	
	private void applyRelationClicked(Person person1, Person person2, String relation){
		removeRelationClicked(person1,  person2);
		addRelationClicked(person1, person2, relation);
		}
    /**
     * This method is executed, when user adds a relation between to persons.
     * E.g. addRelation button is clicked
     * @param person1 Person participating in the relation
     * @param person2 Other person participating in the relation
     * @param relation The relation type
     */
    private void addRelationClicked(
        Person person1,
        Person person2,
        String relation) {
        try {
            if (relation.equals(Relation.CHILD)) {
                person1.addChild(person2);
            } else if (relation.equals(Relation.PARENT)) {
                person2.addChild(person1);
            } else if (relation.equals(Relation.SPOUSE)) {
                person1.addSpouse(person2);
            }
            relationTableModel.personAdded();
        } catch (AddRelationException e) {
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Adding relation failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void relativeChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
        	Person person = (Person)e.getItem();
        	relationTableModel.setCurrentPerson(person);
        	Vector persons = (Vector)personTableModel.getPersons().clone();
        	persons.remove(person);
        	jcbFullNameRelativeToBeAdded.setModel(new DefaultComboBoxModel(persons));
        }
    }

    private void relativeToBeAddedChanged(ItemEvent e) {
    	if (e.getStateChange() == ItemEvent.SELECTED){
    		Person person = (Person)e.getItem();
    		//jtfRelativeGender.setText(person.getGender()); 
    		   	               
    	}
    }


    private void personTableClicked()
	{
		int selectedRow = personTable.getSelectedRow();
		if (selectedRow == -1)
			return;
		//Set data from selected row to the textfields
		jtfFirstName.setText(
			personTableModel.getPersonAt(selectedRow).getFirstName());
		jtfLastName.setText(personTableModel.getPersonAt(selectedRow).getLastName());
		jcbGender.setSelectedIndex(personTableModel.getPersonAt(selectedRow).isFemale()? 1:0);
	}
	private void addPersonClicked(){
		Person person = personTableModel.createPerson (
									   jtfFirstName.getText(), 
									   jtfLastName.getText(), 
									   (String)jcbGender.getSelectedItem());
		
		//Update the relative screen comboboxes						
		jcbFullNameRelativeToBeAdded.setModel(new DefaultComboBoxModel(personTableModel.getPersons()));
		jcbFullNameRelative.setModel(new DefaultComboBoxModel(personTableModel.getPersons()));					   
		
	}
	private void applyPersonClicked(){
		if (personTable.getSelectedRow() > -1) {
			personTableModel.applyChangesToPerson(	
								jtfFirstName.getText(), 
								jtfLastName.getText(), 
								(String)jcbGender.getSelectedItem(),
								personTable.getSelectedRow());	
		}
	}
	
	private void deletePersonClicked(){
		if (personTable.getSelectedRow() > -1)
			personTableModel.deletePerson(personTable.getSelectedRow());
	}
	
	/**
     * Read data from disk if it exists
     */
    private void readDataFromDisk() {
    	DiskManager dm = DiskManager.getDiskManager();
    	try{
	    	dm.readFromDisk(personTableModel);
    	}catch (FileNotFoundException e1){
    		JOptionPane.showMessageDialog(this, e1.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
    	}catch (IOException e2){
    		JOptionPane.showMessageDialog(this, e2.getMessage(), "IO-exception", JOptionPane.ERROR_MESSAGE);    		                
    	}
    }
    private void writeDataToDisk() {
    	DiskManager dm = DiskManager.getDiskManager();
    	try{
	    	dm.writeToDisk(personTableModel);
    	}catch (FileNotFoundException e1){
    		JOptionPane.showMessageDialog(this, e1.getMessage(), "File not found when saving data", JOptionPane.ERROR_MESSAGE);
    	}catch (IOException e2){
    		JOptionPane.showMessageDialog(this, e2.getMessage(), "IO-exception when saving data", JOptionPane.ERROR_MESSAGE);    		                
    	}
    }
    public static void main(String[] args) {
        FamilyFrame frame = new FamilyFrame();
        frame.pack();
        frame.setVisible(true);
    }
}