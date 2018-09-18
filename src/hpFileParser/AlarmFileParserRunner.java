package hpFileParser;


public class AlarmFileParserRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		AlarmTypeParser atp = new AlarmTypeParser();
		atp.buildInFile();
		atp.buildOutFile();
		atp.parseFiles();

	}
	
	
}
