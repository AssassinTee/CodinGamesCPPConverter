package essigautomat.cgconvert;

import java.io.File;
import java.util.*;

public class Main {
	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.err.println("Wrong number of arguments");
			System.exit(1);
		}
		List<File> files = getProjectContents(args[0]);
		CPPFileAnalyser ana = new CPPFileAnalyser(files);
		ana.toPath("blub");
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
