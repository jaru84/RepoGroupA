/*
 *  @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 *  2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 *  All rights reserved.
 *  <p>
 *  This software is the confidential and proprietary information of
 *  Jalasoft, ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 */

package com.fundation.search.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ActionSearch implements ActionListener{
        private SearchWindow w;

        public ActionSearch(SearchWindow win){
            w = win;
        }
        public void actionPerformed(ActionEvent e){

            Map<String, String> parameters = w.getParameters();
            System.out.println("Path: " + parameters.get("path"));
            System.out.println("Name: " + parameters.get("fileName"));
            System.out.println("Extension: " + parameters.get("extension"));

        }
}
