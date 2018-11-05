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
	
	/** startDate variable of Date type used to save the value selected by user from start date calendar.*/
	private Date startDate;
	
	/** endDate variable of Date type used to save the value selected by user from start date calendar.*/
	private Date endDate;
	
	/**dateType variable of String type used to save the value selected by user from drop down list.*/
	private String dateType;
	
	/**
	 * Constructor for SearcherCriteria class where the values inserted to be find
	 * will be stored as an object.
	 */
	public SearcherCriteria() {
		super();
		operator = "";
		
	}
	
	/**
	 * Method setter to operator value.
	 * @param operator It is used to set a String value to operator attribute.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * Method getter to operator value.
	 * @return the value of operator as String.
	 */
	public String getOperator() {
		return operator;
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
	 * @param endDate It is used to save the value selected by the user from calendar - start date.
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 * Method setter to dateType value.
	 * @param dateType String type with a value of "Creation Date", "Modified Date", "Accessed Date"
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
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
	 * Method getter to dateType value.
	 * @return the value chosen by user as String.
	 */
	public String getDateType() {
		return this.dateType;
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
	 * Method toString to display attributes from Searcher Criteria object.
	 * @return All attributes that have the class SearcherCriteria as String. 
	 */
	public String toString() {
		return super.toString() + "\nOperator: " + operator + "\nIs Directory: " + isDirectory + "\nIs Hidden: "
				+ isHidden + "\nIs ReadOnly: " + isReadOnly;
	}
}
