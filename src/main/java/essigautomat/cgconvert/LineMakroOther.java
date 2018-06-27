package essigautomat.cgconvert;

public class LineMakroOther extends LineBehaviour{

	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		return convline.length() > 0 && convline.charAt(0) == '#';
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		if(convline.equals("#pragma once"))//Handle include guard
			return true;
		String args[] = convline.split(" ");
		if(args.length >= 2 && args[0].equals("#pragma"))
		{
			SaveLine.get().addGlobalPragma(convline);
			return true;
		}
		return false;
	}

}
