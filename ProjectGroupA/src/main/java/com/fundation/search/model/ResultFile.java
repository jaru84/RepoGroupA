/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fundation.search.model;

import java.io.File;

/**
 * Class created to manage the result files object and its attributes.
 *
 * @author Jacqueline
 * @version 1.0.
 */
public class ResultFile extends CustomFile {
	private String owner;

	/**
	 * constructor for ResultFiles object
	 */
	public ResultFile() {
		super();
		owner= "";
	}

	/**
	 * Constructor for ResultFiles with parameters
	 */
	public ResultFile(String inputLine, SearcherCriteria file) {
		super();
		setFileValues(inputLine, file);
	}
	
	/**
	 * Filling the data of the Result file to show in the table later
	 */
	private void setFileValues(String inputLine, SearcherCriteria criteria) {

		if (criteria.getIsDirectory()) {
			fileName = "";
			ext = "";
			path= inputLine;
		} else {
			setSizeKB(inputLine);
			
			String[] pathValues = inputLine.split("\\\\");
			String fullFileName = pathValues[pathValues.length - 1];
			String[] fileNameValues = fullFileName.split("\\.");
			fileName = fileNameValues[0]; 
			ext = fileNameValues[1];
			path = "";
			
			for(int i = 0; i< pathValues.length - 1; i++) {
				path += pathValues[i] + "\\";
			}
			
		}
		isDirectory = criteria.getIsDirectory();
		isHidden = criteria.getIsHidden();
		isReadOnly = criteria.getIsReadOnly();	
	}
	
	/**
	 * Method setter to owner value.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * method getter to owner value.
	 */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * Method to update the size of the file found into KB to follow Windows standard.
	 */
	public void setSizeKB (String inputLine) {
		File tempFile = new File (inputLine);
		long tempFileSize = tempFile.length();
		
		//Need to check a better way to round the size, tried with Math.round, BinaryDecimal... 
		//none of them provided the results needed gathered manually.
		String s = String.valueOf(tempFileSize/1024) + " " + "KB";
		this.setSize(s);
	}	
	
}
