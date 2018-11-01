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
package com.fundation.search.controller;

/**
 * Class created to manage our custom exceptions.
 * It class inherits from Exception class.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class CustomSearchException extends Exception {
		
	/**
	 * method to send our custom message to super class.
	 * @param errorMessage (required) String type, error message necessary to send to parent Exception. 
	 */
	public CustomSearchException(String errorMessage) {
		super(errorMessage);
	}
}
