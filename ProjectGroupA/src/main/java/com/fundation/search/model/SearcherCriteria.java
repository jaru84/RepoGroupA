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

import java.util.Date;

/**
 * Class created to manage the search criteria object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherCriteria extends CustomFile {
	
	/** operator variable of String type used to save the value set by the user. */
	private String operator;
	
	/** sizeScale variable of String type used to save the value set by the user. */
	private String sizeScale;
	
	/** isDirectory variable of boolean type used to save the value set by the user. */
	private boolean isDirectory;
	
	/** isHidden variable of boolean type used to save the value set by the user. */
	private boolean isReadOnly;
	
	/** startDate variable of Date type used to save the value selected by user from start date calendar.*/
	private Date startDate;
	
	/** endDate variable of Date type used to save the value selected by user from start date calendar.*/
	private Date endDate;
	
	/** dateSearch variable of String type used to save the value selected by user.*/
	private String dateSearch;
	
	/**
	 * Constructor for SearcherCriteria class where the values inserted to be find
	 * will be stored as an object.
	 */
	public SearcherCriteria() {
		super();
		operator = "";
		sizeScale = "";
		isDirectory = false;
		isReadOnly = false;
	}

	/**
	 * Method setter to operator value.
	 * @param operator It is used to set a String value to operator attribute.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * Method setter to size scale value.
	 * @param operator It is used to set a String value to sizeScale attribute.
	 */
	public void setSizeScale(String sizeScale) {
		this.sizeScale = sizeScale;
	}

	/**
	 * Method getter to operator value.
	 * @return the value of operator as String.
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Method setter to Directory box.
	 * @param isDirectory It is used to set a boolean value to isDirectory attribute.
	 */
	public void setIsDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	/**
	 * Method setter to Read Only box.
	 * @param isReadOnly It is used to set a boolean value to isReadOnly attribute.
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	
	/**
	 * Method setter to start date value.
	 * @param startDate It is used to save the value selected by the user from calendar-start date.
	 */
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	
	/**
	 * Method setter to end date value.
	 * @param endDate It is used to save the value selected by the user from calendar-end date.
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 * Method setter to the type of date to look for (creation, modified, access).
	 * @param s It is used to save the value selected by the user from the type of date to search.
	 */
	public void setDateSearch(String s){
		this.dateSearch = s;
	}
	
	/**
	 * method getter to size scale value.
	 * @return the value of sizeScale as String.
	 */
	public String getSizeScale() {
		return this.sizeScale;
	}

	/**
	 * Method getter to Directory box.
	 * @return the value of isDirectory as boolean.
	 */
	public boolean getIsDirectory() {
		return this.isDirectory;
	}
	
	/**
	 * Method getter to Read Only box.
	 * @return the value of isReadOnly as boolean.
	 */
	public boolean getIsReadOnly() {
		return this.isReadOnly;
	}
	
	/**
	 * Method getter to start date value.
	 * @return the value of start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Method getter to end date value.
	 * @return the value of end date value.
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Method getter to date search value.
	 * @return the value of the type of date to search.
	 */
	public String getDateSearh() {
		return this.dateSearch;
	}
	
	/**
	 * Method which help to convert the criteria size to bytes. Assumption, the
	 * default value is zero.
	 */
	public void sizeToBytes() {
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
	 * @return All attributes that have the class SearcherCriteria as String. 
	 */
	public String toString() {
		return super.toString() + "\nOperator: " + operator + "\nIs Directory: " + isDirectory + "\nIs Hidden: "
				+ isHidden + "\nIs ReadOnly: " + isReadOnly;
	}
}
