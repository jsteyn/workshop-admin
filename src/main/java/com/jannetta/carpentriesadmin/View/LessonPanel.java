package com.jannetta.carpentriesadmin.View;

import com.jannetta.carpentriesadmin.controller.Globals;
import com.jannetta.carpentriesadmin.model.*;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

/**
 * Class to create a panel for updating learners
 */
public class LessonPanel extends JPanel implements ActionListener {
    Logger logger = LoggerFactory.getLogger(getClass());
    Globals globals = Globals.getInstance();

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel lbl_lesson_ID = new JLabel("Lesson ID");
    private JTextField tf_lesson_ID = new JTextField(4);
    private JLabel lbl_lesson_desc = new JLabel("Description");
    private JTextField tf_lesson_desc = new JTextField(50);
    private JLabel lbl_lesson_URL = new JLabel("Lesson URL");
    private JTextField tf_lesson_URL = new JTextField(50);
    private JLabel lbl_exercises_URL = new JLabel("Exercises URL");
    private JTextField tf_exercises_URL = new JTextField(50);
    private JButton btn_submit = new JButton("Submit");
    private JButton btn_update = new JButton("Update");
    private JButton btn_cancel = new JButton("Cancel");
    private JButton btn_save = new JButton("Save");
    private JButton btn_delete = new JButton("Delete");
    private JButton btn_clear = new JButton("Clear");
    private JButton btn_add = new JButton("Add selected lessons to workshop");
    private JTable tbl_lessons;
    private CheckBoxHeader checkboxHeader;
    private JPanel pnl_form = new JPanel();
    private JPanel pnl_button1 = new JPanel();
    private JPanel pnl_table = new JPanel();
    private JPanel pnl_button2 = new JPanel();
    private int selected_row = -1;

    /**
     * Constructor
     */
    public LessonPanel() {
        super();

//        this.setBorder(BorderFactory.createLineBorder(Color.red));
//        pnl_table.setBorder(BorderFactory.createLineBorder(Color.green));

        // Set layouts
        setLayout(new MigLayout("fillx","[grow,fill]", "[]"));
        // Panel for buttons
        pnl_form.setLayout(new MigLayout("", "", ""));
        // Panel for form
        pnl_button1.setLayout(new MigLayout("", "", ""));
        // Panel for table
        pnl_table.setLayout(new MigLayout("", "[grow, fill]", ""));
        // Panel for bottom buttons
        pnl_button2.setLayout(new MigLayout("", "", ""));

        btn_submit.addActionListener(this);
        btn_update.addActionListener(this);
        btn_cancel.addActionListener(this);
        btn_save.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_add.addActionListener(this);
        btn_add.setActionCommand("addlessons");

        tbl_lessons = new JTable(globals.getLessonTableModel());
        tbl_lessons.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (row != -1) {
                    selected_row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    Lesson lesson = globals.getLessonTableModel().getLessons().get(row);
                    if (mouseEvent.getClickCount() == 2 && selected_row != -1) {
                        logger.trace("Double clicked lesson table entry");
                        // Change button to "Update"
                        btn_submit.setEnabled(false);
                        btn_update.setEnabled(true);
                        btn_cancel.setEnabled(true);

                        // Transfer record to form
                        tf_lesson_ID.setText((String) lesson.getLessonID());
                        tf_lesson_desc.setText((String) lesson.getDescription());
                        tf_lesson_URL.setText((String) lesson.getLessonURL());
                        tf_exercises_URL.setText((String) lesson.getExercisesURL());

                    } else if (selected_row != -1 && column == globals.getLessonTableModel().getColumnCount() - 1) {
                        lesson.setPrint(!lesson.isPrint());
                        globals.fireTableDataChanged();
                        globals.getLessonTableModel().getValueAt(selected_row, column);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tbl_lessons);
        tbl_lessons.setFillsViewportHeight(true);
        TableColumn tc = tbl_lessons.getColumnModel().getColumn(globals.getLessonTableModel().getColumnCount() - 1);
        tc.setCellEditor(tbl_lessons.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbl_lessons.getDefaultRenderer(Boolean.class));
        checkboxHeader = new CheckBoxHeader(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getSource();
                if (source instanceof AbstractButton == false)
                    return;
                boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                globals.getLessons().forEach((lesson) -> {
                    lesson.setPrint(checked);
                });
            }
        });
        tc.setHeaderRenderer(checkboxHeader);


        pnl_form.add(lbl_lesson_ID);
        pnl_form.add(tf_lesson_ID, "wrap");
        pnl_form.add(lbl_lesson_desc);
        pnl_form.add(tf_lesson_desc, "wrap");
        pnl_form.add(lbl_lesson_URL);
        pnl_form.add(tf_lesson_URL, "wrap");
        pnl_form.add(lbl_exercises_URL);
        pnl_form.add(tf_exercises_URL, "wrap");


        pnl_button1.add(btn_submit);
        pnl_button1.add(btn_update);
        pnl_button1.add(btn_cancel, "wrap");
        pnl_button1.add(btn_add);
        pnl_button1.add(btn_delete);

        pnl_table.add(scrollPane, "span, grow");

        pnl_button2.add(btn_save);

        add(pnl_button1, "span, wrap");
        add(pnl_form, "span, wrap");
        add(pnl_table, "span, wrap");
        add(pnl_button2, "span, wrap");

        clearTextFields();
        btn_update.setEnabled(false);
        btn_cancel.setEnabled(false);
        btn_submit.setEnabled(true);   }

    /**
     * What to do when buttons are pressed
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            Lesson lesson = new Lesson(tf_lesson_ID.getText().strip(), tf_lesson_desc.getText().strip(), tf_lesson_URL.getText().strip(), tf_exercises_URL.getText().strip());
            if (globals.getLessons().exists(lesson.getLessonID())) {
                JOptionPane.showMessageDialog(this, "A lesson with this ID already exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (tf_lesson_ID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "The lesson id field must not be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                globals.getLessons().add(lesson);
                globals.fireTableDataChanged();
                globals.setLessonssaved(false);
                clearTextFields();
                btn_update.setEnabled(false);
                btn_cancel.setEnabled(false);
                btn_submit.setEnabled(true);
                clearTextFields();
                logger.trace("Submit lesson entry to table");
            }
        }
        if (e.getActionCommand().equals("Update")) {
            table2Lessons(selected_row, tbl_lessons);
            btn_update.setEnabled(false);
            btn_cancel.setEnabled(false);
            btn_submit.setEnabled(true);
            globals.setLessonssaved(false);
            clearTextFields();

        }
        if (e.getActionCommand().equals("Cancel")) {
            clearTextFields();
            btn_update.setEnabled(false);
            btn_cancel.setEnabled(false);
            btn_submit.setEnabled(true);
        }
        if (e.getActionCommand().equals("Save")) {
            logger.trace("Save file");
            globals.saveJSON(globals.getProperty("lessonfile"), globals.getLessons());
            globals.setLessonssaved(true);
        }
        if (e.getActionCommand().equals("Delete")) {
            Globals.delLessons();
            globals.fireTableDataChanged();
        }
        if (e.getActionCommand().equals("addlessons")) {
            logger.trace("Add selected lessons to workshop");
            // Retrieve all the lessons
            Lessons lessons = globals.getLessons();
            // Retrieve the selected lessons
            Lessons selectedLessons = globals.getSelectedLessons(lessons);
            // Retrieve the workshop combobox model
            WorkshopComboBoxModel workshopComboBoxModel = (WorkshopComboBoxModel) globals.getWorkshopComboBoxModel();
            // Create a popup window to select workshop to which the selected lessons should be added
            JPanel selectWorkshopPanel = new JPanel(new MigLayout());
            // Use the combobox model for the combobox
            JComboBox cb_workshopnames = new JComboBox(workshopComboBoxModel);
            // add the combobox to the panel
            selectWorkshopPanel.add(cb_workshopnames);
            // show the dialogbox
            JOptionPane.showMessageDialog(null, selectWorkshopPanel);
            // retrieve the selected workshopID
            String workshopId = (String)cb_workshopnames.getSelectedItem();
            logger.trace("Selected workshop: " + workshopId);

            // create a new object to hold the selected lessons
            Lessons selectedForWorkshop = new Lessons();
            selectedLessons.forEach(lesson -> {
                selectedForWorkshop.add(lesson);
                logger.trace("Lesson " + lesson.getLessonID() + " selected");
            });
            // retrieve the selected workshop from the workshop-lessons relation table
            WorkshopLessonsSingle wsl = Globals.getWorkshopsLessons().getWorkshop(workshopId);
            if (wsl == null) {
                wsl = new WorkshopLessonsSingle(workshopId, selectedLessons);
                Globals.getWorkshopsLessons().add(wsl);
            } else {
                Lessons existing = wsl.getLessons();
                for (int i = 0; i < selectedForWorkshop.size(); i++) {
                    if (!(wsl.getLessons().contains(selectedForWorkshop.get(i)))) {
                        wsl.getLessons().add(selectedForWorkshop.get(i));

                    }
                }
            }
            // if the relation doesn't exist, create it.
//            ArrayList<String> lessonIDs = new ArrayList<>();
//            selectedLessons.forEach(lesson -> {
//                logger.trace("Lesson selected: " + lesson.getLessonID() + "\t" + lesson.getDescription());
//                lessonIDs.add(lesson.getLessonID());
//            });
//            WorkshopLessonsSingle workshopSingleLessons = new WorkshopLessonsSingle((String)cb_workshopnames.getSelectedItem(), lessonIDs);
//            globals.getWorkshopsLessons().add(workshopSingleLessons);
            //globals.saveJSON(globals.getProperty("workshopLessonsfile"), globals.getWorkshopsLessons());
            globals.setWorkshopLessonssaved(false);
        }

    }

    /**
     * Read records from table and add to form
     * 
     * @param row
     * @param lessonTable
     */
    public void table2Lessons(int row, JTable lessonTable) {
        Lesson lesson = globals.findLesson(globals.getLessons().get(row).getLessonID());
        lesson.setDescription((String) tf_lesson_desc.getText().strip());
        lesson.setLessonURL((String) tf_lesson_URL.getText().strip());
        lesson.setExercisesURL((String) tf_exercises_URL.getText().strip());
        globals.fireTableDataChanged();
    }

    /**
     * Clear all the textfields in the form
     */
    private void clearTextFields() {
        tf_lesson_desc.setText("");
        tf_lesson_ID.setText("");
        tf_lesson_URL.setText("");
        tf_exercises_URL.setText("");
    }
}
