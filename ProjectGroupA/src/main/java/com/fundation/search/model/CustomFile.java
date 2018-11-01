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

/**
 * Class created to manage the Custom Files object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public abstract class CustomFile {
	
	/** path variable of String type used to save the value set by the user. */
	protected String path;
	
	/** fileName variable of String type used to save the value set by the user. */
	protected String fileName;
	
	/** ext variable of String type used to save the value set by the user. */
	protected String ext;
	
	/** size variable of String type used to save the value set by the user. */
	protected String size;

	/**
	 * Default constructor for CustomFile.
	 */
	protected CustomFile() {
		path = "";
		fileName = "";
		ext = "";
		size = "";
	}

	/**
	 * Constructor with parameters for CustomFile.
	 * @param path (required) String value necessary to create the object with an path value assigned
	 * @param fileName (required) String value necessary to create the object with an fileName value assigned.
	 * @ext fileName (required) String value necessary to create the object with an ext value assigned.
	 */
	protected CustomFile(String path, String fileName, String ext) {
		this.path = path;
		this.fileName = fileName;
		this.ext = ext;
	}

	/**
	 * Method setter to path value.
	 * @param path It is used to set a String value to path attribute.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Method setter to File Name value.
	 * @param fileName It is used to set a String value to fileName attribute.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Method setter to extension value.
	 * @param ext It is used to set a String value to ext attribute.
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * Method setter to size value.
	 * @param size It is used to set a String value to size attribute.
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Method getter to path value.
	 * @return the value of path as String.
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Method getter to File Name value.
	 * @return the value of fileName as String.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Method getter to extension value.
	 * @return the value of ext as String.
	 */
	public String getExt() {
		return this.ext;
	}

	/**
	 * Method getter for size.
	 * @return the value of size as String.
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * Method ToString to print data from CustomerFile object.
	 * @return All attributes that have the class SearcherCriteria as String. 
	 */
	public String toString() {
		return "You have inserted following values:\nPath: " + path + "\nFile Name: " + fileName + "\nExtension: " + ext
				+ "\nSize: " + size;
	}
}
