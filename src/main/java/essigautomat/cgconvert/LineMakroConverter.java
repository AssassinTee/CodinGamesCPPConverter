package essigautomat.cgconvert;

public class LineMakroConverter extends LineBehaviour{

	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		String args[] = convline.split(" ");
		return args.length >= 1 && args[0] == "#cgconv";
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		// TODO Auto-generated method stub
		//currently ignores this makros
		return true;
	}

}
