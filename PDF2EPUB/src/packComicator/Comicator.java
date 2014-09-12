package packComicator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Comicator {

	public static void main(String[] args) throws BadElementException, IOException 
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
						//if the file it´s not an image fil
						//it will be skipped
						if(ficheros[i].getName().contains("jpg")||ficheros[i].getName().contains("png")||ficheros[i].getName().contains("gif"))
						addImage(ficheros[i], pages);
						//pages.addLast(Image.getInstance(ficheros[i].toURL()));
					} catch (BadElementException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
    	}
	}
    	

	private static void addImage(File file, LinkedList<Image> pages) throws IOException, BadElementException 
	{
		    FileInputStream fis = new FileInputStream(file);  
	        BufferedImage image = ImageIO.read(fis);
	        if (image.getWidth() > image.getHeight())
	        {
	        	File tempImg = new File(file.getParent() + "\\tempImg\\"+file.getName().substring(0,file.getName().length()-4));
	        	tempImg.mkdirs();
	        	splitImages(tempImg, image, (String) file.getName().subSequence(file.getName().length()-3, file.getName().length()));
	        	loadImages(tempImg, pages);
	        }
	        else
	        {
	        	pages.addLast(Image.getInstance(file.toURL()));
	        }
		
	}


	private static void splitImages(File tempImg, BufferedImage image, String type) throws IOException 
	{
		int width = image.getWidth()/2;
		
		//ImageIO.write(StartPointX,StartPointY,StartPointX + custom X, StartPointY + custom Y)
		
		ImageIO.write(image.getSubimage(0, 0, image.getWidth()/2, image.getHeight()), type , new File(tempImg.getAbsolutePath() + "\\00." + type));
		ImageIO.write(image.getSubimage(width, 0, width, image.getHeight()), type , new File(tempImg.getAbsolutePath() + "\\01." + type));
		
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
			
			document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
			img.setAbsolutePosition(0, 0);
			//img.setAlignment(Chunk.ANCHOR);
			document.newPage();
			document.add(img);
		}
		document.close();
		
	}

}


