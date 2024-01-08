package com.lms.course.utils;

public class Constants {

	public static final String[] PROTECTED_RESSOURCES= {
			"course/add","course/update","course/remove"
	};
	
	public static final boolean check(String uri) {
		boolean check = false;
		for(String i : PROTECTED_RESSOURCES) {
			if(uri.endsWith(i)) {
				check = true;
				break;
			}
		}
		return check;
	}
}
