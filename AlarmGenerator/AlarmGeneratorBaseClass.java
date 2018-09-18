package AlarmGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.snmp4j.CommunityTarget;

public abstract class AlarmGeneratorBaseClass implements Runnable {

	private ArrayList<HashMap<String,String>> arrayofAlarms;

	public AlarmGeneratorBaseClass(ArrayList<HashMap<String,String>> arrayofAlarms) {
		this.arrayofAlarms = arrayofAlarms;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				
				// we will now iterate though the arrayList 
				
			    for (HashMap<String,String> hashmap : arrayofAlarms){
			    	System.out.println("Here is the size " + hashmap.size());
			    	int resendtimeminutes = Integer.parseInt(hashmap.get("RequiredPeriodicity"));
			    	long lastSent  = Long.parseLong(hashmap.get("Datelastsent"));
			    	if (timeBasedNeedToResent(lastSent, resendtimeminutes)){
			    		generateNewAlarm(hashmap);			    		
			    		//TO do to invoke the sending method
			    	}
			    }	
				Thread.sleep(1000);
			}
		} catch (Exception e) {
				System.out.println("Hit a sleep exception in HP SNMP Thread" + e.getMessage());
		}
	}

	private boolean timeBasedNeedToResent(long existingTS, int resendtimeminutes) {

		// to do to choose to us the milliseconds.
		
		java.util.Date date = new java.util.Date();
		Timestamp timestamp2 = new Timestamp(date.getTime());
		long millisecondsSinceSent = timestamp2.getTime() - existingTS;

		if (millisecondsSinceSent > resendtimeminutes * 60 * 1000) {
			return true;
		} else {
			return false;
		}
	}
	
	protected abstract void generateNewAlarm(HashMap<String,String> hashmap);
}
