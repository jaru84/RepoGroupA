package com.fundation.search.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

public class SearchWindow extends JFrame{
    //Parameters for frame
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int X_ORIGIN = 150;
    private static final int Y_ORIGIN = 250;

    //GUI elements:
    private MainContainer contentPane;

    public SearchWindow(){
        setting();
        init();
    }
    public void setting(){
        setTitle("Search Files - Team A");
        setSize(WIDTH,HEIGHT);
        setLocation(X_ORIGIN,Y_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void init(){
        //Instantiate GUI elements
        contentPane = new MainContainer();

        //Add GUI elements
        setContentPane(contentPane);

    }
    public Map<String, String> getParameters(){
        return contentPane.getParemeters();
    }
    public JButton getSearchButton(){
        return contentPane.getSearchButton();
    }
    public void setSearchListener(ActionListener listener){
        contentPane.getSearchButton().addActionListener(listener);
    }
}
