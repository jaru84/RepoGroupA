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
 * it inherits from JFrame.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class SearchWindow extends JFrame {

    /**
     * WIDTH variable ctte of int type used to set the width value on 900.
     */
    private static final int WIDTH = 950;

    /**
     * HEIGHT variable ctte of int type used to set the height value on 400.
     */
    private static final int HEIGHT = 400;

    /**
     * X_ORIGIN variable ctte of int type used to set the x_origin value on 150.
     */
    private static final int X_ORIGIN = 150;

    /**
     * Y_ORIGIN variable ctte of int type used to set the y_origin value on 150.
     */
    private static final int Y_ORIGIN = 250;

    /**
     * searchIcon variable of ImageIcon type.
     */
    private ImageIcon searchIcon;

    /**
     * contentPane variable of MainContainer type used to keep all the panels.
     */
    private MainContainer contentPane;

    /**
     * Constructor by default, used load the setting and init method.
     */
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
     * This method return the value in path text field in a String.
     *
     * @return path value as String.
     */
    public String getPath() {
        return contentPane.getPath();

    }

    /**
     * This method return the value in file name text field in a String.
     *
     * @return fileName value as String.
     */
    public String getFileName() {
        return contentPane.getName();

    }

    /**
     * This method return the value in extension text field in a String.
     *
     * @return extension value as String.
     */
    public String getExtension() {
        return contentPane.getExtension();

    }

    /**
     * This method return the value in File Size text field in a String.
     *
     * @return File Size value as String.
     */
    public String getFileSize() {
        return contentPane.getFileSize();

    }

    /**
     * This method return the value in Size Operator text field in a String.
     *
     * @return Size Operator value as String.
     */
    public String getSizeOperator() {
        return contentPane.getSizeOperator();

    }

    /**
     * This method return the value in Size Scale text field in a String.
     *
     * @return Size Scale value as String.
     */
    public String getSizeScale() {
        return contentPane.getSizeScale();

    }

    /**
     * This method return the value in File Owner text field in a String.
     *
     * @return File Owner value as String.
     */
    public String getFileOwner() {
        return contentPane.getOwner();

    }

    /**
     * This method return the value in Content text field in a String.
     *
     * @return Content value as String.
     */
    public String getContent() {
        return contentPane.getContent();

    }

    /**
     * This method return the value in Is Directory check box as boolean.
     *
     * @return true if Directory check box was checked and false if not.
     */
    public boolean getIsDirectory() {
        return contentPane.getIsDirectory();

    }

    /**
     * This method return the value in Hidden check box as boolean.
     *
     * @return true if Hidden check box was checked and false if not.
     */
    public boolean getIsHidden() {
        return contentPane.getIsHidden();

    }

    /**
     * This method return the value in Read Only check box as boolean.
     *
     * @return true if Read Only check box was checked and false if not.
     */
    public boolean getIsReadOnly() {
        return contentPane.getIsReadOnly();

    }

    /**
     * This method set a listener to Search button
     *
     * @param listener (required) variable necessary to set listener on search button.
     */
    public void setSearchListener(ActionListener listener) {
        contentPane.getSearchButton().addActionListener(listener);
    }


    /**
     * This method display the results of searching in the results table.
     *
     * @param dataResults (required) Objects array where each element contains the parameters of file matching the search criteria.
     */
    public void setSearchResults(Object[] dataResults) {
        contentPane.setResults(dataResults);
    }

    /**
     * This method display the error message sent by Controller in a popup dialog
     *
     * @param message (required) error message is expected as a String.
     */
    public void setErrorMessage(String message) {
        JOptionPane errorPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = errorPane.createDialog(null, "Error");
        dialog.setVisible(true);

    }

    /**
     * This method clear the results table
     */
    public void clearResults() {
        contentPane.clearTable();
    }


    public Date getStartDate() {
        return contentPane.getStartDate();

    }

    public Date getEndDate() {
        return contentPane.getEndDate();

    }

    /**
     * This method return the value in Combobox of dates.
     *
     * @return String with a value of "Creation Date", "Modified Date", "Accessed Date".
     */
    public String getDateType() {
        return contentPane.getDateType();

    }

}
