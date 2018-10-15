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
import java.io.File;
import java.util.HashMap;
import java.util.Map;
/**
 * This class is in charge to make validations to the values for path, file name and extension.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Validator {
	private Map<Integer, String> extMap;
	/**
     *  method in charge to send the file object to be validated.*/
	public void validate(SearcherFile file) throws CustomSearchException {
		validatePath(file);
		validateFileName(file);
		validateExtension(file);
	} 
	/**
     *  method in charge to validate the path value inserted.*/
	private void validatePath(SearcherFile file) throws CustomSearchException {
		if(file.path.isEmpty())
			file.path="*";
		else
		{
			File dir = new File(file.path);
			if (!dir.exists()) { 
				throw new CustomSearchException("The path inserted does not exist.");
				}
				else if (!dir.isDirectory()){
					throw new CustomSearchException("The path inserted is not valid directory.");	
				}
		}
		
	}
	/**
     *  method in charge to validate the file name value inserted.*/
	private void validateFileName(SearcherFile file) throws CustomSearchException {
		if (file.fileName.isEmpty())
			file.fileName="*";
		else{
			if (file.fileName.length() >50) {
				throw new CustomSearchException("Your file name inserted exceeds the limit of letters allowed 50");
				}
				else if (checkSymbols(file.fileName)) {
					throw new CustomSearchException("Your file name can't contain any of following characters: \\/:*?\"<>");
				}
		}
	}
	/**
     *  method in charge to validate the extension value inserted.*/
	private void validateExtension(SearcherFile file) throws CustomSearchException {
		if (file.ext.isEmpty())
			file.ext="*";
		else {
			extensionsAllowed();
			if (!extMap.containsValue(file.ext))
				throw new CustomSearchException("The extension inserted does not exist.");
		}
	}
	
	private void extensionsAllowed() {
		Map<Integer, String> extMap = new HashMap<Integer, String>();
		extMap.put(1, ".txt");		
		extMap.put(2, ".pdf");		
		extMap.put(3, ".jpg");	
		extMap.put(4, ".java");	
		extMap.put(5, ".docx");		
		extMap.put(6, ".gif");
	}
	private boolean checkSymbols(String fiName) {
		boolean flag = false;
		String[] chars = new String[fiName.length()];
		for (int i = 0; i < fiName.length(); i++) {
		    chars[i] = Character.toString(fiName.charAt(i));
		    if(chars[i].matches("^[;:*?\"<>\\\\/|]+$")) {
		    	flag = true;
		    	break;
		    }
		}
		return flag;
	}
}
