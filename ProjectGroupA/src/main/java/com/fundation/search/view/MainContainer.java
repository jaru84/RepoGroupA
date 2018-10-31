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

import java.awt.Container;
import java.util.Date;
import javax.swing.*;

/**
 * This class contains the panels which displays the results and parameters, in
 * right side the parameters panel and the results panel in the left.
 * It inherits from Container class.
 * 
 * @author Jose Colina
 * @version 1.0.
 */
public class MainContainer extends Container {
	
	/** resultsPanel variable of ResultsPanel type used to..... */
	private ResultsPanel resultsPanel;
	
	/** parametersPanel of ParametersPanel type used to ........*/
	private ParametersPanel parametersPanel;

	/**
	 * Constructor by default, where resultsPanel and parametersPanel are initialized.
	 */
	public MainContainer() {
		resultsPanel = new ResultsPanel();
		parametersPanel = new ParametersPanel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(parametersPanel);
		add(resultsPanel);
	}

	/**
	 * This method return the value in Path text field in a String.
	 * @return Path value as String.
	 */
	public String getPath() {
		return parametersPanel.getPath();

	}
	
	/**
	 * This method return the value in File Owner text field in a String.
	 * @return File Name value as String.
	 */
	public String getName() {
		return parametersPanel.getName();

	}
	
	/**
	 * This method return the value in Extension text field in a String.
	 * @return extension value as String.
	 */
	public String getExtension() {
		return parametersPanel.getExtension();

	}

	/**
	 * This method return the value in File Size text field in a String.
	 * @return File Size value as String.
	 */
	public String getFileSize() {
		return parametersPanel.getFileSize();

	}

	/**
	 * This method return the value in Size Operator text field in a String.
	 * @return Size Operator value as String.
	 */
	public String getSizeOperator() {
		return parametersPanel.getSizeOperator();

	}
	/**
	 * This method return the value in Size Scale text field in a String.
	 * @return Size Scale value as String.
	 */
	public String getSizeScale() {
		return parametersPanel.getSizeScale();

	}
	
	/**
	 * This method return the value in File Owner text field in a String.
	 * @return Owner value as String.
	 */
	public String getOwner() {
		return parametersPanel.getOwner();

	}
	
	/**
	 * This method return the value in Content text field in a String.
	 * @return Content value as String.
	 */
	public String getContent() {
		return parametersPanel.getContent();

	}

	/**
	 * This method return the value in Directory check box as boolean value.
	 * @return True if check box was checked and False if not.
	 */
	public boolean getIsDirectory() {
		return parametersPanel.getIsDirectory();

	}
	
	/**
	 * This method return the value in Hidden check box as boolean value.
	 * @return True if check box was checked and False if not.
	 */
	public boolean getIsHidden() {
		return parametersPanel.getIsHidden();

	}
	
	/**
	 * This method return the value in Read Only check box as boolean value.
	 * @return True if check box was checked and False if not.
	 */
	public boolean getIsReadOnly() {
		return parametersPanel.getIsReadOnly();

	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getSearchButton() {
		return parametersPanel.getSearchButton();
	}

	/**
	 * 
	 * @param data
	 */
	public void setResults(Object[] data) {
		resultsPanel.setTableDate(data);

	}
	
	/**
	 * 
	 */
	public void clearTable() {
		resultsPanel.clearTable();
	}
	
	/**
	 * 
	 * @return
	 */
	public Date[] getCreationDates() {
		return parametersPanel.getCreationDates();

	}
	
	/**
	 * 
	 * @return
	 */
	public Date[] getModifiedDates() {
		return parametersPanel.getModifiedDates();

	}
	
	/**
	 * 
	 * @return
	 */
	public Date[] getAccessedDates() {
		return parametersPanel.getAccessedDates();

	}
}
