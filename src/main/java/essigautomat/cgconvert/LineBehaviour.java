package essigautomat.cgconvert;

public abstract class LineBehaviour {//could be an interface too
	abstract public boolean isPossible(String line, String convline, boolean isHeader);
	abstract public boolean execute(String line, String convline, boolean isHeader);
}