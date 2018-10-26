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
package com.fundation.search.model;

/**
 * Class created to manage the result files object and its attributes.
 *
 * @author Martha
 * @version 1.0.
 */
public class ResultFile extends CustomFile {
	private String owner;

	/**
	 * constructor for ResultFiles object
	 */
	public ResultFile() {
		super();
		owner= "";
	}
	
	public ResultFile(String path, String fileName, String ext) {
		super (path, fileName, ext);
	}
	
	/**
	 * method setter to owner value.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * method getter to owner value.
	 */
	public String getOwner() {
		return this.owner;
	}
}
