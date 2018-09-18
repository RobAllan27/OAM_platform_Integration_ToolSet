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

import zenOSSJSONFormat.Component;
import zenOSSJSONFormat.Details;
import zenOSSJSONFormat.Device;
import zenOSSJSONFormat.DeviceClass;
import zenOSSJSONFormat.EventClass;
import zenOSSJSONFormat.Events;

// This file will lookup details of the alarm for the excel file. 
// It can also lookup values for the CI from a SNOW file and populate details from the SNOW file

public class ExcelFileParserZenOSS {
	private StubControl stubControl;
	private SNOWFileReader SNOWfilereader;
	private Component zenComponent;
	private Details zenDetails;
	private Device zenDevice;
	private DeviceClass[] zenDeviceClassArray;
	private EventClass zenEventClass;

	public ExcelFileParserZenOSS(StubControl inStubControl, SNOWFileReader inSNOWfilereader) {
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
			System.out.println("Hello building excel chart for ZenOSS");

			// Iterator<Row> iterator = firstSheet. .iterator();
			int sheetcounts = workbook.getNumberOfSheets();
			String nameofsheet = "";
			for (int i = 0; i < sheetcounts; i++) {

				Sheet sheet = workbook.getSheetAt(i);
				// sheet.getSheetName();
				nameofsheet = sheet.getSheetName();

				if ((nameofsheet.equals("ZenOSS")) == false)
						{
					continue;
				}
				
				//HashMap<String,String>>
				
				ArrayList<HashMap<String,String>> arrayofAlarms = new ArrayList();
				ArrayList<String> arrayofColumnNames = new ArrayList();
				Iterator<Row> rowIterator = workbook.getSheetAt(i).iterator();

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

				while (rowIterator.hasNext()) {
					alarmRow = rowIterator.next();
					Events event = new Events();
					//We will initialise new components to be associated with the results object
					zenComponent = new Component();
					zenDetails = new Details();
					zenDevice = new Device();
					zenDeviceClassArray = new DeviceClass[];
					zenEventClass = new EventClass();
					
					arrayofcolumnNamesCtr = 0;
					cellIterator = alarmRow.cellIterator();
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						
						alarmParamVal.setLength(0);
						alarmParamVal.append(cell.toString());
						
						alarmParamType.setLength(0);
						alarmParamType.append(arrayofColumnNames.get(arrayofcolumnNamesCtr));
						
						//we now need the call the method to build the event.
						
						manipulateEvent(event,alarmParamType,alarmParamVal);

						arrayofcolumnNamesCtr++;						

						System.out.print("\nAlarm Param Type - " + alarmParamType.toString() + " Alarm Param Val - " + alarmParamValAfterFormat.toString());
					}
					
					System.out.print("\nabout to add the specificAlarmDetails");
					arrayofAlarms.add(specificAlarmDetails);

					// now we have parsed the sheet - let's pass the alarms back
					// to the stubControl
				}
				System.out.println("\n Writing out now -" + nameofsheet + "-");
					switch (nameofsheet) {
					case "ZenOSS":
						stubControl.buildZenOSSAlarms(arrayofAlarms);
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
	
	private void manipulateEvent(Events event, StringBuilder alarmParamType, StringBuilder alarmParamVal){
		
		// TO DO we have to cover the generate cases
		
		 String typeVal = alarmParamType.toString();
		 String typeofDay;
	     switch (typeVal) {

	     case "String/summary":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/clearid":
	    	 event.setClearid(typeVal);
	    	 break;
	     case "String/count"
	    	 event.setCount(typeVal);
	    	 break;
	     case "String/dedupid":
	    	 event.setDedupid(typeVal);
	    	 break;
	     case "String/eventGroup":
	    	 event.setEventGroup(typeVal);
	    	 break;
	     case "String/ntevid":
	    	 event.setNtevid(typeVal);;
	    	 break;
	     case "String/DevicePriority":
	    	 event.setDevicePriority(typeVal);
	    	 break;
	     case "String/eventClassMapping":
	    	 event.setEventClassMapping(typeVal);
	    	 break;
	     case "String/Id":
	    	 event.setId(typeVal);
	    	 break;
	     case "String/eventClassKey":
	    	 event.setEventClassKey(typeVal);
	    	 break;
	     case "String/facility":
	    	 event.setFacility(typeVal);
	    	 break;
	     case "Details/zenoss_device_device_class/StringsArray":
	    	 // to do string tokeniser etc event.set(typeVal);
	    	 break;
	     case "Details/zenoss_device_priority/StringsArray":
	    	// to do string tokeniser etc event.set(typeVal);
	    	 break;
	     case "Details/zenoss_device_production_state/StringsArray":
	    	// to do string tokeniser etc event.set(typeVal);
	    	 break;
	     case "Component/uid":
	    	 event.setComponent(zenComponent);
	    	 zenComponent.setUid(typeVal);
	    	 break;
	     case "Component/text":
	    	 zenComponent.setText(typeVal);
	    	 break;
	     case "Component/uuid":
	    	 zenComponent.setUuid(typeVal);
	    	 break;
	     case "Component/url":
	    	 zenComponent.setUrl(typeVal);
	    	 break;
	     case "String/priority":
	    	 event.setPriority(typeVal);
	    	 break;
	     case "String/ipAddress":
	    	 event.setIpAddress(typeVal);
	    	 break;
	     case "String/eventState":
	    	 event.setEventState(typeVal);
	    	 break;
	     case "String/monitor":
	    	 event.setMonitor(typeVal);
	    	 break;
	     case "String/evid":
	    	 event.setEvid(typeVal);
	    	 break;
	     case "String/lastTime":
	    	 event.setLastTime(typeVal);
	    	 break;
	     case "String/DeviceGroups/StringsArray":
	    	 event.setSummary(typeVal);
	    	 // to do with a split
	    	 
	    	 break;
	     case "String/Location/StringsArray":
	    	 event.setSummary(typeVal);
	    	// to do with a split
	    	 
	    	 break;
	     case "String/Severity":
	    	 event.setSeverity(typeVal);
	    	 break;
	     case "String/Ownerid":
	    	 event.setOwnerid(typeVal);
	    	 break;
	     case "String/Agent":
	    	 event.setAgent(typeVal);
	    	 break;
	     case "EventClass/uid":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "EventClass/text":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/Message":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/firstTime":
	    	 event.setFirstTime(typeVal);
	    	 break;
	     case "String/eventKey":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "DeviceClass/Uid/ObjectArray":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "DeviceClass/text/ObjectArray":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "Device/uid":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "Device/text":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "Device/uuid":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "Device/url":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/Systems/StringsArray":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/stateChange":
	    	 event.setSummary(typeVal);
	    	 break;
	     case "String/prodState":
	    	 event.setSummary(typeVal);
	    	 break;  	     
	     default:
	    	 throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
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
