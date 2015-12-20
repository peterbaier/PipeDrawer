package com.baier.cw3;

public class Connector {
	
	private String firstJunction;	
	private String secondJunction;
	
	public Connector(){

	}
	
	public Connector(String firstJunction, String secondJunction){
		setFirstJunction(firstJunction);
		setSecondJunction(secondJunction);
	}
	
	public String getFirstJunction() {
		return firstJunction;
	}

	public void setFirstJunction(String firstJunction) {
		this.firstJunction = firstJunction;
	}

	public String getSecondJunction() {
		return secondJunction;
	}

	public void setSecondJunction(String secondJunction) {
		this.secondJunction = secondJunction;
	}

}
