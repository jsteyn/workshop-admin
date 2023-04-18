package com.jannetta.carpentriesadmin.View;

import com.jannetta.carpentriesadmin.controller.Globals;
import com.jannetta.carpentriesadmin.model.People;
import com.jannetta.carpentriesadmin.model.PeopleTableModel;
import com.jannetta.carpentriesadmin.model.Person;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;


public class PeoplePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(getClass());
    Globals globals = Globals.getInstance();

    final private JLabel lbl_person_id = new JLabel("User ID");
    final private JLabel lbl_firstname = new JLabel("Firstname");
    final private JLabel lbl_middlenames = new JLabel("Initials");
    final private JLabel lbl_lastname = new JLabel("Lastname");
    final private JLabel lbl_email = new JLabel("Email Address");
    final private JLabel lbl_programme = new JLabel("Study Programme");

    final private JTextField tf_person_id = new JTextField(50);
    final private JTextField tf_firstname = new JTextField(50);
    final private JTextField tf_middlenames = new JTextField(50);
    final private JTextField tf_lastname = new JTextField(50);
    private JTextField tf_email = new JTextField(50);
    private JTextField tf_programme = new JTextField(50);

    final private JTable tbl_people;
    final private JPanel buttonPanel1 = new JPanel();
    final private JPanel buttonPanel2 = new JPanel();
    final private JButton btn_submit = new JButton("Submit");
    final private JButton btn_update = new JButton("Update");
    final private JButton btn_cancel = new JButton("Cancel");
    final private JButton btn_importA = new JButton("Import: name <email>");
    final private JButton btn_importCSV = new JButton("Import CSV");
    final private JButton btn_save = new JButton("Save");
    final private JButton btn_print = new JButton("Print");
    final private JButton btn_delete = new JButton("Delete");
    private int selected_row = -1;
    final private CheckBoxHeader checkboxHeader;


    public PeoplePanel() {
        super();
        logger.trace("Create People panel");

        // Add action listeners to all buttons
        btn_submit.addActionListener(this);
        btn_update.addActionListener(this);
        btn_cancel.addActionListener(this);
        btn_importA.addActionListener(this);
        btn_importA.setActionCommand("importA");
        btn_importCSV.addActionListener(this);
        btn_importCSV.setActionCommand("csv");
        btn_save.addActionListener(this);
        btn_print.addActionListener(this);
        btn_delete.addActionListener(this);

        tbl_people = new JTable(globals.getPeopleTableModel());
        tbl_people.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (row != -1) {
                    selected_row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    Person person = ((PeopleTableModel) tbl_people.getModel()).getPeople().get(row);
                    if (mouseEvent.getClickCount() == 2 && selected_row != -1) {
                        logger.trace("Double clicked Person table entry");
                        // Change button to "Update"
                        btn_submit.setEnabled(false);
                        btn_update.setEnabled(true);
                        btn_cancel.setEnabled(true);

                        // Transfer record to form
                        tf_person_id.setText((String) table.getValueAt(selected_row, 0));
                        tf_firstname.setText((String) table.getValueAt(selected_row, 1));
                        tf_middlenames.setText((String) table.getValueAt(selected_row, 2));
                        tf_lastname.setText((String) table.getValueAt(selected_row, 3));
                        tf_email.setText((String) table.getValueAt(selected_row, 4));
                        tf_programme.setText((String) table.getValueAt(selected_row, 5));

                    } else if (selected_row != -1 && column == globals.getPeopleTableModel().getColumnCount() - 1) {
                        person.setPrint(!person.isPrint());
                        globals.fireTableDataChanged();
                        globals.getPeopleTableModel().getValueAt(selected_row, column);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tbl_people);
        tbl_people.setFillsViewportHeight(true);
        // Set up the last column with checkbox in the header to select or de-select all
        // records
        TableColumn tc = tbl_people.getColumnModel().getColumn(globals.getPeopleTableModel().getColumnCount() - 1);
        tc.setCellEditor(tbl_people.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbl_people.getDefaultRenderer(Boolean.class));
        checkboxHeader = new CheckBoxHeader(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getSource();
                if (source instanceof AbstractButton == false)
                    return;
                boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                globals.getPeople().forEach((person) -> {
                    person.setPrint(checked);
                });
            }
        });
        tc.setHeaderRenderer(checkboxHeader);


        // Set panel layout
        MigLayout migLayout = new MigLayout("fillx", "[]rel[]rel[]", "[]10[]10[]");
        setLayout(migLayout);
        // Add buttons to panels
        buttonPanel1.add(btn_submit);
        buttonPanel1.add(btn_update);
        buttonPanel1.add(btn_cancel);
        buttonPanel1.add(btn_importA);
        buttonPanel1.add(btn_importCSV);
        buttonPanel1.add(btn_delete);

        buttonPanel2.add(btn_save);
        buttonPanel2.add(btn_print);


        add(lbl_person_id);
        add(tf_person_id, "wrap");
        add(lbl_firstname);
        add(tf_firstname, "wrap");
        add(lbl_middlenames);
        add(tf_middlenames, "wrap");
        add(lbl_lastname);
        add(tf_lastname, "wrap");
        add(lbl_email);
        add(tf_email, "wrap");
        add(lbl_programme);
        add(tf_programme, "wrap");
        // Panel 1 with buttons
        add(buttonPanel1, "span, wrap");

        // Scrollpane with table of People
        add(scrollPane, "span, grow");

        // Panel 2 with buttons
        add(buttonPanel2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Submit")) {

            if (tf_person_id.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "The person id field cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (globals.getAllPeople().exists(tf_person_id.getText().strip())) {
                JOptionPane.showMessageDialog(this, "A person with this ID already exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Person person = new Person((String) tf_person_id.getText().strip(), tf_firstname.getText().strip(), tf_middlenames.getText().strip(),
                        tf_lastname.getText().strip(), tf_email.getText().strip(), tf_programme.getText().strip());
                globals.getAllPeople().add(person);
                logger.trace("Records: "+globals.getAllPeople().size());

                globals.fireTableDataChanged();
                globals.setPeoplesaved(false);

                logger.trace("Submit person entry to table");
                clearForm();

            }
        }
        if (e.getActionCommand().equals("Update")) {
            table2People(selected_row, tbl_people);
            globals.fireTableDataChanged();
            btn_update.setEnabled(false);
            btn_cancel.setEnabled(false);
            btn_submit.setEnabled(true);
            clearForm();
            globals.setPeoplesaved(false);
        }
        if (e.getActionCommand().equals("Cancel")) {
            clearForm();
            btn_update.setEnabled(false);
            btn_cancel.setEnabled(false);
            btn_submit.setEnabled(true);
        }
        if (e.getActionCommand().equals("Save")) {
            logger.trace("Save file");
            globals.saveJSON("People.json", globals.getAllPeople());
            globals.setPeoplesaved(false);
        }
        if (e.getActionCommand().equals("Delete")) {
            People people = globals.getPeople();
            Globals.delPeople(people);
            globals.fireTableDataChanged();
        }



    }
    /**
     * Read records from table and add to form
     *
     * @param row
     * @param peopleTable
     */
    public void table2People(int row, JTable peopleTable) {
        Person person = globals.findPerson(globals.getPeople().get(row).getPersonID());
        person.setPersonID(tf_person_id.getText().strip());
        person.setFirstName(tf_firstname.getText().strip());
        person.setMiddleNames(tf_middlenames.getText().strip());
        person.setLastName(tf_lastname.getText().strip());
        person.setEmail(tf_email.getText().strip());
        person.setProgramme(tf_programme.getText().strip());

    }

    private void clearForm() {
        // Clear form
        tf_person_id.setText("");
        tf_firstname.setText("");
        tf_middlenames.setText("");
        tf_lastname.setText("");
        tf_email.setText("");
        tf_programme.setText("");

    }
}
