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
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.Map;
/**
 * This class defines the window to contain all the panels and GUI elements.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class SearchWindow extends JFrame{
    //Parameters for frame:
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int X_ORIGIN = 150;
    private static final int Y_ORIGIN = 250;
    //Main container to keep all the panels:
    private MainContainer contentPane;

    public SearchWindow(){
        setting();
        init();
    }
    // This method set the parameters required for the frame
    public void setting(){
        setTitle("Search Files - Team A");
        setSize(WIDTH,HEIGHT);
        setLocation(X_ORIGIN,Y_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    // This method initialize the main container and add it to frame
    public void init(){
        //Instantiate GUI elements
        contentPane = new MainContainer();

        //Add GUI elements
        setContentPane(contentPane);

        setVisible(true);

    }
    // This method return the values in text fields in a Map using the name field as a key
    public Map<String, String> getParameters(){
        return contentPane.getParameters();
    }
    /*public JButton getSearchButton(){
        return contentPane.getSearchButton();
    }*/
    // This method set a listener to Search button
    public void setSearchListener(ActionListener listener){
        contentPane.getSearchButton().addActionListener(listener);
    }
}