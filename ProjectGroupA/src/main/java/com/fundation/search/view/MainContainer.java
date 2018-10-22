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

package com.fundation.search.view;

import javax.swing.JButton;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

/**
 * This class contains the panels which displays the results and parameters,
 * in right side the parameters panel and the results panel in the left.
 *
 * @author Jose Colina
 * @version 1.0.
 */
public class MainContainer extends Container {
    private ResultsPanel resultsPanel;
    private ParametersPanel parametersPanel;

    public MainContainer() {
        resultsPanel = new ResultsPanel();
        parametersPanel = new ParametersPanel();

        setLayout(new GridLayout(1, 2));
        add(parametersPanel);
        add(resultsPanel);
    }

    // These methods return the values in text fields in a String
    public String getPath() {
        return parametersPanel.getPath();

    }

    public String getName() {
        return parametersPanel.getName();

    }

    public String getExtension() {
        return parametersPanel.getExtension();

    }

    public String getFileSize() {
        return parametersPanel.getFileSize();

    }

    public String getSizeOperator() {
        return parametersPanel.getSizeOperator();

    }

    public String getSizeScale() {
        return parametersPanel.getSizeScale();

    }

    public JButton getSearchButton() {
        return parametersPanel.getSearchButton();
    }

    public void setResults(Vector dataVector) {
        resultsPanel.setTableDate(dataVector);

    }

    public void setError(String message) {
        parametersPanel.getErrorLabel().setText(message);
    }

    public void cleanError() {
        parametersPanel.getErrorLabel().setText("");
    }
}
