package essigautomat.cgconvert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class SaveLine {
	static SaveLine saveline = new SaveLine();
	public static final int NUM_LEVEL = 5;
	public static final int DEFAULT = 1;
	public static final int MAIN = 0;
	public static final int KLASSDEF = 2;
	//public static final int KLASSDEK = 3;
	//public static final int INCLUDE = 4;
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
	
	public void addFileNames(List<File> files)
	{
		fileIncludes = new HashSet<String>();
		for(File f : files)
		{
			fileIncludes.add(f.getName());
		}
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
		PrintWriter writer;
		try {
			writer = new PrintWriter("all-in-one.cpp", "UTF-8");
			for(String include : includes)
			{
				writer.println("#include "+include);
			}
			for(String classdekl : classDekl)
			{
				writer.println("class "+classdekl+";");
			}
			for(int i = NUM_LEVEL-1; i >= 0; --i)
			{
				boolean isEmpty = false;
				for(String str : build.get(i)) {
					//Only print one empty line in a row!
					if(str.length() == 0)
					{
						if(isEmpty)
							continue;
						isEmpty=true;
					}
					else
						isEmpty=false;
					writer.println(str);
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not create output file!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Your java does not support UTF-8?!");
		}

	}
	
	public static String erode(String s, char firstchar)
	{
		String ret = s;
		if(s.length() >= 3 &&  s.charAt(0) == firstchar)
			ret = s.substring(1, s.length()-2);
		return ret;
	}
}
