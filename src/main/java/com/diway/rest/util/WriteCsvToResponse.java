package com.diway.rest.util;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.diway.rest.model.Word;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class WriteCsvToResponse {

	@Autowired
	public static void writeTopWordResponse(PrintWriter writer, List<Word> wList) {
		try {

            ColumnPositionMappingStrategy mapStrategy = new ColumnPositionMappingStrategy();
            mapStrategy.setType(Word.class);
            mapStrategy.generateHeader();

            String[] columns = new String[]{"wordName", "count	"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator('|')
                    .build();

            btcsv.write(wList);

        } catch (Exception ex) {

            ex.getMessage();
        }
		
	}


	
}
