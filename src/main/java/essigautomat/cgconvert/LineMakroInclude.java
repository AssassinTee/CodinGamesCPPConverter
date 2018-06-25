package essigautomat.cgconvert;

import java.util.HashSet;

public class LineMakroInclude extends LineBehaviour{
	HashSet<String> includes;
	
	@Override
	public boolean isPossible(String line, String convline, boolean isHeader) {
		String args[] = convline.split(" ");
		return args.length == 2 && args[0] == "#include";
	}

	@Override
	public boolean execute(String line, String convline, boolean isHeader) {
		String args[] = convline.split(" ");
		SaveLine.get().saveLevel(args[1], SaveLine.INCLUDE);
		return true;
	}
}
