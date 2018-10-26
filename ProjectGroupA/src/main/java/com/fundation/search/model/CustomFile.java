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

public abstract class CustomFile {
	protected String path;
	protected String fileName;
	protected String ext;

	public String toString() {
		return "You have inserted following values:\nPath: " + path + "\nFile Name: " + fileName + "\nExtension: "
				+ ext;
	}
}
