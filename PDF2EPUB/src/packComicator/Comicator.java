package packComicator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;

public class Comicator {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String title = null;
		LinkedList<Image> pages = new LinkedList<Image>();
		File dir = new File(args[0]);
    	loadImages(dir, pages);
    	
			try {
				createPdf(args[0], pages, title);
			} catch (FileNotFoundException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	
    }


	private static void loadImages(File dir, LinkedList<Image> pages) {
    	File[] ficheros = dir.listFiles();
    	for (int i = 0; i<ficheros.length; i++)
    	{
    		System.out.println(ficheros[i].getName());
    		if (ficheros[i].isDirectory())
    			loadImages(ficheros[i], pages);
    		else
    			{
					try {
						pages.addLast(Image.getInstance(ficheros[i].toURL()));
					} catch (BadElementException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
    	}
	}
    	

	private static void createPdf(String directory,LinkedList<Image> pages, String title) throws FileNotFoundException, DocumentException 
	{
		Document document = new Document();
		PdfWriter writer = null;
			writer = PdfWriter.getInstance(document, new FileOutputStream(new File(directory + ".pdf")));
			Iterator<Image> it = pages.iterator();
		Image img = null;
		document.open();
		if (title == null)
			document.addTitle(new File(directory).getName());
		else
			document.addTitle(title);
		
		while (it.hasNext())
		{
			img = it.next();
			img.setAlignment(Chunk.ANCHOR);
			document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
			document.newPage();
				document.add(img);
		}
		document.close();
		
	}

}


