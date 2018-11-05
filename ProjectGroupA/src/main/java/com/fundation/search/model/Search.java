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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class will allow to search a file given a criteria. The assumption is
 * that we are working on a Windows platform.
 *
 * @author Martha
 * @version 1.0
 */
public class Search implements ISearch{
	
	/** resultList is an ArrayList of type ResultFile, it will be used to save the findings results sent by Search class. */
	private ArrayList<CustomFile> resultFiles;
	
	/** pDos is an variable of type Process used to run the command created. */
	private Process pDOS;

	/**
	 * Constructor for Search class.
	 */
	public Search() {
		resultFiles = new ArrayList<CustomFile>();
	}

	/**
	 * Method to create the command in DOS. Considering the path is already in a correct format.
	 * @param file (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
	 * @return command it will return a String [] with the command created.
	 */
	private String[] createCommand(SearcherCriteria file) {
		String name = file.getFileName();
		String ext = file.getExt();
		String path = file.getPath();

		String res = "dir ";

		/** If it is a directory, the readOnly parameter is not valid. */
		if (file.getIsDirectory()) {
			if (!file.getIsHidden()) {
				res = res + "\"" + path + "\" /s /b /ad";
			} else {
				res = res + "\"" + path + "\" /s /b /adh";
			}

		} else {
			String parameter = "/a";
			if (file.getIsHidden()) {
				parameter = parameter + "h";
			}
			if (file.getIsReadOnly()) {
				parameter = parameter + "r";
			}
			parameter = parameter + "-d";
			if (!path.endsWith("\\")) {
				res = res + "\"" + path + "\\" + name + "." + ext + "\"" + " /s /b " + parameter;
			} else {
				res = res + "\"" + path + name + "." + ext + "\"" + " /s /b " + parameter;
			}
		}
		String[] command = { "cmd.exe", "/c", res };
		System.out.println(res);
		return command;
	}

	/**
	 * Method to run the command in DOS and treat the output with the criteria.
	 * @param criteria (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
	 * @throws IOException if something fails during BufferedReader process.
	 * @return resultFiles it will return a ArrayList <CustomFile> with the results for the search process.
	 */
	public ArrayList<CustomFile> searchFile(SearcherCriteria criteria) throws IOException {

		pDOS = Runtime.getRuntime().exec(createCommand(criteria));

		BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
		String inputLine = "";

		if (!criteria.getSize().equals("0")) {
			criteria.sizeToBytes();
		}
		
		//These variables will help to know if the another option for the search criteria are being used
		int matchSize = -1;
		int matchOwner = -1;
		int matchDate = -1;
		
		while ((inputLine = in.readLine()) != null) {
			if (filterResults(inputLine, criteria)) {
							
				if (!criteria.getSize().equals("0")) {
					matchSize = matchSizeCriteria(inputLine, criteria);
				}
				
				if (!criteria.getOwner().equals("")) {
					matchOwner = matchOwnerCriteria(inputLine, criteria);
				}
			
				if ( (criteria.getStartDate()!=null) && (criteria.getEndDate()!=null) ) {
					matchDate = matchDateCriteria(inputLine, criteria);
				}
				
				//The Size, owner or date criteria has been selected.
				boolean flag = false;
				if ((matchSize==-1) && (matchOwner==-1) && (matchDate==-1)) {
					resultFiles.add(new ResultFile(inputLine, criteria));
				} else {
					if (matchSize==1) {
						if (matchOwner==1) {
							if (matchDate==1) {
								resultFiles.add(new ResultFile(inputLine, criteria));
								flag = true;
							}
							if (!flag && matchDate==-1) {
								resultFiles.add(new ResultFile(inputLine, criteria));
								flag = true;
							}
						} else {
							if (matchDate==1 && matchOwner==-1) {
								resultFiles.add(new ResultFile(inputLine, criteria));
								flag = true;
							}
						}
						if (!flag && matchOwner==-1 && matchDate==-1) {
							resultFiles.add(new ResultFile(inputLine, criteria));
							flag = true;
						}
					} else {
						if (matchSize==-1) {
							if (matchOwner==1) {
								if (matchDate==1) {
									resultFiles.add(new ResultFile(inputLine, criteria));
									flag = true;
								}
								if (!flag && matchDate==-1) {
									resultFiles.add(new ResultFile(inputLine, criteria));
									flag = true;
								}
							} else {
								if (matchDate==1 && matchOwner==-1) {
									resultFiles.add(new ResultFile(inputLine, criteria));
									flag = true;
								}							
							}		
						}						
					}
				}
			}					
		}
		in.close();
		return resultFiles;
	}

	/**
	 * Method to filter results gets by searchFile process and filter by file name, extension and directory.
	 * @param file (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
	 * @param inputline (required). String type, it has one line of value got by search process.
	 * @return a boolean. true if search will be by directory or we want leave empty the fields file name and extension. 
	 */
	private boolean filterResults(String inputline, SearcherCriteria criteria) {
		if (criteria.getIsDirectory()) {
			return true;
		} else if ((criteria.getFileName() == "*") || (criteria.getExt() == "*")) {
			return true;
		} else if (inputline.contains(criteria.getFileName()) || inputline.contains(criteria.getExt())) {
			return true;
		} else
			return false;
	}

	/**
	 * Method which compares the size by converting everything to bytes.
	 * @param inputline (required) String type, it has one line of value got by search process.
	 * @param criteria (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
	 * @return a int value (1) if the size criteria inserted match with the size in the file found.  
	 */
	private int matchSizeCriteria(String inputline, SearcherCriteria criteria) {
		int res = 0;
		if (!criteria.getSize().equals("0")) {
			long cSize = Long.parseLong(criteria.getSize());

			File tFile = new File(inputline);
			long tFileSize = tFile.length();

			switch (criteria.getOperator()) {
			case "==":
				if (tFileSize == cSize) {
					res = 1;
				}
				break;
			case ">":
				if (tFileSize > cSize) {
					res = 1;
				}
				break;
			case ">=":
				if (tFileSize >= cSize) {
					res = 1;
				}
				break;
			case "<":
				if (tFileSize < cSize) {
					res = 1;
				}
				break;
			case "<=":
				if (tFileSize <= cSize) {
					res = 1;
				}
				break;
			}
		}
		return res;
	}

	/**
	 * Method which compares the onwer in the criteria and the files found.
	 * @param inputline (required) String type, it has one line of value got by search process.
	 * @param criteria (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
	 * @return a int (1) if the owner criteria matches with the owner in the file found.  
	 * @throws IOException in case the file cannot be opened or review the attributes
	 */
	private int matchOwnerCriteria(String inputline, SearcherCriteria criteria) throws IOException {
		int res = 0;
		if (!criteria.getOwner().equals("")) {
			
			File tFile = new File(inputline);
			Path pathFile = tFile.toPath();
			
			FileOwnerAttributeView atrib = Files.getFileAttributeView(pathFile, FileOwnerAttributeView.class);
	        UserPrincipal owner = atrib.getOwner();
			//System.out.println(x);
	        if (criteria.getOwner().toLowerCase().trim().equals(owner.getName().toLowerCase().trim()))
	        	res = 1;
		}
		return res;
	}

	/**
	 * Method which look for the content.
	 * @param inputline (required) String type, it has one line of value got by search process.
	 * @param criteria (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
	 * @return an int value (1) if the content is found.  
	 */
	public int matchContentCriteria (String inputline, SearcherCriteria criteria) {
		int res=0;

		//String word = criteria.getDate startDate = setTimeCustom(criteria.getStartDate(),0,0,0);
		/*Do something
		
		*/
		return res;
	}
	
	/**
	 * Method which set a date with a given hour, so later we can covert the dates to milliseconds.
	 * using the calendar since the another methods from Date were deprecated.
	 * @param d (required) Date type, which will be modified.
	 * @param h (required) int type, hour(s) to be specified.
	 * @param m (required) int type, minute(s) to be specified.
	 * @param s (required) int type, second(s) to be specified.
	 * @return a Date value with everything customized.  
	 */
	private Date setTimeCustom (Date d, int h, int m, int s) {
		Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, h);
        c.set(Calendar.MINUTE, m);
        c.set(Calendar.SECOND, s);
        c.set(Calendar.MILLISECOND, 0);   
		return c.getTime();
		
	}
	
	/**
	 * Method which compares dates specified in the criteria and the files found.
	 * Using a minimum unit for time : milliseconds.
	 * @param inputline (required) String type, it has one line of value got by search process.
	 * @param criteria (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
	 * @return a int value (1) if the file belongs to the range of dates specified.  
	 */
	public int matchDateCriteria (String inputline, SearcherCriteria criteria) {
		int res=0;

		Date startDate = setTimeCustom(criteria.getStartDate(),0,0,0);
		Date endDate = setTimeCustom(criteria.getEndDate(),23,59,59);
		
		File tFile = new File(inputline);
		Path pFile = tFile.toPath();
		BasicFileAttributes dateFile;
		
		try {
			dateFile = Files.readAttributes(pFile, BasicFileAttributes.class);
			
			String option = criteria.getDateSearh().toLowerCase().trim();
			switch (option) {
			case "creation date":
				FileTime dateC = dateFile.creationTime();
				if ((dateC.toMillis() >= startDate.getTime()) && (dateC.toMillis() <= endDate.getTime())) {
					res = 1;
				}
				break;
			case "modified date":
				FileTime dateM = dateFile.lastModifiedTime();
				if ((dateM.toMillis() >= startDate.getTime()) && (dateM.toMillis() <= endDate.getTime())) {
					res = 1;
				}
				break;
			case "accessed date":
				FileTime dateA = dateFile.lastModifiedTime();
				if ((dateA.toMillis() >= startDate.getTime()) && (dateA.toMillis() <= endDate.getTime())) {
					res = 1;
				}
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
