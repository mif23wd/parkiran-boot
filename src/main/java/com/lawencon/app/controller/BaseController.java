package com.lawencon.app.controller;

import java.util.Base64;

public class BaseController {
	String[] authUser(String auth) {
		byte[] decodedBytes = Base64.getDecoder().decode(auth);
		String decodedString = new String(decodedBytes);
		return decodedString.split(":");
	}
}
