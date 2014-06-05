package packProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;

public class LectorMarcadores {

	public static void main(String[] args)
	{
		PdfReader reader = null;
		try {
			reader = new PdfReader("./Origin/PDF/Espionaje en la red.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<HashMap<String, Object>> list = SimpleBookmark.getBookmark(reader);
		HashMap<String, Object> aux= null;
		HashMap<String, Object> aux2= null;
		
		for (int i = 0; i < list.size(); i++)
		{
            aux = list.get(i);
            System.out.println(aux);
            System.out.println(aux.get("Title"));
            System.out.println(aux.get("Page"));
            System.out.println(aux.get("Kids").getClass().getName());
            
		
		
		}
		/*Iterator  it = (Iterator) SimpleBookmark.getBookmark(reader).iterator();
		while (it.hasNext())
		{
			System.out.println(it.next());
		}*/
		//java.util.List<HashMap>  bookmarks = SimpleBookmark.getBookmark(reader); 

	}

}
