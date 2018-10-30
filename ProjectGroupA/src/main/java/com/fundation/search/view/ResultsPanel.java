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
    TitledBorder borderPane;

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
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columnHeaders);
        resultsTable = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable.setModel(defaultTableModel);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.CENTER);

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
}