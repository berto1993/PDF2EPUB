package packTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import packLoader.Loaders;
import packPDF.BookmarSplitter;
import packPDF.Bookmark;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;

public class LectorMarcadores {

	public static void main(String[] args)
	{
		PdfReader reader = null;
		try {
			reader = Loaders.getMyLoaders("C:\\Users\\ALBERTOF\\git\\PDF2EPUB\\PDF2EPUB\\Origin\\PDF\\Oda Nobuna.pdf").getPDFReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<HashMap<String, Object>> list = SimpleBookmark.getBookmark(reader);
		System.out.println(list.size());
	reader.get
		BookmarSplitter aux = new BookmarSplitter(reader);
		aux.levelBookmarks(list, 0);
		System.out.println(aux.getN());
		for (int i=0; i < aux.getN();i++){
			{
				System.out.println(aux.getBookmark(i).getTitle());
			}
		}
		
      
		
		
		}
		/*Iterator  it = (Iterator) SimpleBookmark.getBookmark(reader).iterator();
		while (it.hasNext())
		{
			System.out.println(it.next());
		}*/
		//java.util.List<HashMap>  bookmarks = SimpleBookmark.getBookmark(reader); 

	}


