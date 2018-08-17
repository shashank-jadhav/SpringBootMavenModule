package com.shashank.phhub.core.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public MyData(String message) {
		super();
		this.message = message;
	}

	public MyData() {
		super();
	}
	
	
	
	
}
