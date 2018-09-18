package PortTesterPackage;

import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import MoogsoftDataLoader.MoogsoftDataLoader;

public class PortTesterWorker {

	//private String[] myIPaddresses = {"10.122.192.133","10.122.192.134","10.122.192.20","10.122.192.21","10.122.192.22","10.122.192.23","10.122.192.24","10.122.192.25","10.122.192.26","10.122.192.27","10.122.192.28","10.122.192.29","10.122.192.30","198.142.244.20","198.142.244.21","198.142.244.22","198.142.244.23","198.142.244.24","198.142.244.25","198.142.244.26","198.142.244.27","198.142.244.28","198.142.244.29","198.142.244.30","198.142.244.31","198.142.244.32","198.142.244.33","10.122.122.187","10.122.122.188","10.122.122.183","10.122.122.184","10.122.122.189","10.122.122.190","10.122.122.185","10.122.122.186","198.142.244.34","198.142.244.35","10.122.192.31","10.122.192.32","10.122.192.137","10.122.192.1","10.122.122.174","10.122.122.175","10.122.122.176","10.122.122.177","10.122.122.178","10.122.122.179","10.122.122.170","10.122.122.180"};
	
	private String[] myIPaddresses = {"10.122.192.133","10.122.192.134","10.122.192.20","10.122.192.21","10.122.192.22","10.122.192.23","10.122.192.24","10.122.192.25","10.122.192.26","10.122.192.27","10.122.192.28","10.122.192.29","198.142.244.20","198.142.244.21","198.142.244.22","198.142.244.23","198.142.244.24","198.142.244.25","198.142.244.26","198.142.244.27","198.142.244.28","198.142.244.29","198.142.244.30","198.142.244.31","10.122.192.30","10.122.122.174","10.122.122.175","10.122.122.176","10.122.122.177","10.122.122.178","10.122.122.179","10.122.122.180"};

	
	private ArrayList<Integer> myPortsTest;
	
	public PortTesterWorker(){
		
		myPortsTest = new ArrayList<Integer>();
		myPortsTest.add(Integer.parseInt("22"));
		myPortsTest.add(Integer.parseInt("80"));
		//myPortsTest.add(Integer.parseInt("162"));
		myPortsTest.add(Integer.parseInt("443"));
		myPortsTest.add(Integer.parseInt("3306"));
	}
	
	
	
	public void testPorts(){
	
		int server_Port;
		
		for(String server_IP : myIPaddresses){
			 
			
			for(Integer myInteger : myPortsTest){
			
				server_Port  = myInteger.intValue();
				
			    try {
			    //System.out.println("Trying - IP address " + server_IP + " port " + server_Port);
		        Socket socket = new Socket(server_IP,server_Port);
		        System.out.println("Success - able to connect to server IP address " + server_IP + " port " + server_Port);

			    }catch (SocketException e) {
		        System.out.println("Failure - unable to connect to server IP address " + server_IP + " port " + server_Port );
			    }  
		    catch (Exception e) {
		        System.out.println("Failure - unable to connect to server IP address " + server_IP + " port " + server_Port );
			    }
			}
		}	
	}
}
