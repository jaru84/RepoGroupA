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
      *	searcher is a object of type Searcher class, Searcher class must be implemented in model package.
      *	val is a object of type Validator class.
      *	file is object of type SearcherFile class where the data related to one file will be stored and managed as object by model, controller and view packages.
      *	windowUI is object of type SearchWindow class where the UI is implemented.
      * searcher is object of type Search class where the process of searching is performed.*/
	private Search searcher;
	private Validator val;
	public SearcherCriteria file;
	private SearchWindow windowUI;
	private ArrayList<String> resultList;
	
	/** 
     *  constructor for Controller class where the objects declared above will be  initialized by its respective constructors.*/
	public Controller(){
		windowUI= new SearchWindow();
		searcher= new Search();
		val= new Validator();
		file = new SearcherCriteria();
		resultList = new ArrayList<String>();
	}
	
	/**
     *  method to call the search method.*/
	public void init() {
		windowUI.setSearchListener(e ->search());
	}
	/**
     *  method where the values to path, filename,extension,size and operator will be assigned to file object and all them will be validated,
     *  then will be sent to model to start with search process and then showed under UI. */
	private void search() {
		try {
			file.path= windowUI.getPath();  
			file.fileName= windowUI.getFileName(); 
			file.ext= windowUI.getExtension(); 
			file.size=windowUI.getFileSize(); 
			file.operator=windowUI.getSizeOperator(); 
			val.validate(file);			
			displayResults();
		} catch (Exception ex) {	
			System.out.println(ex.getMessage()); 
		}
	}

	/**
     *  method used to call to the method in charge to get results for the search process, the method should be in model package.
	 * @throws IOException */
	private void displayResults() throws IOException {
		//here will be a for statement calling to model method in charge to save results.
		resultList=searcher.searchFile(file);
		System.out.println(file.toString());
		for (String items: resultList)
		{
			System.out.println(items);
		}
		
		
	}
}
