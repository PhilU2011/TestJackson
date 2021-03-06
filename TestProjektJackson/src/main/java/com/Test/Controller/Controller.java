package com.Test.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Test.Controller.JsonMapper;
import com.Test.Model.BookPojo;
import com.Test.Model.SchemaPojo;
import com.Test.Model.TestPojo;
import com.Test.ModelZwei.Entity;
import com.Test.ModelZwei.Property;
import com.Test.ModelZwei.Root;
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
	
	
	@RequestMapping(value="/startAppSeven/{name}")
	@ResponseBody 
	public String startApp7(@PathVariable String name) throws IOException {
				
		String src=name + ".json";
		
		return JsonMapper.readJsonTree(src);
		
	}
	
	@RequestMapping(value="/startAppEight/{no}/{name}")
	@ResponseBody 
	public String startApp8(@PathVariable String no, @PathVariable String name) throws IOException {
		
		int anzahl= Integer.parseInt(no);
		String [] src= new String [anzahl]; 
		
		for (int i=0; i<src.length; i++) {
			if (i==0) {
				src[i]="src/main/JsonFiles/aut.json";}
			else {
				src[i]="src/main/JsonFiles/aut" + i + ".json";
			}
		//src[1]="src/main/JsonFiles/aut2.json";
		}
		String out="";
		
		ArrayList<BookPojo> x = JsonMapper.readJsonFiles(src);
		ListIterator<BookPojo> iter = x.listIterator();
		
		while(iter.hasNext()) {
			BookPojo book = iter.next();
			out= out + " " + book.getAuthor() + "/" + book.getBooks() + "/" + book.getHobbies() + "\n";
		}
		
		name=name+".json";
		JsonMapper.writeJson(x, name);
		
		return out;
		
	}
	
	
	@RequestMapping(value="/startAppNine/{no}/{name}")
	@ResponseBody 
	public String startAppNine(@PathVariable String no, @PathVariable String name) throws IOException {
		
		int anzahl = Integer.parseInt(no);
		String [] src= new String [anzahl]; 
		
		for (int i=0; i<src.length; i++) {
			if (i==0) {
				src[i]="src/main/JsonFiles/aut.json";}
			else {
				src[i]="src/main/JsonFiles/aut" + i + ".json";
			}
		}
		name= name + ".json";
		
		return JsonMapper.readAndWrite(src, name);
	}

	
	@RequestMapping(value="/startAppTen/{name}")
	@ResponseBody 
	public String startAppTen(@PathVariable String name) throws IOException {
		
		name= name + ".json";
		
		return JsonMapper.readJsonArray(name);
	}
	
	
	@RequestMapping(value="/startAppEleven/{name}")
	@ResponseBody 
	public String startAppEleven(@PathVariable String name) throws IOException {
		
		name= "src/main/JsonFiles/" + name + ".json";
		
		return JsonMapper.readJsonTreeModel(name);
	}
	
	
	@RequestMapping(value="/startAppTwelve/{name}")
	@ResponseBody 
	public String startAppTwelve(@PathVariable String name) throws IOException {
		
		name= "src/main/JsonFiles/" + name + ".json";
		
		Root x =JsonMapper.readJsonTreeModel(name, Root.class);
		String out="";
		PropertyRek rek = new PropertyRek();
		
		rek.rekAlg(x);
		
		//System.out.println("Entity: " + out);
		
		out ="Entity:" + x.getRootElement().getName() + " " + x.getRootElement().getNodeType() + " " + x.getRootElement().getProperties_size();
		
		
		// ??ber JsonNode
		JsonNode nd = JsonMapper.getMapper().readTree(new File(name));
		String aus = JsonMapper.JsonToString(nd);
		System.out.println(aus);
		
		return out;
		}
		
}
	
	
