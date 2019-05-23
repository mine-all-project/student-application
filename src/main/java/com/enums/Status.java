package com.enums;

public enum Status {
	SUCCESS,FAIL;
	public static boolean isSuccess(Status status){
		if(SUCCESS==status) {
			return true;
		}
		return false;
	}
}
