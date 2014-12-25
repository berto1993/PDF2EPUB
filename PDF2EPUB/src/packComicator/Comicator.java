package packComicator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import packUtils.Stopwatch;

public class Comicator {


	public static void main(String[] args) throws BadElementException, IOException 
	{	Stopwatch watch = new Stopwatch();
	if (args.length == 0 || args.length > 2)
	{
		help();
	}
	else
	{

		boolean forceNSplit = false;
		if (args.length == 2)	
		{
			if (args[1].equals("-NSplit"))
				forceNSplit = true;
		}

		System.out.println(forceNSplit);

		LinkedList<Image> pages = new LinkedList<Image>();
		//The directory where the images are
		File dir = new File(args[0]);
		File tempImg = new File(dir.getPath() + "/tempimg");
		tempImg.mkdir();
		System.out.println("Loading images\n");
		loadImages(dir, pages, forceNSplit, tempImg);
		System.out.println("Writing the file \t" + args[0] + ".pdf");
		try {
			createPdf(args[0], pages);
			deleteTemp(tempImg);			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enjoy your pdf file");
		System.out.println("Employed Time : \t" + watch.elapsedTime());
	}	
	}



	private static void help() {
		System.out.println("This is Comicator, Version 1.0, by Alberto Fernandez");
		System.out.println("Usage:");
		System.out.println("java -jar Comicator.jar <path to the directory or help> [Options]\n");
		System.out.println("Options:\n \t -NSplit: turns off the auto splitting process");
		System.out.println("Be careful with the name of the images");
	}


	private static void loadImages(File dir, LinkedList<Image> pages, boolean forceNSplit, File tempImg) {
		//All the files contained in the directory
		File[] ficheros = dir.listFiles();
		for (int i = 0; i<ficheros.length; i++)
		{
			//If one of the files is directory then
			//loads that directory recursively
			if (ficheros[i].isDirectory())
				loadImages(ficheros[i], pages, forceNSplit, tempImg);
			else
			{
				try {
					//if the file it´s not an image fil
					//it will be skipped
					if(ficheros[i].getName().contains("jpg")||ficheros[i].getName().contains("png")||ficheros[i].getName().contains("gif"))
						addImage(ficheros[i], pages, forceNSplit, tempImg);
				} catch (BadElementException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	private static void addImage(File file, LinkedList<Image> pages, boolean forceNSplit, File tempImgM) throws IOException, BadElementException 
	{
		FileInputStream fis = new FileInputStream(file);  
		BufferedImage image = ImageIO.read(fis);

		if (forceNSplit)
		{
			pages.addLast(Image.getInstance(FileUtils.readFileToByteArray(file)));
		}
		else
		{
			if (image.getWidth() > image.getHeight())
			{
				File tempImg = new File(tempImgM.getPath() + "/" + file.getName().substring(0,file.getName().length()-4));
				tempImg.mkdir();
				splitImages(tempImg, image, (String) file.getName().subSequence(file.getName().length()-3, file.getName().length()));
				loadImages(tempImg, pages, forceNSplit, tempImg);
			}
			else
			{
				pages.addLast(Image.getInstance(FileUtils.readFileToByteArray(file)));
			}
		}
		fis.close();
	}


	private static void  deleteTemp(File tempImg) 
	{
		File[] tempFiles = tempImg.listFiles();
		File toDelete = null;
		for (int i = 0 ; i < tempFiles.length; i++ )
		{
			toDelete = tempFiles[i];
			if (tempFiles[i].isDirectory())
				deleteTemp(toDelete);
			else
				toDelete.delete();

		}
		System.out.println(tempImg.list().length);
		tempImg.delete();

	}


	private static void splitImages(File tempImg, BufferedImage image, String type) throws IOException 
	{
		int width = image.getWidth()/2;
		File file01 = new File(tempImg.getPath() + "/0." + type);
		File file02 = new File(tempImg.getPath() + "/1." + type);
		//ImageIO.write(StartPointX,StartPointY,StartPointX + custom X, StartPointY + custom Y)

		ImageIO.write(image.getSubimage(0, 0, image.getWidth()/2, image.getHeight()), type , file01);
		ImageIO.write(image.getSubimage(width, 0, width, image.getHeight()), type , file02);
		file01 = null;
		file02 = null;

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
		it.remove();
		document.close();
		img = null;
		writer.close();
		pages.clear();
	}

}


