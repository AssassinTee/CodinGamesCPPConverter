package essigautomat.cgconvert;

import java.io.File;
import java.util.*;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Main {
	//Reqired options
	@Option(name="-i", aliases="--input", usage="input directory", required=true)
	private String inputDir;
	
	@Option(name="-o", aliases="--output", usage="output directory")
	private String outputDir = null;
	
	@Option(name="-m", aliases="--mode", usage="converter mode: 'default', 'debug' or 'shortest'")
	private String mode = "default";//default argument is default ... lol
	
	@Option(name="-c", aliases="--clipboard", usage="copy to clipboard")
	private boolean toClipboard = false;
	
	public static void main(String args[])
	{
		new Main().doMain(args);
	}
	
	public void doMain(String args[])
	{
		final CmdLineParser parser = new CmdLineParser(this);
		if(args.length < 1)
		{
			parser.printUsage(System.out);
			System.exit(1);
		}
		try
		{
			parser.parseArgument(args);
		}
		catch(CmdLineException clex)
		{
			System.err.println("ERROR: Unable to parse command-line options: " + clex);
		}
		if(mode.equals("debug"))
		{
			System.out.println("input directory '"+inputDir+"' and Mode '"+mode.toLowerCase()+"'provided");
		}
		if(outputDir != null)
		{
			File f = new File(outputDir);
			if(!f.isDirectory())
			{
				System.err.println("'"+outputDir+"' is not a directory!");
				System.exit(1);
			}
		}
		List<File> files = getProjectContents(inputDir);
		CPPFileAnalyser ana = new CPPFileAnalyser(files, SaveLine.toMode(mode));
		ana.toPath(outputDir, toClipboard);
		ana.toClipboard();
	}
	
	public static List<File> getProjectContents(String projectDir)
	{
		File project = new File(projectDir);
		if(!project.isDirectory())
			return null;
		List<File> ret = new ArrayList<File>();
		Queue<File> directories = new LinkedList<File>();
		directories.add(project);
		while(!directories.isEmpty())
		{
			File curfile = directories.poll();
			File[] files = curfile.listFiles();
			for(File f : files)
			{
				if(f.isDirectory())
					directories.add(f);
				else
					ret.add(f);
			}
		}
		return ret;
	}
}
