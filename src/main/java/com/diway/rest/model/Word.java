package com.diway.rest.model;

public class Word {
	
	private int count;
	private String wordName;
	
	public Word() {
		super();
	}
	
	public Word(String wordName, int count) {
		super();
		this.count = count;
		this.wordName = wordName;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getWordName() {
		return wordName;
	}
	public void setWordName(String wordName) {
		this.wordName = wordName;
	}
}
