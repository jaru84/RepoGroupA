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

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;

/**
 * This panel contains the field names and text fields to enter the searching criteria.
 *
 * @author Jose Colina
 * @version 1.0.
 */
public class FieldsPanel extends JPanel {
    private JTextField pathText;
    private JTextField nameText;
    private JTextField extensionText;
    private JLabel pathLabel;
    private JLabel nameLabel;
    private JLabel extensionLabel;
    private final int SIZE_TEXT_BOX = 20;

    public FieldsPanel() {
        setLayout(new GridLayout(3, 2));
        init();
    }

    public void init() {
        pathText = new JTextField();
        pathText.setColumns(SIZE_TEXT_BOX);
        nameText = new JTextField();
        nameText.setColumns(SIZE_TEXT_BOX);
        extensionText = new JTextField();
        extensionText.setColumns(SIZE_TEXT_BOX);
        pathLabel = new JLabel("Path: ");
        nameLabel = new JLabel("File Name: ");
        extensionLabel = new JLabel("Extension: ");
        add(pathLabel);
        add(pathText);
        add(nameLabel);
        add(nameText);
        add(extensionLabel);
        add(extensionText);
    }

    // These methods return the values in text fields in a String
    public String getPath() {
        return pathText.getText();

    }

    public String getName() {
        return nameText.getText();

    }

    public String getExtension() {
        return extensionText.getText();

    }
}
