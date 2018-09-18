package stubController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import AlarmGenerator.HPSNMPThread;
import AlarmGenerator.PrognosisSNMPThread;
import AlarmGenerator.SolarWindsThread;
import AlarmGenerator.ZenOSSSNMPThread;
import java.text.SimpleDateFormat;


public class StubControl{
	
	protected void printDate(){
		String dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
		System.out.println("here is the date " + dateStamp + " here is the time" + timeStamp);
	}
	
	
	
	protected void buildScripts(){
		// we will parse the SNOW file and get any extra details
		SNOWFileReader SNOWfilereader = new SNOWFileReader();
		ExcelFileParserSNMP efp = new ExcelFileParserSNMP(this, SNOWfilereader);
		efp.parseExcelFile();		
	}
	
	protected void buildHPAlarms(ArrayList<HashMap<String,String>> arrayofAlarms){
		System.out.print("\n\nIn HP Alarm stub controller");
		HPSNMPThread HPSNMPthread = new HPSNMPThread(arrayofAlarms);
	}
	
	protected void buildPrognosisAlarms(ArrayList<HashMap<String,String>> arrayofAlarms){
		System.out.print("\n\nIn Prognosis Alarm stub controller");
		PrognosisSNMPThread PrognosisSNMPthread = new PrognosisSNMPThread(arrayofAlarms);
		
	}
	
	protected void buildZenOSSAlarms(ArrayList<HashMap<String,String>> arrayofAlarms){
		System.out.print("\n\nIn ZenOSS Alarm stub controller");
		ZenOSSSNMPThread ZenOSSSNMPthread = new ZenOSSSNMPThread(arrayofAlarms);
		
	}
	
	protected void buildSolarwindsAlarms(ArrayList<HashMap<String,String>> arrayofAlarms){
		System.out.print("\n\nIn Solarwinds Alarm stub controller");
		SolarWindsThread SolarWindsthread = new SolarWindsThread(arrayofAlarms);
		
	}
	
}
