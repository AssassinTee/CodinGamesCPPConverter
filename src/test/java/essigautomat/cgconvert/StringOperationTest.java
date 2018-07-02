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
	
	@Test
	public void removeAllComments()
	{
		String test1 = "int a;//test"+'\n'+"int b;//test";
		assertTrue(StringOperations.removeAllComments(test1).equals("int a;\nint b;"));
		String test2 = "int a;/* test\n test \n test*/int b;";
		assertTrue(StringOperations.removeAllComments(test2).equals("int a;int b;"));
	}
	
	@Test
	public void removeMethodSpaces()
	{
		String test1 = "int method() {\ntest;";
		String test2 = "int method()\n{\ntest;";
		//System.out.println(StringOperations.removeMethodSpaces(test1));
		//System.out.println(StringOperations.removeMethodSpaces(test2));
		assertTrue(StringOperations.removeMethodSpaces(test1).equals("int method(){\ntest;"));
		assertTrue(StringOperations.removeMethodSpaces(test2).equals("int method(){\ntest;"));
	}
}
