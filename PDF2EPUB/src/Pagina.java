import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.Image;


public class Pagina {
	private String title;
	private LinkedList<Image> images = null;
	private String linkYoutube;
	
	public Pagina(String pTitle)
	{
		title = pTitle;
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
	
	public void addLinkYoutube (String pLink)
	{
		linkYoutube = pLink;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}
	
	public String getLinkYoutube ()
	{
		return linkYoutube;
	}
}
