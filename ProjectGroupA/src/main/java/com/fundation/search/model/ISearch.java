package com.fundation.search.model;

import java.io.IOException;
import java.util.ArrayList;

import com.fundation.search.controller.CustomSearchException;

/**
 * Interface to Search class.
 *
 * @author Jacky
 * @version 1.0
 */
public interface ISearch {
    /**
     * Method to run the command in DOS and treat the output with the criteria.
     *
     * @param file (required) of SearcherCriteria type, must have content, it has all values inserted by the user for the search process.
     * @return resultFiles it will return a ArrayList <CustomFile> with the results for the search process.
     * @throws IOException           if something fails during BufferedReader process.
     * @throws CustomSearchException if something fails during the searchFile process an error message will be raised.
     */
    public ArrayList<CustomFile> searchFile(SearcherCriteria file) throws IOException, CustomSearchException;

}
