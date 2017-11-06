import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*******************************************************
 *  Class name: GenerateReport
 *  @author Vishal Patil
 *  @version 1.0
 *  @since 2016-06-08
 *  Methods:
 *  	1)addTitle
 *  	2)addData
 *  @Functionality To generate the XLST sheet for school details. 
 *  @Visibility Public
 ********************************************************/
public class GenerateReport 
{
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static int rowCount=1;
	static FileOutputStream fileOut;
	static String s = "";
	
	/*********************************************************
	 *  Method name: addTitle
	 *  @Functionality To add the title and column headings to
	 *                 XLST sheet.
	 *  @param title
	 *  	   Name of state.
	 *  
	 *  @param keyword
	 *  	   Keyword name.
	 *  
	 *  @Visibility Public
	 *********************************************************/
	public static void addTitle(String title, String keyword)
	{
		 try 
		 {
			 
			 workbook = new HSSFWorkbook();
			 sheet = workbook.createSheet("Schools "+keyword+"");
			
			 HSSFRow row = sheet.createRow(0);
			 row.createCell(0).setCellValue("Sr.No.");
			 row.createCell(1).setCellValue("School Name");
			 row.createCell(2).setCellValue("Email Address");
			 row.createCell(3).setCellValue("Phone Number");
			 row.createCell(4).setCellValue("Address");
			 rowCount = 1;
			// fis.close();
			 //
			 fileOut = new FileOutputStream(""+keyword+".xls");
			 workbook.write(fileOut);
	         fileOut.close();
	         s = keyword;
	         System.out.println("Your excel file has been generated!");
         } 
		 catch (Exception e) 
		 {
 			e.printStackTrace();
 		 }
	}
	
	/*********************************************************
	 *  Method name: addData
	 *  @Functionality To add the school details to
	 *                 XLST sheet.
	 *  @param srNo
	 *  	   Serial number for school.
	 *  
	 *  @param schoolName
	 *  	   Name of school.
	 *  @param Email
	 *  	   Email ID of school.
	 *  @param Phone_No
	 *         Phone number of school.
	 *  @param Address
	 *         Address of school.     
	 *  @Visibility Public
	 *********************************************************/
	public static void addData(int srNo, String schoolName, String Email, String Phone_No, String Address)
	{
		try 
		{
			HSSFRow rowhead = sheet.createRow(rowCount);
	        rowhead.createCell(0).setCellValue(srNo);
	        rowhead.createCell(1).setCellValue(schoolName);
	        rowhead.createCell(2).setCellValue(Phone_No);
	        rowhead.createCell(3).setCellValue(Email);
	        rowhead.createCell(4).setCellValue(Address);
	      //  rowhead.setHeight((short)700);
	        /*rowhead.createCell(3).setCellValue(ExpectedOutput);
	        rowhead.createCell(4).setCellValue(ActualOutput); 
	        rowhead.createCell(5).setCellValue(Status);*/
	        for (int i = 0; i<5; i++)
	        {	
	          	sheet.autoSizeColumn(i);
	          
	        }
	        HSSFCellStyle style = workbook.createCellStyle();
            style.setWrapText(true);
	        FileOutputStream fileOut = new FileOutputStream(""+s+".xls");
	        workbook.write(fileOut);
	        fileOut.close();
	        rowCount++;
	            
	            
	    }
		catch (Exception ex ) 
		{
			System.out.println(ex);
	    }
	}
}
