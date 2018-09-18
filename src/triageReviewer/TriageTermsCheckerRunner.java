package triageReviewer;

public class TriageTermsCheckerRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			TriageTermChecker ttc =  new TriageTermChecker();
			ttc.checkTerms();
			SWTermChecker swtc =  new SWTermChecker();
			swtc.checkTerms();
	}

}
