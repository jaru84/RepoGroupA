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
	protected String sizeScale;

	/**
	 * Constructor for SearcherCriteria class where the values inserted to be find
	 * will be stored as an object.
	 */
	public SearcherCriteria() {
		super();
		operator = "";
		sizeScale = "";
	}

	/**
	 * Method setter to operator value.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * Method setter to size scale value.
	 */
	public void setSizeScale(String sizeScale) {
		this.sizeScale = sizeScale;
	}
	
	/**
	 * Method getter to operator value.
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * method getter to size scale value.
	 */
	public String getSizeScale() {
		return this.sizeScale;
	}
	
	/**
	 * Method which help to convert the criteria size to bytes.
	 * Assumption, the default value is zero.
	 */
	public void sizeToBytes () {
		long tempSize = Long.parseLong(this.size);
		if (this.sizeScale.toUpperCase().equals("KB")) {
			tempSize = tempSize * 1024;
		} else {
			if (this.sizeScale.toUpperCase().equals("MB")) {
				tempSize = tempSize * 1024 * 1024;
			} else {
				if (this.sizeScale.toUpperCase().equals("GB")) {
					tempSize = tempSize * 1024 * 1024 * 1024;
				}
			}
		}
		this.size = Long.toString(tempSize);
	}
	
	/**
	 * method toString to display attributes from Searcher Criteria object.
	 */
	public String toString () {
		return super.toString() + "\nOperator: " + operator + "\nIs Directory: " + isDirectory + "\nIs Hidden: " + isHidden + "\nIs ReadOnly: " + isReadOnly;
	}
}
