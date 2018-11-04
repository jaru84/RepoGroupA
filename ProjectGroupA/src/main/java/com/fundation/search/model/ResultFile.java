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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class created to manage the result files object and its attributes.
 * It inherits from CustomFile class.
 *
 * @author Jacqueline
 * @version 1.0.
 */
public class ResultFile extends CustomFile {

	/** isHidden variable of boolean type used to save the value set by the user. */
	private boolean isReadOnly;
	
	/**
	 * constructor for ResultFiles object
	 */
	public ResultFile() {
		super();
	}

	/**
	 * Method getter to Read Only box.
	 * @return the value of isReadOnly as boolean.
	 */
	public boolean getIsReadOnly() {
		return this.isReadOnly;
	}
	
	/**
	 * Method setter to Read Only box.
	 * @param isReadOnly It is used to set a boolean value to isReadOnly attribute.
	 */
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	
	/**
	 * Constructor for ResultFiles with parameters.
	 * @param inputline (required) file got from search process used to create the object of ResultFile type.
	 * @param file (required)  SearcherCriteria type, used to create the object of ResultFile type.
	 */
	public ResultFile(String inputLine, SearcherCriteria file) {
		super();
		setFileValues(inputLine, file);
	}

	/**
	 * Method used by the constructor with parameters required to fill the data of the Result file to show in the table later.
	 * @param inputline (required) file got from search process used to create the object of ResultFile type.
	 * @param file (required)  SearcherCriteria type, used to create the object of ResultFile type.
	 */
	private void setFileValues(String inputLine, SearcherCriteria criteria) {

		if (criteria.getIsDirectory()) {
			fileName = "";
			ext = "";
			path = inputLine;			
		} else {
			setSizeKB(inputLine);
				
			String[] pathValues = inputLine.split("\\\\");
			String fullFileName = pathValues[pathValues.length - 1];
			String[] fileNameValues = fullFileName.split("\\.");
			if ( fileNameValues.length > 2){
				for (int i = 0; i < fileNameValues.length-1; i++)
				{
					fileName += fileNameValues[i] + ".";
				}
				fileName = fileName.substring(0, fileName.length()-1);
				ext = fileNameValues[fileNameValues.length-1];
			} else {
				fileName = fileNameValues[0];
				if (fileNameValues.length > 1) {
					ext = fileNameValues[1];
				} else {
					ext = "";
				}
				
			}
			path = "";

			for (int i = 0; i < pathValues.length - 1; i++) {
				path += pathValues[i] + "\\";
			}
		}
		
		setDates(inputLine);
		try {
			setOwnerFile(inputLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to update the size of the file found into KB to follow Windows
	 * standard.
	 * @param inputline (required) file got from search process used to get its size value.
	 */
	public void setSizeKB(String inputLine) {
		File tempFile = new File(inputLine);
		long tempFileSize = tempFile.length();

		// Need to check a better way to round the size, tried with Math.round,
		// BinaryDecimal...
		// none of them provided the results needed gathered manually.
		String s = String.valueOf(tempFileSize / 1024) + " " + "KB";
		this.setSize(s);
	}
	
	/**
	 * Method to update the size of the file found into KB to follow Windows
	 * standard.
	 * @param inputline (required) file got from search process used to get its size value.
	 * @throws IOException 
	 */
	public void setOwnerFile(String inputLine) throws IOException {
		
		File tempFile = new File(inputLine);
		Path pathFile = tempFile.toPath();
		
		FileOwnerAttributeView atrib = Files.getFileAttributeView(pathFile, FileOwnerAttributeView.class);
        UserPrincipal owner = atrib.getOwner();
        this.owner = owner.getName();
	}
	
	/**
	 * Method to update the dates of an item found
	 * @param inputline (required) file got from search process used to get the dates.
	 */
	public void setDates(String inputLine) {
		File tempFile = new File(inputLine);
		Path pFile = tempFile.toPath();
		
		//Defining the format we will use to show the date, it could be dd/mm/yyyy
		DateFormat dateF = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		BasicFileAttributes dateFile;
		
		try {
			dateFile = Files.readAttributes(pFile, BasicFileAttributes.class);
			
			FileTime dateC = dateFile.creationTime();
			this.createDate = dateF.format(dateC.toMillis());
			
			FileTime dateM = dateFile.lastModifiedTime();
			this.modDate = dateF.format(dateM.toMillis());
			
			FileTime dateA = dateFile.lastAccessTime();
			this.accessDate = dateF.format(dateA.toMillis());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}
