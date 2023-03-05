package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
    	Profile profile = new Profile();
    	try {
			Path path = file.toPath();
			List<String> lines = Files.readAllLines(path);
			profile = constructProfile(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        return profile;
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
