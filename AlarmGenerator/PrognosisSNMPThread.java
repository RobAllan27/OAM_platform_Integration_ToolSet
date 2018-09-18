package AlarmGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class PrognosisSNMPThread extends AlarmGeneratorBaseClass implements Runnable, AlarmsMethods {

	//private ArrayList<HashMap> arrayofAlarms;
	/*
	public PrognosisSNMPThread(ArrayList<HashMap> arrayofAlarms){
		this.arrayofAlarms = arrayofAlarms;
	*/
	
	
	/*    the format for the variable bindings is as below..
	 * 
	 *    new VariableBinding(new OID("1.3.6.1.4.0"), new Integer32(i)),
          new VariableBinding(new OID("1.3.6.1.4.0"),new Counter32(278070606)),
          new VariableBinding(new OID("1.3.6.1.4.0"),new OctetString("Hello world!")),
          new VariableBinding(new OID("1.3.6.1.4.0"),new IpAddress("127.0.0.2")),
          new VariableBinding(new OID("1.3.6.1.4.0"),new Gauge32(867685L))
	 */
	
	  public static final String  community  = "5pec1al"; // to do

	  //  Sending Trap for sysLocation of RFC1213
	  //public static final String  trapOid          = ".1.3.6.1.2.1.1.6";
	  
	  public static final String  snmptrapOid = "1.3.6.1.4.1.6102"; // from Olga 
	  
	  public static final String  snmpirApplicationOID = "1.3.6.1.4.1.6102.1.1";
	  public static final String  snmpirEventTextOID = "1.3.6.1.4.1.6102.1.2.1";
	  public static final String  snmpirSeverityOID = "1.3.6.1.4.1.6102.1.2.2";
	  public static final String  snmpirProblemIDOID = "1.3.6.1.4.1.6102.1.2.3";
	  public static final String  snmpirConditionNameOID = "1.3.6.1.4.1.6102.1.2.4";
	  public static final String  snmpirSourceNodeOID = "1.3.6.1.4.1.6102.1.2.5";
	  public static final String  snmpirFieldName01nameOID = "1.3.6.1.4.1.6102.1.2.6";
	  public static final String  snmpirFieldValue01valueOID  = "1.3.6.1.4.1.6102.1.2.7";
	  public static final String  snmpirFieldName02nameOID = "1.3.6.1.4.1.6102.1.2.8";
	  public static final String  snmpirFieldValue02valueOID = "1.3.6.1.4.1.6102.1.2.9";
	  public static final String  snmpirFieldName03nameOID = "1.3.6.1.4.1.6102.1.2.10";
	  public static final String  snmpirFieldValue03valueOID = "1.3.6.1.4.1.6102.1.2.11";
	  public static final String  snmpirFieldName04nameOID = "1.3.6.1.4.1.6102.1.2.12";
	  public static final String  snmpirFieldValue04valueOID = "1.3.6.1.4.1.6102.1.2.13";
	  public static final String  snmpirFieldName05nameOID = "1.3.6.1.4.1.6102.1.2.14";
	  public static final String  snmpirFieldValue05valueOID = "1.3.6.1.4.1.6102.1.2.15";
	  public static final String  snmpirFieldName06nameOID = "1.3.6.1.4.1.6102.1.2.16";
	  public static final String  snmpirFieldValue06valueOID = "1.3.6.1.4.1.6102.1.2.17";
	  public static final String  snmpirFieldName07nameOID = "1.3.6.1.4.1.6102.1.2.18";
	  public static final String  snmpirFieldValue07valueOID = "1.3.6.1.4.1.6102.1.2.19";
	  public static final String  snmpirFieldName08nameOID = "1.3.6.1.4.1.6102.1.2.20";
	  public static final String  snmpirFieldValue08valueOID = "1.3.6.1.4.1.6102.1.2.21";
	  public static final String  snmpirFieldName09nameOID = "1.3.6.1.4.1.6102.1.2.22";
	  public static final String  snmpirFieldValue09valueOID = "1.3.6.1.4.1.6102.1.2.23";
	  public static final String  snmpirFieldName10nameOID = "1.3.6.1.4.1.6102.1.2.24";
	  public static final String  snmpirFieldValue10valueOID = "1.3.6.1.4.1.6102.1.2.25";
	  public static final String  snmpirFieldName11nameOID = "1.3.6.1.4.1.6102.1.2.26";
	  public static final String  snmpirFieldValue11valueOID = "1.3.6.1.4.1.6102.1.2.27";
	  
	  // local host black hole address
	  public static final String  ipAddressLAM1 = "127.0.0.1";
	  // public static final String  ipAddressLAM2 = "127.0.0.1";
	  	  
	  // PPT LAM addresses
	  // public static final String  ipAddressLAM1      = "10.122.122.187";
	  public static final String  ipAddressLAM2      = "10.122.122.188";

	  //Production LAM addresses
	  
	  //public static final String  ipAddressLAM1      = "198.142.244.34";
	  //public static final String  ipAddressLAM2 = "198.142.244.35";
	  
	  public static final int port = 162;
	  
	  private CommunityTarget comtarget1, comtarget2;
	
	public PrognosisSNMPThread(ArrayList<HashMap<String,String>> arrayofAlarms) {
		super(arrayofAlarms);
		
	      //Create Target 
	      comtarget1 = new CommunityTarget();
	      comtarget1.setCommunity(new OctetString(community));
	      comtarget1.setVersion(SnmpConstants.version1);
	      //comtarget.setVersion(SnmpConstants.version2c);
	      comtarget1.setAddress(new UdpAddress(ipAddressLAM1 + "/" + port));
	      comtarget1.setRetries(2);
	      comtarget1.setTimeout(5000);
	      
	      comtarget2 = new CommunityTarget();
	      comtarget2.setCommunity(new OctetString(community));
	      comtarget2.setVersion(SnmpConstants.version1);
	      //comtarget.setVersion(SnmpConstants.version2c);
	      comtarget2.setAddress(new UdpAddress(ipAddressLAM2 + "/" + port));
	      comtarget2.setRetries(2);
	      comtarget2.setTimeout(5000);
	      
		for (HashMap<String,String> specificAlarmHashMap: arrayofAlarms) {
			generateNewAlarm(specificAlarmHashMap);
		}
	}
	
	public void generateNewAlarm(HashMap<String,String> inHashMap){
		
		try
	    {
	      //Create Transport Mapping
	      TransportMapping transport = new DefaultUdpTransportMapping();
	      transport.listen();
	      
	      System.out.println("Here we go");
	      
		  String snmpirApplication = inHashMap.get("Application");
		  String snmpirEventText = inHashMap.get("EventText");
		  System.out.println("Severity" + inHashMap.get("Severity"));
		  System.out.println("Problemid" + inHashMap.get("ProblemID"));
		  int snmpirSeverity = Integer.parseInt(inHashMap.get("Severity"));
		  System.out.println("severity " + snmpirSeverity);
		  int snmpirProblemID = Integer.parseInt(inHashMap.get("ProblemID"));
		  System.out.println("snmpirProblemID " + snmpirProblemID);
		  String snmpirConditionName = inHashMap.get("ConditionName");
		  String snmpirSourceNode = inHashMap.get("SourceNode");
		  String snmpirFieldName01name = inHashMap.get("FieldName01name");
		  String snmpirFieldValue01value  = inHashMap.get("FieldValue01value");
		  String snmpirFieldName02name = inHashMap.get("FieldName02name");
		  String snmpirFieldValue02value = inHashMap.get("FieldValue02value");
		  String snmpirFieldName03name = inHashMap.get("FieldName03name");
		  String snmpirFieldValue03value = inHashMap.get("FieldValue03value");
		  String snmpirFieldName04name = inHashMap.get("FieldName04name");
		  String snmpirFieldValue04value = inHashMap.get("FieldValue04value");
		  String snmpirFieldName05name = inHashMap.get("FieldName05name");
		  String snmpirFieldValue05value = inHashMap.get("FieldValue05value");
		  String snmpirFieldName06name = inHashMap.get("FieldName06name");
		  String snmpirFieldValue06value = inHashMap.get("FieldValue06value");
		  String snmpirFieldName07name = inHashMap.get("FieldName07name");
		  String snmpirFieldValue07value = inHashMap.get("FieldValue07value");
		  String snmpirFieldName08name = inHashMap.get("FieldName08name");
		  String snmpirFieldValue08value = inHashMap.get("FieldValue08value");
		  String snmpirFieldName09name = inHashMap.get("FieldName09name");
		  String snmpirFieldValue09value = inHashMap.get("FieldValue09value");
		  String snmpirFieldName10name = inHashMap.get("FieldName10name");
		  String snmpirFieldValue10value = inHashMap.get("FieldValue10value");
		  String snmpirFieldName11name = inHashMap.get("FieldName11name");
		  String snmpirFieldValue11value = inHashMap.get("FieldValue11value");
	      
	      //Create PDU for V1
	      PDUv1 pdu = new PDUv1();
	      pdu.setType(PDU.V1TRAP);
	      pdu.setEnterprise(new OID(snmptrapOid));
	      pdu.setGenericTrap(PDUv1.ENTERPRISE_SPECIFIC);  
	      
	      switch (snmpirSeverity) {
	         case 1:
	        	 pdu.setSpecificTrap(10);
	             break;
	         case 2:
	        	 pdu.setSpecificTrap(11);
	             break;
	         case 3:
	        	 pdu.setSpecificTrap(12);
	             break;
	         case 4:
	             pdu.setSpecificTrap(13);
	             break;
	         case 9:
	             pdu.setSpecificTrap(19);
	             break;
	         default:
	        	 throw new Exception("Invalid Prognisis Severity");
	     }
	      
	      /* allowed severity from the MIBs are
	      
          irCriticalSeverity(1),
          irErrorSeverity(2),
          irWarningSeverity(3),
          irInformationSeverity(4),
          irOffSeverity(9)
	      */
	      /*
	      irGenericTrap TRAP-TYPE
	         ENTERPRISE     integratedResearch
	          ::= 2

	      irCriticalTrap      TRAP-TYPE
	         ::= 10

	      irErrorTrap         TRAP-TYPE
	         ::= 11

	      irWarningTrap       TRAP-TYPE
	         ::= 12

	      irInformationTrap   TRAP-TYPE
	         ::= 13
	      irOffTrap           TRAP-TYPE
	         ::= 19
	  */
	      
	      // to do severity
	      
	      //pdu.setAgentAddress(new IpAddress(ipAddress));	      
			//pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(snmptrapOid)));
			pdu.add(new VariableBinding(new OID(snmpirApplicationOID), new OctetString(snmpirApplication)));
			pdu.add(new VariableBinding(new OID(snmpirSeverityOID), new Integer32(snmpirSeverity)));
			pdu.add(new VariableBinding(new OID(snmpirProblemIDOID), new Integer32(snmpirProblemID)));
			pdu.add(new VariableBinding(new OID(snmpirConditionNameOID), new OctetString( snmpirConditionName)));
			pdu.add(new VariableBinding(new OID(snmpirEventTextOID), new OctetString(snmpirEventText)));
			pdu.add(new VariableBinding(new OID(snmpirSourceNodeOID), new OctetString( snmpirSourceNode)));
			/*
			pdu.add(new VariableBinding(new OID(snmpirFieldName01nameOID), new OctetString(snmpirFieldName01name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue01valueOID), new OctetString(snmpirFieldValue01value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName02nameOID), new OctetString(snmpirFieldName02name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue02valueOID), new OctetString(snmpirFieldValue02value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName03nameOID), new OctetString(snmpirFieldName03name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue03valueOID), new OctetString(snmpirFieldValue03value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName04nameOID), new OctetString(snmpirFieldName04name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue04valueOID), new OctetString(snmpirFieldValue04value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName05nameOID), new OctetString(snmpirFieldName05name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue05valueOID), new OctetString(snmpirFieldValue05value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName06nameOID), new OctetString(snmpirFieldName06name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue06valueOID), new OctetString(snmpirFieldValue06value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName07nameOID), new OctetString(snmpirFieldName07name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue07valueOID), new OctetString(snmpirFieldValue07value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName08nameOID), new OctetString(snmpirFieldName08name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue08valueOID), new OctetString(snmpirFieldValue08value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName09nameOID), new OctetString(snmpirFieldName09name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue09valueOID), new OctetString(snmpirFieldValue09value)));
			pdu.add(new VariableBinding(new OID(snmpirFieldName10nameOID), new OctetString(snmpirFieldName10name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue10valueOID), new OctetString(snmpirFieldValue10value)));
			*/
			pdu.add(new VariableBinding(new OID(snmpirFieldName11nameOID), new OctetString(snmpirFieldName11name)));
			pdu.add(new VariableBinding(new OID(snmpirFieldValue11valueOID), new OctetString(snmpirFieldValue11value)));

	      //Send the PDU
	      Snmp snmp = new Snmp(transport);
	      System.out.println("Sending V1 Trap to " + ipAddressLAM1 + " on Port " + port);
	      snmp.send(pdu, comtarget1);
	      System.out.println("Sending V1 Trap to " + ipAddressLAM2 + " on Port " + port);
	      snmp.send(pdu, comtarget2);
	      snmp.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Error in Sending V1 Trap to the IP address " + ipAddressLAM1 + "or " + ipAddressLAM2 + " on Port " + port);
	      System.err.println("Exception Message = " + e.getMessage());
	    }
	}	
}
	
