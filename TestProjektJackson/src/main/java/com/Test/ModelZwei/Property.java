package com.Test.ModelZwei;

public class Property {

	private String name;
	private String nodeType;
	private Property [] children;
	
	public Property () {};	
	
	
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
	public Property[] getChildren() {
		return children;
	}
	public void setChildren(Property[] children) {
		this.children = children;
	}
	
	
	
	
}
