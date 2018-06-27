package essigautomat.cgconvert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class SaveLine {
	enum Mode {
		DEFAULT,
		DEBUG,
		SHORTEST,
	}
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
	
	public boolean addInclude(String s)
	{
		if(fileIncludes == null || !fileIncludes.contains(StringOperations.removeQuotationMarks(s))) {
			includes.add(s);
			return true;
		}
		return false;
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
	
	public void write(Mode m, String outputDir) {
		//format outputDir
		String outputFile = "all-in-one.cpp";
		if(outputDir != null && outputDir.length() != 0)
		{
			if(outputDir.charAt(outputDir.length()-1) != '/');
				outputDir = outputDir+"/";
			outputFile = outputDir+outputFile;
		}
			
		
		PrintWriter writer;
		try {
			
				
			writer = new PrintWriter(outputFile, "UTF-8");
			if(m != Mode.SHORTEST)
				writeInfo(writer, m);
			if(m == Mode.DEBUG)
				writer.println("//cgconv: include files");
			for(String include : includes)
			{
				writer.println("#include "+include);
			}
			if(m == Mode.DEBUG)
				writer.println("//cgconv: deklare ALL classes");
			for(String classdekl : classDekl)
			{
				writer.println("class "+classdekl+";");
			}
			if(m == Mode.DEBUG)
				writer.println("//cgconv: define rest (in priority order)");
			for(int i = NUM_LEVEL-1; i >= 0; --i)
			{
				boolean isEmpty = false;
				for(String str : build.get(i)) {
					//Only print one empty line in a row!
					if(m == Mode.SHORTEST)
						str = StringOperations.normalizeSpace(str);
					if(str.length() == 0)
					{
						if(isEmpty || m == Mode.SHORTEST)
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
	
	private void writeInfo(PrintWriter writer, Mode m)
	{
		writer.println("/** Converted project into one File with CGConvert (CGC).");
		writer.println("*   CGC is made by Essigautomat");
		writer.println("*   https://github.com/AssassinTee/CodinGamesCPPConverter");
		writer.println("*   CGC runs under the GPL v3! You are free to change what you want");
		if(m == Mode.DEBUG)
			writer.println("*   Converter Mode: DEBUG");
		else if(m == Mode.DEFAULT)
			writer.println("*   Converter Mode: DEFAULT");
		writer.println("*/");
		
	}

	public static Mode toMode(String mode) {
		switch(mode.toLowerCase())
		{
		case "debug": return Mode.DEBUG;
		case "shortest": return Mode.SHORTEST;
		default: return Mode.DEFAULT;
		}
	}
}
