package com.Test.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.Test.Controller.JsonMapper;
import com.Test.Model.BookPojo;
import com.Test.Model.SchemaPojo;
import com.Test.Model.TestPojo;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class Controller {

	@RequestMapping("/startApp")
	@ResponseBody 
	public String startApp() {
		
		String src="{ \"title\" : \"Theater\" , \"author\" : \"James\" }";
		String x="Leer";
		TestPojo pj = new TestPojo();
		try {
		JsonNode nd = JsonMapper.parse(src);
		x=nd.get("title").asText();
		pj = JsonMapper.fromJson(nd, TestPojo.class);
		}
		catch (IOException e) {e.printStackTrace();}
		
		return x + " " + pj.getTitle() ;
		
		
	}
	
	@RequestMapping("/startAppTwo")
	@ResponseBody 
	public String startApp2() {
	
		TestPojo x = new TestPojo();
		x.setId(5);
		x.setTitle("Jens Lehmann");
		
		JsonNode no = JsonMapper.toJson(x);
		System.out.println(no.get("title").asText() + " " + no.get("id").asText());
		return no.get("title").asText() + " " + no.get("id").asText();
		
	}
	
	@RequestMapping("/startAppThree")
	@ResponseBody 
	public String startApp3() throws JsonProcessingException {
		TestPojo x = new TestPojo();
		x.setId(5);
		x.setTitle("Jens Hans Lehmann");
		
		JsonNode no = JsonMapper.toJson(x);
			
		String out = JsonMapper.JsonToString(no);
		System.out.println(out);
		return out;
	}
	
	@RequestMapping("/startAppFour")
	@ResponseBody 
	public String startApp4() throws JsonProcessingException {
		// String src="{ \"entity\" : \"personAx\" , \"propOne\" : \"String\" , \"propTwo\" : \"xxx\" , "
		//		+ "\"id\" : \"3\", \"HasName\" :\"false\" ,"
		//		+ "\"date\" :\"25-12-2019\" 				}";
		String src= "{ \"entity\" : \"Nein\" , \"id\" : \"5\" , \"propTwo\" : \"xxx\" , \"propOne\" : \"String\" , \"HasName\" : \"false\" }"; 
		SchemaPojo pj = new SchemaPojo();
		try {
		JsonNode nd = JsonMapper.parse(src);
		pj = JsonMapper.fromJson(nd, SchemaPojo.class);
		}
		catch (IOException e) {e.printStackTrace();}
		System.out.println(pj.getEntity()  + " " + pj.getId() + " " + pj.getPropTwo() + pj.getPropOne() + " " + pj.gethasName());
		return pj.getEntity()  + " " + pj.getId() + " " + pj.getDate() + pj.getPropOne() + pj.getPropTwo() ;
	}
	
	
	
	@RequestMapping("/startAppFive")
	@ResponseBody 
	public String startApp5() throws JsonProcessingException {
		String src= "{ \"author\" : \"Hans\" , \"books\" : [\"John\" , \"Dee\"] ,  \"hobbies\" :  [{ \"name\" : \"Surf\" , \"action\" : \"5\" }, "
				+ "{ \"name\" : \"Surfer2\" , \"action\" : \"9\" }] }";				
				
		BookPojo pj = new BookPojo();
		try {
		JsonNode nd = JsonMapper.parse(src);
		pj = JsonMapper.fromJson(nd, BookPojo.class);
		}
		catch (IOException e) {e.printStackTrace();}
		System.out.println(pj.getAuthor()  + " " + pj.getBooks() + " " + pj.getHobbies());
		return pj.getAuthor()  + " " + pj.getBooks() + " " + pj.getHobbies() ;
	}
	
	@RequestMapping(value="/startAppSix")
	@ResponseBody 
	public String startApp6() throws IOException {
		
		String src= "{ \"author\" : \"Hans\" , \"books\" : [\"John\" , \"Dee\"] ,  \"hobbies\" :  [{ \"name\" : \"Surf\" , \"action\" : \"5\" }, "
				+ "{ \"name\" : \"Surfer2\" , \"action\" : \"9\" }] }";				
		
		String out="";
		BookPojo pj = new BookPojo();
		try {
		JsonNode nd = JsonMapper.parse(src);
		out=JsonMapper.JsonToString(nd);
		pj = JsonMapper.fromJson(nd, BookPojo.class);
		}
		catch (IOException e) {e.printStackTrace();}
		String filename = "newFile.json";
		//src\main\JsonFiles
		File x = JsonMapper.writeJson(pj,filename);
		
		return out;
		
	}
	
	
	@RequestMapping(value="/startAppSeven")
	@ResponseBody 
	public String startApp7() throws IOException {
		
		String src= "src/main/JsonFiles/aut.json";				
		
		String out="";
		
		ArrayList<BookPojo> x = JsonMapper.readJson(src);
		ListIterator<BookPojo> iter = x.listIterator();
		
		while(iter.hasNext()) {
			BookPojo book = iter.next();
			out= out + " " + book.getAuthor() + "/" + book.getBooks() + "/" + book.getHobbies();
		}
		
		return out;
		
	}
	
	@RequestMapping(value="/startAppEight")
	@ResponseBody 
	public String startApp8() throws IOException {
		
		String [] src= new String [2]; 
		src[0]="src/main/JsonFiles/aut.json";				
		src[1]="src/main/JsonFiles/aut2.json";
		String out="";
		
		ArrayList<BookPojo> x = JsonMapper.readJsonFiles(src);
		ListIterator<BookPojo> iter = x.listIterator();
		
		while(iter.hasNext()) {
			BookPojo book = iter.next();
			out= out + " " + book.getAuthor() + "/" + book.getBooks() + "/" + book.getHobbies() + "\n";
		}
		
		return out;
		
	}
	
	
}
