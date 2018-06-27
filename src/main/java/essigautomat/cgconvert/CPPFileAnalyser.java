package essigautomat.cgconvert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CPPFileAnalyser {

	// Behaviour tree for line analysis
	private List<LineBehaviour> behaviourlist;

	public CPPFileAnalyser(List<File> files) {
		behaviourlist = new ArrayList<LineBehaviour>();
		behaviourlist.add(new LineMakroInclude());
		behaviourlist.add(new LineMakroConverter());
		behaviourlist.add(new LineDefault());

		// Analyse each file
		SaveLine.get().addFileNames(files);
		for (File f : files) {
			analyseFile(f);
		}
	}

	public void analyseFile(File file) {
		// Find out mimetype, can this be a cpp file?
		String mime = StringOperations.getMimeType(file.getName());

		if (!mime.equals("h") && !mime.equals("cpp")) {
			System.err.println("File '"+file.getName()+"' with mimetype '" + mime + "' is not supported!");
			return;
		}

		InfoSubscriber info = new InfoSubscriber(mime.equals("h"));
		//Read File
		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				readLine(line, info);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("could not read File '" +file.getName() + "'");
		}
	}

	private void readLine(String line, InfoSubscriber info) {
		String convline = StringOperations.normalizeSpace(line);
		for (LineBehaviour lb : behaviourlist) {
			if (lb.isPossible(line, convline, info)) {
				if (lb.execute(line, convline, info))
					break;
			}
		}

	}

	public void toPath(String string) {
		SaveLine.get().write();
	}

	public void toClipboard() {
		// TODO Auto-generated method stub

	}
}
