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

import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * This panel contains the field names and text fields to enter the searching
 * criteria.
 * It inherits from JPanel.
 *
 * @author Jose Colina
 * @version 1.0.
 */
public class FieldsPanel extends JPanel {

    /**
     * pathText variable of JTextField type.
     */
    private JTextField pathText;

    /**
     * nameText variable of JTextField type.
     */
    private JTextField nameText;

    /**
     * pathText variable of JTextField type.
     */
    private JTextField extensionText;

    /**
     * sizeText variable of JTextField type.
     */
    private JTextField sizeText;

    /**
     * ownerText variable of JTextField type.
     */
    private JTextField ownerText;

    /**
     * contentText variable of JTextField type.
     */
    private JTextField contentText;

    /**
     * pathLabel variable of JLabel type.
     */
    private JLabel pathLabel;

    /**
     * nameLabel variable of JLabel type.
     */
    private JLabel nameLabel;

    /**
     * extensionLabel variable of JLabel type.
     */
    private JLabel extensionLabel;

    /**
     * sizeLabel variable of JLabel type.
     */
    private JLabel sizeLabel;

    /**
     * ownerLabel variable of JLabel type.
     */
    private JLabel ownerLabel;

    /**
     * contentLabel variable of JLabel type.
     */
    private JLabel contentLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;

    /** modifiedDateLabel variable of JLabel type. */

    /**
     * chooserButton variable of JButton type.
     */
    private JButton chooserButton;

    /**
     * sizeOperator variable of JComboBox type.
     */
    private JComboBox sizeOperator;

    /**
     * sizeScale variable of JComboBox type.
     */
    private JComboBox sizeScale;
    private JComboBox dateType;
    /**
     * chooser variable of JFileChooser type.
     */
    private JFileChooser chooser;

    /**
     * dirCheckbox variable of JCheckBox type.
     */
    private JCheckBox dirCheckbox;

    /**
     * hiddenCheckbox variable of JCheckBox type.
     */
    private JCheckBox hiddenCheckbox;

    /**
     * readonlyCheckbox variable of JCheckBox type.
     */
    private JCheckBox readonlyCheckbox;
    /**
     * startDateModel variable of UtilDateModel to handle Date picker.
     */
    private UtilDateModel startDateModel;
    /**
     * startDatePanel variable of JDatePanelImpl to contain Date picker.
     */
    private JDatePanelImpl startDatePanel;
    /**
     * startDatePicker variable of JDatePickerImpl to implement the Start Date picker.
     */
    private JDatePickerImpl startDatePicker;
    /**
     * endDateModel variable of UtilDateModel to handle Date picker.
     */
    private UtilDateModel endDateModel;
    /**
     * endDatePanel variable of JDatePanelImpl to contain Date picker.
     */
    private JDatePanelImpl endDatePanel;
    /**
     * endDatePicker variable of JDatePickerImpl to implement the End Date picker.
     */
    private JDatePickerImpl endDatePicker;
    /**
     * datesPanel variable of JPanel to contain the start/end date pickers.
     */
    private JPanel datesPanel;
    /**
     * pickerProperties variable of Properties type to configure start/end Date pickers.
     */
    private Properties pickerProperties;
    /**
     * SIZE_TEXT_BOX variable ctte of int type.
     */
    private final int SIZE_TEXT_BOX = 20;

    /**
     * Constructor by default, where setLayout and init methods are loaded.
     */
    public FieldsPanel() {
        setLayout(new GridBagLayout());
        init();
    }

    /**
     * Method used to initialize the GUI elements
     */
    public void init() {
        initTextFields();
        initLabels();
        initDates();
        initCheckboxes();
        initVarious();
        addElements();
    }

    /**
     * This method return the value in path text field in a String.
     *
     * @return path value as String.
     */
    public String getPath() {
        return pathText.getText();

    }

    /**
     * This method return the value in file name text field in a String.
     *
     * @return fileName value as String.
     */
    public String getName() {
        return nameText.getText();

    }

    /**
     * This method return the value in extension text field in a String.
     *
     * @return extension value as String.
     */
    public String getExtension() {
        return extensionText.getText();

    }

    /**
     * This method return the value in File Size text field in a String.
     *
     * @return File Size value as String.
     */
    public String getFileSize() {
        return sizeText.getText();

    }

    /**
     * This method return the value in Size Operator text field in a String.
     *
     * @return Size Operator value as String.
     */
    public String getSizeOperator() {
        return sizeOperator.getSelectedItem().toString();

    }

    /**
     * This method return the value in Size Scale text field in a String.
     *
     * @return Size Scale value as String.
     */
    public String getSizeScale() {
        return sizeScale.getSelectedItem().toString();

    }

    /**
     * This method return the value in File Owner text field in a String.
     *
     * @return File Owner value as String.
     */
    public String getOwner() {
        return ownerText.getText();

    }

    /**
     * This method return the value in Content text field in a String.
     *
     * @return Content value as String.
     */
    public String getContent() {
        return contentText.getText();

    }

    /**
     * This method return the value in Is Directory check box as boolean.
     *
     * @return true if Directory check box was checked and false if not.
     */
    public boolean getIsDirectory() {
        return dirCheckbox.isSelected();

    }

    /**
     * This method return the value in Hidden check box as boolean.
     *
     * @return true if Hidden check box was checked and false if not.
     */
    public boolean getIsHidden() {
        return hiddenCheckbox.isSelected();

    }

    /**
     * This method return the value in Read Only check box as boolean.
     *
     * @return true if Read Only check box was checked and false if not.
     */
    public boolean getIsReadOnly() {
        return readonlyCheckbox.isSelected();

    }

    public Date getStartDate() {
        return (Date) startDatePicker.getModel().getValue();

    }

    public Date getEndDate() {
        return (Date) endDatePicker.getModel().getValue();

    }

    public String getDateType() {
        return dateType.getSelectedItem().toString();

    }


    /**
     * This method add a component in the panel in the position specified by col and
     * row, grow indicates if the component will be expanded if extra space is
     * available.
     *
     * @param component is the component to be added in the panel
     * @param col       the column position to set the component
     * @param row       the row position to set the component
     * @param grow      if true the element will be expanded to use the additional space
     * @param width     the number of rows that the element will occupy
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
     * This method fill the Path text field with the selected path in the dialog file
     * chooser, It's called from the chooser button's action listener
     *
     * @param status if APPROVE_OPTION then Open button was pressed otherwise Cancel was pressed
     */
    public void getChosenFile(int status) {
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            pathText.setText(file.toString());
        }

    }

    /**
     * This method to disable/enable proper text fields when Directory checkbox is selected
     *
     * @param e used to get the change of state in Directory checkbox
     */
    public void toggleTextFields(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            nameText.setEditable(false);
            extensionText.setEditable(false);
            contentText.setEditable(false);
            readonlyCheckbox.setEnabled(false);
            dateType.setSelectedIndex(0);
            dateType.setEnabled(false);
        } else {
            nameText.setEditable(true);
            extensionText.setEditable(true);
            readonlyCheckbox.setEnabled(true);
            contentText.setEditable(true);
            dateType.setEnabled(true);

        }
    }

    /**
     * This method initialize the necessary elements to instantiate the start/end date pickers
     */
    public void initDates() {
        pickerProperties = new Properties();
        pickerProperties.put("text.today", "Today");
        pickerProperties.put("text.month", "Month");
        pickerProperties.put("text.year", "Year");
        // Initialize start/end dates pickers
        startDateModel = new UtilDateModel();
        startDatePanel = new JDatePanelImpl(startDateModel, pickerProperties);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        startDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endDateModel.setValue(startDateModel.getValue());

            }
        });
        endDateModel = new UtilDateModel();
        endDatePanel = new JDatePanelImpl(endDateModel, pickerProperties);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        datesPanel = new JPanel();
        datesPanel.add(startDateLabel);
        datesPanel.add(startDatePicker);
        datesPanel.add(endDateLabel);
        datesPanel.add(endDatePicker);
    }

    /**
     * Method initialize the text fields
     */
    public void initTextFields() {
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

    /**
     * Method initialize the labels
     */
    public void initLabels() {
        pathLabel = new JLabel("Path: ");
        nameLabel = new JLabel("File Name: ");
        extensionLabel = new JLabel("Extension: ");
        sizeLabel = new JLabel("Size: ");
        ownerLabel = new JLabel("Owner: ");
        contentLabel = new JLabel("Content: ");
        startDateLabel = new JLabel("Between: ");
        endDateLabel = new JLabel("and: ");
    }

    /**
     * Method initialize the checkboxes
     */
    public void initCheckboxes() {
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

    /**
     * Method initialize additional GUI elements
     */
    public void initVarious() {
        chooserButton = new JButton("...");
        sizeOperator = new JComboBox(new Object[]{"==", ">", ">=", "<", "<="});
        sizeScale = new JComboBox(new Object[]{"bytes", "kB", "MB", "GB"});
        dateType = new JComboBox(new Object[]{"< Select a Value >", "Creation Date", "Modified Date", "Accessed Date"});
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChosenFile(chooser.showOpenDialog(null));
            }
        });
    }

    /**
     * Method add all the elements in the panel
     */
    public void addElements() {
        addComponent(pathLabel, 0, 0, false, 1);
        addComponent(nameLabel, 0, 1, false, 1);
        addComponent(extensionLabel, 0, 2, false, 1);
        addComponent(sizeLabel, 0, 3, false, 1);
        addComponent(ownerLabel, 0, 4, false, 1);
        addComponent(contentLabel, 0, 5, false, 1);
        addComponent(pathText, 1, 0, true, 2);
        addComponent(nameText, 1, 1, true, 3);
        addComponent(extensionText, 1, 2, true, 3);
        addComponent(sizeText, 1, 3, true, 1);
        addComponent(ownerText, 1, 4, true, 3);
        addComponent(contentText, 1, 5, true, 3);
        addComponent(sizeOperator, 2, 3, false, 1);
        addComponent(sizeScale, 3, 3, false, 1);
        addComponent(chooserButton, 3, 0, false, 1);
        addComponent(dirCheckbox, 4, 0, false, 1);
        addComponent(hiddenCheckbox, 4, 1, false, 1);
        addComponent(readonlyCheckbox, 4, 2, false, 1);
        addComponent(dateType, 0, 6, false, 1);
        addComponent(datesPanel, 1, 6, false, 1);
    }

    /**
     * Method clear all the content in the parameters fields
     */
    public void clearFields() {

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
        dateType.setSelectedIndex(0);

    }
}
