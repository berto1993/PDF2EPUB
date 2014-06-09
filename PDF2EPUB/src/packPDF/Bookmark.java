package packPDF;

import java.util.HashMap;

public class Bookmark 
{
	private Coordinates bookmarkCoordinates;
	private int page;
	private String name;
	private int level;
	
	public Bookmark(HashMap<String, Object> pBookmark, int plevel)
	{
		splitPageCoordinates((String) pBookmark.get("Page"));
		name = (String) pBookmark.get("Title");
		level = plevel;
	}
	
	private void splitPageCoordinates(String myString) 
	{
		String[] splitted = myString.split(" ");
		page = Integer.parseInt(splitted[0]);
		bookmarkCoordinates = new Coordinates(Float.parseFloat(splitted[2]), Float.parseFloat(splitted[3]), Float.parseFloat(splitted[4]));	
	}

	public String getTitle()
	{
		return name;
	}
	
	public int getPage()
	{
		return page;
	}
	
	public Coordinates getCoordinates()
	{
		return bookmarkCoordinates;
	}

}
