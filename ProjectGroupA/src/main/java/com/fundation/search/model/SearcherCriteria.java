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

import java.util.Calendar;
import java.util.Date;

/**
 * Class created to manage the search criteria object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherCriteria extends CustomFile {

    /**
     * operator variable of String type used to save the value set by the user.
     */
    private String operator;

    /**
     * startDate variable of Date type used to save the value selected by user from start date calendar.
     */
    private Date startDate;

    /**
     * endDate variable of Date type used to save the value selected by user from start date calendar.
     */
    private Date endDate;

    /**
     * dateType variable of String type used to save the value selected by user from drop down list.
     */
    private String dateType;

    /**
     * Constructor for SearcherCriteria class where the values inserted to be find
     * will be stored as an object.
     */
    public SearcherCriteria() {
        super();
        operator = "";
        startDate = null;
        endDate = null;
        dateType = "";
    }

    /**
     * Method setter to operator value.
     *
     * @param operator It is used to set a String value to operator attribute.
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Method getter to operator value.
     *
     * @return the value of operator as String.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Method setter to start date value.
     *
     * @param startDate It is used to save the value selected by the user from calendar-start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Method setter to end date value.
     *
     * @param endDate It is used to save the value selected by the user from calendar - start date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Method setter to dateType value.
     *
     * @param dateType String type with a value of "Creation Date", "Modified Date", "Accessed Date"
     */
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    /**
     * Method getter to start date value.
     *
     * @return the value of start date
     */
    public Date getStartDate() {
        return setTimeCustom(this.startDate, 0, 0, 0);
    }

    /**
     * Method getter to end date value.
     *
     * @return the value of end date value.
     */
    public Date getEndDate() {
        return setTimeCustom(this.endDate, 23, 59, 59);
    }

    /**
     * Method getter to dateType value.
     *
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
     * Method which set a date with a given hour, so later we can covert the dates to milliseconds.
     * using the calendar since the another methods from Date were deprecated.
     *
     * @param d (required) Date type, which will be modified.
     * @param h (required) int type, hour(s) to be specified.
     * @param m (required) int type, minute(s) to be specified.
     * @param s (required) int type, second(s) to be specified.
     * @return a Date value with everything customized.
     */
    public Date setTimeCustom(Date d, int h, int m, int s) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, m);
            c.set(Calendar.SECOND, s);
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
        } else {
            return null;
        }
    }

    /**
     * Method toString to display attributes from Searcher Criteria object.
     *
     * @return All attributes that have the class SearcherCriteria as String.
     */
    public String toString() {
        return super.toString() + "\nOperator: " + operator + "\nIs Directory: " + isDirectory + "\nIs Hidden: "
                + isHidden + "\nIs ReadOnly: " + isReadOnly;
    }
}
