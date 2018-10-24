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

/**
 * Class created to manage the search criteria object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherCriteria extends CustomFile {

	private String size, operator, storageUnit;

	/**
	 * constructor for SearcherCriteria class where the values inserted to be find
	 * will be stored as an object.
	 */
	public SearcherCriteria() {
		path = "";
		fileName = "";
		ext = "";
		size = "";
		operator = "";
		storageUnit = "";

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
	 * method setter to operator value.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * method setter to storage unit value.
	 */
	public void setStorageUnit(String storageUnit) {
		this.storageUnit = storageUnit;
	}

	/**
	 * method getter to path value.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * method getter to File Name value.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * method getter to extension value.
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * method getter to size value.
	 */
	public String getSize() {
		return size;
	}

	/**
	 * method getter to operator value.
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * method getter to storage unit value.
	 */
	public String getStorageUnit() {
		return storageUnit;
	}

	/**
	 * method String to String to print values of object.
	 */
	public String toString() {
		return super.toString() + "\nSize: " + size + "\nOperator: " + "\nStorage Unit: " + storageUnit;
	}
}
