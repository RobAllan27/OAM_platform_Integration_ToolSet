package zenOSSTrapComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class zenOSSTrapComparator {

	private FileWriter fw1;
	private BufferedWriter bw;
	private BufferedReader br;
	
	private String inFileName = "C:\\Temp\\TempZenFiles\\lam02zen";
	private String outFileName = "C:\\Temp\\TempZenFiles\\outfile02";
	
	
	public void buildInFile(){
		
		try {

            File f = new File(inFileName);
            
            br = new BufferedReader(new FileReader(f));

           // System.out.println("Set up input stream for reading file using Buffered Reader");

        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	public void buildOutFile(){
		

		try {

			fw1 = new FileWriter(outFileName);
			bw = new BufferedWriter(fw1);

			//System.out.println(inStrBuf.);

		} catch (IOException e) {

			e.printStackTrace();

		}
}
	
	private void writeToFile(StringBuffer strbuf){
		
		try{
			bw.write(strbuf.toString());
        	bw.newLine();
			bw.flush();
		}
		catch (IOException io){
		}
		
	}
	
	
	
	// method to return a map for the JSON object
	
	public void parseFiles(){
		
		String readLine;
		StringBuffer lineStringBeingExtracted = new StringBuffer();
		
    	int startPositionOfTrapData = 0;
    	int endPositionOfTrapData = 0;
		
		try{

			
        while ((readLine = br.readLine()) != null) {
           // System.out.println(readLine);
        /*
        1 replace any triple \ ie \\\ with #
        2 replace and single \ with nothing
        3 reinstate the \
        2 then get the start location (gives first characters of the trapdata string)
        3 then add the length of the trapdata string - start pos
        4 then get the end location (gives first characters of the trapdata string) - ","description":"Unknown trap from lpptzenrm02.awemc.infra.local enterprise 14296,
        */
        	String stringatstart = "trapData";
        	String stringatend = "Unknown trap from .... enterprise 14296";
        	startPositionOfTrapData = 0;
        	endPositionOfTrapData = 0;
        	String noSlashReadLine = readLine.replace("\\\\\\", "#");
        	noSlashReadLine = noSlashReadLine.replace("\\", "");
        	noSlashReadLine = noSlashReadLine.replace("#", "\\");
        	startPositionOfTrapData = noSlashReadLine.indexOf(stringatstart);
        	endPositionOfTrapData = noSlashReadLine.indexOf(stringatend);
        	
        	if (startPositionOfTrapData < 1 || endPositionOfTrapData < 1){
        	// nothing found - string not there
        		continue;
        		}
        	String  extractedJson = noSlashReadLine.substring(startPositionOfTrapData + 10, endPositionOfTrapData - 19);
        	
        	// now call the json parser
        	
        	lineStringBeingExtracted = this.getOIDsStringBufFromJSON(extractedJson);
        	this.writeToFile(lineStringBeingExtracted);
        	lineStringBeingExtracted.setLength(0);
        	
        	//System.out.println(extractedJson);
        	//System.exit(0);
        	
        	}
		
       } catch (IOException e) {
        e.printStackTrace();
       }
    }
	
	
	
	private StringBuffer getOIDsStringBufFromJSON(String inJson){
	
	StringBuffer foundStrings =  new StringBuffer();
	Map<String, Object> unsortedmap = new HashMap<String, Object>();
	Map<String, Object> sortedMap = new LinkedHashMap<>();
		
	try {

		ObjectMapper mapper = new ObjectMapper();
		//String inJsonlocal = "{\"name\":\"mkyong\", \"age\":29}";

		// convert JSON string to Map
		unsortedmap = mapper.readValue(inJson, new TypeReference<Map<String, Object>>(){});

		//now we can have  sorted map -a LinkedHashMap
			
		unsortedmap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		
		//System.out.println(unsortedmap);
		
		//now we will print the sorted map
		
		/*
        Set<String> keysToSortedSet = sortedMap.keySet();
        for(String k:keysToSortedSet){
            System.out.println(k+" -- "+sortedMap.get(k));
        }
		*/
		// now let's find the values for specific cases 
		
        //String[] keysTobeFoundinJSONInAlertDebug = {"AAA", "BBB", "CCC", "DDD", "EEE"};
        
        // or we can iterate through the file and get the object based on incrementing value
        
        String baseSearchString  = "enterprises.14296.1.100.";
        
        for (int i = 0; i < 45; i++) {
			String stringToFind  =  baseSearchString + i;
			foundStrings.append(stringToFind);
			foundStrings.append(",");
			//System.out.println(stringToFind +","+sortedMap.get(stringToFind));
			foundStrings.append(sortedMap.get(stringToFind));
			foundStrings.append(",");
		}
        
        
	} catch (JsonGenerationException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return foundStrings;
	}
}

/* sample trap at the bottom
 * 
 * 
 * 				"enterprises.14296.1.100.32": "3",
				"enterprises.14296.1.100.31": "",
				"snmptrapobjectname": "enterprises.14296.1.100.0.0.1",
				"traptime": 1509427029,
				"agentaddress": "10.122.122.177:44587",
				"generictrap": "V2 Mib Defined",
				"enterprises.14296.1.100.25": "",
				"enterprises.14296.1.100.24": "/Non-Sensitive/Hastings Deering (Australia) Limited/Cloud-DC",
				"snmptrapoid.0": "enterprises.14296.1.100.0.0.1",
				"enterprises.14296.1.100.23": "",
				"enterprises.14296.1.100.22": "",
				"sysuptimeinstance": "582398214",
				"enterprises.14296.1.100.29": "",
				"errorstatus": 0,
				"enterprises.14296.1.100.28": "0",
				"enterprises.14296.1.100.27": "0",
				"enterprises.14296.1.100.26": "0",
				"snmptrapoid": "enterprises.14296.1.100.0.0.1",
				"enterprises.14296.1.100.21": "/vSphere",
				"community": "zenosssnmpsecret",
				"enterprises.14296.1.100.20": "zenhubworker",
				"specificcode": 0,
				"node": "lpptzenrm02.awemc.infra.local",
				"requestid": 1461947504,
				"snmpversion": "2c",
				"enterprises.14296.1.100.14": "1502932550997",
				"enterprises.14296.1.100.13": "",
				"enterprises.14296.1.100.12": "",
				"enterprises.14296.1.100.11": "",
				"enterprises.14296.1.100.17": "1000",
				"enterprises.14296.1.100.16": "10470",
				"enterprise": "snmpTrap",
				"enterprises.14296.1.100.15": "1509427028868",
				"enterprises.14296.1.100.10": "0",
				"snmptrapenterpriseobjectid": "1.3.6.1.4.1.14296.1.100.0.0.1",
				"enterprises.14296.1.100.2": "m-sdi-qld-ndc-vcs01|ResourcePool_resgroup-360|/App/Zenoss/ImpactStatusTrigger|1|ModelChange event state update request",
				"enterprises.14296.1.100.3": "m-sdi-qld-ndc-vcs01",
				"enterprises.14296.1.100.1": "0242ac11-000b-97cc-11e7-82e99c5beb5b",
				"genericcode": 0,
				"enterprises.14296.1.100.6": "",
				"enterprises.14296.1.100.7": "ModelChange event state update request",
				"enterprises.14296.1.100.4": "ResourcePool_resgroup-360",
				"enterprises.14296.1.100.5": "/App/Zenoss/ImpactStatusTrigger",
				"errorindex": 0,
				"severity": 3,
				"enterprises.14296.1.100.8": "ModelChange event state update request",
				"enterprises.14296.1.100.9": "1"
				
				*/
