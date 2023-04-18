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

public class WorkshopLessonsPanel extends JPanel implements ActionListener {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    Globals globals = Globals.getInstance();

    WorkshopComboBoxModel workshopComboBoxModel = globals.getWorkshopComboBoxModel();
    JComboBox cb_workshopnames = new JComboBox(workshopComboBoxModel);
    JLabel lbl_workshopnames = new JLabel("Select a workshop:");
    WorkshopLessonsTableModel workshopLessonsTableModel = new WorkshopLessonsTableModel();
    private int selected_row = -1;

    WorkshopLessonsPanel() {
        super();
        final JPanel pnl_form = new JPanel();
        final JPanel pnl_buttons1 = new JPanel();
        final JPanel pnl_table = new JPanel();
        final JPanel pnl_buttons2 = new JPanel();

        final JButton btn_delete = new JButton("Delete");
        final JTable tbl_lessons;
        final CheckBoxHeader checkboxHeader;
        final JButton btn_save = new JButton("Save");
        setLayout(new MigLayout("fillx","[]", "[]"));

        btn_delete.addActionListener(this);
        btn_save.addActionListener(this);
        cb_workshopnames.addActionListener(this);

        tbl_lessons = new JTable(workshopLessonsTableModel);
        tbl_lessons.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (row != -1) {
                    selected_row = table.getSelectedRow();
                    int column = table.getSelectedColumn();
                    Lesson lesson = workshopLessonsTableModel.getLessons().get(row);
                    if (selected_row != -1 && column == globals.getLessonTableModel().getColumnCount() - 1) {
                        lesson.setPrint(!lesson.isPrint());
                        globals.fireTableDataChanged();
                        globals.getLessonTableModel().getValueAt(selected_row, column);
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tbl_lessons);
        tbl_lessons.setFillsViewportHeight(true);
        TableColumn tc = tbl_lessons.getColumnModel().getColumn(workshopLessonsTableModel.getColumnCount() - 1);
        tc.setCellEditor(tbl_lessons.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tbl_lessons.getDefaultRenderer(Boolean.class));
        checkboxHeader = new CheckBoxHeader(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getSource();
                if (!(source instanceof AbstractButton))
                    return;
                boolean checked = e.getStateChange() == ItemEvent.SELECTED;
                workshopLessonsTableModel.getLessons().forEach((lesson) -> {
                    lesson.setPrint(checked);
                });
            }
        });

        pnl_form.setLayout(new MigLayout("", "", ""));
        tc.setHeaderRenderer(checkboxHeader);
        pnl_form.add(lbl_workshopnames);
        pnl_form.add(cb_workshopnames, "wrap");


        pnl_buttons1.setLayout(new MigLayout());
        pnl_buttons1.add(btn_delete);

        pnl_table.setLayout(new MigLayout("","[grow, fill]",""));
        pnl_table.setBorder(BorderFactory.createTitledBorder("LESSONS"));
        pnl_table.add(scrollPane,"grow");

        pnl_buttons2.add(btn_save);
        add(pnl_buttons2);

        add(pnl_form, "span, wrap");
        add(pnl_buttons1, "span, wrap");
        add(pnl_table, "span, grow");
        pnl_buttons2.add(btn_save);
        add(pnl_buttons2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("comboBoxChanged")) {
            // Get the id selected from the combobox
            String workshopID = (String) cb_workshopnames.getSelectedItem();
            // Set lessons in the table model
            // Retrieve the workshop of the id selected in the combobox
            WorkshopLessonsSingle wsl = Globals.getWorkshopsLessons().getWorkshop(workshopID);
            if (!(wsl == null)) {
                workshopLessonsTableModel.setLessons(wsl.getLessons());
                workshopLessonsTableModel.fireTableDataChanged();
            }

        }
        if (e.getActionCommand().equals("Delete")) {
            logger.trace("Delete selected lessons from Workshop");
            workshopLessonsTableModel.getLessons().delLessons();
            workshopLessonsTableModel.fireTableDataChanged();
            globals.setWorkshopLessonssaved(false);
        }
        if (e.getActionCommand().equals("Save")) {
            logger.trace("Save file");
            globals.saveJSON(globals.getProperty("workshopLessonsfile"), Globals.getWorkshopsLessons());
            globals.setWorkshopLessonssaved(true);
        }
    }

}
