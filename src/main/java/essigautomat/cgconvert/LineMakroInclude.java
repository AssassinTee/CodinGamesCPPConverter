package essigautomat.cgconvert;

public class LineMakroInclude extends LineBehaviour{
	@Override
	public boolean isPossible(String line, String convline, InfoSubscriber info) {
		String args[] = convline.split(" ");
		return args.length == 2 && args[0].equals("#include");
	}

	@Override
	public boolean execute(String line, String convline, InfoSubscriber info) {
		String args[] = convline.split(" ");
		SaveLine.get().addInclude(args[1]);
		return true;
	}
}
