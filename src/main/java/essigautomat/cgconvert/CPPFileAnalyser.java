package essigautomat.cgconvert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CPPFileAnalyser {

	// Behaviour tree for line analysis
	private List<LineBehaviour> behaviourlist;
	private SaveLine.Mode mode;

	public CPPFileAnalyser(List<File> files, SaveLine.Mode m) {
		behaviourlist = new ArrayList<LineBehaviour>();
		behaviourlist.add(new LineMakroInclude());// collect all includes
		behaviourlist.add(new LineMakroConverter());// collect all converter makros
		behaviourlist.add(new LineMakroOther());// collect other makros
		behaviourlist.add(new LineKlassDekl());// collect all class dekl/def
		behaviourlist.add(new LineDefault());// collect rest

		mode = m;
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
			System.err.println("File '" + file.getName() + "' with mimetype '" + mime + "' is not supported!");
			return;
		}

		InfoSubscriber info = new InfoSubscriber(mime.equals("h"));
		// Read File
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				readLine(line, info);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("could not read File '" + file.getName() + "'");
		}
	}

	private void readLine(String line, InfoSubscriber info) {
		String convline = StringOperations.normalizeSpace(line);
		if (mode == SaveLine.Mode.SHORTEST)
			convline = StringOperations.removeSingleComment(convline);
		for (LineBehaviour lb : behaviourlist) {
			if (lb.isPossible(line, convline, info)) {
				if (lb.execute(line, convline, info))
					break;
			}
		}
	}

	public void toPath(String outputDir, boolean toClipboard) {
		StringBuilder sb = null;
		if (toClipboard || mode == SaveLine.Mode.SHORTEST)
			sb = new StringBuilder();
		SaveLine.get().write(mode, outputDir, sb);
		if (mode == SaveLine.Mode.SHORTEST)
			SaveLine.get().writeStringToFile(outputDir, StringOperations.removeMethodSpaces(StringOperations.removeAllComments(sb.toString())));
	}

	public void toClipboard() {
		SaveLine.get().toClipboard();
	}
}
