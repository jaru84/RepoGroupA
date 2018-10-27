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
	 * constructor for Search class.
	 */
	public Search() {
		resultFiles = new ArrayList<ResultFile>();
	}

	/**
	 * Method to create the command in DOS. Considering the path
	 * is already in a correct format.
	 */
	private String [] createCommand(String name, String ext, String path) {

		String res = "dir ";

		if (!path.endsWith("\\")) {
			res = res + "\"" + path + "\\" + name + "." + ext + "\"" + " /s /b /a-d";
		} else {
			res = res +  "\"" + path + name + "." + ext + "\"" + " /s /b /a-d";
		}
		String[] command= { "cmd.exe", "/c", res};
		//System.out.println(res);
		return command;
	}

	/**
	 * Method to run the command in DOS which will receive for this time 3
	 * parameters path, name and extension It will also treat the output.
	 */
	public ArrayList<ResultFile> searchFile(SearcherCriteria file) throws IOException {
		
		pDOS = Runtime.getRuntime().exec(createCommand(file.getFileName(), file.getExt(), file.getPath()));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
		String inputLine = "";
		
		while ((inputLine = in.readLine()) != null) {
			if (filterResults(inputLine, file)) {
				resultFiles.add(new ResultFile(inputLine));
			}
		}
		in.close();
		return resultFiles;
	}
	
	private boolean filterResults(String inputline, SearcherCriteria file) {
		if ((file.getFileName() == "*") || (file.getExt() == "*")) {
			return true;
		} else if (inputline.contains(file.getFileName()) || inputline.contains(file.getExt())) {
			return true;
		} else return false;
	}
}
