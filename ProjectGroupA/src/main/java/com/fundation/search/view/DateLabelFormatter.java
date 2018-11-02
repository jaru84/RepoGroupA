/*
 *  @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 *  2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 *  All rights reserved.
 *  <p>
 *  This software is the confidential and proprietary information of
 *  Jalasoft, ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 */

package com.fundation.search.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * This class formats the date pattern for the JDatePickerImpl.
 * it inherits from AbstractFormatter class.
 *
 * @author Jose Colina
 * @version 1.0.
 */
public class DateLabelFormatter extends AbstractFormatter {
	
	/** datePattern variable of String type used to get date inserted by user. */
	private String datePattern = "dd-MM-yyyy";
	
	/** dateFormatter variable of SimpleDateFormat type used to manage create an object using the datePattern variable. */
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	/**
	 * Method necessary to return the string value in date format.
	 * @param text (required) it is String type.
	 * @throws ParseException if an error has been reached unexpectedly while parsing
	 * @return An object.
	 */
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}
	
	/**
	 * Method necessary to convert the object value to String.
	 * @param value (required) It is of Object type.
	 * @throws ParseException if an error has been reached unexpectedly while parsing
	 * @return An String value.
	 */
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}
}
