package triageReviewer;

public class TriageTermChecker {

	//private String[] AlarmStrings = { "Node <$2> is unavailable","IF: <$12> on <$2> is Down","Agent Interface Link Down (Trap) on IF <$2> <$4>","BGP State is <$2>. Please check logs and BGP Neighbour status table on router (Critical/Red alarm)","BGP State is idle","BGP State is idle","IF: <$12> on <$2> is Down","Agent Interface Link Down (Trap) on IF <$4> #1362","Interface <$4> is down","changed state to down","CPU is rising above threshold value of <$1>","IF: <$12> on <$2> is Down","AP:  <$3> is down","Threshold Metric:<$1> has breached on node <$2>:<$3> for the condition <$6> with the value of <$5> of Category <$7> and Severity <$8> with the value of ovpiThresholdDisplay String <$14> at <$4>","<$1> on interface <$3> has breached threshold of <thrsh> on <$2>","<$1> on interface <$3> has breached threshold of <thrsh> on <$2>","CPU Utilisation is above","Discards this hour is greater","Errors this hour is greater","Node <$2> is unavailable","Memory Utilisation is above","NSS<*>failed","NSS<*>failed","Interface <$4> is down","CPU is rising above threshold value of <$1>","/Status/Ping","/Status","/Change","/Unknown","/Perf/CPU, /Perf/Memory","/Perf/Interface","/Perf/CPU","/Perf/Memory"};
	//private String[] RegexString = {  "node.*is\\s+unavailable","if:.*on.*is\\s+down","agent\\s+interface\\s+link\\s+down\\s+\\(trap\\)\\s+on\\s+if","agent\\s+interface\\s+link\\s+down\\s+\\(trap\\)\\s+on\\s+if\\s+tengigabitrethernet3/4","bgp\\s+state\\s+is.*please\\s+check\\s+logs\\s+and\\s+bgp\\s+neighbour\\s+status\\s+table\\s+on\\s+router\\s+\\(critical/red\\s+alarm\\)","bgp\\s+state\\s+is\\s+idle","bgp\\s+state\\s+is\\s+idle","if:.*on.*is\\s+down","agent\\s+interface\\s+link\\s+down\\s+\\(trap\\)\\s+on\\s+if.*#1362","interface.*is\\s+down","changed\\s+state\\s+to\\s+down","cpu\\s+is\\s+rising\\s+above\\s+threshold\\s+value\\s+of","if:.*on.*is\\s+down","ap:.*is\\s+down","threshold\\s+metric:.*has\\s+breached\\s+on\\s+node.*:.*for\\s+the\\s+condition.*with\\s+the\\s+value\\s+of.*of\\s+category.*and\\s+severity.*with\\s+the\\s+value\\s+of\\s+ovpithresholddisplay\\s+string.*at", "on\\s+interface.*has\\s+breached\\s+threshold\\s+of.*on","on\\s+interface.*has\\s+breached\\s+threshold\\s+of.*on","cpu\\s+utilisation\\s+is\\s+above","discards\\s+this\\s+hour\\s+is\\s+greater", "errors\\s+this\\s+hour\\s+is\\s+greater","node.*is\\s+unavailable", "memory\\s+utilisation\\s+is\\s+above","nss.*failedd","nss.*failed","interface.*is\\s+down","cpu\\s+is\\s+rising\\s+above\\s+threshold\\s+value\\s+of","/Status/Ping","/Status","/Change","/Unknown","/Perf/CPU","/Perf/Memory","/Perf/Interface","/Perf/CPU","/Perf/Memory" }; 
	
	private String[] AlarmStrings = { "fail", "failed", "failure","stop", "stopped","not responding", "not  responding", "not   responding", "not    responding","invalid", "mismatch","metrics","unmanage"," undefined"," undefined","exit","exited", "connection closed","trash","trashing"," changed","  changed","   changed","    changed","     changed","      changed"," added","  added","   added","    added","     added","      added","active", "activated", "activating","restarted", "start", "started", "restart","previously dead","previously  dead"};			
	private String[] RegexString = {  "(fail(ed|ure)*)","(stop(ped)*)","not\\s+responding","invalid", "mismatch","metrics","^unmanage","^[^\\s]*\\sundefined","(exit(ed)?)","connection\\sclosed","(trash(ing)*)","^[^\\s]*\\s(changed|added)","(activ(e|(at(ed|ing))))","((re)?start(ed)?)","previously\\s+dead"};
	
	public void checkTerms(){
		boolean foundRelevantREGEX = false;
		String alarmStringlower;
		for (String alarmString: AlarmStrings) {           
		        //Do your stuff here
				alarmStringlower = alarmString.toLowerCase();
			foundRelevantREGEX = false; // reset this each time 
			for (String regexString: RegexString) {           
		        //Do your stuff here
				if (alarmStringlower.matches(regexString)) {
					foundRelevantREGEX = true;
				} 
		    }
			if (foundRelevantREGEX){
				System.out.println("Success! We did find a REGEX for the following string " + alarmString);			
			}
			else {	
				System.out.println("we did not find a REGEX for the following string " + alarmString);
			}
		 }		
	}
	
}


//"Node <$2> is unavailable","IF: <$12> on <$2> is Down","Agent Interface Link Down (Trap) on IF <$2> <$4>","BGP State is <$2>. Please check logs and BGP Neighbour status table on router (Critical/Red alarm)","BGP State is idle","BGP State is idle","IF: <$12> on <$2> is Down","Agent Interface Link Down (Trap) on IF <$4> #1362","Interface <$4> is down","changed state to down","CPU is rising above threshold value of <$1>","IF: <$12> on <$2> is Down","AP:  <$3> is down","Threshold Metric:<$1> has breached on node <$2>:<$3> for the condition <$6> with the value of <$5> of Category <$7> and Severity <$8> with the value of ovpiThresholdDisplay String <$14> at <$4>","<$1> on interface <$3> has breached threshold of <thrsh> on <$2>","<$1> on interface <$3> has breached threshold of <thrsh> on <$2>","CPU Utilisation is above","Discards this hour is greater","Errors this hour is greater","Node <$2> is unavailable","Memory Utilisation is above","NSS<*>failed","NSS<*>failed","Interface <$4> is down","CPU is rising above threshold value of <$1>"
               
