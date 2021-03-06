package com.Test.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import com.Test.Model.BookPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonMapper {

	private static ObjectMapper mapper= getMapper();
	
	public static ObjectMapper getMapper () {
		ObjectMapper defaultMapper = new ObjectMapper();
		defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		defaultMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return defaultMapper;
	}
	
	public static JsonNode parse (String src) throws IOException {
		return mapper.readTree(src);	
	}
	
	public static <A> A fromJson (JsonNode node ,Class<A> cl) throws IOException {
	
			return mapper.treeToValue(node,cl);	 
		
	}
	
	
	public static JsonNode toJson (Object ob) {
		JsonNode node = mapper.valueToTree(ob);
		return node;
	}
	
	
	public static String JsonToString (JsonNode nd) throws JsonProcessingException {
		ObjectWriter writer = mapper.writer();
		//writer=writer.with(SerializationFeature.INDENT_OUTPUT);
		String output = writer.withDefaultPrettyPrinter().writeValueAsString(nd);
		return output;
			
	}
	
	public static File writeJson (Object nd, String filename) throws StreamWriteException, DatabindException, IOException {
		File datei = new File(filename);
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(datei, nd);
		return datei;		
	}
	
	
	// Ein Json File lesen
	public static ArrayList<BookPojo> readJson (String filename) throws IOException {
		ArrayList<BookPojo> x = new ArrayList<BookPojo> ();
		
		//BookPojo book = mapper.readerFor(BookPojo.class).readValue(filename);
		BookPojo book = mapper.readValue(new File(filename), BookPojo.class);
		x.add(book);
		return x;
	}
	// mehrere Json Files lesen
		public static  ArrayList<BookPojo> readJsonFiles (String [] filename) throws IOException {
			ArrayList<BookPojo> x = new ArrayList<BookPojo> ();
			
			for (int i=0; i<filename.length; i++) {
				BookPojo objekt = mapper.readValue(new File(filename[i]), BookPojo.class);
				x.add(objekt);
				}
			return x;
		}

	
	//Mehrere JSON Files als Tree lesen
		
		public static String readAndWrite (String [] filenames, String newName) throws IOException {
			JsonNode [] nd = new JsonNode[filenames.length];
			
			for (int i=0; i<filenames.length; i++) {
				nd[i]=mapper.readTree(new File (filenames[i]));
			}
			
			ArrayList<BookPojo> list = new ArrayList<BookPojo>();
			
			String output="";
			for(int i=0; i<nd.length; i++) {
				BookPojo bk = mapper.treeToValue(nd[i], BookPojo.class);
				list.add(bk);
				//output=output + i + ")" + bk.getAuthor() + bk.getBooks().toString() + bk.getHobbies().toString(); 
			}
			
			//write File
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File (newName), nd);
			
			for (int j=0; j<nd.length; j++) {
				ObjectWriter writer = mapper.writer();
				//writer=writer.with(SerializationFeature.INDENT_OUTPUT);
				output =output +  writer.withDefaultPrettyPrinter().writeValueAsString(nd[j]) + System.lineSeparator();
			}
				
			return output;			
			
		}
		
		// ein JSON File als Tree lesen
		
		public static String readJsonTree (String filename) throws IOException {
			JsonNode node = mapper.readTree(new File(filename));
			String output = JsonMapper.JsonToString(node);
			
			ArrayList<BookPojo> x = new ArrayList<BookPojo> ();
			
			BookPojo book = mapper.treeToValue(node, BookPojo.class);
			x.add(book);
			ListIterator<BookPojo> iter = x.listIterator();
			
			while (iter.hasNext()) {
			BookPojo bk = iter.next();	
			System.out.println(bk.getAuthor());
			System.out.println(bk.getBooks());
			System.out.println(bk.getHobbies());
			}
			
			return output;
		}

		
		//Json File als Array lesen
		

		public static String readJsonArray (String filename) throws IOException {
			JsonNode node = mapper.readTree(new File(filename));
			String output = JsonMapper.JsonToString(node);
			
			ArrayList<BookPojo []> x = new ArrayList<BookPojo []> ();
			
			BookPojo [] book = mapper.treeToValue(node, BookPojo [].class);
			x.add(book);
			ListIterator<BookPojo []> iter = x.listIterator();
			
			while (iter.hasNext()) {
				BookPojo [] bk = iter.next();	
			
				for (int z=0; z<bk.length; z++) {
			
				System.out.println(bk[z].getAuthor());
				System.out.println(bk[z].getBooks());
				System.out.println(bk[z].getHobbies());
				}
			}
			
			return output;
		}
		
		
		// Zweites Modell einlesen
		public static <A> String readJsonTreeModel (String filename) throws IOException {
			JsonNode node = mapper.readTree(new File(filename));
			String output = JsonMapper.JsonToString(node);
			
			return output;
		}

		public static <A> A readJsonTreeModel (String filename, Class<A> cl) throws IOException {
			
			JsonNode node = mapper.readTree(new File(filename));
			A root = mapper.treeToValue(node, cl);
			
			return root;
			
		}
		
}