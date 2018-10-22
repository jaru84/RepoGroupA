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

package com.fundation.search.view;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * This panel keeps the fields panel in top and search button in the bottom.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class ParametersPanel extends JPanel {
    private FieldsPanel fieldsPanel;
    private JButton searchButton;
    private JLabel errorMessage;

    public ParametersPanel() {
        setLayout(new BorderLayout());
        init();
    }

    public void init() {
        searchButton = new JButton("Search");
        fieldsPanel = new FieldsPanel();
        errorMessage = new JLabel("");
        add(fieldsPanel, BorderLayout.NORTH);
        add(searchButton, BorderLayout.SOUTH);
        add(errorMessage, BorderLayout.CENTER);

    }

    // These methods return the values in text fields in a String
    public String getPath() {
        return fieldsPanel.getPath();

    }

    public String getName() {
        return fieldsPanel.getName();

    }

    public String getExtension() {
        return fieldsPanel.getExtension();

    }

    public String getFileSize() {
        return fieldsPanel.getFileSize();

    }

    public String getSizeOperator() {
        return fieldsPanel.getSizeOperator();

    }

    public String getSizeScale() {
        return fieldsPanel.getSizeScale();

    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JLabel getErrorLabel() {
        return errorMessage;
    }
}
