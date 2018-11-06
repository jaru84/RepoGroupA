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
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import com.fundation.search.controller.LogUtil;

/**
 * This class will allow to search a file given a criteria. The assumption is
 * that we are working on a Windows platform.
 *
 * @author Martha
 * @version 1.0
 */
public class Search implements ISearch {

    /**
     * resultList is an ArrayList of type ResultFile, it will be used to save the findings results sent by Search class.
     */
    private ArrayList<CustomFile> resultFiles;

    /**
     * pDos is an variable of type Process used to run the command created.
     */
    private Process pDOS;

    /**
     * resultFileFactory is an variable of type ResultFileFactory used to create ResultFile objects.
     */
    private ResultFileFactory resultFileFactory;

    /**
     * Constructor for Search class.
     *
     * @param window (required) to show during the error messages captured in validation.
     */
    public Search() {
        resultFiles = new ArrayList<CustomFile>();
        resultFileFactory = new ResultFileFactory();
    }

    /**
     * Method to create the command in DOS. Considering the path is already in a correct format.
     *
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
		LogUtil.print(res);  /** Only will be printed when the flag DEBUG_MODE is in true.*/
		return command;
	}

    /**
     * Method to run the command in DOS and treat the output with the criteria.
     *
     * @param criteria (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
     * @return resultFiles it will return a ArrayList <CustomFile> with the results for the search process.
     * @throws IOException if something fails during BufferedReader process will be printed by console.
     */
    public ArrayList<CustomFile> searchFile(SearcherCriteria criteria) throws IOException {
    	pDOS = Runtime.getRuntime().exec(createCommand(criteria));
		BufferedReader in = new BufferedReader(new InputStreamReader(pDOS.getInputStream()));
		String inputLine = "";
		
		if (!criteria.getSize().equals("0")) {
			criteria.sizeToBytes();
		}

		int matchSize = -1;    	/** This variables will help to know if the another option for the search criteria are being used */
		int matchOwner = -1;	/** This variables will help to know if the another option for the search criteria are being used */
		int matchDate = -1;		/** This variables will help to know if the another option for the search criteria are being used */
		
        while ((inputLine = in.readLine()) != null) {
            if (filterResults(inputLine, criteria)) {

                if (!criteria.getSize().equals("0")) {
                    matchSize = matchSizeCriteria(inputLine, criteria);
                }

                if (!criteria.getOwner().equals("")) {
                    matchOwner = matchOwnerCriteria(inputLine, criteria);
                }

                if ((criteria.getStartDate() != null) && (criteria.getEndDate() != null)) {
                    matchDate = matchDateCriteria(inputLine, criteria);
                }

                boolean flag = false;
                if ((matchSize == -1) && (matchOwner == -1) && (matchDate == -1)) {
                    resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                } else {
                    if (matchSize == 1) {
                        if (matchOwner == 1) {
                            if (matchDate == 1) {
                                resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                                flag = true;
                            }
                            if (!flag && matchDate == -1) {
                                resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                                flag = true;
                            }
                        } else {
                            if (matchDate == 1 && matchOwner == -1) {
                                resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                                flag = true;
                            }
                        }
                        if (!flag && matchOwner == -1 && matchDate == -1) {
                            resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                            flag = true;
                        }
                    } else {
                        if (matchSize == -1) {
                            if (matchOwner == 1) {
                                if (matchDate == 1) {
                                    resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                                    flag = true;
                                }
                                if (!flag && matchDate == -1) {
                                    resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
                                    flag = true;
                                }
                            } else {
                                if (matchDate == 1 && matchOwner == -1) {
                                    resultFiles.add(resultFileFactory.createResultFile(inputLine, criteria));
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
     *
     * @param file      (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
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
     *
     * @param inputline (required) String type, it has one line of value got by search process.
     * @param criteria  (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
     * @return an int value (1) if the size criteria inserted match with the size in the file found.
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
     *
     * @param inputline (required) String type, it has one line of value got by search process.
     * @param criteria  (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
     * @return a int (1) if the owner criteria matches with the owner in the file found.
     * @throws IOException in case the file cannot be opened or review the attributes
     */
    private int matchOwnerCriteria(String inputline, SearcherCriteria criteria) throws IOException {
    	int res = 0;
		if (!criteria.getOwner().equals("")) {
			File tFile = new File(inputline);
			FileOwnerAttributeView atrib = Files.getFileAttributeView(tFile.toPath(), FileOwnerAttributeView.class);
	        UserPrincipal owner= null;
				owner = atrib.getOwner();
	        if (criteria.getOwner().toLowerCase().trim().equals(owner.getName().toLowerCase().trim()))
	        	res = 1;
		}
		return res;
    }

    /**
     * Method which look for the content.
     *
     * @param inputline (required) String type, it has one line of value got by search process.
     * @param criteria  (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
     * @return an int value (1) if the content is found.
     */
    public int matchContentCriteria(String inputline, SearcherCriteria criteria) {
        int res = 0;

        //String word = criteria.getDate startDate = setTimeCustom(criteria.getStartDate(),0,0,0);
        /*Do something

         */
        return res;
    }

    /**
     * Method which compares dates specified in the criteria and the files found.
     * Using a minimum unit for time : milliseconds.
     *
     * @param inputline (required) String type, it has one line of value got by search process.
     * @param criteria  (required) SearcherCriteria type, must have content, it has all values inserted by the user to the match process.
     * @return an int value (1) if the file belongs to the range of dates specified.
     * @throws IOException if something fails during the matchDateCriteria an error message will be printed by console. 
     */
    public int matchDateCriteria(String inputline, SearcherCriteria criteria) throws IOException {
    	int res=0;
		File tFile = new File(inputline);
		BasicFileAttributes dateFile;
		dateFile = Files.readAttributes(tFile.toPath(), BasicFileAttributes.class);
		String option = criteria.getDateType().toLowerCase().trim();
		switch (option) {
		case "creation date":
			FileTime dateC = dateFile.creationTime();
			if ((dateC.toMillis() >= criteria.getStartDate().getTime()) && (dateC.toMillis() <= criteria.getEndDate().getTime())) {
				res = 1;
			}
			break;
		case "modified date":
			FileTime dateM = dateFile.lastModifiedTime();
			if ((dateM.toMillis() >= criteria.getStartDate().getTime()) && (dateM.toMillis() <= criteria.getEndDate().getTime())) {
				res = 1;
			}
			break;
		case "accessed date":
			FileTime dateA = dateFile.lastAccessTime();
			if ((dateA.toMillis() >= criteria.getStartDate().getTime()) && (dateA.toMillis() <= criteria.getEndDate().getTime())) {
				res = 1;
			}
			break;
		}
		return res;
    }
}
