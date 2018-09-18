package hpFileParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class AlarmTypeParser {

	private BufferedReader bufferedreader;
	private FileWriter fw;
	private String readLine = "";
	private StringBuffer setStringBeingExtracted = new StringBuffer();
	private static final String inFileName = "C://Users//Rob//Userdata//DX Software Testing//Project Work//**********//Test Data//HP OVO traps set//filenew.txt";
	private static final String outFileName = "C://Users//Rob//Userdata//DX Software Testing//Project Work//*************//Test Data//HP OVO traps set//testfileoutb.txt";
	private BufferedWriter bw;
	
	//C://Users//Rob//Userdata//DX Software Testing//Project Work//***********//Test Data//HP OVO traps set//testfilein
	
	public void buildInFile(){
		
		try {

            File f = new File(inFileName);
            
            bufferedreader = new BufferedReader(new FileReader(f));

            System.out.println("Set up input stream for reading file using Buffered Reader");

        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	
	public void buildOutFile(){
			
			//FileWriter fw = null;

			try {

				//String content = "This is the content to write into file\n";

				fw = new FileWriter(outFileName);
				bw = new BufferedWriter(fw);

				//System.out.println(inStrBuf.);

			} catch (IOException e) {

				e.printStackTrace();

			}
	}
			
			/*
			finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			}
			*/

	/* sample of what we are looking for
	 * 
	 * 2017-10-13 06:59:50 program start
2017-10-13 06:59:50 [MSGID]
2017-10-13 06:59:50 dec5b076-af87-71e7-17fd-c68ef47d0000
2017-10-13 06:59:50 [NODENAME]
2017-10-13 06:59:50 m-cogc-qld-cmw-sw05
2017-10-13 06:59:50 [SEVERITY]
2017-10-13 06:59:50 critical
2017-10-13 06:59:50 [MSGTEXT]
2017-10-13 06:59:50 Node m-cogc-qld-cmw-sw05 is unavailable
2017-10-13 06:59:50 [RECEIVE_TIME]
2017-10-13 06:59:50 06:59:37
2017-10-13 06:59:50 [RECEIVE_DATE]
2017-10-13 06:59:50 10/13/2017
2017-10-13 06:59:50 [OBJECT]
2017-10-13 06:59:50 m-cogc-qld-cmw-sw05
2017-10-13 06:59:50 [INSTRUCTIONS]
2017-10-13 06:59:50 <none>
2017-10-13 06:59:50 [APPLICATION]
2017-10-13 06:59:50 SNMPTraps
2017-10-13 06:59:50 [GROUP]
2017-10-13 06:59:50 Solarwinds
2017-10-13 06:59:50 [CREATION_DATE]
2017-10-13 06:59:50 10/13/2017
2017-10-13 06:59:50 [CREATION_TIME]
2017-10-13 06:59:50 06:59:35
2017-10-13 06:59:50 [SUPP_DUP_MSGS]
2017-10-13 06:59:50 0
	 * 
	 */
	
	
	public void parseFiles(){
		
		boolean inValidbock = false;
		int linesSinceStartOfValidBlock  = 0;
		String strToStartBlock = "/usr/sfw/bin/snmptrap -v 2c -c 5pec1al 198.142.244.34";
		String strAtEnd = "[SUPP_DUP_MSGS]";
		String strRanIntoNextBlock = "/usr/sfw/bin/snmptrap -v 2c -c 5pec1al 198.142.244.35";
		int nextlineIsStored = 0;
		String nextLineType = "";
		StringBuffer lineStringBeingExtracted = new StringBuffer();
		StringBuffer dateTimeStringBeingExtracted = new StringBuffer();
		try{


				fw = new FileWriter(outFileName);
				bw = new BufferedWriter(fw);
			
        while ((readLine = bufferedreader.readLine()) != null) {
           // System.out.println(readLine);

            if (readLine.toLowerCase().contains(strToStartBlock)){
            	
            	callParseInLineMethod(readLine, bw);
            	
            	inValidbock = true;
            	
            	// let's reset the values  
            	linesSinceStartOfValidBlock = 0;
            	nextlineIsStored = 0;
            	setStringBeingExtracted.setLength(0);
            	lineStringBeingExtracted.setLength(0);
            	dateTimeStringBeingExtracted.setLength(0);
                nextLineType = "";
                
                String[] tokensDateTime = StringUtils.split(readLine," ");
                int tokencounterDT = 0;
                lineStringBeingExtracted.setLength(0);
                for (String token : tokensDateTime)
                {
                    if (tokencounterDT < 2){
                    	setStringBeingExtracted.append(token);
                    	setStringBeingExtracted.append(" ");
                    }
                    tokencounterDT++;	
                }
                
                
            	// lets get the date time here..
                
            }
                            
            // check if start of valid block

            
            if (inValidbock){
            	linesSinceStartOfValidBlock++;
            	// let's work out the next line type
                if (readLine.contains("[MSGID]")){
                	nextlineIsStored = 2;
                	nextLineType = "[MSGID]";   	
                }
                
                if (readLine.contains("[NODENAME]")){
                	nextlineIsStored = 2;
                	nextLineType = "[NODENAME]";
            
                }
                
                if (readLine.contains("[SEVERITY]")){
                	nextlineIsStored = 2;
                	nextLineType = "[SEVERITY]";
                	
                }
                
                if (readLine.contains("[OBJECT]")){
                	nextlineIsStored = 2;
                	nextLineType = "[OBJECT]";
                }
                
                if (readLine.contains("[INSTRUCTIONS]")){
                	nextlineIsStored = 2;
                	nextLineType = "[INSTRUCTIONS]";
                }
                
                if (readLine.contains("[APPLICATION]")){
                	nextlineIsStored = 2;
                	nextLineType = "[APPLICATION]";
                }
                
                if (readLine.contains("[GROUP]")){
                	nextlineIsStored = 2;
                	nextLineType = "[GROUP]";
                        	
                }
            	
                if (nextlineIsStored == 1){
                	
                    String[] tokens = StringUtils.split(readLine," ");
                    int tokencounter = 0;
                    lineStringBeingExtracted.setLength(0);
                    for (String token : tokens)
                    {
                        if (tokencounter > 1){
                        	lineStringBeingExtracted.append(token);
                        	lineStringBeingExtracted.append(" ");
                        }
                        tokencounter++;	
                    }
                    setStringBeingExtracted.append("~");
                    setStringBeingExtracted.append(nextLineType);
                    setStringBeingExtracted.append("~");
                    setStringBeingExtracted.append(lineStringBeingExtracted.toString());
                    
                    nextlineIsStored--;
                    nextLineType = "";	
                }
                
                nextlineIsStored--;
            
            //reset the block valid control and write out
            
            //2 methods look to see if it is the "[SUPP_DUP_MSGS]" o if the number of lines has been exceeded
            
            if (readLine.toLowerCase().contains(strAtEnd) || linesSinceStartOfValidBlock > 50 || readLine.toLowerCase().contains(strRanIntoNextBlock)){
            	
            	//time to write nothing more to collect
            	
            	//System.out.println("Here we go a line " + setStringBeingExtracted.toString());
            	
				//bw.write(setStringBeingExtracted.toString());
            	//bw.newLine();
				//bw.flush();
            	inValidbock = false;
	   	
            }
          }  
        }
		
       } catch (IOException e) {
        e.printStackTrace();
    }
  }
	
	
	private void callParseInLineMethod(String readLine, BufferedWriter bw){
		
		
        String[] tokensValues = StringUtils.split(readLine,"\"");
        int tokencounterFirstString = 0;
        int tokencounterDT = 0;
        setStringBeingExtracted.setLength(0); 
        for (String token : tokensValues)
        {
            if (tokencounterDT == 0) {
            	
            	String[] tokensfirstString = StringUtils.split(readLine," ");
                
            	for (String tokenFS : tokensfirstString)
                {
            	if (tokencounterFirstString < 2){
	            	tokencounterFirstString++; 
	                setStringBeingExtracted.append(tokenFS);
	                setStringBeingExtracted.append(" ");
            	}
             }
           }
        	// 1, 3, 5, 7, 15
            // (tokencounterDT == 1 || tokencounterDT == 5|| tokencounterDT == 9 || tokencounterDT == 13 || tokencounterDT == 21){
         
          //  if (tokencounterDT == 1 || tokencounterDT == 3|| tokencounterDT == 5 || tokencounterDT == 7 || tokencounterDT == 15)
            
        if (tokencounterDT == 1 || tokencounterDT == 3 || tokencounterDT == 5 || tokencounterDT == 7 ){ //|| tokencounterDT == 13 || tokencounterDT == 15 || tokencounterDT == 17 || tokencounterDT == 19){
        		
        		setStringBeingExtracted.append ("|");
        		setStringBeingExtracted.append(token);
        		setStringBeingExtracted.append(" ");
            }
            tokencounterDT++;	
        }
		try{
			bw.write(setStringBeingExtracted.toString());
        	bw.newLine();
			bw.flush();
			
			setStringBeingExtracted.setLength(0);
		}
		catch (IOException io){
		}
		
	}
	
}
