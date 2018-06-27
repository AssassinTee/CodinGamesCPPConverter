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
		String lst[] = s.split("\\.");//<-- fuck you java
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
	
	public static boolean isKlassDefOrDek(String s)
	{
		String lst[] = s.split(" ");
		if(lst.length >= 2)
		{
			return lst[0].equals("class");
		}
		return false;
	}

	public static boolean isKlassDekl(String s) {
		String lst[] = s.split(" ");
		if(lst.length < 2 || !lst[0].equals("class"))
			return false;//can't tell, maybe next line :C
		if(lst.length >= 3)
		{
			return lst[1].charAt(lst[1].length()-1) == ';' || lst[2].charAt(0) == ';';
		}
		else //if(lst.length == 2)
		{
			return lst[1].charAt(lst[1].length()-1) == ';';
		}
	}
}
