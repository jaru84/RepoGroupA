/*
 *  @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 *  2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 *  All rights reserved.
 *  <p>
 *  This software is the confidential and proprietary information of
 *  Jalasoft, ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 */

package com.fundation.search.View;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.Vector;

/**
 * This class displays the results of searching in a table.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class ResultsPanel extends JPanel {
    JTable resultsTable;
    DefaultTableModel defaultTableModel;
    JScrollPane scrollPane;
    Vector columnHeaders;

    public ResultsPanel() {
        setting();
        init();

    }

    public void setting() {
        setLayout(new BorderLayout());

    }

    public void init() {
        columnHeaders = new Vector();
        columnHeaders.addElement("Path");
        columnHeaders.addElement("Name");
        columnHeaders.addElement("Extension");
        columnHeaders.addElement("Size");
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnHeaders);
        resultsTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable.setModel(defaultTableModel);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.CENTER);

    }

    // This method receives a vector of vectors to be displayed in the Jtable.
    // Same column headers are used
    // Each row represents a file matching with search criteria
    public void setTableDate(Vector dataVector) {
        defaultTableModel.setDataVector(dataVector, columnHeaders);
    }

    //This method set to 0 the number of rows discarding all the rows if exist
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }
}