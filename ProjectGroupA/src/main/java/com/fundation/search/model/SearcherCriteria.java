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
package com.fundation.search.model;

/**
 * Class created to manage the search criteria object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherCriteria extends CustomFile {

	private String operator;
	private boolean isDirectory, isHidden, isReadOnly;

	/**
	 * constructor for SearcherCriteria class where the values inserted to be find
	 * will be stored as an object.
	 */
	public SearcherCriteria() {
		super();
		operator = "";
		isDirectory = false;
		isHidden = false;
		isReadOnly = false;
	}

	/**
	 * method setter to operator value.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * method setter to Directory box.
	 */
	public void setIsDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	/**
	 * method setter to Hidden box.
	 */
	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	
	/**
	 * method setter to Read Only box.
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	/**
	 * method getter to operator value.
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * method setter to Directory box.
	 */
	public boolean getIsDirectory() {
		return this.isDirectory;
	}
	
	/**
	 * method setter to Hidden box.
	 */
	public boolean getIsHidden() {
		return this.isHidden;
	}
	
	/**
	 * method setter to Read Only box.
	 */
	public boolean getIsReadOnly() {
		return this.isReadOnly;
	}

	/**
	 * method toString to display attributes from Searcher Criteria object.
	 */
	public String toString () {
		return super.toString() + "\nOperator: " + operator + "\nIs Directory: " + isDirectory + "\nIs Hidden: " + isHidden + "\nIs ReadOnly: " + isReadOnly;
	}
}
