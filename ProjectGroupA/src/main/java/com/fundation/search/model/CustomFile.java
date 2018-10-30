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
	protected String path;
	protected String fileName;
	protected String ext;
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
	 */	
	protected CustomFile(String path, String fileName, String ext) {
		this.path = path;
		this.fileName = fileName;
		this.ext = ext;
	}
	
	/**
	 * Method setter to path value.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Method setter to File Name value.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Method setter to extension value.
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
		
	/**
	 * Method setter to size value.
	 */
	public void setSize(String size) {
		this.size = size;
	}

	
	/**
	 * Method getter to path value.
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Method getter to File Name value.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Method getter to extension value.
	 */
	public String getExt() {
		return this.ext;
	}
	
	/**
	 * Method getter for size.
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * Method ToString  to print data from CustomerFile object.
	 */
	public String toString() {
		return "You have inserted following values:\nPath: " + path + "\nFile Name: " + fileName + "\nExtension: "
				+ ext + "\nSize: " + size;
	}
}
