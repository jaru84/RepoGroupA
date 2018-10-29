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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class will allow to search a file given a criteria. The assumption is
 * that we are working on a Windows platform.
 *
 * @author Martha
 * @version 1.0
 */
public class Search {

	private ArrayList<ResultFile> resultFiles;
	private Process pDOS;	

	/**
	 * Constructor for Search class.
	 */
	public Search() {
		resultFiles = new ArrayList<ResultFile>();
	}

	/**
	 * Method to create the command in DOS. Considering the path
	 * is already in a correct format.
	 */
	private String [] createCommand(SearcherCriteria file) {
		String name = file.getFileName();
		String ext = file.getExt();
		String path = file.getPath();
				
		String res = "dir ";
		
		//If it is a directory, the readOnly parameter is not valid.
		if ( file.getIsDirectory() ){
			if ( !file.getIsHidden() ) 
				res = res +  "\"" + path + "\" /s /b /ad";
			else
				res = res +  "\"" + path + "\" /s /b /adh";
			
		} else {
			String parameter = "/a";
			if (file.getIsHidden())
				parameter = parameter + "h";
			if (file.getIsReadOnly())
				parameter = parameter + "r";
			parameter = parameter + "-d";
			if (!path.endsWith("\\")) {
				res = res + "\"" + path + "\\" + name + "." + ext + "\"" + " /s /b "+ parameter;
			} else {
				res = res +  "\"" + path + name + "." + ext + "\"" + " /s /b " + parameter;
			}
		}
		String[] command= { "cmd.exe", "/c", res};
		System.out.println(res);
		return command;
	}

	/**
	 * Method to run the command in DOS and treat the output with the criteria.
	 */
	public ArrayList<ResultFile> searchFile(SearcherCriteria file) throws IOException {
		
		pDOS = Runtime.getRuntime().exec(createCommand(file));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
		String inputLine = "";
		
		if (!file.getSize().equals("0"))
			file.sizeToBytes();
		
		while ((inputLine = in.readLine()) != null) {
			if (filterResults(inputLine, file)) {
				if (!file.getSize().equals("0")) {
					if (matchSizeCriteria(inputLine, file)) {
						resultFiles.add(new ResultFile(inputLine, file));
					}
				} else {
					resultFiles.add(new ResultFile(inputLine, file));
				}
			}
		}
		in.close();
		return resultFiles;
	}
	
	private boolean filterResults(String inputline, SearcherCriteria file) {
		if (file.getIsDirectory()) {
			return true;
		} else if ((file.getFileName() == "*") || (file.getExt() == "*")) {
			return true;
		} else if (inputline.contains(file.getFileName()) || inputline.contains(file.getExt())) {
			return true;
		} else return false;
	}
	
	/**
	 * Method which compares the size specified in the criteria and the files found.
	 */
	private boolean matchSizeCriteria (String inputline, SearcherCriteria criteria) {
		boolean res = false;
		if (! criteria.getSize().equals("0")) {
			long cSize = Long.parseLong(criteria.getSize());
			
			File tFile = new File (inputline);
			long tFileSize = tFile.length();
			
			switch (criteria.getOperator()) {
				case "=="	: 	if (tFileSize == cSize)
									res = true;
								break;
				case ">" 	: 	if (tFileSize > cSize)
									res = true;
								break;
				case ">=" 	: 	if (tFileSize >= cSize)
									res = true;
								break;
				case "<" 	: 	if (tFileSize < cSize)
									res = true;
								break;
				case "<="	: 	if (tFileSize <= cSize)
									res = true;
								break;
			}			
		}
		return res;
	}
		
}
