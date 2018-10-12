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

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FieldsPanel extends JPanel {
    private JTextField pathText;
    private JTextField nameText;
    private JTextField extensionText;
    private JLabel pathLabel;
    private JLabel nameLabel;
    private JLabel extensionLabel;
    private int SIZE_TEXT_BOX = 20;

    public FieldsPanel(){
        setLayout(new GridLayout(3,2));
        pathText = new JTextField();
        pathText.setColumns(SIZE_TEXT_BOX);
        nameText = new JTextField();
        nameText.setColumns(SIZE_TEXT_BOX);
        extensionText = new JTextField();
        extensionText.setColumns(SIZE_TEXT_BOX);
        pathLabel = new JLabel("Path: ");
        nameLabel = new JLabel("File Name: ");
        extensionLabel = new JLabel("Extension: ");
        addElements();
    }

    public void addElements(){
        add(pathLabel);
        add(pathText);
        add(nameLabel);
        add(nameText);
        add(extensionLabel);
        add(extensionText);
    }
    public Map<String,String> getTextFields(){
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("path",pathText.getText());
        parameters.put("fileName",nameText.getText());
        parameters.put("extension",extensionText.getText());

        return parameters;
    }
}
