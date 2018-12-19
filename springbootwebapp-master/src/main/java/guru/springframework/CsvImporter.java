package guru.springframework;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import guru.springframework.domain.ExcelSheetData;


@Component
public class CsvImporter {

	public List<ExcelSheetData> importDataFromExcel(String filePath) throws IOException{
		Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL);
        List<ExcelSheetData> ls= new ArrayList<>();
        int i=0;
        for (CSVRecord csvRecord : csvParser) {
        	if(i==0){
        		i++;
        		continue; 
        	}
            // Accessing Values by Column Index
        	ExcelSheetData e= new ExcelSheetData();
        	e.setAccountID(csvRecord.get(0));
        	e.setAccountName(csvRecord.get(1));
        	e.setWebsite(csvRecord.get(2));
        	e.setFirstName(csvRecord.get(6));
        	e.setLastName(csvRecord.get(7));
        	e.setTitle(csvRecord.get(8));
        	e.setEmail(csvRecord.get(9));
        	e.setPhone(csvRecord.get(10));
        	e.setAlternativePhon(csvRecord.get(11));
        	e.setLinkedInProfile(csvRecord.get(12));
        	
        	ls.add(e);
        	i++;
        }
        
        System.out.println("Data import successfull");
        return ls;
	}
}
