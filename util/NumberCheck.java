package com.poseidon.util;

public class NumberCheck {
	public static int number(String str) {
		String temp = "";
		for (int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				temp += str.charAt(i);				
			}
		}
		return Integer.parseInt(temp);
	}

	public static boolean numberCh(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
