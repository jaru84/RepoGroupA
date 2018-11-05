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
package com.fundation.search.model;

/**
 * This class will only create an object of type CustomFile given a inputLine and criteria.
 *
 * @author Jose
 * @version 1.0
 */

import java.io.IOException;

public class ResultFileFactory {
    /**
     * Method to create the command in DOS. Considering the path is already in a correct format.
     *
     * @param inputLine (required) of String type, file got from search process used to create the object of ResultFile typ
     * @param criteria  SearcherCriteria type, used to create the object of ResultFile type.
     * @return command it will return a String [] with the command created.
     */
    public CustomFile createResultFile(String inputLine, SearcherCriteria criteria) throws IOException {
        CustomFile resultFile = new ResultFile(inputLine, criteria);
        return resultFile;
    }
}
