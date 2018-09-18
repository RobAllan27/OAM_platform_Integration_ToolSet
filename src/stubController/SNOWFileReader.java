package stubController;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SNOWFileReader {

	// this file will read a SNOW file and then create a new HashMap, whose key
	// is the CI value, and create hashmap of <strings,strings>.
	// it will expose a method that can then return the hashmap and will then
	// add values to a passed in hashmap.

	private HashMap<String, HashMap<String, String>> hashMapNodeSpecificDetails;

	public SNOWFileReader(){
		
		// Here we will parse the SNOW file and build details of the CI.
		
		// We as yet do not know the file format of this so we will assume that it is an excel chart.
		
		hashMapNodeSpecificDetails = new HashMap<String,HashMap<String, String>>();
		
		try {
			String excelFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\OPTUS OB OAM replacement\\Automation\\CIlist.xlsx";

			// ""C:\\test\\filename.txt"";
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Thread.sleep(1000);
			System.out.println("Hello building CI list excel chart");

			// specificCIDetails
			
				//ArrayList<HashMap> arrayofAlarms = new ArrayList();
				ArrayList<String> arrayofCIParamNames = new ArrayList<String>();
				Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();

				// let's gets the column values
				Row firstRow = rowIterator.next();
				Row CIRow;
				HashMap<String, String> specificCIDetails;

				Iterator<Cell> cellIterator = firstRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					arrayofCIParamNames.add(cell.toString());
					System.out.print(" - " + cell.toString());
				}

				System.out.println("\n\n");

				// we now have the set of fields for the CI - we now need to
				// iterate though the table and build the details of the CIs

				int arrayofcolumnNamesCtr = 0;
				// we will use this to iterate through the columns

				StringBuilder CIIdentifier = new StringBuilder("");
				StringBuilder CIParamType = new StringBuilder("");
				StringBuilder CIParamVal = new StringBuilder("");

				while (rowIterator.hasNext()) {
					CIRow = rowIterator.next();
					specificCIDetails = new HashMap<String, String>();
					arrayofcolumnNamesCtr = 0;
					cellIterator = CIRow.cellIterator();
					
					String nodeName = "";
					// we will use the nodename to look up the relevant hashmap 
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						CIParamType.setLength(0);
						CIParamType.append(arrayofCIParamNames.get(arrayofcolumnNamesCtr));
						arrayofcolumnNamesCtr++;
						
						//specificAlarmDetails
						
						CIParamVal.setLength(0);
						CIParamVal.append(cell.toString());
						specificCIDetails.put(CIParamType.toString(), CIParamVal.toString());
						if (CIParamType.toString().equals("NODENAME")){
							nodeName = CIParamVal.toString();
						}
						//System.out.print("\nCI Param Type - " + CIParamType.toString() + "CI Param Val - " + CIParamVal.toString());
						// TODO to remove
						//System.out.print("Before the put " + hashMapNodeSpecificDetails.size());
					}
					
					hashMapNodeSpecificDetails.put(nodeName,specificCIDetails);
					// TODO to remove
					//System.out.print("after the put " + hashMapNodeSpecificDetails.size());
					// now we have parsed the sheet - let's pass the alarms back
					// to the stubControl
				}
				//System.out.println("\n We have complete the parse of the SNOW config file ");
				workbook.close();
			}catch(Exception io)
	{
		System.out.println("\n Here is the error message " + io.getMessage());
	}
	}

	public void getSNOWFileDetils(HashMap<String, String> specificCIDetails, String SNOW_CI_Lookup) {
		// this will lookup the details of the SNOW file to get any extra
		// details

	}

}
