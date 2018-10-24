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

	private ArrayList<String> resultFiles;

	/**
	 * constructor for Search class.
	 */
	public Search() {
		resultFiles = new ArrayList<String>();
	}

	/**
	 * Method to run the command in DOS and treat the outputs. Considering the path
	 * is already in a correct format.
	 */
	private String createCommand(String name, String ext, String path) {

		String res = "dir ";

		if (!path.endsWith("\\")) {
			res = res + path + "\\" + name + "." + ext + " /s /b /a-d";
		} else {
			res = res + path + name + "." + ext + " /s /b /a-d";
		}
		return res;
	}

	/**
	 * Method to run the command in DOS which will receive for this time 3
	 * parameters path, name and extension It will also treat the output.
	 */
	public ArrayList<String> searchFile(SearcherCriteria file) throws IOException {

		String cmd = createCommand(file.getFileName(), file.getExt(), file.getPath());
		String[] command = { "cmd.exe", "/c", cmd };

		Process pDOS;
		pDOS = Runtime.getRuntime().exec(command);

		BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
		String inputLine = "";

		/* Filling the list with a single string, need to user ResultFile */
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.contains(file.getFileName())) {
				inputLine = file.path + "\\" + inputLine;
			}
			this.resultFiles.add(inputLine);
			// System.out.println(inputLine);
		}
		in.close();

		return resultFiles;
	}

}
