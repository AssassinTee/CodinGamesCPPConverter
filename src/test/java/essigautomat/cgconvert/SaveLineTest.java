package essigautomat.cgconvert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.*;

public class SaveLineTest {
	@Test
	public void containsFiles()
	{
		File f1 = new File("A.cpp");
		File f2 = new File("A.h");
		List<File> filelist = new ArrayList<File>();
		filelist.add(f1);
		filelist.add(f2);
		SaveLine.get().addFileNames(filelist);
		assertEquals(SaveLine.get().addInclude("\"A.h\""), false);
		assertEquals(SaveLine.get().addInclude("\"A.h"), false);
		assertEquals(SaveLine.get().addInclude("A.h"), false);
		assertEquals(SaveLine.get().addInclude("\"A.h"), false);
		assertEquals(SaveLine.get().addInclude("\"A.cpp\""), false);
		assertEquals(SaveLine.get().addInclude("\"B.h"), true);
	}
}
