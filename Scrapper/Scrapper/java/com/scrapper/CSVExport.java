package com.scrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.scrapper.bean.Company;
import com.scrapper.bean.Person;

@Component
public class CSVExport {

	public void exportPerson(List<Person> pls, String[] linkedin) {
	      CSVPrinter csvPrinter=null;
		try {
			 BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:/personProfile.csv"));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("Name", "Location","Current Company", "Headline" ,"Previous Company","Previous Role","Linkedin","Dublicat"));
			 if(pls.size() == linkedin.length ){
			      for(int i=0;i<pls.size();i++){
			    	  Person p=pls.get(i);
			    	  if( p.getExperiences()!=null && p.getExperiences().getJobs().size()>0){
			    	   csvPrinter.printRecord(p.getPersonal_info().getName(),p.getPersonal_info().getLocation(),
			    			   p.getPersonal_info().getCompany(),p.getPersonal_info().getHeadline(),
			    			   p.getExperiences().getJobs().get(0).getCompany(),p.getExperiences().getJobs().get(0).getTitle(),linkedin[i],p.getPersonal_info().isPresent());
			    	  }else{
			    		  csvPrinter.printRecord(p.getPersonal_info().getName(),p.getPersonal_info().getLocation(),
				    			   p.getPersonal_info().getCompany(),p.getPersonal_info().getHeadline(),"","","","");
			    	  }
			      }
			 }else{
				 for( Person p: pls){
			    	  if( p.getExperiences()!=null && p.getExperiences().getJobs().size()>0){
			    	   csvPrinter.printRecord(p.getPersonal_info().getName(),p.getPersonal_info().getLocation(),
			    			   p.getPersonal_info().getCompany(),p.getPersonal_info().getHeadline(),
			    			   p.getExperiences().getJobs().get(0).getCompany(),p.getExperiences().getJobs().get(0).getTitle(),"",p.getPersonal_info().isPresent());
			    	  }else{
			    		  csvPrinter.printRecord(p.getPersonal_info().getName(),p.getPersonal_info().getLocation(),
				    			   p.getPersonal_info().getCompany(),p.getPersonal_info().getHeadline(),"","","","");
			    	  }
			      }
			 }
	      System.out.println("Export Complete");
	      csvPrinter.flush();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(csvPrinter!=null)
					csvPrinter.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void exportCompany(List<Company> cmpProfil){

	      CSVPrinter csvPrinter=null;
		try {
			  BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:/companyProfile.csv"));
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
			            .withHeader("Name", "Industry","Size", "Website","Street","City","State","Zip"));
	      
	      for(Company cmp:cmpProfil){
	    	   csvPrinter.printRecord(cmp.getName(),cmp.getIndustry(),cmp.getSize() ,cmp.getWebsite(),
	    			   cmp.getAddress().getStreet(),cmp.getAddress().getCity(),cmp.getAddress().getState(),
	    			   cmp.getAddress().getZip());
	      }
	      csvPrinter.flush();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(csvPrinter!=null)
					csvPrinter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
}
