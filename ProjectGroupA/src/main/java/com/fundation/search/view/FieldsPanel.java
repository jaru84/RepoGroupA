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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;


/**
 * This panel contains the field names and text fields to enter the searching
 * criteria.
 *
 * @author Jose Colina
 * @version 1.0.
 */
public class FieldsPanel extends JPanel {
    private JTextField pathText;
    private JTextField nameText;
    private JTextField extensionText;
    private JTextField sizeText;
    private JLabel pathLabel;
    private JLabel nameLabel;
    private JLabel extensionLabel;
    private JLabel sizeLabel;
    private JButton chooserButton;
    private JComboBox sizeOperator;
    private JComboBox sizeScale;
    private JFileChooser chooser;
    private JCheckBox dirCheckbox;
    private JCheckBox hiddenCheckbox;
    private JCheckBox readonlyCheckbox;
    private final int SIZE_TEXT_BOX = 20;

    public FieldsPanel() {
        setLayout(new GridBagLayout());
        init();
    }

    public void init() {
        pathText = new JTextField();
        pathText.setColumns(SIZE_TEXT_BOX);
        pathText.setEditable(false);
        nameText = new JTextField();
        nameText.setColumns(SIZE_TEXT_BOX);
        extensionText = new JTextField();
        extensionText.setColumns(SIZE_TEXT_BOX);
        sizeText = new JTextField();
        sizeText.setColumns(SIZE_TEXT_BOX);
        pathLabel = new JLabel("Path: ");
        nameLabel = new JLabel("File Name: ");
        extensionLabel = new JLabel("Extension: ");
        sizeLabel = new JLabel("Size: ");
        dirCheckbox = new JCheckBox("Directory");
        hiddenCheckbox = new JCheckBox("Hidden");
        readonlyCheckbox = new JCheckBox("Read-only");
        chooserButton = new JButton("...");
        sizeOperator = new JComboBox(new Object[]{"==", ">", ">=", "<", "<="});
        sizeScale = new JComboBox(new Object[]{"", "kB", "MB", "GB"});
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChosenFile(chooser.showOpenDialog(null));
            }
        });

        addComponent(pathLabel, 0, 0, false);
        addComponent(pathText, 1, 0, true);
        addComponent(nameLabel, 0, 1, false);
        addComponent(nameText, 1, 1, true);
        addComponent(extensionLabel, 0, 2, false);
        addComponent(extensionText, 1, 2, true);
        addComponent(sizeLabel, 0, 3, false);
        addComponent(sizeText, 1, 3, true);
        addComponent(sizeOperator, 2, 3, false);
        addComponent(sizeScale, 3, 3, false);
        addComponent(chooserButton, 2, 0, false);
        addComponent(dirCheckbox, 0, 4, false);
        addComponent(hiddenCheckbox, 0, 5, false);
        addComponent(readonlyCheckbox, 0, 6, false);

    }

    /**
     * These methods return the values in text fields in a String.
     */
    public String getPath() {
        return pathText.getText();

    }

    public String getName() {
        return nameText.getText();

    }

    public String getExtension() {
        return extensionText.getText();

    }

    public String getFileSize() {
        return sizeText.getText();

    }

    public String getSizeOperator() {
        return sizeOperator.getSelectedItem().toString();

    }

    public String getSizeScale() {
        return sizeScale.getSelectedItem().toString();

    }

    public boolean getIsDirectory() {
        return dirCheckbox.isSelected();

    }

    public boolean getIsHidden() {
        return hiddenCheckbox.isSelected();

    }

    public boolean getIsReadOnly() {
        return readonlyCheckbox.isSelected();

    }

    /**
     * This method add a component in the panel in the position specified by col and
     * row grow indicates if the component will be expanded if extra space is
     * available.
     */
    private void addComponent(Component component, int col, int row, boolean grow) {
        GridBagConstraints gridConstraint = new GridBagConstraints();
        gridConstraint.gridx = col;
        gridConstraint.gridy = row;
        gridConstraint.fill = GridBagConstraints.BOTH;
        if (grow) {
            gridConstraint.weightx = 1.0;
            gridConstraint.weighty = 1.0;
        }
        add(component, gridConstraint);
    }
    /**
     * This method fill the Path field with the selected path in the dialog file chooser,
     * It's called from the chooser button's action listener
     */
    public void getChosenFile(int status) {
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            pathText.setText(file.toString());
        }

    }
}
