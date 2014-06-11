package packPDF;

import java.util.Iterator;
import java.util.LinkedList;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

public class Page 
{
	private LinkedList<Bookmark> bookmarkList;
	private int pageNumber;
	private String htmlPage;
	
	public Page (int pPageN, String pHtmlP)
	{
		pageNumber = pPageN;
		htmlPage = pHtmlP;
		bookmarkList = new LinkedList<Bookmark>();
	}
	
	private void identifyBookmarks()
	{
		if (bookmarkList.size()== 0)
		{
			
		}
		else
		{
			Iterator<Bookmark> it = bookmarkList.iterator();
			String title = null;
			int level = 0;
			Bookmark actualBookmark = null;
			while (it.hasNext())
			{
				actualBookmark = it.next();
				level = actualBookmark.getLevel();
				title = actualBookmark.getTitle();
				htmlPage = htmlPage.replaceFirst(title, "<h"+level+">"+HTMLEntities.htmlentities(title)+"</h"+level+">");
			}
		}
	}
		public void addBookmark(Bookmark pBokmark)
		{
			bookmarkList.addLast(pBokmark);
		}
		
		public String getHtml()
		{
			identifyBookmarks();
			return htmlPage;
		}
	

}
