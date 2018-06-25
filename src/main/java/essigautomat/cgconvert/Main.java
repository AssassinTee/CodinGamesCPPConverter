package essigautomat.cgconvert;

public class Main {
	public static void main(String args[])
	{
		CPPFileAnalyser ana = new CPPFileAnalyser(args);
		ana.toPath("blub");
		ana.toClipboard();
	}
}
