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
import java.util.Date;

import com.fundation.search.model.SearcherCriteria;
import com.fundation.search.view.SearchWindow;

/**
 * Validator class is in charge to do validations for the values inserted in the fields in the UI.
 * i.e. validate that path is required field or validate that owner is in the correct format.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Validator {

	/** Window variable used to show the warnings messages. */
	private SearchWindow window;

	/**
	 * Constructor for the Validator class.
	 * @param window required to show during the error messages captured in validation.
	 */
	public Validator(SearchWindow window) {
		this.window = window;
	}

	/**
	 * method in charge to send the criteria object to be validated.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the validation fails for some attribute.
	 */
	public void validate(SearcherCriteria criteria) throws CustomSearchException {
		validatePath(criteria);
		validateFileName(criteria);
		validateExtension(criteria);
		validateSize(criteria);
		validateOwner(criteria);
		validateDates(criteria);
	}
	
	/**
	 * Method in charge to validate the owner value inserted by user.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the owner does not have the correct format.
	 */
	private void validateOwner(SearcherCriteria criteria) throws CustomSearchException {
		if ((! criteria.getOwner().contains("\\")) && (!criteria.getOwner().isEmpty())) {
			window.setErrorMessage("Please introduce an account value in Owner field with following format: domain\\user.");
			throw new CustomSearchException("Please introduce an account value in Owner field with following format: domain\\user.");
		} 
	}
	
	/**
	 * Method in charge to validate the path value inserted.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the path is empty, it does not exist or it is not a valid directory.
	 */
	private void validatePath(SearcherCriteria criteria) throws CustomSearchException {
		if (criteria.getPath().isEmpty()) {
			window.setErrorMessage("The path is a required field.");
			throw new CustomSearchException("The path is a required field.");
		} else {
			File dir = new File(criteria.getPath());
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
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the fileName is greater than 100 or has special symbols not allowed.
	 */
	private void validateFileName(SearcherCriteria criteria) throws CustomSearchException {
		if (criteria.getFileName().isEmpty()) {
			criteria.setFileName("*");
		} else {
			if (criteria.getFileName().length() > 100) {
				window.setErrorMessage("Your file name inserted exceeds the limit of letters allowed 50");
				throw new CustomSearchException("Your file name inserted exceeds the limit of letters allowed 50");
			} else if (checkSymbols(criteria.getFileName())) {
				window.setErrorMessage("Your file name can't contain any of following characters: \\/:?\"<>");
				throw new CustomSearchException("Your file name can't contain any of following characters: \\/:?\"<>");
			}
		}
	}

	/**
	 * method in charge to validate the extension value inserted.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the extension has special symbols not allowed.
	 */
	private void validateExtension(SearcherCriteria criteria) throws CustomSearchException {
		if (criteria.getExt().isEmpty()) {
			criteria.setExt("*");
		} else {
			if (checkSymbols(criteria.getExt())) {
				window.setErrorMessage(
						"Your extension can't contain any of following characters: \\\\\\\\/:?\\\\\\\"<>.");
				throw new CustomSearchException(
						"Your extension can't contain any of following characters: \\\\/:?\\\"<>.");
			}
		}
	}

	/**
	 * method in charge to validate special chars not allowed in file name and extension fields.
	 * @param wordCheck (required) string word that will be revised.
	 */
	private boolean checkSymbols(String wordCheck) {
		boolean flag = false;
		String[] chars = new String[wordCheck.length()];
		for (int i = 0; i < wordCheck.length(); i++) {
			chars[i] = Character.toString(wordCheck.charAt(i));
			if (chars[i].matches("^[;:?\"<>\\\\/|]+$")) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * method in charge to validate the size value inserted.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the size is a negative number or is not an integer value.
	 */
	private void validateSize(SearcherCriteria criteria) throws CustomSearchException {
		if ((criteria.getSize() == null) || (criteria.getSize().isEmpty())) {
			criteria.setSize("0");
		} else {
			if (onlyNumbers(criteria.getSize())) {
				if (Integer.valueOf(criteria.getSize()) < 0) {
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
	 * Method in charge to validate the start and end dates selected by the user.
	 * @param file object must have content, it is used to validate its different attributes.
	 * @throws CustomSearchException if the dates are nulls or start date is after than end date or current date.
	 */
	private void validateDates(SearcherCriteria criteria) throws CustomSearchException {
		Date startDate = criteria.getStartDate();
		Date endDate = criteria.getEndDate();
		Date currDate = new Date();
		
		if (!criteria.getDateType().equals("< Select a Value >")) {
			if ((startDate == null) || (endDate == null)) {
				window.setErrorMessage("Please select valid dates for interval: between - and.");
				throw new CustomSearchException("Please select valid dates for interval: between - and.");
			} else {
				if (startDate.after(endDate)) {
					window.setErrorMessage("The Start Date selected could not be after End Date.");
					throw new CustomSearchException("The Start Date selected could not be after End Date.");
				} else if (startDate.after(currDate)) {
					window.setErrorMessage("The Start Date selected could not be after Current Date.");
					throw new CustomSearchException("The Start Date selected could not be after Current Date.");
				}
			}
		} else {
			if ((startDate != null) || (endDate != null)) {
				window.setErrorMessage("Please select some value from drop-down list different to: <Select a value> and ensure that you select valid dates.");
				throw new CustomSearchException("Please select some value from drop-down list different to: <Select a value> and ensure that you select valid dates.");
			}
		} 		
	}
	
	/**
	 * method in charge to validate that size only allows integer numbers.
	 * @param sizeFile (required) value to be checked.
	 */
	private boolean onlyNumbers(String sizeFile) {
		if (sizeFile.matches("^[0-9]*$")) {
			return true;
		} else {
			return false;
		}
	}

}
