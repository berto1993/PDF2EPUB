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
		if (args.length != 1)
		{
			help();
		}
		else
		{
			if (args[0].equals("help"))
				help();
			else
			{
			LinkedList<Image> pages = new LinkedList<Image>();
			//The directory where the images are
			File dir = new File(args[0]);
	    	System.out.println("Loading images\n");
			loadImages(dir, pages);
	    	System.out.println("Writing the file \t" + args[0] + ".pdf");
				try {
					createPdf(args[0], pages);
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("Enjoy your pdf file");
			}	
		}
    }


	private static void help() {
		System.out.println("This is Comicator, Version 1.0");
		System.out.println("Usage:");
		System.out.println("java -jar Comicator.jar <path to the directory or help>\n");
		System.out.println("Be careful with the name of the images");
	}


	private static void loadImages(File dir, LinkedList<Image> pages) {
    	//All the files contained in the directory
		File[] ficheros = dir.listFiles();
    	for (int i = 0; i<ficheros.length; i++)
    	{
    	//If one of the files is directory then
		//loads that directory recursively
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
    	

	private static void createPdf(String directory,LinkedList<Image> pages) throws FileNotFoundException, DocumentException 
	{
		Document document = new Document();
		//The pdf file is created in the
		//same location of the initial 
		//directory
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(directory + ".pdf")));
		Iterator<Image> it = pages.iterator();
		Image img = null;
		
		document.open();
		
		document.addTitle(new File(directory).getName());
			
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


