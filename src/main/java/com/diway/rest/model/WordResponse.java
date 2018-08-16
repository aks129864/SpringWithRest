package com.diway.rest.model;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WordResponse {

	@JsonIgnore
	private Map<String,Integer> wordMap;
	
	private Set<Map.Entry<String,Integer>> counts;

	public Map<String, Integer> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<String, Integer> wordMap) {
		this.wordMap = wordMap;
		this.counts = wordMap.entrySet();
	}

	public Set<Map.Entry<String, Integer>> getCounts() {
		return counts;
	}

	
	
}
