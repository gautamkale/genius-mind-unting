package guru.springframework;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrapper.repository.ExcelSheetDataRepo;
import com.scrapper.repository.PersonRepository;

import guru.springframework.domain.ExcelSheetData;
import guru.springframework.domain.Person;


@Component
public class JsonMapper {
	@Autowired
	public PersonRepository personRepo;
	
	@Autowired
	public ExcelSheetDataRepo excelRepo;

	public List<Person> mapEmpJson(String filePath) throws JsonParseException, JsonMappingException, IOException{
				List<Person> persons= null;
		        ObjectMapper mapper = new ObjectMapper();
				TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};
				File initialFile = new File(filePath);
			    InputStream inputStream = new FileInputStream(initialFile);
				///InputStream inputStream = TypeReference.class.getResourceAsStream("/json/testJson.json");
				try {
					persons = mapper.readValue(inputStream,typeReference);
					//personRepo.save(persons);
					//System.out.println("Users Saved!");
					
					for(Person p:persons){
						String[] str= p.getPersonal_info().getName().split(" ");
						
						ExcelSheetData ob=excelRepo.findByLastNameAndFirstName(str[1],str[0]);
					//	System.out.println(ob.getFirstName() + "--------------------------------------" +Arrays.toString(str));
						if(ob !=null){
							p.getPersonal_info().setPresent(true);
						}
					}
				} catch (IOException e){
					System.out.println("Unable to save users: " + e.getMessage());
				}
		        return persons;
		  
	}
	
	/*public List<Company> mapCompanyJson(String filePath) throws IOException{
		 JSONArray companyObj = null;//(JSONArray) JSON.parse(new FileReader(filePath));
		 List<Company> cPProfile= new  ArrayList<>();
		 for(Object cmpn:companyObj){
			 Company cp= new Company();
			 JSONObject cmObj=(JSONObject) cmpn;
			 cp.setWebsite((String) cmObj.get("website"));
			 cp.setIndustry((String) cmObj.get("industry"));
			 cp.setName((String) cmObj.get("company_name"));
			 cp.setSize((String) cmObj.get("size"));
			 JSONArray speciality = (JSONArray)cmObj.get("specialities");
			 List<String> specialityLs= new  ArrayList<>();
			 for(Object s:speciality){
				 specialityLs.add((String) s);
			 }
			 cp.setSpecialities(specialityLs);
			 Address addr= new Address();
			 addr.setCity((String) cmObj.get("city"));
			 addr.setState((String) cmObj.get("state"));
			 addr.setStreet((String) cmObj.get("street"));
			 addr.setZip((String) cmObj.get("zip"));
			 cp.setAddress(addr);
			 cPProfile.add(cp);
			// cp.setSpecialities();
		 }
		return cPProfile;
		
	}*/

	}
