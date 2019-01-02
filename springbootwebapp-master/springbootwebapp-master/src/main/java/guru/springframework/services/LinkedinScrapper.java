package guru.springframework.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Education;
import guru.springframework.domain.Experince;
import guru.springframework.domain.Job;
import guru.springframework.domain.Person;
import guru.springframework.domain.PersonInfo;



@Component
public class LinkedinScrapper {
	
	public List<Person> Login(String filename){
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get("https://www.linkedin.com/");
		/*Cookie ck = new Cookie("li_at", "AQEDASlFklsDHENYAAABZ-4aMdYAAAFoEia11lYAawwC9_4s8bARS603D2NT2OdxxGI9Clp-QAY1xaK7LLTVVv1cE4bhDJiIOw3bLTEVr_MLn5qR1qhlo6Jb8JANORVAR6OtSKaNaxLF-iBiLEajz1wl");
		driver.manage().addCookie(ck);*/
		driver.findElementById("login-email").sendKeys("jimmy16black@gmail.com");
		driver.findElementById("login-password").sendKeys("samyak2013");
		driver.findElementById("login-submit").click();
		List<Person> ls=scrapProfile(driver,filename);
		driver.close();
		return ls;
	}

	public List<Person> scrapProfile(ChromeDriver driver, String linkedin) {
		List<Person> ls= new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(linkedin))) {

			Iterator<String> it= stream.iterator();
			while(it.hasNext()){
				try{
					driver.get(it.next());
					WebDriverWait wait = new WebDriverWait(driver, 20);
					WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pv-top-card-section")));
					scrollDown(driver,wait);
					String profile =driver.findElementById("profile-wrapper").getAttribute("outerHTML");
					Document doc = Jsoup.parse(profile);
					Person p = new Person(); 
					p.setPersonal_info(getPersoInfo(doc));
					p.setExperiences(getExperince(doc));
					ls.add(p);
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		return ls;
		
	}

	private void scrollDown(ChromeDriver driver, WebDriverWait wait) {
		String[] expandableButtonSelectors ={"button[aria-expanded=\"false\"].pv-skills-section__additional-skills",
	            "button[aria-expanded=\"false\"].pv-profile-section__see-more-inline",
	            "button[aria-expanded=\"false\"].pv-top-card-section__summary-toggle-button",
	            "button[data-control-name=\"contact_see_more\"]"};
		double currentHeight=0,scroll_increment=300;
		double scroll_pause=0.1;
		while(true){
				for(String button: expandableButtonSelectors){
					//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(button)));
					try {
					driver.findElementByCssSelector(button).click();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						continue;
					}
				}
				double temp =currentHeight + scroll_increment ;
				long newHight= (long) driver.executeScript(
	                "return Math.min("+temp +", document.body.scrollHeight)");
				if(newHight == currentHeight){
					break;
				}
				driver.executeScript("return Math.min("+ newHight +", document.body.scrollHeight)");
				currentHeight= newHight;
				try {
					Thread.sleep((long) scroll_pause);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	private Experince getExperince(Document doc) {
		Experince ep= new Experince();
		Element e= doc.select(".background-section").first();
			List<Job> job= collectJobs(doc);
			ep.setJobs(job);
	       List<Education> edu= collectEducation(doc);
	       ep.setEducation(edu);
		return ep;
	}

	private List<Education> collectEducation(Document doc) {
		Elements schools= doc.select("#education-section .pv-education-entity");
	/*	schools.stream().map(ex-> new Education(ex.getElementsByClass(".pv-entity__school-name").text(),
				ex.getElementsByClass(".pv-entity__degree-name span:nth-of-type(2)").text(),
				ex.getElementsByClass(".pv-entity__grade span:nth-of-type(2)").text(),
				ex.getElementsByClass(".pv-entity__fos span:nth-of-type(2)").text(),
				ex.getElementsByClass(".pv-entity__dates span:nth-of-type(2)").text()
    		   )).collect(Collectors.toList());*/
		for(Element e:schools){
			Education ed=	new Education();
			ed.setName(e.getElementsByClass("pv-entity__school-name").text());
			Elements temp=e.getElementsByClass("pv-entity__degree-name");
			if(temp.size()>0){
				ed.setDegree(temp.first().getElementsByTag("span").get(1).text());
			}
			 temp=e.getElementsByClass("pv-entity__grade");
				if(temp.size()>0){
					ed.setGrades(temp.first().getElementsByTag("span").get(1).text());
				}
			temp = e.getElementsByClass("pv-entity__grade");
			if (temp.size() > 0) {
				ed.setGrades(temp.first().getElementsByTag("span").get(1).text());
			}
			temp = e.getElementsByClass("pv-entity__fos");
			if (temp.size() > 0) {
				ed.setField_of_study(temp.first().getElementsByTag("span").get(1).text());
			}
			temp = e.getElementsByClass("pv-entity__dates");
			if (temp.size() > 0) {
				ed.setDate_range(temp.first().getElementsByTag("span").get(1).text());
			}
		}
	
		return null;
	}

	private List<Job> collectJobs(Document doc) {
		Elements jobs =doc.select("#experience-section ul .pv-position-entity");
		List<Job> multiplePositions=new ArrayList<>();
			for(Element job:jobs){
				Job b= new Job();
				Elements t =job.getElementsByClass("pv-entity__company-summary-info");
				if(t.size()>0){ 
					b.setCompany(t.first().getElementsByTag("h3").first().getElementsByTag(" span").get(1).text());
				}
				Elements multiplePosition=job.getElementsByClass("pv-entity__role-details-container");
				if(multiplePosition.size()>0){
					Elements temp=job.getElementsByClass("pv-entity__summary-info-v2");
					if(temp.size()>0){
						b.setTitle(temp.first().getElementsByTag("h3").first().getElementsByTag(" span").get(1).text());
					}
					
					temp=job.getElementsByClass("pv-entity__date-range");
					if(temp.size()>0){
						b.setDateRange(temp.first().getElementsByTag(" span").get(1).text());
					}
							
					temp=job.getElementsByClass("pv-entity__location");
					if(temp.size()>0){
						b.setLocation(temp.first().getElementsByTag(" span").get(1).text());
					}
						
					
				}else{
					Elements temp=job.getElementsByClass("pv-entity__summary-info");
					if(temp.size()>0){
						b.setTitle(temp.first().getElementsByTag("h3").first().text());
					}
					
					b.setCompany(job.getElementsByClass("pv-entity__secondary-title").text());
					/*if(temp.size()>0){
						temp.first().getElementsByTag("h3").first().getElementsByTag(" span").get(1).text();
					}*/
							
					temp=job.getElementsByClass("pv-entity__date-range");
					if(temp.size()>0){
						b.setDateRange(temp.first().getElementsByTag(" span").get(1).text());
					}
							
					temp=job.getElementsByClass("pv-entity__location");
					if(temp.size()>0){
						b.setLocation(temp.first().getElementsByTag(" span").get(1).text());
					}
				}
			
				multiplePositions.add(b);
						
			}
			
		return multiplePositions;
	}

	private PersonInfo getPersoInfo(Document doc) {
		PersonInfo p= new PersonInfo();
		p.setName(doc.select(".pv-top-card-section__name").text());
		p.setHeadline( doc.select(".pv-top-card-section__headline").text());
		p.setLocation(doc.select(".pv-top-card-section__location").text());
		p.setCompany(doc.select(".pv-top-card-v2-section__company-name").text());
		return p;
	}

}
