package essigautomat.cgconvert;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

public class InfoSubscriber {
	private int opencount = 0;
	private Stack<String> classstack = new Stack<String>();
	private Stack<Integer> classopenstack = new Stack<Integer>();
	private boolean inHeader;

	public InfoSubscriber(boolean isHeader) {
		this.inHeader = isHeader;
	}

	public void readLine(String line, String convline) {
		if (!classopenstack.isEmpty() && opencount < classopenstack.peek()) {
			classstack.pop();
			classopenstack.pop();
		}

		String args[] = convline.split(" ");
		if (args.length >= 2) {
			if (args[0].equals("class")) {
				classstack.push(args[0]);
				classopenstack.push(opencount);
			}

		}
		int nextopen = StringUtils.countMatches(line, "{");
		int nextclose = StringUtils.countMatches(line, "}");
		opencount += nextopen - nextclose;
	}

	String getCurrentClass() {
		if (!classstack.isEmpty()) {
			return classstack.peek();
		}
		return "";
	}

	boolean isHeader() {
		return inHeader;
	}
}
