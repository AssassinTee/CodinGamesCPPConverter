package essigautomat.cgconvert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineBehaviourTest {
	@Test
	public void doNothing()
	{
		assertEquals(true, true);
	}
	
	@Test
	public void LineDefaultTest()
	{
		LineDefault ld = new LineDefault();
		assertEquals(ld.isPossible("test", "idk", null), true);
		assertEquals(ld.execute("test", "idk", new InfoSubscriber(true)), true);
	}
	
	@Test
	public void LineMakroConverterTest()
	{
		LineBehaviour ld = new LineMakroConverter();
		assertEquals(ld.isPossible("egal", "#cgconv", null), true);
		assertEquals(ld.execute(null, null, null), true);
		assertEquals(ld.isPossible("egal", "#cgcon", null), false);
	}
	
	@Test
	public void LineMakroIncludeTest()
	{
		LineBehaviour ld = new LineMakroInclude();
		assertEquals(ld.isPossible("egal", "#include <vektor>", null), true);//
		assertEquals(ld.isPossible("egal", "#include \"irgendwas.h\"", null), true);
		assertEquals(ld.isPossible("egal", "include \"irgendwas.h\"", null), false);
		
		assertEquals(ld.isPossible("egal", "#include", null), false);
		assertEquals(ld.isPossible("egal", "#include \"a\" //a.magic()", null), true);
		
		assertEquals(ld.execute(null, "#include \"irgendwas.h\"", null), true);
	}
	
	@Test
	public void LineKlassDeklTest()
	{
		LineBehaviour ld = new LineKlassDekl();
		InfoSubscriber headerinfo = new InfoSubscriber(true);
		InfoSubscriber cppinfo = new InfoSubscriber(false);
		
		//Right class defs
		assertEquals(ld.isPossible("egal", "class A", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A {", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A;", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A{", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A : public B", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A : public B{", headerinfo), true);
		assertEquals(ld.isPossible("egal", "class A : public B {", headerinfo), true);
		
		//Wrong class defs
		assertEquals(ld.isPossible("egal", "classA", headerinfo), false);
		assertEquals(ld.isPossible("egal", "weird", headerinfo), false);
		assertEquals(ld.isPossible("egal", "class A", cppinfo), false);
		assertEquals(ld.isPossible("egal", "class A{", cppinfo), false);
		assertEquals(ld.isPossible("egal", "A class", headerinfo), false);
		assertEquals(ld.isPossible("egal", "class", headerinfo), false);//weird case, bad programmer
	}
}
