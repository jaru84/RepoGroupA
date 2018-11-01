/*******************************************************************************
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 *  2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 *  All rights reserved.
 *  <p>
 *  This software is the confidential and proprietary information of
 *  Jalasoft, ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 *******************************************************************************/
package com.fundation.search.view;

import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

/**
 * This class displays the results of searching in a table.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class ResultsPanel extends JPanel {
    private JTable resultsTable;
    private DefaultTableModel defaultTableModel;
    private JScrollPane scrollPane;
    private Vector columnHeaders;
    private TitledBorder borderPane;
    private TableColumn hiddenColumn;
    private TableColumn readonlyColumn;

    public ResultsPanel() {
        setting();
        init();

    }

    public void setting() {
        setLayout(new BorderLayout());
        borderPane = BorderFactory.createTitledBorder("Search Results");
        borderPane.setTitleJustification(TitledBorder.CENTER);
        borderPane.setTitlePosition(TitledBorder.BELOW_TOP);
        setBorder(borderPane);

    }

    public void init() {
        columnHeaders = new Vector();
        columnHeaders.addElement("Path");
        columnHeaders.addElement("Name");
        columnHeaders.addElement("Extension");
        columnHeaders.addElement("Size");
        columnHeaders.addElement("Owner");
        columnHeaders.addElement("Creation Date");
        columnHeaders.addElement("Last Modified Date");
        columnHeaders.addElement("Accessed Date");
        columnHeaders.addElement("Hidden");
        columnHeaders.addElement("Read-only");
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnHeaders);
        resultsTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        resultsTable.setModel(defaultTableModel);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.CENTER);

        initTableColumns();

        Object[] row = {"path","name","ext","size","owner","crea date", "modified date","accessed date", true,true};
        defaultTableModel.addRow(row);

    }

    /**
     * This method receives a vector of vectors to be displayed in the Jtable. Same
     * column headers are used Each row represents a file matching with search
     * criteria.
     */
    public void setTableDate(Object[] data) {
        defaultTableModel.addRow(data);
    }

    /**
     * This method set to 0 the number of rows discarding all the rows if exist
     */
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }

    public void initTableColumns(){
        hiddenColumn = resultsTable.getColumnModel().getColumn(8);
        readonlyColumn = resultsTable.getColumnModel().getColumn(9);
        hiddenColumn.setCellRenderer(resultsTable.getDefaultRenderer(Boolean.class));
        readonlyColumn.setCellRenderer(resultsTable.getDefaultRenderer(Boolean.class));
        resultsTable.getColumnModel().getColumn(0).setMinWidth(170);
        resultsTable.getColumnModel().getColumn(1).setMinWidth(100);
        resultsTable.getColumnModel().getColumn(2).setMinWidth(80);
        resultsTable.getColumnModel().getColumn(3).setMinWidth(100);
        resultsTable.getColumnModel().getColumn(4).setMinWidth(100);
        resultsTable.getColumnModel().getColumn(5).setMinWidth(120);
        resultsTable.getColumnModel().getColumn(6).setMinWidth(120);
        resultsTable.getColumnModel().getColumn(7).setMinWidth(120);
        resultsTable.getColumnModel().getColumn(8).setMinWidth(70);
        resultsTable.getColumnModel().getColumn(9).setMinWidth(70);

    }
}