import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
/*******************************************************
 *  Class name: GetSchool_Info
 *  @author Vishal Patil
 *  @version 1.0
 *  @since 2016-06-08
 *  Methods:
 *  	1)GetSchool_Info
 *  	2)getSchools
 *  @Functionality To extract school details from CBSE website. 
 *  @Visibility Public
 ********************************************************/


public class GetSchool_Info 
{
	WebDriver driver;
	
	/*********************************************************
	 *  Method name: GetSchool_Info
	 *  @Functionality To initialize Firefox driver.
	 *  @Visibility Public
	 *********************************************************/
	public GetSchool_Info()
	{
		
	}
	
	
	/*********************************************************
	 *  Method name: getSchools
	 *  @Functionality To extract information from CBSE site.
	 *  @Visibility Public
	 *********************************************************/
	@Test
	public void getSchools()
	{
		String state ;
		String keyword;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter state name");
		state = sc.next();
		System.out.println("Enter keyword");
		keyword = sc.next();
		
		driver = new FirefoxDriver();
		driver.get("http://cbseaff.nic.in/cbse_aff/schdir_Report/userview.aspx");
		driver.findElement(By.xpath("//*[@id='optlist_2']")).click();
		Select st = new Select(driver.findElement(By.xpath("//*[@id='ddlitem']")));
		try
		{
			st.selectByVisibleText(state.toUpperCase());
			//st.selectByValue("10");
		}
		catch(Exception e)
		{
			System.out.println("Please enter correct state. State is not matching with the states available in dropdown.");
			System.exit(0);
		}
		driver.findElement(By.xpath("//*[@id='keytext']")).sendKeys(keyword);
		driver.findElement(By.xpath("//*[@id='search']")).click();
		int schoolcount = 0;
		try
		{
			schoolcount = Integer.parseInt((driver.findElement(By.xpath("//*[@id='tot']")).getText()));
		}
		catch(Exception e)
		{
			System.out.println("No records found for this keyword");
			System.exit(0);
		}
			
		if(schoolcount == 0)
		{
			System.out.println("No schools are present with your given inputs");
			System.exit(0);
		}
		System.out.println(schoolcount);
		int loop = schoolcount/25;
		GenerateReport.addTitle(state, keyword);
		int count = 1;
		for(int i = 0; i <= loop; i++)
		{
			for(int j = 2;j <= 26;j++ )
			{
				String one = "";
				String two = "";
				try
				{
					
					one = driver.findElement(By.xpath("//*[@id='T1']/tbody/tr/td/table["+j+"]/tbody/tr/td[2]")).getText();
					two = driver.findElement(By.xpath("//*[@id='T1']/tbody/tr/td/table["+j+"]/tbody/tr/td[3]")).getText();
					System.out.println("One---"+one+"\n\n");
					System.out.println("Two---"+two+"\n\n");
				}
				catch(Exception e)
				{
					System.out.println("School names are not displayed with your given inputs/End of the school list");
					System.exit(0);
				}
				
				String arr[] = one.split("\n");
				String name[] = arr[1].split(":");
				String Name = name[1];
				
				
				String arr1[] = two.split("\n"); 
				String temp1[] = arr1[0].split(":");
				String Address = temp1[1];
				
				temp1 = null;
				temp1 = arr1[1].split(":");
				String Phone_No = temp1[1];
				
				temp1 = null; 
				temp1 = arr1[2].split(":");
				String Email = "";
				try
				{
					Email = temp1[1];
				}
				catch(Exception e)
				{
					Email = "Not available";
				}
						
				System.out.println(one);
				System.out.println(two);
				System.out.println("\n\n");
				System.out.println(count);
				GenerateReport.addData(count, Name, Phone_No, Email, Address);
				count++;
			}
			driver.findElement(By.xpath("//*[@id='Button1']")).click();
		}	
	}
}
