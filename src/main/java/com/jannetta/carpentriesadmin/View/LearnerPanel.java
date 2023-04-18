package com.jannetta.carpentriesadmin.View;


import com.jannetta.carpentriesadmin.controller.FileTypeFilter;
import com.jannetta.carpentriesadmin.controller.Globals;
import com.jannetta.carpentriesadmin.model.*;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LearnerPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(getClass());
	Globals globals = Globals.getInstance();

	private JLabel lbl_workshopID = new JLabel("*WorkshopID");
	private JLabel lbl_badge = new JLabel("*Badge");
	private JLabel lbl_instructor = new JLabel("*Instructor");
	private JLabel lbl_user_id = new JLabel("User ID");
	private JLabel lbl_firstname = new JLabel("Firstname");
	private JLabel lbl_initials = new JLabel("Initials");
	private JLabel lbl_lastname = new JLabel("Lastname");
	private JLabel lbl_email = new JLabel("Email Address");
	private JLabel lbl_date = new JLabel("Date");
	private JComboBox<String> cb_workshopIDs;
	private JComboBox<String> cb_badge = new JComboBox<String>(globals.getWorkshopType());
	private JTextField tf_instructor = new JTextField(50);
	private JTextField tf_user_id = new JTextField(50);
	private JTextField tf_firstname = new JTextField(50);
	private JTextField tf_initials = new JTextField(50);
	private JTextField tf_lastname = new JTextField(50);
	private JTextField tf_email = new JTextField(50);
	private JTextField tf_date = new JTextField(15);
	private LessonSelectionPanel pnl_lessonselection;
	private JTable tbl_learners;
	private JPanel buttonPanel1 = new JPanel();
	private JPanel buttonPanel2 = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	private JButton btn_update = new JButton("Update");
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_importA = new JButton("Import: name <email>");
	private JButton btn_importCSV = new JButton("Import CSV");
	private JButton btn_save = new JButton("Save");
	private JButton btn_print = new JButton("Print");
	private JButton btn_delete = new JButton("Delete");
	private int selected_row = -1;
	private CheckBoxHeader checkboxHeader;

	public LearnerPanel() {
		super();
		logger.trace("Create learner panel");

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

		// Dropdown box with all workshop IDs to select from
		WorkshopComboBoxModel workshopComboBoxModel = (WorkshopComboBoxModel) globals.getWorkshopComboBoxModel();
		cb_workshopIDs = new JComboBox<String>(workshopComboBoxModel);
		cb_workshopIDs.addActionListener(this);

		pnl_lessonselection = new LessonSelectionPanel();

		// Table with all learners on file
		tbl_learners = new JTable(globals.getLearnerTableModel());
		tbl_learners.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (row != -1) {
					selected_row = table.getSelectedRow();
					int column = table.getSelectedColumn();
					Learner learner = ((LearnerTableModel) tbl_learners.getModel()).getLearners().get(row);
					if (mouseEvent.getClickCount() == 2 && selected_row != -1) {
						logger.trace("Double clicked learner table entry");
						// Change button to "Update"
						btn_submit.setEnabled(false);
						btn_update.setEnabled(true);
						btn_cancel.setEnabled(true);

						// Transfer record to form
						cb_workshopIDs.setSelectedItem(table.getValueAt(selected_row, 0));
						logger.debug("Selected item index: " + cb_workshopIDs.getSelectedIndex());
						logger.debug("Set workshop combobox to: " + table.getValueAt(selected_row, 0));
						cb_workshopIDs.setSelectedIndex(cb_workshopIDs.getSelectedIndex());
						cb_badge.setSelectedItem((String) table.getValueAt(selected_row, 1));
						tf_instructor.setText((String) table.getValueAt(selected_row, 2));
						tf_user_id.setText((String) table.getValueAt(selected_row, 3));
						tf_firstname.setText((String) table.getValueAt(selected_row, 4));
						tf_initials.setText((String) table.getValueAt(selected_row, 5));
						tf_lastname.setText((String) table.getValueAt(selected_row, 6));
						tf_email.setText((String) table.getValueAt(selected_row, 7));
						tf_date.setText((String) table.getValueAt(selected_row, 8));
						pnl_lessonselection.checkBoxes((String)table.getValueAt(selected_row, 9));

					} else if (selected_row != -1 && column == globals.getLearnerTableModel().getColumnCount() - 1) {
						learner.setPrint(!learner.isPrint());
						globals.fireTableDataChanged();
						globals.getLearnerTableModel().getValueAt(selected_row, column);
					}
				}
			}
		});

		// Put the table in a scroll pane
		JScrollPane scrollPane = new JScrollPane(tbl_learners);
		tbl_learners.setFillsViewportHeight(true);

		// Set up the last column with checkbox in the header to select or de-select all
		// records
		TableColumn tc = tbl_learners.getColumnModel().getColumn(globals.getLearnerTableModel().getColumnCount() - 1);
		tc.setCellEditor(tbl_learners.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(tbl_learners.getDefaultRenderer(Boolean.class));
		checkboxHeader = new CheckBoxHeader(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object source = e.getSource();
				if (source instanceof AbstractButton == false)
					return;
				boolean checked = e.getStateChange() == ItemEvent.SELECTED;
				globals.getLearners().forEach((learner) -> {
					learner.setPrint(checked);
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

		// Add components to main panel
		// Form components
		add(lbl_workshopID);
		add(cb_workshopIDs);
		add(pnl_lessonselection, "span 1 3, wrap");
		add(lbl_badge);
		add(cb_badge, "wrap");
		add(lbl_instructor);
		add(tf_instructor, "wrap");
		add(lbl_user_id);
		add(tf_user_id, "wrap");
		add(lbl_firstname);
		add(tf_firstname, "wrap");
		add(lbl_initials);
		add(tf_initials, "wrap");
		add(lbl_lastname);
		add(tf_lastname, "wrap");
		add(lbl_email);
		add(tf_email, "wrap");
		add(lbl_date);
		add(tf_date, "wrap");

		// Panel 1 with buttons
		add(buttonPanel1, "span, wrap");

		// Scrollpane with table of learners
		add(scrollPane, "span, grow");

		// Panel 2 with buttons
		add(buttonPanel2);
	}

	private void resetTableHeader() {
		checkboxHeader.setSelected(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("comboBoxChanged")) {
			String view = (String) cb_workshopIDs.getSelectedItem();
			if (!(globals.getAllLearners() == null))
				globals.getAllLearners().forEach((learner) -> {
					learner.setPrint(false);
				});
			resetTableHeader();
			globals.learnersSetView(view);
		}
		if (e.getActionCommand().equals("Submit")) {

			if (tf_user_id.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "The learner id field cannot be empty.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (globals.getAllLearners().exists(tf_user_id.getText().strip())) {
				JOptionPane.showMessageDialog(this, "A learner with this ID already exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Lessons lessons = pnl_lessonselection.getSelectedLessons();
				Learner learner = new Learner((String) cb_workshopIDs.getSelectedItem(),
						(String) cb_badge.getSelectedItem(), tf_instructor.getText().strip(),
						tf_user_id.getText().strip(), tf_firstname.getText().strip(), tf_initials.getText().strip(),
						tf_lastname.getText().strip(), tf_email.getText().strip(), tf_date.getText().strip(), lessons);				

				globals.getAllLearners().add(learner);
				globals.learnersSetView((String) cb_workshopIDs.getSelectedItem());
				globals.fireTableDataChanged();
				globals.setLearnerssaved(false);

				logger.trace("Submit learner entry to table");
				// Clear form
				cb_workshopIDs.setSelectedIndex(-1);
				cb_badge.setSelectedItem(-1);
				tf_instructor.setText("");
				tf_user_id.setText("");
				tf_firstname.setText("");
				tf_initials.setText("");
				tf_lastname.setText("");
				tf_email.setText("");
				tf_date.setText("");

			}
		}
		if (e.getActionCommand().equals("Update")) {
			table2Learners(selected_row, tbl_learners);
			globals.fireTableDataChanged();
			btn_update.setEnabled(false);
			btn_cancel.setEnabled(false);
			btn_submit.setEnabled(true);
			globals.learnersSetView((String) cb_workshopIDs.getSelectedItem());
			globals.setLearnerssaved(false);
		}
		if (e.getActionCommand().equals("Cancel")) {
			btn_update.setEnabled(false);
			btn_cancel.setEnabled(false);
			btn_submit.setEnabled(true);
		}
		if (e.getActionCommand().equals("Save")) {
			logger.trace("Save file");
			globals.saveJSON("Learners.json", globals.getAllLearners());
			globals.setLearnerssaved(false);
		}
		if (e.getActionCommand().equals("Print")) {
			Learners learners = globals.getLearners();

			learners.getLearners().forEach((learner) -> {
				if (learner.isPrint()) {
					// replace fields in template svg
					String badge = learner.getBadge();
					String svgtemplatefile = badge + "-attendance.svg";
					try {
						Scanner sc = new Scanner(new File(svgtemplatefile));
						String content = sc.useDelimiter("\\Z").next();
						content = content.replace("{{name}}",
								learner.getFirstname().strip() + " " + learner.getLastname().strip());
						content = content.replace("{{date}}", learner.getDate().strip());
						content = content.replace("{{instructor}}", learner.getInstructor().strip());
						sc.close();
						String svgfile = learner.getUser_id() + ".svg";
						PrintWriter pw = new PrintWriter(new File(svgfile));
						pw.write(content);
						pw.close();

						// convert svg to pdf
						Globals.svg2pdf(svgfile, learner.getUser_id());
					} catch (FileNotFoundException e1) {
						logger.error(e1.getMessage());
					}

				}

			});
		}
		if (e.getActionCommand().equals("Delete")) {
			String workshop = (String) cb_workshopIDs.getSelectedItem();
			globals.setAllLearners(globals.delLearners(globals.getAllLearners()));
			globals.setLearnerssaved(false);
			globals.learnersSetView(workshop);

		}
		if (e.getActionCommand().equals("importA")) {
			logger.trace("Import from text file");
			String workshop = (String) cb_workshopIDs.getSelectedItem();
			if (workshop.equals("All")) {
				JOptionPane.showMessageDialog(this, "Select a workshop from the dropdown box", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				final JFileChooser fc = new JFileChooser();
				FileFilter docFilter = new FileTypeFilter(".txt", "Text file");
				FileFilter pdfFilter = new FileTypeFilter(".csv", "Comma separated value file");
				fc.addChoosableFileFilter(docFilter);
				fc.addChoosableFileFilter(pdfFilter);
				//fc.setFileFilter(docF);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					globals.setAllLearners(globals.importTextFile(globals.getAllLearners(), workshop,
							(String) cb_badge.getSelectedItem(), tf_instructor.getText(), tf_date.getText(), file));
					globals.learnersSetView(workshop);
					globals.setLearnerssaved(false);
					globals.fireTableDataChanged();
				}
			}
		}
		if (e.getActionCommand().equals("csv")) {
			logger.trace("Import from CSV file");
			String workshop = (String) cb_workshopIDs.getSelectedItem();
			if (workshop.equals("All")) {
				JOptionPane.showMessageDialog(this, "Select a workshop from the dropdown box", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					globals.setAllLearners(globals.readCSVLearnersFile(globals.getAllLearners(), file, workshop));
					globals.learnersSetView(workshop);
					globals.setLearnerssaved(false);
					globals.fireTableDataChanged();
				}
			}
		}
	}

	/**
	 * Read records from table and add to form
	 * 
	 * @param row
	 * @param learnerTable
	 */
	public void table2Learners(int row, JTable learnerTable) {
		// Learner learner = globals.getAllLearners().get(row);
		Lessons lessons = pnl_lessonselection.getSelectedLessons();
		Learner learner = globals.findLearner(globals.getLearners().get(row).getUser_id());
		learner.setWorkshop((String) cb_workshopIDs.getSelectedItem());
		learner.setBadge((String) cb_badge.getSelectedItem());
		learner.setInstructor(tf_instructor.getText().strip());
		learner.setUser_id(tf_user_id.getText().strip());
		learner.setFirstname(tf_firstname.getText().strip());
		learner.setLastname(tf_lastname.getText().strip());
		learner.setInitials(tf_initials.getText().strip());
		learner.setEmail(tf_email.getText().strip());
		learner.setDate(tf_date.getText().strip());
		learner.setLessons(lessons);

		globals.getWorkshopComboBoxModel().setWorkshops((globals.getWorkshops().getWorkshopNames()));

	}


}
