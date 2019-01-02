package com.scrapper.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrapper.bean.ExcelSheetData;

@Transactional
public interface ExcelSheetDataRepo extends JpaRepository<ExcelSheetData, String> {
	
	//List<ExcelSheetData> findByLastNameAndFirstName(ExcelSheetData excelSheetData);

	ExcelSheetData findByLastNameAndFirstName(String LastName, String FirstName); 
}