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

/**
 * Main class to test the View.
 *
 * @author Jose Colina
 * @version 1.0.
 */

public class mainV {
    static SearchWindow win;

    public static void main(String[] args) {
        win = new SearchWindow();
        win.setSearchListener(new ActionSearch(win));
    }

}
