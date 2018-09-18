package zenOSSTrapComparison;

public class ZenOSSTrapComparatorRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		zenOSSTrapComparator zOTC = new zenOSSTrapComparator();
		zOTC.buildInFile();
		zOTC.buildOutFile();
		zOTC.parseFiles();
	}

}
