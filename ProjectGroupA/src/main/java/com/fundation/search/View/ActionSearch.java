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
/**
 * This is a auxiliary listener test class to display the text that the user enters in the searching fields.
 * Controller should implement its own listener to get the text fields.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class ActionSearch implements ActionListener{
        private SearchWindow w;

        public ActionSearch(SearchWindow win){
            w = win;
        }
        public void actionPerformed(ActionEvent e){

            System.out.println("Path: " + w.getPath());
            System.out.println("Name: " + w.getName());
            System.out.println("Extension: " + w.getExtension());

        }
}
