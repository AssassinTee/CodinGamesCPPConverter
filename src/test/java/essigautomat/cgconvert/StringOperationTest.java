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
		assertTrue(StringOperations.removeSyntaxChars("A:").equals("A"));
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
	
	@Test
	public void mimeType()
	{
		assertTrue(StringOperations.getMimeType("A.cpp").equals("cpp"));
		assertTrue(StringOperations.getMimeType("A.h").equals("h"));
		assertTrue(StringOperations.getMimeType("A").equals(""));
	}
	
	@Test
	public void removeComment()
	{
		assertTrue(StringOperations.removeComment("class A//bullshit").equals("class A"));
		assertTrue(StringOperations.removeComment("class A//bull//shit").equals("class A"));
		assertTrue(StringOperations.removeComment("class A/*bullshit*/").equals("class A"));
		assertTrue(StringOperations.removeComment("class A/*bullshit").equals("class A"));
		assertTrue(StringOperations.removeComment("class A").equals("class A"));
		assertTrue(StringOperations.removeComment("//bullshit").equals(""));
		assertTrue(StringOperations.removeComment("/* bullshit */").equals(""));
		assertTrue(StringOperations.removeComment("A/* bullshit */").equals("A"));
		assertTrue(StringOperations.removeComment("A// bullshit */").equals("A"));
		assertTrue(StringOperations.removeComment("A").equals("A"));
	}
}
