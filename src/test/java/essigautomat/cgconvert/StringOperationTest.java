package essigautomat.cgconvert;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
public class StringOperationTest {
	@Test
	public void removeSyntaxCharsTest()
	{
		assertTrue(StringOperations.removeSyntaxChars("class weird{").equals("classweird"));
		assertTrue(StringOperations.removeSyntaxChars("{ A;").equals("A"));
		assertTrue(StringOperations.removeSyntaxChars("move(){};").equals("move()"));
	}
	
	@Test
	public void removeQuotationMarks()
	{
		assertTrue(StringOperations.removeQuotationMarks("\"").equals(""));
		assertTrue(StringOperations.removeQuotationMarks("\"A").equals("A"));
		assertTrue(StringOperations.removeQuotationMarks("\"A\"").equals("A"));
		assertTrue(StringOperations.removeQuotationMarks("\"\"").equals(""));
		assertTrue(StringOperations.removeQuotationMarks("\"A\"B\"").equals("AB"));
	}
	
	@Test
	public void normalizeSpace()
	{
		assertTrue(StringOperations.normalizeSpace(" ").equals(""));
		assertTrue(StringOperations.normalizeSpace("   Test   ").equals("Test"));
		assertTrue(StringOperations.normalizeSpace("   JOHN   SNOW    ").equals("JOHN SNOW"));
	}
}
