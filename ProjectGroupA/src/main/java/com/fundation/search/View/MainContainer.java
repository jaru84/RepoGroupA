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

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainContainer extends Container {
    private ResultsPanel resultsPanel;
    private ParametersPanel paramnetersPanel;

    public MainContainer(){
        resultsPanel = new ResultsPanel();
        paramnetersPanel = new ParametersPanel();

        setLayout(new GridLayout(1,2));
        add(paramnetersPanel);
        add(resultsPanel);
    }
    public Map<String,String> getParemeters(){
        return paramnetersPanel.getParameters();
    }
    public JButton getSearchButton(){
        return paramnetersPanel.getSearchButton();
    }
}
