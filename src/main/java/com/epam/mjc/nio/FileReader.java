package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
    	Profile profile = new Profile();
    	Path path = file.toPath();    	
    	try(BufferedReader br = Files.newBufferedReader(path)){
    		List<String> lines = fetchLines(br);
    		profile = constructProfile(lines);
    	} catch (IOException e) {
			e.printStackTrace();
		}    	
        return profile;
    }
    
    private List<String> fetchLines(BufferedReader br) throws IOException{
    	List<String> lines = new ArrayList<>();
    	while(br.ready()) {
    		String theLine = br.readLine();
    		lines.add(theLine);
    	}		
    	return lines;
    }
    
    private Profile constructProfile(List<String> lines) {
    	Profile profile = new Profile();
    	for (int i = 0; i < lines.size(); i++) {
    		String[] splitted = splitLine(lines.get(i));
    		if (splitted[0].equalsIgnoreCase("name")) {
    			profile.setName(splitted[1]);
			}
    		if (splitted[0].equalsIgnoreCase("age")) {
    			profile.setAge(Integer.parseInt(splitted[1]));
			}
    		if (splitted[0].equalsIgnoreCase("email")) {
    			profile.setEmail(splitted[1]);
			}
    		if (splitted[0].equalsIgnoreCase("phone")) {
    			profile.setPhone(Long.parseLong(splitted[1]));
			}
		}    	
    	return profile;
    }
    
    
    private String[] splitLine(String str) {
    	return str.split(": ");
    }
    
    
    
    
    
    
    
}
