package com.scrapper.accessor;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.scrapper.bean.EmailValidationDetails;
import com.scrapper.bean.ExcelSheetData;

@Component
public class CrowdGeniusAutomate {
	
	public List<ExcelSheetData> leadLs;

	public List<ExcelSheetData> getLeadLs() {
		return leadLs;
	}

	public void setLeadLs(List<ExcelSheetData> leadLs) {
		this.leadLs = leadLs;
	}

	public  void runScript() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();

		options.addExtensions(new File("B:/leadgenius/CrowdGenius_v18.9.3.crx"));

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("pageLoadStrategy", "none");

		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		loadCrowdGeniusApp(driver,wait);
		
		Thread.sleep(20000);
		List<ExcelSheetData> l=getLeadLs();
		Set<ExcelSheetData> ls= new LinkedHashSet<>();
		List<ExcelSheetData> temp=l.stream().filter(t->!t.isValidationDone()).limit(10).collect(Collectors.toList());
		while(temp.size()>0){
			filledUpValidationData(driver,wait,temp);
			//extractTable(driver,wait,temp);
			ls.addAll(temp);
			temp=l.stream().filter(t->!t.isValidationDone()).limit(10).collect(Collectors.toList());
			System.out.println(temp.size()+" ---------size "+ ls);
		}
	}

	private static void loadCrowdGeniusApp(ChromeDriver driver, WebDriverWait wait) {

		driver.get("chrome-extension://jebmonoolbdbdnhfekcgegnaamniigpl/app.html#/login");
		By addItem = By.name("email");
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addItem));
		element.sendKeys("gautam16kale@gmail.com");
		driver.findElementByName("password").sendKeys("samyak2013");
		driver.findElementByXPath("//*[@id='main-content']/div/form/div/button").click();
	}

	private  void filledUpValidationData(ChromeDriver driver, WebDriverWait wait, List<ExcelSheetData> temp) {
		
		By addItem = By.xpath("//a[contains(@href,'#/email-validate')]");
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addItem));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		int count=0;
		if(temp.size()<10){
			count=temp.size();
		}else{
			count=10;
		}
		for (int i = 0; i < count; i++) {
			if (i > 4) {
				driver.findElementByXPath("//*[@id='main-content']/div/form/div[1]/button").click();
			}
			driver.findElementByName("email" + i).sendKeys(temp.get(i).getEmail());
			temp.get(i).setValidationDone(true);
		}
		driver.findElementByXPath("//*[@id='main-content']/div/form/div[2]/button").click();
		/*try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		setLeadLs(temp);
	}

	private  void extractTable(ChromeDriver driver, WebDriverWait wait, List<ExcelSheetData> temp) {
		WebElement table_element = driver.findElement(By.xpath("//*[@id='main-content']/div/table"));
		List<WebElement> tr_collection = table_element
				.findElements(By.xpath("//*[@id='main-content']/div/table/tbody/tr"));

		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
		int row_num, col_num;
		row_num = 1;
		tr_collection=tr_collection.stream().limit(temp.size()).collect(Collectors.toList());
		for (WebElement trElement : tr_collection) {
			EmailValidationDetails emailValid= new EmailValidationDetails();
			List<WebElement> td_collection = trElement.findElements(By.xpath("td")).stream().limit(10).collect(Collectors.toList());
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				String value = "";
				List<WebElement> ls = trElement.findElements(By.xpath(
						"//*[@id='main-content']/div/table/tbody/tr[" + row_num + "]/td[" + col_num + "]/div/a"));
				if (ls.size() > 0) {
					value = ls.get(0).getAttribute("href");
				}
				if (value != null) {
					System.out.println("report link =" + value);
					emailValid.setReport(value);
				}
				if(tdElement.getText().contains("Risk")){
					temp.get(row_num-1).setValidationStatus(false);
				}
				if(col_num ==3){
					String lines[] = tdElement.getText().split("\\r?\\n");
					emailValid.setEmail(lines[0]);
					emailValid.setValidationStatus(lines[2]);
					String indicators[]=lines[1].split(",");
					Map<String,Integer> m= new LinkedHashMap<>();
					for(int k=0;k<indicators.length;k++){
						String s[]=indicators[k].split(":");
						m.put(s[0], Integer.parseInt(s[1].trim()));
					}
					emailValid.setInicator(m);
				}
				if(col_num ==4){
					emailValid.setTimestamp(tdElement.getText());
				}
				//System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
			}
			row_num++;
		}
		
		setLeadLs(temp);
		//filledUpValidationData(driver, wait);

	}

	public static void main(String args[]) {
		/*try {
			runScript();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
 