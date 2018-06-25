package essigautomat.cgconvert;

public class LineDefault extends LineBehaviour {

	@Override
	public boolean isPossible(String line, String convline, boolean isHeader) {
		return true;//works always
	}

	@Override
	public boolean execute(String line, String convline, boolean isHeader) {
		
		if(isHeader)
			SaveLine.get().saveLevel(line, SaveLine.KLASSDEF);
		else
			SaveLine.get().saveLevel(convline, SaveLine.DEFAULT);
		return true;
	}

}
