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
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class will allow to search a file given a criteria.
 * The assumption is that we are working on a Windows platform.
 *
 * @author Martha
 * @version 1.0
 */

public class Search {
	private ArrayList<String> resultFiles;

	/*
	 * Constructor*/
	public Search ()  {
		resultFiles = new ArrayList<String>();
	}
	
	/*
	 * Method to run the command in DOS and treat the outputs. 
	 * Considering the path is already in a correct format*/
	private String createCommand (String name, String ext, String path)  {
		String res = "dir ";
		
		if ( ! path.isEmpty() || path.equals("*") ) {
			res = res + path + " " + name + "." + ext + " /b /a-d";
		} else {
			res = res + name + "." + ext + " /s /b /a-d";
		}
		return res;
	}
		

	/*
	 * Method to run the command in DOS which will receive for this time 3 parameters path, name and extension
	 * It will also treat the output. */
	//public static void runDOS (String name, String est, String path) throws IOException {
	public ArrayList<String> searchFile(SearcherCriteria file) throws IOException{
		
		String cmd = createCommand (file.fileName, file.ext, file.path);
		//System.out.println(cmd);
		
		String[] command = {"cmd.exe","/c",cmd};
		Process pDOS;
		try {
			pDOS = Runtime.getRuntime().exec(command);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
			String inputLine="" ;
							
			//Filling the list with a single string, need to user ResultFile
			while ( (inputLine = in.readLine() ) != null)  {
				if (inputLine.contains(file.fileName)) {  //(! file.path.isEmpty()) {
					inputLine = file.path + "\\" + inputLine;
				}
				this.resultFiles.add(inputLine);
				//System.out.println(inputLine);
			}
			in.close();
								
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return resultFiles;
	}

}
