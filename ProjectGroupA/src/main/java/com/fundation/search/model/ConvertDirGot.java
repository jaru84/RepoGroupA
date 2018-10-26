package com.fundation.search.model;

public class ConvertDirGot {
	public ResultFile resFile;
	private String path, fileN, ext;
	private String pathGot = "";
	private static String [] splitDir, splitFileExt;
	
	public ConvertDirGot() {
		resFile = new ResultFile();
		path = "";
		fileN = "";
		ext = "";
		pathGot = "";
	}
		
	public ResultFile convertDir (String inputLine) {
		path = splitPath(inputLine, "\\\\");
		fileN = splitFile(splitDir [splitDir.length-1], "\\.");
		ext = splitExt();
		resFile = new ResultFile(path, fileN, ext); 
		return resFile;
	}
	
	private String splitPath(String inputLine, String charSplit ) {
		splitDir = inputLine.split(charSplit);
		for (int i=0; i< splitDir.length-1; i++ )
			pathGot += splitDir[i].concat("\\");
		return pathGot;
	}
	
	private  String splitFile(String inputLine, String charSplit ) {
		splitFileExt = inputLine.split(charSplit);
		return splitFileExt [0];
	}
	
	private String splitExt() {
		return splitFileExt [1];
	}
}
