package com.baier.cw3;

public class Pipe extends Connector {
	
	private String direction;
		
	public Pipe(){
	}
	
	public Pipe(String firstJunction, String secondJunction) {
		super(firstJunction, secondJunction);
	}


	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
