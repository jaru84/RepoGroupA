package com.fundation.search.controller;

public class LogUtil {
	public static boolean DEBUG_MODE = false;
	
	public static void setDEBUG_MODE(boolean flag) {
		DEBUG_MODE = flag;
	}
	public static void print(String line) {
		if (DEBUG_MODE == true) {
			System.out.println(line);
		}
	} 
	
}