package com.Test.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BookPojo {

	private String author;
	private String [] books;
	private List<HobbyPojo> hobbies = new ArrayList<HobbyPojo> ();
	
	
	public BookPojo () {}
	
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getBooks() {
		String out="";
		for (int i=0; i<books.length; i++) {
		out = out + " " + books[i].toString();	
		}
		return out;
	}


	public void setBooks(String[] books) {
		this.books = books;
	}


	public String getHobbies() {
		ListIterator<HobbyPojo> iter = hobbies.listIterator();
		String out="{";
		while (iter.hasNext()) {
			//System.out.println("Ja." + iter.next().getName() + iter.next().getAction() );
			HobbyPojo po = iter.next();
			out= out + "\"" + po.getName() +"\"" + "," + "\"" + po.getAction() +"\"" ;
			System.out.println(out);
		}
		
		out=out +"}";
		return out;
	}


	public void setHobbies(ArrayList<HobbyPojo> hobbies) {
		this.hobbies = hobbies;
	}


	
	
	
}
