package AlarmGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class ZenOSSSNMPThread extends AlarmGeneratorBaseClass implements Runnable, AlarmsMethods {

	public ZenOSSSNMPThread(ArrayList<HashMap<String,String>> arrayofAlarms) {
		super(arrayofAlarms);
	}
	
	public void generateNewAlarm(HashMap<String,String> hashmap){
		
		
	}

}
