package essigautomat.cgconvert;

public class LineKlassDekl extends LineBehaviour {

	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		return info.isHeader() && StringOperations.isKlassDefOrDek(convline);
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		String lst[] = convline.split(" ");
		SaveLine.get().addClassDekl(StringOperations.removeSyntaxChars(lst[1]));
		if(!StringOperations.isKlassDekl(convline))//Its a Definition, not a Deklaration
			SaveLine.get().saveLevel(line, SaveLine.KLASSDEF);
		return true;
	}

}
