package com.Test.Controller;

import java.util.ArrayList;
import java.util.ListIterator;

import com.Test.ModelZwei.Property;
import com.Test.ModelZwei.Root;

public class PropertyRek {

	private Property vor;
	private Property nach;
	private ArrayList<Property> liste;
	private ArrayList<Property> durchlaufenListe;
	private PropertyRek propEntity;
	
	PropertyRek () {};
	
	PropertyRek (Property vor, Property nach){
		this.vor=vor;
		this.nach=nach;
	}

	
	private PropertyRek getPropertyRek() {
		propEntity = new PropertyRek();
		liste= new ArrayList<Property>();
		durchlaufenListe= new ArrayList<Property>();
		
		return propEntity;
	}
	
	
	public void addAllProperties(Property [] arr) {
		
		ListIterator<Property> iter = liste.listIterator();
		
		for (int i=0; i<arr.length; i++) {
			iter.add(arr[i]);	
		}
	}
	
	public boolean compareList(ArrayList<Property> a, ArrayList<Property> b ){
		boolean out=false;
		
		if (a.size()==b.size()) {
			out=true;
		}
		return out;
	}
	
	
	
	public void addGeschalten(Property x) {
		durchlaufenListe.add(x);
	}
	
	
	public boolean hasChildren(Property [] x) {
		boolean out=true;
		if (x.length==0 || x==null) {
			out=false;
		}
		return out;
	}
	
	
	public void schalten (Property pro) {
		String ausgabe="";
		
		if (pro.getChildren()==null) {
			System.out.println(pro.getName() +" " + pro.getNodeType() + " " + "null");
		} else {
			if (pro.getChildren().length==0) {
				System.out.println(pro.getName() +" " + pro.getNodeType() + " " + "Children leer");
			} else {
				for (int i=0; i<pro.getChildren().length; i++) {
					System.out.print(pro.getName() +" " + pro.getNodeType() + " " + "Children:" );
					System.out.println(pro.getChildren()[i].getName() + pro.getChildren()[i].getNodeType());
					schalten(pro.getChildren()[i]);
				}
			}
		}
	}
	
	
	public void rekAlg(Root x) {
		
		ArrayList<Property> pro = x.getRootElement().getProperties();
		ListIterator<Property> iter = pro.listIterator();
		String ausgabe="";
		
		while (iter.hasNext()) {
			Property property = iter.next();
			PropertyRek rek = new PropertyRek();
			
			//rek.addAllProperties(property.getChildren());
			//schalten(property.getChildren());
			schalten(property);
			//rek.addGeschalten(property.getChildren()[i]);
			//if (compareList(rek.getDurchlaufenListe(),rek.getListe())) {
			//} else {
		
			}
	}
	
	
	public Property getVor() {
		return vor;
	}

	public void setVor(Property vor) {
		this.vor = vor;
	}

	public Property getNach() {
		return nach;
	}

	public void setNach(Property nach) {
		this.nach = nach;
	}

	public ArrayList<Property> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Property> liste) {
		this.liste = liste;
	}

	public ArrayList<Property> getDurchlaufenListe() {
		return durchlaufenListe;
	}

	public void setDurchlaufenListe(ArrayList<Property> durchlaufenListe) {
		this.durchlaufenListe = durchlaufenListe;
	}
	
}
