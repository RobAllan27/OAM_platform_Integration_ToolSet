package MoogsoftDataLoader;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelFileReader {

	private ArrayList<Integer> myCIsFieldSizes;
	private ArrayList<Integer> myCIRsFieldSizes;
	private ArrayList<Integer> myUserFieldSizes;
	private ArrayList<Integer> myCompanyFieldSizes;
	private MoogsoftDataLoader myMDL;
	
	public ExcelFileReader(MoogsoftDataLoader inMDL){		
			myCIsFieldSizes = new ArrayList<Integer>();
			buildmyCIsFieldSizes();
			myCIRsFieldSizes = new ArrayList<Integer>();
			buildmyCIRsFieldSizes();
			myUserFieldSizes = new ArrayList<Integer>();
			buildmyUserFieldSizes();
			myCompanyFieldSizes = new ArrayList<Integer>();
			buildmyCompanyFieldSizes();
			myMDL = inMDL;
	}
	
	private void buildmyCIsFieldSizes(){	
		//myCIsFieldSizes = new HashMap<Integer>();
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("64"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("45")); //line_class_code
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("120"));        
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120")); // managed_by_group 
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("240"));
		myCIsFieldSizes.add(Integer.parseInt("45")); //network_address         
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("45"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("45")); //priority                
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		myCIsFieldSizes.add(Integer.parseInt("120"));
		}
	
	private void buildmyCIRsFieldSizes(){
		//myCIRsFieldSizes = new HashMap<Integer>();
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
		myCIRsFieldSizes.add(Integer.parseInt("120"));
	}
	
	private void buildmyUserFieldSizes(){
		//myUserFieldSizes = new HashMap<Integer>();
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
		myUserFieldSizes.add(Integer.parseInt("120"));
	}
	
	private void buildmyCompanyFieldSizes(){
		//myCompanyFieldSizes.add(new Integer(1), New Integer 1);
		myCompanyFieldSizes.add(Integer.parseInt("120"));
		myCompanyFieldSizes.add(Integer.parseInt("120"));
		myCompanyFieldSizes.add(Integer.parseInt("120"));
		myCompanyFieldSizes.add(Integer.parseInt("45"));
		myCompanyFieldSizes.add(Integer.parseInt("45"));
	}
	
	public void parseAllFile(){	
		
		
		String excelCIFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\...\\Test Data\\Data Extract 13-10-2017\\1_cmdb_ci_13.xlsx";
		String excelCIRsFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\...\\Test Data\\Data Extract 13-10-2017\\3_cmdb_ci_rel_8.xlsx";
		String excelCompaniesFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\...\\Test Data\\Data Extract 13-10-2017\\2_core_company_8.xlsx";
		//String excelUsersFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\....\\Test Data\\Data Extract 13-10-2017\\small sample data\\";
		
		//"C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\...\\Test Data\\Data Extract 14-08-17\\small sample data\\";

		//parseFile(excelCIFilePath,"CIs");
		parseFile(excelCompaniesFilePath,"Companies");
		//parseFile(excelCIRsFilePath,"CIRs");
		//parseFile(excelUsersFilePath,"Users");	
	}
	
	private void parseFile(String excelFilePath, String methodName){
		
		try {
			//String excelCIFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\....\\Automation\\alarmstotrigger.xlsx";

			// ""C:\\test\\filename.txt"";
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(inputStream);
			//Thread.sleep(1000);
			System.out.println("Hello building the CIs excel chart");

			// Iterator<Row> iterator = firstSheet. .iterator();
			int sheetcounts = workbook.getNumberOfSheets();
			//String methodName = "CIs"; 
			int rowCounter = 1;
			int fieldCounter = 1;
			
			// Only need one of these
			DataFormatter fmt = new DataFormatter();
			

			// Once per cell
			//String valueAsSeenInExcel = fmt.formatCellValue(cell);
			
			for (int i = 0; i < sheetcounts; i++) {
				
				ArrayList<String> myCISetofValues = new ArrayList<String>();
				Iterator<Row> rowIterator = workbook.getSheet("datatoload").iterator();

				// let's gets the column values
				Row firstRow = rowIterator.next();
			
				checkRowTitles(firstRow,methodName);
				
				Row valueRow;

				Iterator<Cell> cellIterator = firstRow.cellIterator();

				StringBuilder fieldParamVal = new StringBuilder("");

				while (rowIterator.hasNext()) {
					myCISetofValues.clear();
					valueRow = rowIterator.next();
					cellIterator = valueRow.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();						
						fieldParamVal.setLength(0);
						fieldParamVal.append(fmt.formatCellValue(cell));
						//myCISetofValues.add(fieldParamVal.toString());
						
					    //if (HSSFDateUtil.isCellDateFormatted(cell)) 
					    
						if ((fieldCounter == 11) || (fieldCounter == 14) || (fieldCounter == 15) || (fieldCounter == 26) || (fieldCounter == 27))				
					    {
					        //System.out.println (fieldParamVal.toString());
					        if  (!(fieldParamVal.toString().equals("NULL"))){
					        	// we have a valid date to format
					        	//System.out.println (fieldParamVal.toString());
					            SimpleDateFormat informatter = new SimpleDateFormat("MM/dd/yy");

					            Date date = informatter.parse(fieldParamVal.toString());

					            //converting date format for US
					            SimpleDateFormat sdfAustralian = new SimpleDateFormat("dd/MM/yyyy");

					            String dateInAustralian = sdfAustralian.format(date); // Convert to String first
					            
					            fieldParamVal.setLength(0);
					            fieldParamVal.append(dateInAustralian);
					        }
					    }
						
						/*
						
					    String pattern = "MM/dd/yyyy";
					    SimpleDateFormat format = new SimpleDateFormat(pattern);
					    try {
					      Date date = format.parse("12/31/2006");
					      System.out.println(date);
					    } catch (ParseException e) {
					      e.printStackTrace();
					    }
					    // formatting
					    System.out.println(format.format(new Date()));
					  }
					
					*/
						
						
						
						
						
						
						
						
						
						
						
						
						// we have to set the sensitive to true or false -  incoming data is 0 for false and 1 for true
						if (methodName.equals("Companies") && fieldCounter == 5){
							if (fieldParamVal.toString().startsWith("0")){
								fieldParamVal.setLength(0);
								fieldParamVal.append("false");
							}
							else if (fieldParamVal.toString().startsWith("1")){
								fieldParamVal.setLength(0);
								fieldParamVal.append("true");
							}
							else {
								System.out.println("Hit a bad entry for the sensitivity");
								System.exit(0);
							}
						}
						
						// now check for truncations (which we log) and get the max size
						int maxFieldSize = checkFieldLengthForTruncations(methodName, rowCounter, fieldCounter, fieldParamVal.toString());
						//Now form a possibly truncated value 

						if (maxFieldSize < fieldParamVal.toString().length()){
						// Now add the possibly truncated value
						myCISetofValues.add(fieldParamVal.substring(0, maxFieldSize-1));
						}
						else
						{ 
							myCISetofValues.add(fieldParamVal.toString());
						}
						fieldCounter++;
					}
					//System.out.println("About to write to the MDL instance");
					myMDL.writelinetoDBChooser(myCISetofValues, methodName);
					fieldCounter = 1;
					rowCounter++;
					if (rowCounter > 3262){ // typically set to 10000 for a 10k file
						// stop looping at file end
						System.out.println("Finished at row 10000 - went now further - after increment " + rowCounter);
						System.exit(0);
					}
				}
					workbook.close();	
			}
		} catch (Exception io) {
			System.out.println("\n Here is the error message in the excel file parser " + io.getMessage());
			System.exit(0);
		}
	}
	
	private int checkFieldLengthForTruncations(String methodName, int rowCounter, int fieldCounter, String fieldParamVal){
		int maxFieldSize = 0; 

        switch (methodName) {
        case "CIs":  
        	maxFieldSize = myCIsFieldSizes.get(fieldCounter-1);
                 break;
        case "CIRs":  
        	maxFieldSize = myCIRsFieldSizes.get(fieldCounter-1);
                 break;
        case "Users":  
        	maxFieldSize = myUserFieldSizes.get(fieldCounter-1);
                 break;
        case "Companies":  
        	maxFieldSize = myCompanyFieldSizes.get(fieldCounter-1);
                 break;
        default: 
        	System.exit(0);
                 break;
        }        
        if (maxFieldSize < fieldParamVal.length()) {    	
        	System.out.println("The field is too large for the database - Method " + methodName + "+++  Row Counter "+ rowCounter + " +++ FieldCounter " + fieldCounter + " +++ field value " + fieldParamVal + " +++ Max Size " + maxFieldSize);
        }
		return maxFieldSize;
	}

	private void checkRowTitles(Row firstRow,String methodName){
		
	// this will work through the headers and make sure that they meet the order for the insert statement. 
		
			String[] expectedColValuesCIs = {"sys_id","Name","install_status","address","sys_class_name","company","company_name","u_contact","contact_name","u_default_gateway","u_end_of_life","u_line_class_code","mac_address","u_maintenance_start_date","u_maintenance_end_date","u_maintenance_type","managed_by","managed_by_name","u_managed_by_group","managed_by_group_name","ip_address","u_host_ci_information","u_location_comments","u_network_address","u_managed_ip_subnet_mask","u_management_start_date","u_management_end_date","Manufacturer","manufacturer_name","model_number","u_part_number","u_priority","u_rack_location","u_security_ci","u_security_classification","serial_number","Vendor","vendor_name","u_version"};
						
			// sys_id	Name	install_status	install_label	u_address	address	sys_class_name	company	company_name	u_contact	contact_name	u_default_gateway	u_end_of_life	u_line_class_code	mac_address	u_maintenance_start_date	u_maintenance_end_date	u_maintenance_type	managed_by	managed_by_name	u_managed_by_group	managed_by_group_name	ip_address	u_host_ci_information	u_location_comments	u_network_address	u_managed_ip_subnet_mask	u_management_start_date	u_management_end_date	Manufacturer	manufacturer_name	model_number	u_part_number	u_priority	u_rack_location	u_security_ci	u_security_classification	serial_number	Vendor	vendor_name	u_version

			/* format of the prepared statement
			
			+ "(ci_id, ci, install_status, address, class_name, company_id, company, contact_id, contact, default_gateway, end_of_life, line_class_code, mac_address, maintenance_start_date, maintenance_end_date, maintenance_type, managed_by_id, managed_by,  managed_by_group_id, managed_by_group, ip_address, host_ci_information, location_comments, network_address, managed_ip_subnet_mask, management_start_date, management_end_date, manufacturer_id, manufacturer, model_number, part_number, priority, rack_location, security_ci, security_classification, serial_number, vendor_id, vendor , version) VALUES"
			
			Name	install_status	u_address	sys_class_name	company	company_name	u_contact	contact_name	u_default_gateway	u_end_of_life	u_line_class_code	mac_address	u_maintenance_start_date	u_maintenance_end_date	u_maintenance_type	managed_by	managed_by_name	u_managed_by_group	managed_by_group_name	ip_address	u_host_ci_information	u_location_comments	u_network_address	u_managed_ip_subnet_mask	u_management_start_date	u_management_end_date	Manufacturer	manufacturer_name	model_number	u_part_number	u_priority	u_rack_location	u_security_ci	u_security_classification	serial_number	Vendor	vendor_name	u_version

			"CI_ID","Name","install_status","u_address","sys_class_name","company","company_name","u_contact","contact_name","u_default_gateway","u_end_of_life","u_line_class_code","mac_address","u_maintenance_start_date","u_maintenance_end_date","u_maintenance_type","managed_by","managed_by_name","u_managed_by_group","managed_by_group_name","ip_address","u_host_ci_information","u_location_comments","u_network_address","u_managed_ip_subnet_mask","u_management_start_date","u_management_end_date","Manufacturer","manufacturer_name","model_number","u_part_number","u_priority","u_rack_location","u_security_ci","u_security_classification","serial_number","Vendor","vendor_name","u_version"

			
			*/
			
			String[] expectedColValuesCIRs = {"sys_id","child","child_name","parent","parent_name","type","type_name"};
			
			/* format of the prepared statement
			
			"(ci_relationship_id, child_ci_id, child_ci, parent_ci_id, parent_ci, relation_type_id, relation_ type) VALUES"
			
			"sys_id","child","child_name","parent","parent_name","type","type_name"

			
			*/
			
			
			String[] expectedColValuesUsers = {"Cheese", "Pepperoni", "Black Olives"};
			
			/* format of the prepared statement
			
			+ "(user_id, user, company_id, company, email, title, sensitive, employee_number) VALUES"
			
			*/
			//TODO TBC the sensitive mapping 0 = false 1 = true
			
			String[] expectedColValuesCompanies = {"sys_id","name","u_trading_name","u_sm7_name","u_sensitive"};
	
			/* format of the prepared statement
			
			+ "(company_id, company, trading_name, sm7_name, sensitive) VALUES"
			
			"sys_id","name","u_trading_name","u_sm7_name","u_sensitive"

			
			TODO TBC the sensitive mapping 0 = false 1 = true 
			
			*/
			
			switch (methodName) {
	        case "CIs":  
	        	checkRowTitlesValues(firstRow, expectedColValuesCIs);
	                 break;
	        case "CIRs":  
	        	checkRowTitlesValues(firstRow, expectedColValuesCIRs);
	                 break;
	        case "Users":  
	        	checkRowTitlesValues(firstRow, expectedColValuesUsers);
	                 break;
	        case "Companies":  
	        	checkRowTitlesValues(firstRow, expectedColValuesCompanies);
	                 break;
	        default: 
	        	System.exit(0);
	                 break;
	        }
	    }
	
		private void checkRowTitlesValues (Row firstRow, String[] expectedColValues){
			
			Iterator<Cell> firstRowcellIterator = firstRow.cellIterator();

			StringBuilder fieldColVal = new StringBuilder("");
			int positionInexpectedColValuesArray = 0;
			while (firstRowcellIterator.hasNext()) {
				Cell cell = firstRowcellIterator.next();
				fieldColVal.setLength(0);
				fieldColVal.append(cell.toString());
				if (fieldColVal.toString().equals(expectedColValues[positionInexpectedColValuesArray])){
				positionInexpectedColValuesArray ++;
				}
				else
				{
					System.out.println("Column header fields are not correct");
					System.exit(0);
					
				}
			}
			System.out.println("Checked the values in the columns");
		}
}
