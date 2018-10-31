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
 * It inherits from JPanel class.
 * 
 * @author Jose Colina
 * @version 1.0.
 */

public class ParametersPanel extends JPanel {
	
	/** fieldsPanel variable of FieldsPanel type used to.....*/
	private FieldsPanel fieldsPanel;
	
	/** searchButton variable of JButton type used to.....*/
	private JButton searchButton;
	
	/** clearButton variable of JButton type used to.....*/
	private JButton clearButton;
	
	/** buttonsPanel variable of JPanel type used to.....*/
	private JPanel buttonsPanel;

	/**
	 * Constructor by default where setting and init methods are load.
	 */
	public ParametersPanel() {
		setting();
		init();
	}
	
	/**
	 * Method used to.......
	 */
	public void setting() {
		setLayout(new BorderLayout());
	}
	
	/**
	 * method used to.......
	 */
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
		searchButton.setMaximumSize(new Dimension(400, 30));
		clearButton.setMaximumSize(new Dimension(400, 30));
		buttonsPanel.add(searchButton);
		buttonsPanel.add(clearButton);
		fieldsPanel = new FieldsPanel();
		add(fieldsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);

	}

	/**
	 * This method return the value in path text field in a String.
	 * @return path value as String.
	 */
	public String getPath() {
		return fieldsPanel.getPath();

	}
	
	/**
	 * This method return the value in file name text field in a String.
	 * @return fileName value as String.
	 */
	public String getName() {
		return fieldsPanel.getName();

	}
	
	/**
	 * This method return the value in extension text field in a String.
	 * @return extension value as String.
	 */
	public String getExtension() {
		return fieldsPanel.getExtension();

	}
	
	/**
	 * This method return the value in File Size text field in a String.
	 * @return File Size value as String.
	 */
	public String getFileSize() {
		return fieldsPanel.getFileSize();

	}
	
	/**
	 * This method return the value in Size Operator text field in a String.
	 * @return Size Operator value as String.
	 */
	public String getSizeOperator() {
		return fieldsPanel.getSizeOperator();

	}
	
	/**
	 * This method return the value in Size Scale text field in a String.
	 * @return Size Scale value as String.
	 */
	public String getSizeScale() {
		return fieldsPanel.getSizeScale();

	}
	
	/**
	 * This method return the value in File Owner text field in a String.
	 * @return File Owner value as String.
	 */
	public String getOwner() {
		return fieldsPanel.getOwner();

	}
	
	/**
	 * This method return the value in Content text field in a String.
	 * @return Content value as String.
	 */
	public String getContent() {
		return fieldsPanel.getContent();

	}
	
	/**
	 * This method return the value in Is Directory check box as boolean.
	 * @return true if Directory check box was checked and false if not.
	 */
	public boolean getIsDirectory() {
		return fieldsPanel.getIsDirectory();

	}
	
	/**
	 * This method return the value in Hidden check box as boolean.
	 * @return true if Hidden check box was checked and false if not.
	 */
	public boolean getIsHidden() {
		return fieldsPanel.getIsHidden();

	}
	
	/**
	 * This method return the value in Read Only check box as boolean.
	 * @return true if Read Only check box was checked and false if not.
	 */
	public boolean getIsReadOnly() {
		return fieldsPanel.getIsReadOnly();

	}
	
	/**
	 * Method used for...
	 * @return
	 */
	public JButton getSearchButton() {
		return searchButton;
	}
	
	/**
	 * Method used for...
	 * @return
	 */
	public Date[] getCreationDates() {
		return fieldsPanel.getCreationDates();

	}
	
	/**
	 * Method used for...
	 * @return
	 */
	public Date[] getModifiedDates() {
		return fieldsPanel.getModifiedDates();

	}
	
	/**
	 * Method used for...
	 * @return
	 */
	public Date[] getAccessedDates() {
		return fieldsPanel.getAccessedDates();

	}

}
