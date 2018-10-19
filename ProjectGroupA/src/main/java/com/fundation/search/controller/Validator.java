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
import com.fundation.search.model.SearcherCriteria;
/**
 * This class is in charge to make validations to the values for path, file name and extension.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Validator {
	/**
     *  method in charge to send the file object to be validated.*/
	public void validate(SearcherCriteria file) throws CustomSearchException {
		validatePath(file);
		validateFileName(file);
		validateExtension(file);
		validateSize(file);
	} 
	/**
     *  method in charge to validate the path value inserted.*/
	private void validatePath(SearcherCriteria file) throws CustomSearchException {
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
	private void validateFileName(SearcherCriteria file) throws CustomSearchException {
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
	private void validateExtension(SearcherCriteria file) throws CustomSearchException {
		if (file.ext.isEmpty())
			file.ext="*";
		else {
				if (checkSymbols(file.ext))
				throw new CustomSearchException("Your extension can't contain any of following characters: \\\\/:*?\\\"<>.");
		}
	}
	/**
     *  method in charge to validate special chars not allowed in file name and extension fields.*/
	private boolean checkSymbols(String wordCheck) {
		boolean flag = false;
		String[] chars = new String[wordCheck.length()];
		for (int i = 0; i < wordCheck.length(); i++) {
		    chars[i] = Character.toString(wordCheck.charAt(i));
		    if(chars[i].matches("^[;:*?\"<>\\\\/|]+$")) {
		    	flag = true;
		    	break;
		    }
		}
		return flag;
	}
	/**
     *  method in charge to validate the size value inserted.*/
	private void validateSize(SearcherCriteria file) throws CustomSearchException{
		if((file.size== null) ||(file.size.isEmpty()))
			file.size="0";
		else {
			if(onlyNumbers(file.size))
			{
				if(Integer.valueOf(file.size) < 0)
					throw new CustomSearchException("You only can insert numbers greater than 0.");
			}
			else
				throw new CustomSearchException("You only can insert integer numbers on size field.");	
		}
	}
	/**
     *  method in charge to validate that size only allows integer numbers.*/
	private boolean onlyNumbers(String sFile) {
		if (sFile.matches("^[0-9]*$"))
			return true;
		else
			return false;
	}
}
