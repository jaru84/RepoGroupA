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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

/**
 * This panel keeps the fields panel in top and search button in the bottom.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class ParametersPanel extends JPanel {
    private FieldsPanel fieldsPanel;
    private JButton searchButton;
    private JButton clearButton;
    private JPanel buttonsPanel;

    public ParametersPanel() {
        setting();
        init();
    }

    public void setting(){
        setLayout(new BorderLayout());
    }

    public void init() {
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldsPanel.clearFields();
            }
        });
        buttonsPanel = new JPanel();

        BoxLayout box = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        buttonsPanel.setLayout(box);
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        clearButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchButton.setMaximumSize(new Dimension(400,30));
        clearButton.setMaximumSize(new Dimension(400,30));
        buttonsPanel.add(searchButton);
        buttonsPanel.add(clearButton);
        fieldsPanel = new FieldsPanel();
        add(fieldsPanel, BorderLayout.CENTER);
        add( buttonsPanel, BorderLayout.EAST);

    }

    /**
     * These methods return the values in text fields in a String.
     */
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

    public String getOwner() {
        return fieldsPanel.getOwner();

    }

    public String getContent() {
        return fieldsPanel.getContent();

    }

    public boolean getIsDirectory() {
        return fieldsPanel.getIsDirectory();

    }

    public boolean getIsHidden() {
        return fieldsPanel.getIsHidden();

    }

    public boolean getIsReadOnly() {
        return fieldsPanel.getIsReadOnly();

    }

    public JButton getSearchButton() {
        return searchButton;
    }



    public Date[] getCreationDates() {
        return fieldsPanel.getCreationDates();

    }

    public Date[] getModifiedDates() {
        return fieldsPanel.getModifiedDates();

    }

    public Date[] getAccessedDates() {
        return fieldsPanel.getAccessedDates();

    }

}
