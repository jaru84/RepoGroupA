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

import com.fundation.search.model.CustomFile;
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
	public SearcherCriteria criteria;
	/** windowUI is an object of type SearchWindow, it will be used to interact with the View package and load the UI. */
	private SearchWindow windowUI;
	/** resultList is an ArrayList of type CustomFile, it will be used to save the findings results sent by Search class. */
	private ArrayList<CustomFile> resultList;
	
	/**
	 * constructor for Controller class where the objects declared above will be
	 * initialized by its respective constructors.
	 */
	public Controller() {
		windowUI = new SearchWindow();
		searcher = new Search();
		val = new Validator(windowUI);
		criteria = new SearcherCriteria();
		resultList = new ArrayList<CustomFile>();
	}

	/**
	 * Method used to call the action listener of button and perform the search
	 * process.
	 */
	public void init() {
		LogUtil.setDEBUG_MODE(false);  /** Only to debug propose set to TRUE, to production leave in FALSE */
		windowUI.setSearchListener(e ->	search());
	}

	/**
	 * method where all values inserted by the user in the UI like path, filename, extension, size, etc. 
	 * will be assigned to file object and all them will be validated, then will be sent to
	 * search process and then showed under UI.
	 * @throws CustomSearchException It will raise an error message on UI if something fails during search method.
	 * @throws Exception It will print an error message by console.
	 */
	private void search() {
		try {
			criteria.setPath(windowUI.getPath());
			criteria.setFileName(windowUI.getFileName());
			criteria.setExt(windowUI.getExtension());
			criteria.setSize(windowUI.getFileSize());
			criteria.setOperator(windowUI.getSizeOperator());
			criteria.setSizeScale(windowUI.getSizeScale());
			criteria.setOwner(windowUI.getFileOwner());
			criteria.setIsDirectory(windowUI.getIsDirectory());
			criteria.setIsHidden(windowUI.getIsHidden());
			criteria.setIsReadOnly(windowUI.getIsReadOnly());
			criteria.setDateType(windowUI.getDateType());
			criteria.setStartDate(windowUI.getStartDate());
			criteria.setEndDate(windowUI.getEndDate());
			
			val.validate(criteria);
			displayResults();
		}
		catch (CustomSearchException ex) {
			windowUI.setErrorMessage(ex.getMessage());
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage() + ex.getStackTrace());
		}
	}

	/**
	 * Method used to show the results in the UI table.
	 * @throws IOException if something fails during the process of show results.
	 * @throws CustomSearchException It will print an error message if something fails during displayResults method.
	 */
	private void displayResults() throws CustomSearchException, IOException  {
		resultList = searcher.searchFile(criteria);
		windowUI.clearResults();
		
		if (resultList.isEmpty()) {
			windowUI.setErrorMessage("No items match your search.");
		} else {
			for (CustomFile item : resultList) {
				LogUtil.print(item.getPath() + " " + item.getFileName() + " " + item.getExt() + " " + item.getSize() + " " + item.getOwner());  /** Only will be printed when the flag DEBUG_MODE is in true.*/
				Object[] arrRes = { item.getPath(), item.getFileName(), item.getExt(), item.getSize(), item.getOwner(), ((ResultFile) item).getCreationDate(), 
						((ResultFile) item).getLastModifiedDate(), ((ResultFile) item).getAccessedDate(), item.getIsHidden(), item.getIsReadOnly() };
				windowUI.setSearchResults(arrRes);
			}
			resultList.clear();
		}
	}
}
