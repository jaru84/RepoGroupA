package com.fundation.search.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface to Search class.
 *
 * @author Jacky
 * @version 1.0
 */
public interface ISearch {
	/**
	 * Method to run the command in DOS and treat the output with the criteria.
	 * @param file (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
	 * @throws IOException if something fails during BufferedReader process.
	 * @return resultFiles it will return a ArrayList <CustomFile> with the results for the search process.
	 */
	public ArrayList<CustomFile> searchFile(SearcherCriteria file) throws IOException;
	
}
