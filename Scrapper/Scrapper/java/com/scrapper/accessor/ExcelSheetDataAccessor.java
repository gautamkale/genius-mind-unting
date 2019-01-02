package com.scrapper.accessor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scrapper.bean.ExcelSheetData;
import com.scrapper.repository.ExcelSheetDataRepo;

@Component
public class ExcelSheetDataAccessor {
	
	@Autowired
	public ExcelSheetDataRepo excelRepo;
	
	public void saveExcelData(List<ExcelSheetData> excel){
		
		for(ExcelSheetData e:excel){
			try{
				excelRepo.save(e);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println("Data Saved succssful");
	}
	
	public void deleteExcelData(){
		
		excelRepo.deleteAll();
		
		System.out.println("Data deleted succssful");
	}
	
	public ExcelSheetData getExcelSheetData(String name){
		String[] str= name.split(" ");
		System.out.println(name +" ------------------------------------");
/*		ExcelSheetData d= new ExcelSheetData();
		d.setFirstName(str[1]);
		d.setLastName(str[0]);*/
		ExcelSheetData rs= (ExcelSheetData) excelRepo.findByLastNameAndFirstName(str[1],str[0]);
		
		System.out.println(rs+"  retrived");
		return rs;
		
	}
}
