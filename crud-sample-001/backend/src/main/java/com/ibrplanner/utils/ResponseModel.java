package com.ibrplanner.utils;

import org.springframework.stereotype.Component;

@Component
public class ResponseModel {

    private String message;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

}
