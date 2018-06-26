package essigautomat.cgconvert;

import org.apache.commons.lang.StringUtils;

public class LineKlassDekl extends LineBehaviour {

	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		return info.isHeader() && StringOperations.isKlassDef(convline);
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		String lst[] = convline.split(" ");
		SaveLine.get().addClassDekl(StringOperations.removeSyntaxChars(lst[1]));
		SaveLine.get().saveLevel(line, SaveLine.KLASSDEF);
		return true;
	}

}
