package AlarmGenerator;


import java.util.ArrayList;
import java.util.HashMap;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class HPSNMPThread extends AlarmGeneratorBaseClass implements Runnable, AlarmsMethods {

	  //public static final String  community  = "rabbit"; // to do
	  public static final String  community  = "****"; // to do

	  //  Sending Trap for sysLocation of RFC1213
	  //public static final String  trapOid          = ".1.3.6.1.2.1.1.6";
	  
	  
	  public static final String  trapOidMSGID  = "1.3.6.1.4.1.47827.2500.3003.1";
	  public static final String  trapOidNODENAME = "1.3.6.1.4.1.47827.2500.3003.2";
	  public static final String  trapOidSEVERITY = "1.3.6.1.4.1.47827.2500.3003.3";
	  public static final String  trapOidMSGTEXT = "1.3.6.1.4.1.47827.2500.3003.4";
	  public static final String  trapOidRECEIVE_TIME = "1.3.6.1.4.1.47827.2500.3003.5"; 
	  public static final String  trapOidRECEIVE_DATE = "1.3.6.1.4.1.47827.2500.3003.6";
	  public static final String  trapOidOBJECT = "1.3.6.1.4.1.47827.2500.3003.7";
	  public static final String  trapOidINSTRUCTIONS = "1.3.6.1.4.1.47827.2500.3003.8";
	  public static final String  trapOidAPPLICATION  = "1.3.6.1.4.1.47827.2500.3003.9";
	  public static final String  trapOidGROUP  = "1.3.6.1.4.1.47827.2500.3003.10";
	  public static final String  trapOidCREATION_TIME = "1.3.6.1.4.1.47827.2500.3003.11";
	  public static final String  trapOidCREATION_DATE = "1.3.6.1.4.1.47827.2500.3003.12";
	  public static final String  trapOidSUPP_DUP_MSGS = "1.3.6.1.4.1.47827.2500.3003.13";
	  
	 
	  /*  
	  /// * with a shortened OID.
	  	  public static final String  trapOidMSGID  = "47827.2500.3003.1";
	  public static final String  trapOidNODENAME = "47827.2500.3003.2";
	  public static final String  trapOidSEVERITY = "47827.2500.3003.3";
	  public static final String  trapOidMSGTEXT = "47827.2500.3003.4";
	  public static final String  trapOidRECEIVE_TIME = "47827.2500.3003.5"; 
	  public static final String  trapOidRECEIVE_DATE = "47827.2500.3003.6";
	  public static final String  trapOidOBJECT = "47827.2500.3003.7";
	  public static final String  trapOidINSTRUCTIONS = "47827.2500.3003.8";
	  public static final String  trapOidAPPLICATION  = "47827.2500.3003.9";
	  public static final String  trapOidGROUP  = "47827.2500.3003.10";
	  public static final String  trapOidCREATION_TIME = "47827.2500.3003.11";
	  public static final String  trapOidCREATION_DATE = "47827.2500.3003.12";
	  public static final String  trapOidSUPP_DUP_MSGS = "47827.2500.3003.13";
	  */
	  
	  
	  //public static final String  ipAddress      = "127.0.0.1";
	  
	  // local host black hole address
	 public static final String  ipAddressLAM1 = "127.0.0.1";
	 // public static final String  ipAddressLAM2 = "127.0.0.1";
	  	  
	  // PPT LAM addresses
	 // public static final String  ipAddressLAM1      = "10.122.122.187";
	  public static final String  ipAddressLAM2      = "10.122.122.188";;

	  //Production LAM addresses
	  
	  // public static final String  ipAddressLAM1      = "198.142.244.34";
	 // public static final String  ipAddressLAM2 = "198.142.244.35";
	  
	  public static final int     port      = 162;
	  
	//public static final int     port      = 165;
	  
	  private CommunityTarget comtarget1;
	  private CommunityTarget comtarget2;
	  
	public HPSNMPThread(ArrayList<HashMap<String,String>> arrayofAlarms) {
		super(arrayofAlarms);
		
	      //Create Target 
	      comtarget1 = new CommunityTarget();
	      comtarget1.setCommunity(new OctetString(community));
	      comtarget1.setVersion(SnmpConstants.version2c);
	      comtarget1.setAddress(new UdpAddress(ipAddressLAM1 + "/" + port));
	      comtarget1.setRetries(2);
	      comtarget1.setTimeout(5000);
	      
	      comtarget2 = new CommunityTarget();
	      comtarget2.setCommunity(new OctetString(community));
	      comtarget2.setVersion(SnmpConstants.version2c);
	      comtarget2.setAddress(new UdpAddress(ipAddressLAM2 + "/" + port));
	      comtarget2.setRetries(2);
	      comtarget2.setTimeout(5000);
		
		for (HashMap<String,String> specificAlarmHashMap: arrayofAlarms) {
			 generateNewAlarm(specificAlarmHashMap);
			// to do to turn off the HP SNMP traps
		}
		
		 try{
		 Thread.sleep(20000);
		 }
		 catch (Exception e){
			 System.out.println("Did not sleep ok betwen sening loops");
		 }
		
		boolean sendagain = false; 
		if  (sendagain) {
		for (HashMap<String,String> specificAlarmHashMap: arrayofAlarms) {
				//generateNewAlarm(specificAlarmHashMap);
				// to do to turn off the HP SNMP traps
			}
		}
	}
	
	public void generateNewAlarm(HashMap<String,String> inHashMap){
		String snmpEnterprise_OID;
		String snmpMSGID;
		String snmpNODENAME;
		String snmpSEVERITY;
		String snmpMSGTEXT;
		String snmpRECEIVE_TIME;
		String snmpRECEIVE_DATE;
		String snmpOBJECT;
		String snmpINSTRUCTIONS;
		String snmpAPPLICATION;
		String snmpGROUP;
		String snmpCREATION_TIME;
		String snmpCREATION_DATE;
		String snmpSUPP_DUP_MSGS;

		snmpEnterprise_OID = inHashMap.get("Enterprise_OID");
		snmpMSGID = inHashMap.get("MSGID");
		snmpNODENAME = inHashMap.get("NODENAME");
		snmpSEVERITY = inHashMap.get("SEVERITY");
		snmpMSGTEXT = inHashMap.get("MSGTEXT");
		snmpRECEIVE_TIME = inHashMap.get("RECEIVE_TIME");
		snmpRECEIVE_DATE = inHashMap.get("RECEIVE_DATE");
		snmpOBJECT = inHashMap.get("OBJECT");
		snmpINSTRUCTIONS = inHashMap.get("INSTRUCTIONS");
		snmpAPPLICATION = inHashMap.get("APPLICATION");
		snmpGROUP = inHashMap.get("GROUP");
		snmpCREATION_TIME = inHashMap.get("CREATION_TIME");
		snmpCREATION_DATE = inHashMap.get("CREATION_DATE");
		snmpSUPP_DUP_MSGS= inHashMap.get("SUPP_DUP_MSGS");
		
		try
	    {  
// to allow us to loop and send multiple SNMP traps
			//		for (int j = 0; j < 100000; j++){
			//Create Transport Mapping
	      TransportMapping transport1 = new DefaultUdpTransportMapping();
	      transport1.listen();
	      
	      TransportMapping transport2 = new DefaultUdpTransportMapping();
	      transport2.listen();

	      //Create PDU for V2
	      PDU pdu = new PDU();
	
	      // now we will loop around the hashmap... 
	      
	      // don't forget the enterprise OID
	      
	      //to do to put in the real string values.

			int i = 10;
			
			//long sysUpTime = (System.nanoTime()) / 10000000;  // 10^-7
			//pdu.add(new VariableBinding(SnmpConstants.sysUpTime, new TimeTicks(sysUpTime)));
			
			if (i == 1){ // do not use 1
		// we need the enterprise OID - may use the SNMP constants 	
				// lets do the String String approach - this throws parse exceptions needs to be an octet string
				//pdu.add(new VariableBinding(SnmpConstants.snmpTrapEnterprise, new OctetString(snmpEnterprise_OID)));
				//pdu.add(new VariableBinding(SnmpConstants.snmpTrapEnterprise, snmpEnterprise_OID));
		//	pdu.add(new VariableBinding(new OID(trapOid), snmpEnterprise_OID));
		 pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(snmpEnterprise_OID)));
		 pdu.add(new VariableBinding(new OID(trapOidMSGID), snmpMSGID));
	      pdu.add(new VariableBinding(new OID(trapOidNODENAME), snmpNODENAME));
	      pdu.add(new VariableBinding(new OID(trapOidSEVERITY), snmpSEVERITY));
	      pdu.add(new VariableBinding(new OID(trapOidMSGTEXT), snmpMSGTEXT));
	      pdu.add(new VariableBinding(new OID(trapOidRECEIVE_TIME), snmpRECEIVE_TIME));
	      pdu.add(new VariableBinding(new OID(trapOidRECEIVE_DATE), snmpRECEIVE_DATE));
	      pdu.add(new VariableBinding(new OID(trapOidOBJECT), snmpOBJECT));
	      pdu.add(new VariableBinding(new OID(trapOidINSTRUCTIONS), snmpINSTRUCTIONS));
	      pdu.add(new VariableBinding(new OID(trapOidAPPLICATION), snmpAPPLICATION));
	      pdu.add(new VariableBinding(new OID(trapOidGROUP), snmpGROUP));
	      pdu.add(new VariableBinding(new OID(trapOidCREATION_TIME), snmpCREATION_TIME));
	      pdu.add(new VariableBinding(new OID(trapOidCREATION_DATE), snmpCREATION_DATE));
	      pdu.add(new VariableBinding(new OID(trapOidSUPP_DUP_MSGS), snmpSUPP_DUP_MSGS));	      
			}
			else
			{
				// lets do the Octet String approach
				//SnmpConstants.snmpTrapOID,
				pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OctetString(snmpEnterprise_OID)));
				//pdu.add(new VariableBinding(SnmpConstants.snmpTrapEnterprise, new OctetString(snmpEnterprise_OID)));
				//	pdu.add(new VariableBinding(new OID(trapOid), new OctetString(snmpEnterprise_OID)));
				  pdu.add(new VariableBinding(new OID(trapOidMSGID), new OctetString(snmpMSGID)));
			      pdu.add(new VariableBinding(new OID(trapOidNODENAME), new OctetString(snmpNODENAME)));
			      pdu.add(new VariableBinding(new OID(trapOidSEVERITY), new OctetString(snmpSEVERITY)));
			      pdu.add(new VariableBinding(new OID(trapOidMSGTEXT), new OctetString(snmpMSGTEXT)));
			      pdu.add(new VariableBinding(new OID(trapOidRECEIVE_TIME), new OctetString(snmpRECEIVE_TIME)));
			      pdu.add(new VariableBinding(new OID(trapOidRECEIVE_DATE), new OctetString(snmpRECEIVE_DATE)));
			      pdu.add(new VariableBinding(new OID(trapOidOBJECT), new OctetString(snmpOBJECT)));
			      pdu.add(new VariableBinding(new OID(trapOidINSTRUCTIONS), new OctetString(snmpINSTRUCTIONS)));
			      pdu.add(new VariableBinding(new OID(trapOidAPPLICATION), new OctetString(snmpAPPLICATION)));
			      pdu.add(new VariableBinding(new OID(trapOidGROUP), new OctetString(snmpGROUP)));
			      pdu.add(new VariableBinding(new OID(trapOidCREATION_TIME), new OctetString(snmpCREATION_TIME)));
			      pdu.add(new VariableBinding(new OID(trapOidCREATION_DATE), new OctetString(snmpCREATION_DATE)));
			      pdu.add(new VariableBinding(new OID(trapOidSUPP_DUP_MSGS), new OctetString(snmpSUPP_DUP_MSGS)));	      
			}
	      /* need to specify the system up time
	      pdu.add(new VariableBinding(SnmpConstants.sysUpTime, new OctetString(new Date().toString())));
	      pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(trapOid)));
	      pdu.add(new VariableBinding(SnmpConstants.snmpTrapAddress, new IpAddress(ipAddress)));

	      // variable binding for Enterprise Specific objects, Severity (should be defined in MIB file)
	      pdu.add(new VariableBinding(new OID(trapOid), new OctetString("Major")));
	      */
	      
	      
	      //pdu.setType(PDU.NOTIFICATION);
	      pdu.setType(PDU.TRAP);
	      
	      
	      //Send the PDU
	      Snmp snmp1 = new Snmp(transport1);
	      System.out.println("Sending V2 Trap to " + ipAddressLAM1 + " on Port " + port);
	      
	      snmp1.send(pdu, comtarget1);
	      
	      //Thread.sleep(20000);
	      
	      Snmp snmp2 = new Snmp(transport2);
	      System.out.println("Sending V2 Trap to " + ipAddressLAM2 + " on Port " + port);
	      
	      snmp2.send(pdu, comtarget2);
	      
	      //ResponseEvent response = snmp.send(pdu, comtarget, transport);
	      //PDU respPDU = response.getResponse();
	      //System.out.println(respPDU);
	      // System.out.println(j);
	      snmp1.close();
	      snmp2.close();
	      Thread.sleep(100);
		  // the close { for the repeated loops
	      //}
	    }
	    catch (Exception e)
	    {
	      System.err.println("Error in Sending V2 Trap to " + ipAddressLAM1 + " on Port " + port);
	      System.err.println("Exception Message = " + e.getMessage());
	    }
	}
}
