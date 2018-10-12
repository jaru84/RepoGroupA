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

public class ParametersPanel extends JPanel {
    private FieldsPanel fieldsPanel;
    private JButton searchButton;

    public ParametersPanel(){
        setLayout(new BorderLayout());
        searchButton = new JButton("Search");
        fieldsPanel = new FieldsPanel();
        addElements();
    }
    public void addElements(){
        add(fieldsPanel, BorderLayout.NORTH);
        add(searchButton, BorderLayout.SOUTH);

    }
    public Map<String,String> getParameters(){
        return fieldsPanel.getTextFields();
    }
    public JButton getSearchButton(){
        return searchButton;
    }
}
