package com.example.watsonyourplate;

public class Diet {
	
	String name = "undefined";
	String description = "undefined";
	boolean selected = false;
	
	public Diet(String n, String d) {
		name = n;
		description = d;
		selected = false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}

}
