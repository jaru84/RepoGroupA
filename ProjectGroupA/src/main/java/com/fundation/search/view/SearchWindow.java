/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.fundation.search.view;

import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

/**
 * This class defines the window to contain all the panels and GUI elements.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class SearchWindow extends JFrame {
    /**
     * Parameters for frame:
     */
    private static final int WIDTH = 900;
    private static final int HEIGHT = 400;
    private static final int X_ORIGIN = 150;
    private static final int Y_ORIGIN = 250;
    private ImageIcon searchIcon;

    /**
     * Main container to keep all the panels:
     */
    private MainContainer contentPane;

    public SearchWindow() {
        setting();
        init();
    }

    /**
     * This method set the parameters required for the frame
     */
    public void setting() {
        setTitle("Search Files - Team A");
        setSize(WIDTH, HEIGHT);
        setLocation(X_ORIGIN, Y_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        searchIcon = new ImageIcon("src/main/resources/searchIcon.png");
        setIconImage(searchIcon.getImage());

    }

    /**
     * This method initialize the main container and add it to frame
     */
    public void init() {
        /** Instantiate GUI elements */
        contentPane = new MainContainer();

        /** Add GUI elements */
        setContentPane(contentPane);

        setVisible(true);

    }

    /**
     * These methods return the values in text fields in a String
     * For checkboxes the value to return is a boolean
     */
    public String getPath() {
        return contentPane.getPath();

    }

    public String getFileName() {
        return contentPane.getName();

    }

    public String getExtension() {
        return contentPane.getExtension();

    }

    public String getFileSize() {
        return contentPane.getFileSize();

    }

    public String getSizeOperator() {
        return contentPane.getSizeOperator();

    }

    public String getSizeScale() {
        return contentPane.getSizeScale();

    }

    public String getFileOwner() {
        return contentPane.getOwner();

    }

    public String getContent() {
        return contentPane.getContent();

    }

    public boolean getIsDirectory() {
        return contentPane.getIsDirectory();

    }

    public boolean getIsHidden() {
        return contentPane.getIsHidden();

    }

    public boolean getIsReadOnly() {
        return contentPane.getIsReadOnly();

    }

    /**
     * This method set a listener to Search button
     */
    public void setSearchListener(ActionListener listener) {
        contentPane.getSearchButton().addActionListener(listener);
    }

    /**
     * This method display the results of searching in the left table It's expected
     * a Vector of Vectors where each vector contains the parameters of file
     * matching the search criteria
     */
    public void setSearchResults(Object[] dataResults) {
        contentPane.setResults(dataResults);
    }

    /**
     * This method display the error message sent by Controller in a popup dialog
     * The error message is expected as a String
     */
    public void setErrorMessage(String message) {
        JOptionPane errorPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = errorPane.createDialog(null, "Error");
        dialog.setVisible(true);

    }

    /**
     * This method display clear the results table
     */
    public void clearResults() {
        contentPane.clearTable();
    }


    public Date[] getCreationDates() {
        return contentPane.getCreationDates();

    }

    public Date[] getModifiedDates() {
        return contentPane.getModifiedDates();

    }

    public Date[] getAccessedDates() {
        return contentPane.getAccessedDates();

    }
}
