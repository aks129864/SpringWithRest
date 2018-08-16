package com.diway.rest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.diway.rest.model.Word;
import com.diway.rest.model.WordRequest;
import com.diway.rest.model.WordResponse;

@Service
public class WordCountService {


	//@Autowired
    //WordResponse wordResponse;
		
	public WordResponse queryForWords(WordRequest wordReq) throws IOException {
		
		List<String> countList = new ArrayList<String>();
		countList = wordReq.getSearchText();
		
		WordResponse wordResponse = new WordResponse();
		HashMap<String,Integer> countMap = new HashMap<String,Integer>();
		File file = ResourceUtils.getFile("classpath:file/sample.txt");
				
		for (String searchWord : countList) {
			Scanner scanner = new Scanner(file).useDelimiter(",|\\s|\\.");
			int counter = 0;
			String line;
		    while (scanner.hasNext())
		    {
		    	line = scanner.next();
		    	if(line.equalsIgnoreCase(searchWord)) {
		    		counter++;
		    	}
		    }  	    
		    countMap.put(searchWord, counter);
		    scanner.close();
		}		
		wordResponse.setWordMap(countMap);
		return wordResponse;
	}

	public List<Word> queryForTopWords(int value) throws FileNotFoundException{
		HashMap<String,Integer> topWordsMap = new HashMap<String,Integer>();
		File file = ResourceUtils.getFile("classpath:file/sample.txt");
		Scanner scanner = new Scanner(file).useDelimiter(",|\\s|\\.");
		String line;
		while (scanner.hasNext())
	    {
	    	line = scanner.next().toLowerCase();
	    	//System.out.println(line);
	    	if(topWordsMap.containsKey(line)) {
	    		int count = topWordsMap.get(line);
	    		topWordsMap.put(line, count+1);
	    	}
	    	else {
	    		topWordsMap.put(line, 1);
	    	}
	    }  
		
		if(topWordsMap.containsKey(""))
			topWordsMap.remove("");
		
		Map<String,Integer> resultMap = topWordsMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(value)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(v1,v2)-> v1,
						LinkedHashMap::new));

		
		List<Word> wordList = resultMap.entrySet().stream()
				.map(m-> new Word(m.getKey(),m.getValue()))
				.collect(Collectors.toList());
		
		return wordList;

	}
	
}
