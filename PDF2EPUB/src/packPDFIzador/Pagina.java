package packPDFIzador;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.Image;


public class Pagina {
	private String title;
	private LinkedList<Image> images = null;
	
	public Pagina()
	{
		images = new LinkedList<Image>();
	}

	public void addImage(Image img)
	{
		images.addLast(img);
	}
	
	public Iterator<Image> getIterator()
	{
		return images.iterator();
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public void setTitle(String name) {
		title = name;
		
	}
}
