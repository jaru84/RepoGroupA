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
import java.util.HashMap;
import java.util.Map;

/**
 * Class created to manage the search criteria object and its attributes.
 *
 * @author Jacqueline Rosales
 * @version 1.0.
 */
public class SearcherCriteria extends CustomFile {
	public Map<Integer, String> mapOperator = new HashMap<Integer, String>();
	public String size, operator, storageUnit;
		
	public SearcherCriteria() {
		path= "";
		fileName= "";
		ext="";	
		size="";
		operator="";
		storageUnit="";
		mapOperator= new HashMap();
		loadOperators();
	}
	public void loadOperators() {
		mapOperator.put(1, "==");
		mapOperator.put(2, "<");
		mapOperator.put(3, "<=");
		mapOperator.put(4, ">");
		mapOperator.put(5, ">=");
	}
	public String toString() {
		return super.toString()+"\nSize: "+size+"\nOperator: "+mapOperator.get(1);
	}
}
