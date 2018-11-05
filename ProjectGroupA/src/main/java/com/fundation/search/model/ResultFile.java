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
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fundation.search.controller.CustomSearchException;


/**
 * Class created to manage the result files object and its attributes.
 * It inherits from CustomFile class.
 *
 * @author Jacqueline
 * @version 1.0.
 */
public class ResultFile extends CustomFile {
	
	/** creationDate variable of String type used to store the value of Creation Date of a file.*/
	private String creationDate;
	
	/** lastModifiedDate variable of String type used to store the value of Last Modified Date of a file.*/
	private String lastModifiedDate;
	
	/** accessedDate variable of Date String used to store the value of Accessed Date of a file.*/
	private String accessedDate;
	
	/**
	 * constructor for ResultFiles object
	 */
	public ResultFile() {
		super();
		creationDate = null;
		lastModifiedDate = null;
		accessedDate = null;
	}
	
	/**
	 * Constructor for ResultFiles with parameters.
	 * @param inputline (required) file got from search process used to create the object of ResultFile type.
	 * @param file (required)  SearcherCriteria type, used to create the object of ResultFile type.
	 * @throws IOException If during dates assignation something is wrong.
	 */
	public ResultFile(String inputLine, SearcherCriteria criteria) throws IOException {
		super();
		setFileValues(inputLine, criteria);
		setHiddenAndReadOnly(inputLine);
		setDates(inputLine);
		setOwner(inputLine);
	}
	
	/**
	 * Method setter to the creationDate value.
	 * @param creationDate (required) It needs an attribute of Date value.
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Method setter to the lastModifiedDate value.
	 * @param lastModifiedDate (required) It needs an attribute of Date value.
	 */
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	/**
	 * Method setter to the lastModifiedDate value.
	 * @param accessedDate (required) It needs an attribute of Date value.
	 */
	public void setAccessedDate(String accessedDate) {
		this.accessedDate = accessedDate;
	}
	
	/**
	 * Method getter to return the value of creationDate attribute.
	 * @return An value on Date type.
	 */
	public String getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * Method getter to return the value of creationDate attribute.
	 * @return An value on Date type.
	 */
	public String getLastModifiedDate() {
		return this.lastModifiedDate;
	}
	
	/**
	 * Method getter to return the value of creationDate attribute.
	 * @return An value on Date type.
	 */
	public String getAccessedDate() {
		return this.accessedDate;
	}
	
	/**
	 * Method used by the constructor with parameters required to fill the data of the Result file (file name, extension, path, size) to show in the table later.
	 * @param inputline (required) file got from search process used to create the object of ResultFile type.
	 * @param criteria (required)  SearcherCriteria type, used to create the object of ResultFile type.
	 */
	private void setFileValues(String inputLine, SearcherCriteria criteria) {

		if (criteria.getIsDirectory()) {
			fileName = "";
			ext = "";
			path = inputLine;
			setSizeKB(inputLine);
			
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
	}

	/**
	 * Method to update the size of the file found into KB to follow Windows
	 * standard.
	 * @param inputline (required) file got from search process used to get its size value.
	 */
	public void setSizeKB(String inputLine) {
		File tempFile = new File(inputLine);
		long tempFileSize = tempFile.length();
		String s = String.valueOf(tempFileSize / 1024) + " " + "KB";
		this.setSize(s);
	}
  
  /**
	 * Method used by the constructor with parameters required to fill the data of the Result file (check boxes Hidden and Read Only) 
	 * to show in the table later.
	 * @param criteria (required)  SearcherCriteria type, used to create the object of ResultFile type.
	 */
	public void setHiddenAndReadOnly(String inputLine) {
		File tempFile = new File(inputLine);
		super.setIsHidden(tempFile.isHidden());
		super.setIsReadOnly(!tempFile.canWrite());
	}
	
	/**
	 * Method used by the constructor with parameters required to fill the data of the Result file (Last Modified Date, Creation Date, 
	 * and AccessedDate) to show in the table later.
	 * @param inputLine (required) String value with the path to get the dates values.
	 * @throws IOException If some exception is raised during this operation.
	 */
	public void setDates(String inputLine) throws IOException {
		
		File tempFile = new File(inputLine);
		BasicFileAttributes fileAtt = Files.readAttributes(tempFile.toPath(), BasicFileAttributes.class);
		DateFormat dateF = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		
		setLastModifiedDate(dateF.format(new Date(tempFile.lastModified())));
		setCreationDate(dateF.format(new Date(fileAtt.creationTime().toMillis())));
		setAccessedDate(dateF.format(new Date(fileAtt.lastAccessTime().toMillis())));
	}
	
	/**
	 * Method used by the constructor with parameters required to fill the data of the Result file (Owner) 
	 * to show in the table later.
	 * @param inputLine (required) String value with the path to get the owner value.
	 * @throws IOException If some exception is raised during this operation.
	 */
	public void setOwner(String inputLine) {
		File tempFile = new File(inputLine);
		FileOwnerAttributeView view = Files.getFileAttributeView(tempFile.toPath(),FileOwnerAttributeView.class);
		UserPrincipal userPrincipal = null;
		try {
			userPrincipal = view.getOwner();
		} catch (IOException e) {
			new CustomSearchException("Something was owner assignation from ResultFile.setOwner method", e.getCause());
		}
		super.setOwner(userPrincipal.getName());
	}
}
