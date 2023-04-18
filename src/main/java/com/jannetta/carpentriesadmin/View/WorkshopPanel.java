package com.jannetta.carpentriesadmin.View;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.table.TableColumn;


import com.jannetta.carpentriesadmin.controller.Globals;
import com.jannetta.carpentriesadmin.model.Workshop;
import com.jannetta.carpentriesadmin.model.WorkshopTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.miginfocom.swing.MigLayout;

public class WorkshopPanel extends JPanel implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Globals globals = Globals.getInstance();

    private JLabel lbl_workshopID = new JLabel("Workshop ID");
    private JLabel lbl_workshopname = new JLabel("Workshop Name");
    private JLabel lbl_workshopType = new JLabel("Workshop Type");
    private JLabel lbl_fromDate = new JLabel("Date From");
    private JLabel lbl_toDate = new JLabel("Date to");
    private JLabel lbl_fromTime = new JLabel("Start time");
    private JLabel lbl_toTime = new JLabel("End time");

    private JLabel lbl_workshopWebsiteURL = new JLabel("Website URL");
    private JLabel lbl_communityDocURL = new JLabel("Collaboration Doc URL");
    private JLabel lbl_preSurveyURL = new JLabel("Pre-workshop survey URL");
    private JLabel lbl_postSurveyURL = new JLabel("Post-workshop survey URL");
    private JLabel lbl_surveyResults = new JLabel("Survey results URL");

    private JLabel lbl_host = new JLabel("Host name");
    private JLabel lbl_hostInstitution = new JLabel("Host Institution");

    private JLabel lbl_emailRemindDate = new JLabel("Reminder email date");
    private JLabel lbl_postWorkshopSurveyRemindDate = new JLabel("Post workshop survey reminder date");

    private JTextField tf_workshopID  = new JTextField(20);
    private JTextField tf_workshopname = new JTextField(20);
    private JComboBox<String> cb_workshopType = new JComboBox<String>(globals.getWorkshopType());
    private JTextField tf_fromDate = new JTextField(20);
    private JTextField tf_toDate = new JTextField(20);
    private JTextField tf_fromTime = new JTextField(20);
    private JTextField tf_toTime = new JTextField(20);
    private JTextField tf_workshopURL = new JTextField(40);
    private JTextField tf_communityDocURL = new JTextField(40);
    private JTextField tf_preWorkshopSurveyURL = new JTextField(40);
    private JTextField tf_postWorkshopSurveyURL = new JTextField(40);
    private JTextField tf_surveyResultsURL = new JTextField(40);
    private JTextField tf_host = new JTextField(20);
    private JTextField tf_hostInstitution = new JTextField(20);
    private JTextField tf_emailRemindDate = new JTextField(20);
    private JTextField tf_postWorkshopSurveyRemindDate = new JTextField(20);

    private JTable tbl_workshops;
    private JButton btn_clear = new JButton("Clear");
    private JButton btn_submit = new JButton("Submit");
    private JButton btn_update = new JButton("Update");
    private JButton btn_cancel = new JButton("Cancel");
    private JButton btn_save = new JButton("Save");
    private JButton btn_delete = new JButton("Delete");
    private JButton btn_open1;
    private JButton btn_open2;
    private JButton btn_open3;
    private JButton btn_open4;
    private JButton btn_open5;

    private JPanel pnl_form = new JPanel();
    private JPanel pnl_buttonholder = new JPanel();
    private JPanel pnl_table = new JPanel();
    private JPanel pnl_bottombuttons = new JPanel();

    private int selected_row = -1;
    private CheckBoxHeader checkboxHeader;

    WorkshopPanel() {
        super();

        logger.trace("Create workshop panel");
//        this.setBorder(BorderFactory.createLineBorder(Color.red));
//        pnl_table.setBorder(BorderFactory.createLineBorder(Color.green));

        tf_workshopID.addKeyListener(this);
        tf_workshopname.addKeyListener(this);
        tf_fromDate.addKeyListener(this);
        tf_toDate.addKeyListener(this);
        tf_fromTime.addKeyListener(this);
        tf_toTime.addKeyListener(this);
        tf_host.addKeyListener(this);
        tf_hostInstitution.addKeyListener(this);
        tf_emailRemindDate.addKeyListener(this);
        tf_postWorkshopSurveyRemindDate.addKeyListener(this);
        tf_workshopURL.addKeyListener(this);
        tf_communityDocURL.addKeyListener(this);
        tf_preWorkshopSurveyURL.addKeyListener(this);
        tf_postWorkshopSurveyURL.addKeyListener(this);
        tf_surveyResultsURL.addKeyListener(this);

        // Buttons
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon icon = Globals.createImageIcon("webicon_30.png", "Web icon");
        btn_open1 = new JButton(icon);
        btn_open1.setMargin(new Insets(0,0,0,0));
        btn_open2 = new JButton(icon);
        btn_open2.setMargin(new Insets(0,0,0,0));
        btn_open3 = new JButton(icon);
        btn_open3.setMargin(new Insets(0,0,0,0));
        btn_open4 = new JButton(icon);
        btn_open4.setMargin(new Insets(0,0,0,0));
        btn_open5 = new JButton(icon);
        btn_open5.setMargin(new Insets(0,0,0,0));
        btn_clear.addActionListener(this);
        btn_submit.addActionListener(this);
        btn_update.addActionListener(this);
        btn_cancel.addActionListener(this);
        btn_save.addActionListener(this);
        btn_open1.addActionListener(this);
        btn_open1.setActionCommand("www1");
        btn_open2.addActionListener(this);
        btn_open2.setActionCommand("www2");
        btn_open3.addActionListener(this);
        btn_open3.setActionCommand("www3");
        btn_open4.addActionListener(this);
        btn_open4.setActionCommand("www4");
        btn_open5.addActionListener(this);
        btn_open5.setActionCommand("www5");
        btn_submit.setEnabled(true);
        btn_cancel.setEnabled(false);
        btn_update.setEnabled(false);
        btn_delete.addActionListener(this);

        // Table listing all workshops
        tbl_workshops = new JTable(globals.getWorkshopTableModel());
        tbl_workshops = new JTable(globals.getWorkshopTableModel());
        TableColumn tc = tbl_workshops.getColumnModel().getColumn(globals.getWorkshopTableModel().getColumnCount() - 1);
        tc.setCellEditor(tbl_workshops.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbl_workshops.getDefaultRenderer(Boolean.class));
        checkboxHeader = new CheckBoxHeader(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getSource();
                if (source instanceof AbstractButton == false)
                    return;
                boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                globals.getWorkshops().forEach((workshop) -> {
                    workshop.setPrint(checked);
                });
            }
        });
        tc.setHeaderRenderer(checkboxHeader);

        tbl_workshops.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (row != -1) {
                    selected_row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    Workshop workshop = ((WorkshopTableModel) tbl_workshops.getModel()).getWorkshops().get(row);
                    if (mouseEvent.getClickCount() == 2 && selected_row != -1) {
                        logger.trace("Double clicked workshop table entry");

                        // Transfer record to form
                        tf_workshopID.setText((String) workshop.getWorkshopID());
                        tf_workshopname.setText((String) workshop.getWorkshopName());
                        cb_workshopType.setSelectedItem((String) workshop.getWorkshopType());
                        tf_fromDate.setText((String) workshop.getStartDate());
                        tf_toDate.setText((String) workshop.getEndDate());
                        tf_fromTime.setText((String) workshop.getStartTime());
                        tf_toTime.setText((String) workshop.getEndTime());
                        tf_host.setText((String) workshop.getHost());
                        tf_hostInstitution.setText((String) workshop.getHostInstitution());
                        tf_emailRemindDate.setText((String) workshop.getEmailRemindDate());
                        tf_postWorkshopSurveyRemindDate.setText((String) workshop.getPostWorkshopSurveyRemindDate());
                        tf_workshopURL.setText((String) workshop.getWorkshopURL());
                        tf_communityDocURL.setText( workshop.getCommunityDocURL());
                        tf_preWorkshopSurveyURL.setText(workshop.getPreWorkshopSurveyURL());
                        tf_postWorkshopSurveyURL.setText(workshop.getPostWorkshopSurveyURL());
                        tf_surveyResultsURL.setText(workshop.getSurveyResults());


                        // Change button to "Update"
                        btn_submit.setEnabled(false);
                        btn_update.setEnabled(true);
                        btn_cancel.setEnabled(true);
                    } else if (selected_row != -1 && column == globals.getWorkshopTableModel().getColumnCount() - 1) {
                        workshop.setPrint(!workshop.isPrint());
                        globals.fireTableDataChanged();
                        globals.getWorkshopTableModel().getValueAt(selected_row, column);
                    }
                }
            }

        });

        // Set layouts
        setLayout(new MigLayout("fillx","[grow,fill]", "[]"));
        // Panel for form
        pnl_form.setLayout(new MigLayout("", "", ""));
        // Panel for buttons
        pnl_buttonholder.setLayout(new MigLayout("","",""));
        // Panel for table
        pnl_table.setLayout(new MigLayout("","[grow, fill]",""));
        // Panel for bottom buttons
        pnl_bottombuttons.setLayout(new MigLayout("","",""));

        JScrollPane scrollPane = new JScrollPane(tbl_workshops);
        tbl_workshops.setFillsViewportHeight(true);
        pnl_table.add(scrollPane,"grow");

        pnl_buttonholder.add(btn_clear);
        pnl_buttonholder.add(btn_submit);
        pnl_buttonholder.add(btn_update);
        pnl_buttonholder.add(btn_cancel);
        pnl_buttonholder.add(btn_delete);



        pnl_form.add(lbl_workshopID);
        pnl_form.add(tf_workshopID);
        pnl_form.add(lbl_workshopname);
        pnl_form.add(tf_workshopname, "shrink, wrap");
        pnl_form.add(lbl_workshopType);
        pnl_form.add(cb_workshopType, "wrap");
        pnl_form.add(lbl_fromDate);
        pnl_form.add(tf_fromDate);
        pnl_form.add(lbl_toDate);
        pnl_form.add(tf_toDate, "wrap");
        pnl_form.add(lbl_fromTime);
        pnl_form.add(tf_fromTime);
        pnl_form.add(lbl_toTime);
        pnl_form.add(tf_toTime, "wrap");
        pnl_form.add(lbl_host);
        pnl_form.add(tf_host);
        pnl_form.add(lbl_hostInstitution);
        pnl_form.add(tf_hostInstitution, "wrap");
        pnl_form.add(lbl_emailRemindDate);
        pnl_form.add(tf_emailRemindDate);
        pnl_form.add(lbl_postWorkshopSurveyRemindDate);
        pnl_form.add(tf_postWorkshopSurveyRemindDate, "span, wrap");
        pnl_form.add(lbl_workshopWebsiteURL);
        pnl_form.add(tf_workshopURL, "span 2");
        pnl_form.add(btn_open1,"wrap");
        pnl_form.add(lbl_communityDocURL);
        pnl_form.add(tf_communityDocURL, "span 2");
        pnl_form.add(btn_open2,"wrap");
        pnl_form.add(lbl_preSurveyURL);
        pnl_form.add(tf_preWorkshopSurveyURL, "span 2");
        pnl_form.add(btn_open3,"wrap");
        pnl_form.add(lbl_postSurveyURL);
        pnl_form.add(tf_postWorkshopSurveyURL, "span 2");
        pnl_form.add(btn_open4,"wrap");
        pnl_form.add(lbl_surveyResults);
        pnl_form.add(tf_surveyResultsURL, "span 2");
        pnl_form.add(btn_open5,"wrap");
        pnl_bottombuttons.add(btn_save, "wrap");

        add(pnl_form,"wrap");
        add(pnl_buttonholder, "span, wrap");
        add(pnl_table, "span, wrap");
        add(pnl_bottombuttons, "span, wrap");

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            clearTextFields();
        }
        if (e.getActionCommand().equals("Submit")) {
            if (globals.getWorkshops().exists(tf_workshopID.getText().strip())) {
                JOptionPane.showMessageDialog(this, "A workshop with this ID already exists.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (tf_workshopID.getText().strip().equals("")) {
                JOptionPane.showMessageDialog(this, "The workshop ID cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                Workshop workshop = new Workshop(tf_workshopID.getText().strip(),tf_workshopname.getText().strip(), tf_fromDate.getText().strip(), tf_toDate.getText().strip(),
                        tf_fromTime.getText().strip(), tf_toTime.getText().strip(), tf_communityDocURL.getText().strip(), tf_preWorkshopSurveyURL.getText().strip(),
                        tf_postWorkshopSurveyURL.getText().strip(), tf_surveyResultsURL.getText().strip(), tf_host.getText().strip(), tf_hostInstitution.getText().strip(),
                        tf_workshopURL.getText().strip(), tf_emailRemindDate.getText().strip(), tf_postWorkshopSurveyRemindDate.getText().strip());
                ((WorkshopTableModel) tbl_workshops.getModel()).getWorkshops().add(workshop);
                globals.getWorkshopComboBoxModel().addElement(tf_workshopID.getText());
                globals.fireTableDataChanged();
                globals.setWorkshopssaved(false);
                clearTextFields();
                btn_update.setEnabled(false);
                btn_cancel.setEnabled(false);
                btn_submit.setEnabled(true);
                logger.trace("Submit workshop entry to table");
            }
        }
        if (e.getActionCommand().equals("Update")) {
            table2Workshop(selected_row, tbl_workshops);
            globals.fireTableDataChanged();
            btn_update.setEnabled(false);
            btn_cancel.setEnabled(false);
            btn_submit.setEnabled(true);
            globals.setWorkshopssaved(false);
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
            globals.saveJSON("Workshops.json", globals.getWorkshops());
            globals.setWorkshopssaved(true);
        }
        if (e.getActionCommand().startsWith("www")) {
            int c = (Integer.valueOf(e.getActionCommand().substring(3)));
            logger.trace("URL: " + c);
            try {
                Desktop desktop = Desktop.getDesktop();
                switch (c) {
                    case 1:
                        if (tf_workshopURL.getText().strip() != null) {
                            desktop.browse(new URI(tf_workshopURL.getText()));
                        }
                        break;
                    case 2:
                        if (tf_workshopURL.getText().strip() != null) {
                            desktop.browse(new URI(tf_communityDocURL.getText()));
                        }
                        break;
                    case 3:
                        if (tf_workshopURL.getText().strip() != null) {
                            desktop.browse(new URI(tf_preWorkshopSurveyURL.getText()));
                        }
                        break;
                    case 4:
                        if (tf_workshopURL.getText().strip() != null) {
                            desktop.browse(new URI(tf_postWorkshopSurveyURL.getText()));
                        }
                        break;
                    case 5:
                        if (tf_workshopURL.getText().strip() != null) {
                            desktop.browse(new URI(tf_surveyResultsURL.getText()));
                        }
                        break;
                }
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getActionCommand().equals("Delete")) {
            globals.delWorkshops(globals.getWorkshops());
            globals.fireTableDataChanged();
        }

    }

    public void table2Workshop(int row, JTable workshoptable) {
        Workshop workshop = ((WorkshopTableModel) workshoptable.getModel()).getWorkshops().get(row);
        workshop.setWorkshopID(tf_workshopID.getText().strip());
        workshop.setWorkshopName(tf_workshopname.getText().strip());
        workshop.setWorkshopType((String) cb_workshopType.getSelectedItem());
        workshop.setStartDate(tf_fromDate.getText().strip());
        workshop.setEndDate(tf_toDate.getText().strip());
        workshop.setStartTime(tf_fromTime.getText().strip());
        workshop.setEndTime(tf_toTime.getText().strip());
        workshop.setHost(tf_host.getText().strip());
        workshop.setHostInstitution(tf_hostInstitution.getText().strip());
        workshop.setEmailRemindDate(tf_emailRemindDate.getText().strip());
        workshop.setPostWorkshopSurveyRemindDate(tf_postWorkshopSurveyRemindDate.getText().strip());
        workshop.setWorkshopURL(tf_workshopURL.getText().strip());
        workshop.setCommunityDocURL(tf_communityDocURL.getText().strip());
        workshop.setPreWorkshopSurveyURL(tf_preWorkshopSurveyURL.getText().strip());
        workshop.setPostWorkshopSurveyURL(tf_postWorkshopSurveyURL.getText().strip());
        workshop.setSurveyResults(tf_surveyResultsURL.getText().strip());
        globals.getWorkshopComboBoxModel().setWorkshops((globals.getWorkshops().getWorkshopNames()));

    }

    @Override
    public void keyTyped(KeyEvent e) {
        globals.setEditDetected(true);

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void clearTextFields() {
        tf_workshopID.setText("");
        tf_workshopname.setText("");
        cb_workshopType.setSelectedItem(globals.getWorkshopType()[0]);
        tf_fromDate.setText("");
        tf_toDate.setText("");
        tf_fromTime.setText("");
        tf_toTime.setText("");
        tf_host.setText("");
        tf_hostInstitution.setText("");;
        tf_emailRemindDate.setText("");
        tf_postWorkshopSurveyRemindDate.setText("");
        tf_workshopURL.setText("");
        tf_communityDocURL.setText("");
        tf_preWorkshopSurveyURL.setText("");
        tf_postWorkshopSurveyURL.setText("");
        tf_surveyResultsURL.setText("");
        btn_update.setEnabled(false);
        btn_cancel.setEnabled(false);
        btn_submit.setEnabled(true);
    }
}
