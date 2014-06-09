package packPDF;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;

public class BookmarSplitter 
{
	private LinkedList<Bookmark> myBookmarkList = null;
	private PdfReader myReader = null;
	
	public BookmarSplitter(PdfReader pReader)
	{
		myReader = pReader;
		myBookmarkList = new LinkedList<Bookmark>();
	}
	public int getN()
	{
		return myBookmarkList.size();
	}
	public void getMyBookmarkList()
	{
		java.util.List<HashMap<String, Object>> myAuxBookmarkList = SimpleBookmark.getBookmark(myReader);
		//only will work if there are bookmarks
		if (myBookmarkList.size()==0)
		{
			//The text will be converted to EPUB without splitting
			withoutSplit();
		}
		else
		{
			levelBookmarks(myAuxBookmarkList, 0);
			//The text will be converted to EPUB splitting wiht the first level bookmarks
			splitByBookmarks();
		}
		
		}

	public void levelBookmarks(java.util.List<HashMap<String, Object>> pList ,int plevel) 
	{
		java.util.List<HashMap<String, Object>> auxList = null;
		for (int i = 0; i < pList.size(); i++)
		{
			auxList = (java.util.List<HashMap<String, Object>>) pList.get(i).get("Kids");
			
			if (auxList == null)
			{
			myBookmarkList.addLast(new Bookmark(pList.get(i), plevel));	
			}
			else
			{
			myBookmarkList.addLast(new Bookmark(pList.get(i), plevel));	
			levelBookmarks(auxList, plevel + 1);	
			}	
		}
	}
	
	public Bookmark getBookmark(int n)
	{
		return myBookmarkList.get(n);
	}

	private void splitByBookmarks() 
	{
		
	}

	private void splitText(Bookmark nextBookmark) 
	{
		
	}

	private void withoutSplit() {
		// TODO Auto-generated method stub
		
	}
}
