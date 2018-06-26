package essigautomat.cgconvert;

import org.apache.commons.lang.StringUtils;

/**Collection of weird string operations
 * 
 * @author Marv
 *
 */
public class StringOperations {	
	public static String removeSyntaxChars(String s)
	{
		return StringUtils.replaceChars(s, "{}; ", "");
	}
	
	public static String removeQuotationMarks(String s)
	{
		return StringUtils.replaceChars(s, "\"", "");//weird line
	}
	
	public static String getMimeType(String s)
	{
		String lst[] = s.split(".");
		if(lst.length != 2)
			return "";
		return lst[1].trim();
	}
	
	/**
	 * Wrapper of StringUtils.nromalizeSpace
	 * @param s
	 * @return
	 */
	public static String normalizeSpace(String s)
	{
		return StringUtils.normalizeSpace(s);
	}
	
	public static boolean isKlassDef(String s)
	{
		String lst[] = s.split(" ");
		if(lst.length >= 2)
		{
			return lst[0].equals("class");
		}
		return false;
	}
}
