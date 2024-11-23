package com.project.entity;




public class Software {
	
	private int id;
	private String name;
	
	private String description;
	private String accessLevels;
	
	
	public Software(String name, String description, String accessLevel) {
		super();
		this.name = name;
		this.description = description;
		this.accessLevels = accessLevel;
	}
	public Software() {
		super();
	}
	public Software(int id, String name, String description, String accessLevel) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.accessLevels = accessLevels;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccessLevel() {
		return accessLevels;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevels = accessLevel;
	}
	@Override
	public String toString() {
		return "Software [id=" + id + ", name=" + name + ", description=" + description + ", accessLevel=" + accessLevels
				+ "]";
	}
	
}
