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
import com.fundation.search.view.SearchWindow;

/**
 * This class is in charge to do validations to the values for path, file name
 * and extension.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Validator {

	/** window variable used to show the warnings messages. */
	private SearchWindow window;

	/**
	 * constructor.
	 */
	public Validator(SearchWindow window) {
		this.window = window;
	}

	/**
	 * method in charge to send the criteria object to be validated.
	 */
	public void validate(SearcherCriteria file) throws CustomSearchException {
		validatePath(file);
		validateFileName(file);
		validateExtension(file);
		validateSize(file);
	}

	/**
	 * method in charge to validate the path value inserted.
	 */
	private void validatePath(SearcherCriteria file) throws CustomSearchException {
		if (file.getPath().isEmpty()) {
			window.setErrorMessage("The path is a required field.");
			throw new CustomSearchException("The path is a required field.");
		} else {
			File dir = new File(file.getPath());
			if (!dir.exists()) {
				window.setErrorMessage("The path inserted does not exist.");
				throw new CustomSearchException("The path inserted does not exist.");
			} else if (!dir.isDirectory()) {
				window.setErrorMessage("The path inserted is not valid directory.");
				throw new CustomSearchException("The path inserted is not valid directory.");
			}
		}

	}

	/**
	 * method in charge to validate the file name value inserted.
	 */
	private void validateFileName(SearcherCriteria file) throws CustomSearchException {
		if (file.getFileName().isEmpty()) {
			file.setFileName("*");
		} else {
			if (file.getFileName().length() > 50) {
				window.setErrorMessage("Your file name inserted exceeds the limit of letters allowed 50");
				throw new CustomSearchException("Your file name inserted exceeds the limit of letters allowed 50");
			} else if (checkSymbols(file.getFileName())) {
				window.setErrorMessage("Your file name can't contain any of following characters: \\/:*?\"<>");
				throw new CustomSearchException("Your file name can't contain any of following characters: \\/:*?\"<>");
			}
		}
	}

	/**
	 * method in charge to validate the extension value inserted.
	 */
	private void validateExtension(SearcherCriteria file) throws CustomSearchException {
		if (file.getExt().isEmpty()) {
			file.setExt("*");
		} else {
			if (checkSymbols(file.getExt())) {
				window.setErrorMessage(
						"Your extension can't contain any of following characters: \\\\\\\\/:*?\\\\\\\"<>.");
				throw new CustomSearchException(
						"Your extension can't contain any of following characters: \\\\/:*?\\\"<>.");
			}
		}
	}

	/**
	 * method in charge to validate special chars not allowed in file name and
	 * extension fields.
	 */
	private boolean checkSymbols(String wordCheck) {
		boolean flag = false;
		String[] chars = new String[wordCheck.length()];
		for (int i = 0; i < wordCheck.length(); i++) {
			chars[i] = Character.toString(wordCheck.charAt(i));
			if (chars[i].matches("^[;:*?\"<>\\\\/|]+$")) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * method in charge to validate the size value inserted.
	 */
	private void validateSize(SearcherCriteria file) throws CustomSearchException {
		if ((file.getSize() == null) || (file.getSize().isEmpty())) {
			file.setSize("0");
		} else {
			if (onlyNumbers(file.getSize())) {
				if (Integer.valueOf(file.getSize()) < 0) {
					window.setErrorMessage("You only can insert numbers greater than 0.");
					throw new CustomSearchException("You only can insert numbers greater than 0.");
				}
			} else {
				window.setErrorMessage("You only can insert integer numbers on size field.");
				throw new CustomSearchException("You only can insert integer numbers on size field.");
			}
		}
	}

	/**
	 * method in charge to validate that size only allows integer numbers.
	 */
	private boolean onlyNumbers(String sizeFile) {
		if (sizeFile.matches("^[0-9]*$")) {
			return true;
		} else {
			return false;
		}
	}
}
