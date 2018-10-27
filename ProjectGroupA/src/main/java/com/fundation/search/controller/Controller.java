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
	/**
	 * searcher is a object of type Searcher class, Searcher class must be
	 * implemented in model package. val is a object of type Validator class. file
	 * is object of type SearcherFile class where the data related to one file will
	 * be stored and managed as object by model, controller and view packages.
	 * windowUI is object of type SearchWindow class where the UI is implemented.
	 * searcher is object of type Search class where the process of searching is
	 * performed.
	 */
	private Search searcher;
	private Validator val;
	public SearcherCriteria file;
	private SearchWindow windowUI;
	private ArrayList <ResultFile> resultList;

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
	 * method used to call the action listener of button and perform the search process.
	 */
	public void init() {
		windowUI.setSearchListener(e -> search());
	}

	/**
	 * method where the values to path, filename,extension,size and operator will be
	 * assigned to file object and all them will be validated, then will be sent to
	 * model to start with search process and then showed under UI.
	 */
	private void search() {
		try {
			file.setPath(windowUI.getPath());
			file.setFileName(windowUI.getFileName());
			file.setExt(windowUI.getExtension());
			file.setSize(windowUI.getFileSize());
			file.setOperator(windowUI.getSizeOperator());
			file.setSizeScale(windowUI.getSizeScale());
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
	 * method used to show the results on the table UI
	 */
	private void displayResults() throws IOException {
		resultList = searcher.searchFile(file);
		windowUI.clearResults();
		if (resultList.isEmpty()) {
			windowUI.setErrorMessage("No items match your search.");
		} else {
			for (ResultFile item : resultList) {
				//System.out.println(item.getPath()+" "+item.getFileName()+ " "+item.getExt());
				Object[] arrRes = { item.getPath(), item.getFileName(), item.getExt(), item.getSize() };
				windowUI.setSearchResults(arrRes);
			}
			resultList.clear();
		}
	}
}
