package com.scrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParseException;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.scrapper.accessor.CrowdGeniusAutomate;
import com.scrapper.accessor.ExcelSheetDataAccessor;
import com.scrapper.bean.ExcelSheetData;
import com.scrapper.bean.Person;
import com.scrapper.controller.LinkedinScrapper;

@SpringBootApplication
public class ScrapperApplication {
 
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ApplicationContext ctx=SpringApplication.run(ScrapperApplication.class, args);
		CsvImporter c= ctx.getBean(CsvImporter.class);
		JsonMapper mapper= ctx.getBean(JsonMapper.class);
		CSVExport export= ctx.getBean(CSVExport.class);
		CrowdGeniusAutomate cw=ctx.getBean(CrowdGeniusAutomate.class);
		LinkedinScrapper lscrapper= ctx.getBean(LinkedinScrapper.class);
		ExcelSheetDataAccessor acc= ctx.getBean(ExcelSheetDataAccessor.class);
		try {
			
			List<ExcelSheetData> l=new ArrayList<>();// c.importDataFromExcel("C:/personProfile.csv");
			/*acc.deleteExcelData();
			acc.saveExcelData(l);*/
		//	List<Person> ls = mapper.mapEmpJson("B:/leadgenius/LinkedinScrapper-master/LinkedinScrapper-master/profileScraped.json");
		//	List<String> linkedin = mapper.mapProfileJson("B:/leadgenius/LinkedinScrapper-master/LinkedinScrapper-master/Profile.json");
			//export.exportPerson(ls,linkedin);
			String[] str= {"whyteaa@jncb.com",
					"whyteab@jncb.com",
					
					"whyteaz@jncb.com"};
			for(int i=0;i<str.length;i++)
			{
				ExcelSheetData e=new ExcelSheetData();
				e.setEmail(str[i]);
				l.add(e);
			}
			ChromeDriver driver= lscrapper.Login();
			String[] linkedin = { "https://www.linkedin.com/in/neilplaistowe/?originalSubdomain=uk                  ",
					"https://www.linkedin.com/in/kaaren-bernsen-9934894/?originalSubdomain=uk         ",
					"https://www.linkedin.com/in/ged-chadwick-80306911/?originalSubdomain=uk          ",
					"https://www.linkedin.com/in/alexeysinyugin/?trk=seokp-title-professional-name    ",
					"https://www.linkedin.com/in/eugenegavrilov/                                      " };
			List<Person> p=lscrapper.scrapProfile(driver,linkedin);
			export.exportPerson(p,linkedin);
			//cw.setLeadLs(l);
			//cw.runScript();
			
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
