package packPDFIzador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfWriter;


public class Main {

	
	

	public static void main(String[] args) 	throws DocumentException, IOException {
	    	//new Main().createPdf("C:\\Users\\Alberto\\Desktop\\prueba.pdf");
	    	
	    	if (args.length != 2)
	    	{
	    		System.out.println("Se deben introducir los siguentes arguemntos:\n\t ruta de la carpeta \t nombre final del fichero");
	    	}
	    	else
	    	{
	    	LinkedList<Pagina> paginas = new LinkedList<Pagina>();
	    	File dir = new File(args[0]);
	    	File[] ficheros = dir.listFiles();
	    	System.out.println("Extracting data");
	    	for (int i = 0; i<ficheros.length; i++)
	    	{
	    		if (!ficheros[i].isDirectory() && ficheros[i].getName().contains("htm") && !ficheros[i].getName().contains("Fernando"))
	    		{
	    			leerHTML(ficheros[i], paginas, dir);
	    		}
	    	}
	    	if (!args[1].contains(".pdf"))
	    		args[1] = args[1].concat(".pdf");
	
	     	System.out.println("Creating pdf document:" + args[1]);
	    	crearPdf(paginas, args[1], paginas.size(), dir);
	    	System.out.println("Conversión finished");
	    	
		}
	}
	   
		
		private static void crearPdf(LinkedList<Pagina> paginas, String nombre, int length,File dir) 
		{
			Iterator <Pagina> it = paginas.iterator();
			Iterator<Image> itimg = null;
			Pagina aux =  null;
			int actual = 0;
			Image img = null;
			 Document document = new Document();
			 document.setPageSize(new Rectangle(600, 800));
		        // step 2
		        PdfWriter writer = null;
				try {
					writer = PdfWriter.getInstance(document, new FileOutputStream(dir.getParentFile() + "/" + nombre));
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        document.open();
		        PdfOutline root = writer.getRootOutline();
		        PdfOutline marcador;
		        Paragraph title;
		        document.addTitle(nombre);
			while (it.hasNext())
			{
				aux = it.next();
				actual ++;
				System.out.println(actual + "\t of \t" + length);
				marcador = new PdfOutline(root, new PdfDestination(PdfDestination.FITH), aux.getTitle());
				title = new Paragraph(aux.getTitle(),  FontFactory.getFont("arial", 14, Font.BOLDITALIC));
				try {
					document.add(title);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				itimg = aux.getIterator();
				while (itimg.hasNext())
				{
					img = itimg.next();
					if(img != null)
					{img.setAlignment(Chunk.ALIGN_CENTER);
					img.scaleToFit(300, 300);		
					try {
						document.add(img);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			document.newPage();
			document.newPage();
			

			}
				document.close();
		}


		private static void leerHTML(File file, LinkedList<Pagina> paginas, File dir) 
		{
		Pagina page = new Pagina();
		BufferedReader br = null;
		String linea = null;
		String title = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			try {
				while ((linea = br.readLine())!=null)
				{
					if ((linea.contains("» <span>") && linea.contains("</span>")) || linea.contains("&#187; <span>"))
						title = extraerTitulo(linea);
					
					if (linea.contains("<img src") && linea.contains("imageanchor"))
						extraerImagen(linea, page, dir);
					
		if(linea.contains("style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" src=\""))
					extraerImagen2(linea, page, dir, file);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (title == null)
				title = file.getName();
			page.setTitle(title);
			paginas.addLast(page);
		}


		private static String extraerTitulo(String linea) {
			// TODO Auto-generated method stub
			int start, end;
			start = linea.indexOf("<span>")+"<span>".length();
			end = linea.indexOf("</span>");
			return linea.substring(start, end).trim();
		}


		private static void extraerImagen(String linea, Pagina page, File dir)
		{
			// TODO Auto-generated method stub
			File auxFile;
			int start, end;
			start = linea.indexOf("<img src=\"")+"<img src=\"".length();
			end = linea.indexOf("\" border");
			String photofile = linea.substring(start, end);
			photofile = photofile.replace('/', '\\');
			auxFile = new File(dir.getPath()+ "/" + photofile);
			Image photo = null;
			try {
				photo = Image.getInstance(auxFile.toURL());
			} catch (BadElementException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			page.addImage(photo);
				
		}


		private static void extraerImagen2(String linea, Pagina page, File dir, File file) {
			File auxFile;
			int start, end, i;
		//	start = linea.indexOf("<img src=\"")+"<img src=\"".length();
			end = linea.indexOf("\" imageanchor=\"1\"");
			for (i = end; i > 0 && linea.charAt(i)!='/' ; i--)
			{}
			start = i;
			String photofile = linea.substring(start, end);
			photofile = photofile.replace('/', '\\');
			auxFile = new File(file.getPath().replace(".htm", "_files") + "/" + photofile);
			if (auxFile.exists())
			{Image photo = null;
			try {
				photo = Image.getInstance(auxFile.toURL());
			} catch (BadElementException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(photofile);
			} 
			page.addImage(photo);}
		}


		
	}
