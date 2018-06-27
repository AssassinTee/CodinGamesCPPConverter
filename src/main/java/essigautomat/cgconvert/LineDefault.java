package essigautomat.cgconvert;

public class LineDefault extends LineBehaviour {

	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		return true;//works always
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		
		if(info.isHeader())
			SaveLine.get().saveLevel(line, SaveLine.KLASSDEF);
		else
			SaveLine.get().saveLevel(line, SaveLine.DEFAULT);
		return true;
	}

}
