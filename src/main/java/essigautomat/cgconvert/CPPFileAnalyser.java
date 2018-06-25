package essigautomat.cgconvert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CPPFileAnalyser {
	
	//Behaviour tree for line analysis
	private List<LineBehaviour> behaviourlist;
	
	public CPPFileAnalyser(String filenames[])
	{
		behaviourlist = new ArrayList<LineBehaviour>();
		behaviourlist.add(new LineMakroInclude());
		behaviourlist.add(new LineMakroConverter());
		behaviourlist.add(new LineDefault());
		
		//Analyse each file
		for(String filename : filenames)
		{
			analyseFile(filename);
		}
	}
	
	public void analyseFile(String filename) {
		//Find out mimetype, can this be a cpp file?
		String[] mime = filename.split(".");
		boolean isHeader = false;
		if(mime.length == 2)
		{
			String type = mime[1];
			if(!type.equals("h") && !type.equals("cpp"))
			{
				System.err.println("The file with mimetype '"+type+"' is not supported!");
				return;
			}
			isHeader = type.equals("h");
		}
		
		
		File file = new File(filename);
		
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine())
			{
				String line = sc.next();
				readLine(line, isHeader);
			}
			sc.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.err.println("could not read File '"+filename+"'");
		}
	}

	private void readLine(String line, boolean isHeader) {
		String convline = line.trim().replaceAll("\\s+", " ");
		for(LineBehaviour lb : behaviourlist)
		{
			if(lb.isPossible(line, convline, isHeader))
			{
				if(lb.execute(line, convline, isHeader))
					break;
			}
		}
		
	}

	public void toPath(String string) {
		// TODO Auto-generated method stub
		
	}

	public void toClipboard() {
		// TODO Auto-generated method stub
		
	}
}
