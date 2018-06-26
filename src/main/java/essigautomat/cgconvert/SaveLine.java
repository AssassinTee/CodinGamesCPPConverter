package essigautomat.cgconvert;

import java.util.*;

public class SaveLine {
	static SaveLine saveline = new SaveLine();
	public static final int NUM_LEVEL = 5;
	public static final int DEFAULT = 1;
	public static final int MAIN = 0;
	public static final int KLASSDEF = 2;
	//public static final int KLASSDEK = 3;
	public static final int INCLUDE = 4;
	private HashSet<String> includes = new HashSet<String>();
	private HashSet<String> fileIncludes = null;
	private HashSet<String> classDekl = new HashSet<String>();
	private List<List<String>> build = new ArrayList<List<String>>();

	public static SaveLine get() {
		return saveline;
	}

	private SaveLine() {
		for (int i = 0; i < NUM_LEVEL; ++i) {
			build.add(new ArrayList<String>());
		}
	}
	
	public void addFileNames(String s[])
	{
		fileIncludes = new HashSet<String>(Arrays.asList(s));
	}
	
	public void addInclude(String s)
	{
		if(fileIncludes == null || !fileIncludes.contains(erode(s, '"')))
			includes.add(s);
	}
	
	public void addClassDekl(String s)
	{
		classDekl.add(s);
	}

	public void saveLevel(String s, int level) {
		if (level < 0 || level >= NUM_LEVEL) {
			System.err.println("You can't save in Level " + level + "!");
			return;
		}
		build.get(level).add(s);
	}
	
	public void write() {
		// TODO this;
	}
	
	public static String erode(String s, char firstchar)
	{
		String ret = s;
		if(s.length() >= 3 &&  s.charAt(0) == firstchar)
			ret = s.substring(1, s.length()-2);
		return ret;
	}
}
