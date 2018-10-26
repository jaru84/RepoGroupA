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
package com.fundation.search;
import java.io.IOException;
import com.fundation.search.controller.Controller;
import com.fundation.search.controller.CustomSearchException;
/**
 * This class is the Main where the search application will be started calling to Controller.init.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class Main {
	public static void main (String args[]) throws IOException, CustomSearchException {
		Controller controller= new Controller();
		controller.init();	
	}
}
