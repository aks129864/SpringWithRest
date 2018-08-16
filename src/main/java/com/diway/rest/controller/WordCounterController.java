package com.diway.rest.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diway.rest.model.Word;
import com.diway.rest.model.WordRequest;
import com.diway.rest.model.WordResponse;
import com.diway.rest.service.WordCountService;
import com.diway.rest.util.WriteCsvToResponse;

@RestController
@RequestMapping("/counter-api")
public class WordCounterController {
	
    @Autowired
    WordCountService wordCountService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST,produces = {"application/JSON"},consumes = {"application/JSON"})
	public ResponseEntity<WordResponse> countWords(@RequestBody WordRequest wr) throws IOException {
		
		WordResponse wRes = wordCountService.queryForWords(wr);

		return new ResponseEntity<WordResponse>(wRes, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/top/{value}", method = RequestMethod.GET,produces = {"text/csv"})
	public void topWords(@PathVariable Integer value,HttpServletResponse response) throws IOException, URISyntaxException {

		List<Word> wList  = wordCountService.queryForTopWords(value);
		
		WriteCsvToResponse.writeTopWordResponse(response.getWriter(), wList);
		
	}
}
