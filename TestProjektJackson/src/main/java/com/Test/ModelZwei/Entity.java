package com.Test.ModelZwei;

public class Entity {

	private String name;
	private String nodeType;
	private int properties_size;
	
	
	public Entity () {};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public int getProperties_size() {
		return properties_size;
	}
	public void setProperties_size(int properties_size) {
		this.properties_size = properties_size;
	}
	
}
