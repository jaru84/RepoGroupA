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
 * Class created to manage the file object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherFile {
	public String path;
	public String fileName;
	public String ext;
	
	public SearcherFile() {
		path= "";
		fileName= "";
		ext="";
		
	}
}
