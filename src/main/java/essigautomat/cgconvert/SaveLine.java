package essigautomat.cgconvert;

import java.util.*;

public class SaveLine {
	static SaveLine saveline = new SaveLine();
	public static final int NUM_LEVEL = 5;
	public static final int DEFAULT = 1;
	public static final int MAIN = 0;
	public static final int KLASSDEF = 2;
	public static final int KLASSDEK = 3;
	public static final int INCLUDE = 4;
	private HashSet<String> includes = new HashSet<String>();
	private List<List<String>> build = new ArrayList<List<String>>();

	public static SaveLine get() {
		return saveline;
	}

	private SaveLine() {
		for (int i = 0; i < NUM_LEVEL; ++i) {
			build.add(new ArrayList<String>());
		}
	}

	public void saveLevel(String s, int level) {
		if (level < 0 || level >= NUM_LEVEL) {
			System.err.println("You can't save in Level " + level + "!");
			return;
		}
		if(level == INCLUDE)
			includes.add(s);
		else
			build.get(level).add(s);
	}
	
	public void write() {
		// TODO this;
	}
}
