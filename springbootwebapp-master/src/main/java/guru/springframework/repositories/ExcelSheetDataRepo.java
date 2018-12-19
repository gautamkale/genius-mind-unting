package guru.springframework.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.ExcelSheetData;


@Transactional
public interface ExcelSheetDataRepo extends JpaRepository<ExcelSheetData, String> {
	
	//List<ExcelSheetData> findByLastNameAndFirstName(ExcelSheetData excelSheetData);

	ExcelSheetData findByLastNameAndFirstName(String LastName, String FirstName); 
}