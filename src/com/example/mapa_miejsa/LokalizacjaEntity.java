package com.example.mapa_miejsa;

public class LokalizacjaEntity {
	
	private int id;
	private String title;
	private String description;
	private Double x;
	private Double y;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(double d) {
		this.y = d;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
