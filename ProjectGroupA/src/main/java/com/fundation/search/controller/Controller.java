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

import java.io.IOException;
import java.util.ArrayList;

import com.fundation.search.model.ResultFile;
import com.fundation.search.model.Search;
import com.fundation.search.model.SearcherCriteria;
import com.fundation.search.view.SearchWindow;

/**
 * This class is going to interact with the view and model class.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Controller {
	
	/** searcher is a object of type Searcher class, it will be used to call the searchFile method. */
	private Search searcher;
	
	/** val is a object of type Validator class. */
	private Validator val;
	
	/** file is an object of type SearcherCriteria, it will be used to save the inputs inserted by the user. */
	public SearcherCriteria file;
	
	/** windowUI is an object of type SearchWindow, it will be used to interact with the View package and load the UI. */
	private SearchWindow windowUI;
	
	/** resultList is an ArrayList of type CustomFile, it will be used to save the findings results sent by Search class. */
	private ArrayList<ResultFile> resultList;

	/**
	 * constructor for Controller class where the objects declared above will be
	 * initialized by its respective constructors.
	 */
	public Controller() {
		windowUI = new SearchWindow();
		searcher = new Search();
		val = new Validator(windowUI);
		file = new SearcherCriteria();
		resultList = new ArrayList<ResultFile>();
	}

	/**
	 * method used to call the action listener of button and perform the search
	 * process.
	 */
	public void init() {
		windowUI.setSearchListener(e -> search());
		
	}

	/**
	 * method where all values inserted by the user in the UI like path, filename, extension, size, etc. 
	 * will be assigned to file object and all them will be validated, then will be sent to
	 * search process and then showed under UI.
	 */
	private void search() {
		try {
			file.setPath(windowUI.getPath());
			file.setFileName(windowUI.getFileName());
			file.setExt(windowUI.getExtension());
			file.setSize(windowUI.getFileSize());
			file.setOperator(windowUI.getSizeOperator());
			file.setSizeScale(windowUI.getSizeScale());
			file.setOwner(windowUI.getFileOwner());
			file.setIsDirectory(windowUI.getIsDirectory());
			file.setIsHidden(windowUI.getIsHidden());
			file.setIsReadOnly(windowUI.getIsReadOnly());
			val.validate(file);
			displayResults();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * method used to show the results in the UI table.
	 * @throws IOException if something fails during the process of show results.
	 */
	private void displayResults() throws IOException {
		resultList = searcher.searchFile(file);
		windowUI.clearResults();
		
		if (resultList.isEmpty()) {
			windowUI.setErrorMessage("No items match your search.");
		} else {
			for (ResultFile item : resultList) {
				System.out.println(item.getPath() + " " + item.getFileName() + " " + item.getExt());
				Object[] arrRes = { item.getPath(), item.getFileName(), item.getExt(), item.getSize(), item.getOwner() };
				windowUI.setSearchResults(arrRes);
			}
			resultList.clear();
		}
	}
}
