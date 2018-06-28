package essigautomat.cgconvert;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
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
	private HashSet<String> globalMakros = new HashSet<String>();
	private List<List<String>> build = new ArrayList<List<String>>();
	
	//Runtime variables
	private StringBuilder sb = null;
	private PrintWriter writer = null;
	private Mode m = Mode.DEFAULT;

	public static SaveLine get() {
		return saveline;
	}

	private SaveLine() {
		for (int i = 0; i < NUM_LEVEL; ++i) {
			build.add(new ArrayList<String>());
		}
	}
	
	public void addStringBuilder(StringBuilder sb)
	{
		this.sb = sb;
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
	
	public void write(Mode m, String outputDir, StringBuilder sb) {
		this.m = m;
		this.sb = sb;
		//format outputDir
		String outputFile = "all-in-one.cpp";
		if(outputDir != null && outputDir.length() != 0)
		{
			if(outputDir.charAt(outputDir.length()-1) != '/');
				outputDir = outputDir+"/";
			outputFile = outputDir+outputFile;
		}
		
		try {
			writer = new PrintWriter(outputFile, "UTF-8");
			writeInfo();
			writeMakro();
			writeInclude();
			writeKlassDekl();
			writePriorityOrder();
			writer.close();
			writer = null;
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
	
	private void writePriorityOrder()
	{
		if(m == Mode.DEBUG)
			writeLine("//cgconv: define rest (in priority order)");
		for(int i = NUM_LEVEL-1; i >= 0; --i)
		{
			boolean isEmpty = false;
			for(String str : build.get(i)) {
				
				//in shortest mode normalize everything
				if(m == Mode.SHORTEST)
					str = StringOperations.normalizeSpace(str);
				
				//Only print one empty line in a row!
				if(str.length() == 0)
				{
					if(isEmpty)
						continue;
					isEmpty=true;
				}
				else
					isEmpty=false;
				writeLine(str);
			}
		}
	}
	
	private void writeKlassDekl()
	{
		if(m == Mode.DEBUG && classDekl.size() != 0)
			writeLine("//cgconv: deklare ALL classes");
		for(String classdekl : classDekl)
		{
			writeLine("class "+classdekl+";");
		}
	}
	
	private void writeInclude()
	{
		if(m == Mode.DEBUG)
			writeLine("//cgconv: include files");
		for(String include : includes)
		{
			writeLine("#include "+include);
		}
	}
	
	private void writeMakro()
	{
		if(m == Mode.DEBUG && globalMakros.size() != 0)
			writeLine("//cgconv: global Makros");
		for(String makro : globalMakros)
		{
			writeLine(makro);
		}
	}
	
	private void writeInfo()
	{
		if(m == Mode.SHORTEST)
			return;
		writeLine("/** Converted project into one File with CGConvert (CGC).");
		writeLine("*   CGC is made by Essigautomat");
		writeLine("*   https://github.com/AssassinTee/CodinGamesCPPConverter");
		writeLine("*   CGC runs under the GPL v3! You are free to change what you want");
		if(m == Mode.DEBUG)
			writeLine("*   Converter Mode: DEBUG");
		else if(m == Mode.DEFAULT)
			writeLine("*   Converter Mode: DEFAULT");
		writeLine("*/");
	}
	
	private void writeLine(String s)
	{
		//Handle shortest mode: remove whitespaces, trim, remove single line comments, remove empty lines
		if(m == Mode.SHORTEST)
		{
			s = StringOperations.removeSingleComment(StringOperations.normalizeSpace(s));
			if(s.length() == 0)
				return;
		}
		
		writer.println(s);
		if(sb != null)
		{
			sb.append(s);
			sb.append("\n");
		}
	}

	public static Mode toMode(String mode) {
		switch(mode.toLowerCase())
		{
		case "debug": return Mode.DEBUG;
		case "shortest": return Mode.SHORTEST;
		default: return Mode.DEFAULT;
		}
	}

	public void addGlobalPragma(String convline) {
		globalMakros.add(convline);
	}

	public void toClipboard() {
		if(sb == null)
			return;
		StringSelection stringSelection = new StringSelection(sb.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		if(m == Mode.DEBUG)
			System.out.println("Copied contents to clipboard!");
	}
}
