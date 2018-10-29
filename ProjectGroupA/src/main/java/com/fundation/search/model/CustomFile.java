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
	protected String sizeScale;
	
	protected CustomFile() {
		path = "";
		fileName = "";
		ext = "";
		size = "";
		sizeScale = "";
	}
	
	protected CustomFile(String path, String fileName, String ext) {
		this.path = path;
		this.fileName = fileName;
		this.ext = ext;
	}
	
	/**
	 * method setter to path value.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * method setter to File Name value.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * method setter to extension value.
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
		
	/**
	 * method setter to size value.
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	/**
	 * method setter to size scale value.
	 */
	public void setSizeScale(String sizeScale) {
		this.sizeScale = sizeScale;
	}
	
	/**
	 * method getter to path value.
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * method getter to File Name value.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * method getter to extension value.
	 */
	public String getExt() {
		return this.ext;
	}
	
	/**
	 * method getter to size value.
	 */
	public String getSize() {
		return this.size;
	}
	
	/**
	 * method getter to size scale value.
	 */
	public String getSizeScale() {
		return this.sizeScale;
	}
	
	/**
	 * method ToString  to print data from CustomerFile object.
	 */
	public String toString() {
		return "You have inserted following values:\nPath: " + path + "\nFile Name: " + fileName + "\nExtension: "
				+ ext + "\nSize: " + size + "\nSize Scale: " + sizeScale;
	}
}
