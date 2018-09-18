package stubController;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// This file will lookup details of the alarm for the excel file. 
// It can also lookup values for the CI from a SNOW file and populate details from the SNOW file

public class ExcelFileParserSNMP {
	private StubControl stubControl;
	private SNOWFileReader SNOWfilereader;

	public ExcelFileParserSNMP(StubControl inStubControl, SNOWFileReader inSNOWfilereader) {
		this.stubControl = inStubControl;
		SNOWfilereader = inSNOWfilereader;
	}

	protected void parseExcelFile() {

		try {
			String excelFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\OPTUS OB OAM replacement\\Automation\\alarmstotrigger.xlsx";

			// ""C:\\test\\filename.txt"";
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Thread.sleep(1000);
			System.out.println("Hello building excel chart");

			// Iterator<Row> iterator = firstSheet. .iterator();
			int sheetcounts = workbook.getNumberOfSheets();
			String nameofsheet = "";
			for (int i = 0; i < sheetcounts; i++) {

				//HashMap<String,String>>
				
				Sheet sheet = workbook.getSheetAt(i);
				// sheet.getSheetName();
				nameofsheet = sheet.getSheetName();

				if (((nameofsheet.equals("HP")) || (nameofsheet.equals("Prognosis"))) == false)
						{
					continue;
				}
				
				ArrayList<HashMap<String,String>> arrayofAlarms = new ArrayList();
				ArrayList<String> arrayofColumnNames = new ArrayList();
				Iterator<Row> rowIterator = workbook.getSheetAt(i).iterator();

				System.out.println("\n Here is the sheets name " + sheet.getSheetName());

				// let's gets the column values
				Row firstRow = rowIterator.next();
				Row alarmRow;
				HashMap<String, String> specificAlarmDetails;

				Iterator<Cell> cellIterator = firstRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					arrayofColumnNames.add(cell.toString());
					//System.out.print(" - " + cell.toString());
				}

				//System.out.println("\n\n");

				// we now have the set of fields for the alarm - we now need to
				// iterate though the table and build the alarms

				int arrayofcolumnNamesCtr = 0;
				// we will use this to iterate through the columns

				StringBuilder alarmParamType = new StringBuilder("");
				StringBuilder alarmParamVal = new StringBuilder("");
				StringBuilder alarmParamValAfterFormat = new StringBuilder("");

				while (rowIterator.hasNext()) {
					alarmRow = rowIterator.next();
					specificAlarmDetails = new HashMap();
					arrayofcolumnNamesCtr = 0;
					cellIterator = alarmRow.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						alarmParamType.setLength(0);
						alarmParamType.append(arrayofColumnNames.get(arrayofcolumnNamesCtr));
						arrayofcolumnNamesCtr++;
						
						if (alarmParamType.toString().equals("SNOW Reference")){
							// call the file lookup method to get details from SNOW file
							SNOWfilereader.getSNOWFileDetils(specificAlarmDetails,alarmParamVal.toString());
						}
							
						
						alarmParamVal.setLength(0);
						alarmParamVal.append(cell.toString());
						
						// call the method to do any parameters reformatting
						alarmParamValAfterFormat.setLength(0);
						alarmParamValAfterFormat.append(parameterFormat(nameofsheet,alarmParamType.toString(), alarmParamVal.toString()));
						
						specificAlarmDetails.put(alarmParamType.toString(), alarmParamValAfterFormat.toString());
						System.out.print("\nAlarm Param Type - " + alarmParamType.toString() + " Alarm Param Val - " + alarmParamValAfterFormat.toString());
					}
					
					System.out.print("\nabout to add the specificAlarmDetails");
					arrayofAlarms.add(specificAlarmDetails);

					// now we have parsed the sheet - let's pass the alarms back
					// to the stubControl
				}
				System.out.println("\n Writing out now -" + nameofsheet + "-");
					switch (nameofsheet) {
					case "HP":
						stubControl.buildHPAlarms(arrayofAlarms);
						break;
					case "Prognosis":
						stubControl.buildPrognosisAlarms(arrayofAlarms);
						break;
					default:
						throw new IllegalArgumentException(
								"Invalid type of source in excel chart - only HP, Prognosis, Solarwinds and ZenOSS currently implemented - ");
				}
					
					workbook.close();	
			}
		} catch (Exception io) {
			System.out.println("\n Here is the error message " + io.getMessage());
		}
	}
	
	private String parameterFormat(String nameofsheet,String rawAlarmParamType,String rawAlarmParamVal){
		String returnVal = rawAlarmParamVal;
		//
		switch (rawAlarmParamType) {
        case "MSGID":
        	returnVal = this.generateMSGID(nameofsheet);
            break;
        case "RECEIVE_DATE":
        case "CREATION_DATE":
        	returnVal = this.dateGenerator(rawAlarmParamVal, nameofsheet);
            break;
        case "RECEIVE_TIME":
        case "CREATION_TIME":	
        	returnVal = this.timeGenerator(rawAlarmParamVal, nameofsheet);
            break;
        case "Severity":
        case "ProblemID":
    		String[] output = rawAlarmParamVal.split("\\.");
    		returnVal = output[0];
		}
		return returnVal;

    }
		
	
	//next methods allow us to generate an format that is correct for that interface - 
	// we use the raw format and the name of the sheet if we need to specialise the format for other sheets
	private String dateGenerator(String rawAlarmParamVal, String nameofsheet){
		String 	dateStamp = "";
		if (rawAlarmParamVal.equals("Generate")){
				dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			}
		else {
			try{
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");

			Date date=sdf.parse(rawAlarmParamVal);

			sdf=new SimpleDateFormat("dd/MM/yyyy");
			
			dateStamp = sdf.format(date);
			}
			catch(Exception e){
				System.err.println("Badly formatted in incoming excel chart - should be dd/mm/yyyy and is r-endered by code to dd-MMM-yyyy " + rawAlarmParamVal + " in sheet " + nameofsheet);
				System.exit(0);
			}
		}
		return dateStamp;
	}

	private String timeGenerator(String rawAlarmParamVal, String nameofsheet){
		String 	timeStamp = rawAlarmParamVal;
		if (rawAlarmParamVal.equals("Generate")){
				timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
			}
		return timeStamp;
	}
	
	private String generateMSGID(String nameofsheet){
		// single list of chars - salt and length might be varied later for different interfaces-hence pass in name of sheet 
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
       
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	} 
}
