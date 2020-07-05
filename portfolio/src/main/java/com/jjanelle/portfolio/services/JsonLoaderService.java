package com.jjanelle.portfolio.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjanelle.portfolio.models.Project;
import java.io.InputStream;

@Service
public class JsonLoaderService {

	private final ObjectMapper jsonMapper;
	
	
	JsonLoaderService() {
		this.jsonMapper = new ObjectMapper();
	}

    public List<Project> readProjectsFile(String fileName) throws IOException {
    	InputStream file = new ClassPathResource(fileName).getInputStream();
        return jsonMapper.readValue(file, new TypeReference<List<Project>>(){});
    }
}