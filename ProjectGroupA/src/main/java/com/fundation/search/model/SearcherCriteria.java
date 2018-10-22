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
	
	public String size, operator, storageUnit;
		
	public SearcherCriteria() {
		path= "";
		fileName= "";
		ext="";	
		size="";
		operator="";
		storageUnit="";
		
	}
	
	public String toString() {
		return super.toString()+"\nSize: "+size+"\nOperator: ";
	}
}
