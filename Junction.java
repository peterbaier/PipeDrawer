package com.baier.cw3;

public class Junction {
	
	private String name;
	private int xCord;
	private int yCord;
	
	
	public Junction(){
	}
	
	public Junction(String name, int xCord, int yCord){
		setName(name);
		setxCord(xCord);
		setyCord(yCord);
	}
	
	public int getyCord() {
		return yCord;
	}

	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getxCord() {
		return xCord;
	}

	public void setxCord(int xCord) {
		this.xCord = xCord;
	}
	
}
