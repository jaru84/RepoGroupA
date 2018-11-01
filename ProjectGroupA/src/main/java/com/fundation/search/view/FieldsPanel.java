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

import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
    private JTextField ownerText;
    private JTextField contentText;
    private JLabel pathLabel;
    private JLabel nameLabel;
    private JLabel extensionLabel;
    private JLabel sizeLabel;
    private JLabel ownerLabel;
    private JLabel contentLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JButton chooserButton;
    private JComboBox sizeOperator;
    private JComboBox sizeScale;
    private JComboBox dateType;
    private JFileChooser chooser;
    private JCheckBox dirCheckbox;
    private JCheckBox hiddenCheckbox;
    private JCheckBox readonlyCheckbox;
    private UtilDateModel startDateModel;
    private JDatePanelImpl startDatePanel;
    private JDatePickerImpl startDatePicker;
    private UtilDateModel endDateModel;
    private JDatePanelImpl endDatePanel;
    private JDatePickerImpl endDatePicker;
    private JPanel datesPanel;
    private Properties pickerProperties;
    private final int SIZE_TEXT_BOX = 20;

    public FieldsPanel() {
        setLayout(new GridBagLayout());
        init();
    }

    public void init() {
        initTextFields();
        initLabels();
        initDates();
        initCheckboxes();
        initVarious();
        addElements();
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

    public String getOwner() {
        return ownerText.getText();

    }

    public String getContent() {
        return contentText.getText();

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

    public Date getStartDate() {
        return (Date)startDatePicker.getModel().getValue();

    }

    public Date getEndDate() {
        return (Date)endDatePicker.getModel().getValue();

    }

    public String getDateType() {
        return dateType.getSelectedItem().toString();

    }


    /**
     * This method add a component in the panel in the position specified by col and
     * row grow indicates if the component will be expanded if extra space is
     * available.
     */
    private void addComponent(Component component, int col, int row, boolean grow, int width) {
        GridBagConstraints gridConstraint = new GridBagConstraints();
        gridConstraint.gridx = col;
        gridConstraint.gridy = row;
        gridConstraint.gridwidth = width;
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

    public void toggleTextFields(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED){
            nameText.setEditable(false);
            extensionText.setEditable(false);
            sizeText.setEditable(false);
            sizeOperator.setEnabled(false);
            sizeScale.setEnabled(false);
            contentText.setEditable(false);
            readonlyCheckbox.setEnabled(false);
        }
        else{
            nameText.setEditable(true);
            extensionText.setEditable(true);
            sizeText.setEditable(true);
            sizeOperator.setEnabled(true);
            sizeScale.setEnabled(true);
            readonlyCheckbox.setEnabled(true);
            contentText.setEditable(true);

        }
    }

    public void initDates(){
        pickerProperties = new Properties();
        pickerProperties.put("text.today", "Today");
        pickerProperties.put("text.month", "Month");
        pickerProperties.put("text.year", "Year");
        // Initialize start/end dates pickers
        startDateModel = new UtilDateModel();
        startDatePanel = new JDatePanelImpl(startDateModel, pickerProperties);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        endDateModel = new UtilDateModel();
        endDatePanel = new JDatePanelImpl(endDateModel, pickerProperties);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        datesPanel = new JPanel();
        datesPanel.add(startDateLabel);
        datesPanel.add(startDatePicker);
        datesPanel.add(endDateLabel);
        datesPanel.add(endDatePicker);
    }

    public void initTextFields(){
        pathText = new JTextField();
        pathText.setColumns(SIZE_TEXT_BOX);
        pathText.setEditable(false);
        nameText = new JTextField();
        nameText.setColumns(SIZE_TEXT_BOX);
        extensionText = new JTextField();
        extensionText.setColumns(SIZE_TEXT_BOX);
        sizeText = new JTextField();
        sizeText.setColumns(SIZE_TEXT_BOX);
        ownerText = new JTextField();
        ownerText.setColumns(SIZE_TEXT_BOX);
        contentText = new JTextField();
        contentText.setColumns(SIZE_TEXT_BOX);
    }

    public void initLabels(){
        pathLabel = new JLabel("Path: ");
        nameLabel = new JLabel("File Name: ");
        extensionLabel = new JLabel("Extension: ");
        sizeLabel = new JLabel("Size: ");
        ownerLabel = new JLabel("Owner: ");
        contentLabel = new JLabel("Content: ");
        startDateLabel = new JLabel("Between: ");
        endDateLabel = new JLabel("and: ");
    }

    public void initCheckboxes(){
        dirCheckbox = new JCheckBox("Directory");
        ItemListener dirListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                toggleTextFields(e);
            }
        };
        dirCheckbox.addItemListener(dirListener);
        hiddenCheckbox = new JCheckBox("Hidden");
        readonlyCheckbox = new JCheckBox("Read-only");
    }

    public void initVarious(){
        chooserButton = new JButton("...");
        sizeOperator = new JComboBox(new Object[]{"==", ">", ">=", "<", "<="});
        sizeScale = new JComboBox(new Object[]{"bytes", "kB", "MB", "GB"});
        dateType = new JComboBox(new Object[]{"Creation Date", "Modified Date", "Accessed Date"});
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChosenFile(chooser.showOpenDialog(null));
            }
        });
    }

    public void addElements(){
        addComponent(pathLabel, 0, 0, false,1);
        addComponent(nameLabel, 0, 1, false,1);
        addComponent(extensionLabel, 0, 2, false,1);
        addComponent(sizeLabel, 0, 3, false,1);
        addComponent(ownerLabel, 0, 4, false,1);
        addComponent(contentLabel, 0, 5, false,1);
        addComponent(pathText, 1, 0, true,2);
        addComponent(nameText, 1, 1, true,3);
        addComponent(extensionText, 1, 2, true,3);
        addComponent(sizeText, 1, 3, true,1);
        addComponent(ownerText, 1, 4, true,3);
        addComponent(contentText, 1, 5, true,3);
        addComponent(sizeOperator, 2, 3, false,1);
        addComponent(sizeScale, 3, 3, false,1);
        addComponent(chooserButton, 3, 0, false,1);
        addComponent(dirCheckbox, 4, 0, false,1);
        addComponent(hiddenCheckbox, 4, 1, false,1);
        addComponent(readonlyCheckbox, 4, 2, false,1);
        addComponent(dateType, 0, 6, false,1);
        addComponent(datesPanel, 1, 6, false,1);
    }

    public void clearFields(){

        pathText.setText("");
        nameText.setText("");
        extensionText.setText("");
        sizeText.setText("");
        ownerText.setText("");
        contentText.setText("");
        sizeScale.setSelectedIndex(0);
        sizeOperator.setSelectedIndex(0);
        dirCheckbox.setSelected(false);
        hiddenCheckbox.setSelected(false);
        readonlyCheckbox.setSelected(false);
        startDateModel.setValue(null);
        endDateModel.setValue(null);
    }
}
