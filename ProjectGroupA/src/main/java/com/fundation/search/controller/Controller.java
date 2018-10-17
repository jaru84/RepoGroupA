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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.fundation.search.model.SearcherCriteria;

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
      *	interface is object of type SearcherGUI class where UI should be implemented.*/
	//private Searcher searcher;
	private Validator val;
	private SearcherCriteria file;
	//private SearcherGUI interface;
	
	/** 
     *  constructor for Controller class where the objects declared above will be  initialized by its respective constructors.*/
	public Controller(){
		//interface= new SearcherGUI();
		//searcher= new Searcher();
		val= new Validator();
		file = new SearcherCriteria();
	}
	
	/**
     *  method to call the search method.*/
	public void init() {
		search();
	}
	/**
     *  method where the values to path, filename,extension,size and operator will be assigned to file object and all them will be validated,
     *  then will be sent to model to start with search process and then showed under UI. */
	private void search() {
		try {
			file.path= getPath();  
			file.fileName= getFileName();
			file.ext= getExtension();
			file.size=getSize();
			file.operator=getOperator();
			val.validate(file);			
			//searcher.search(file);
			displayResults();
			}catch (Exception ex) {	System.out.println(ex.getMessage()); }
	}
	/**
     *  method that currently is simulating the process to get value for path. It will change to one call to File object where 
     *  SearcherGUI class should set the value.*/
	private String getPath() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      	System.out.println("Insert a valid path:");
      	return reader.readLine(); 		
	}
	/**
     *  method that currently is simulating the process to get value for file name. It will change to one call to File object where 
     *  SearcherGUI class should set the value. */
	private String getFileName() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      	System.out.println("Insert a valid file name:");
		return reader.readLine(); 		
	}
	/**
     *  method that currently is simulating the process to get value for extension. It will change to one call to File object where 
     *  SearcherGUI class should set the value. */
	private String getExtension() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      	System.out.println("Insert a valid extension:");
		return reader.readLine(); 	
	}
	/**
     *  method that currently is simulating the process to get value for size. It will change to one call to File object where 
     *  SearcherGUI class should set the value. */
	private String getSize() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      	System.out.println("Insert a size value:");
      	return reader.readLine(); 	
	}
	/**
    *  method that currently is simulating the process to get value for operator. It will change to one call to File object where 
    *  SearcherGUI class should set the value. */
	private String getOperator() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      	System.out.println("Insert an operator:");
      	return reader.readLine(); 	
	}
	/**
     *  method used to call to the method in charge to get results for the search process, the method should be in model package.*/
	private void displayResults() {
		System.out.println(file.toString());
		//searcher.resultList;
	}
}
