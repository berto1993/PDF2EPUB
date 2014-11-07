import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfWriter;
import com.tecnick.htmlutils.htmlentities.HTMLEntities;


public class Main {

	private static String ruta = new File("D:\\Sandra").getAbsolutePath()+"\\";


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
	    		if (!ficheros[i].isDirectory() && ficheros[i].getName().contains("htm") && !ficheros[i].getName().contains("Fernando") && !ficheros[i].getName().contains("Jmg"))
	    		{
	    			leerHTML(ficheros[i], paginas);
	    		}
	    	}
	    	if (!args[1].contains(".pdf"))
	    		args[1] = args[1].concat(".pdf");
	    	
	    	ruta = dir.getParent() + "\\" + args[1];
	    	System.out.println(ruta);

	     	System.out.println("Creating pdf document:" + args[1]);
	    	crearPdf(paginas, args[1], paginas.size());
	    	System.out.println("Conversión finished:\t"+ ruta);
	    	
		}
	}
	   
		
		private static void crearPdf(LinkedList<Pagina> paginas, String nombre, int length) throws MalformedURLException 
		{	
			Rectangle titlePage = new Rectangle(300, 200);
			Iterator <Pagina> it = paginas.iterator();
			Iterator<Image> itimg = null;
			Pagina aux =  null;
			int actual = 0;
			Image img = null;
			 Document document = new Document();
			 document.setPageSize(titlePage);
		        // step 2
		        PdfWriter writer = null;
				try {
					writer = PdfWriter.getInstance(document, new FileOutputStream(ruta));
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
				 document.setPageSize(titlePage);
				 document.newPage();
				System.out.println(actual + "\t of \t" + length);
				marcador = new PdfOutline(root, new PdfDestination(PdfDestination.FITH), aux.getTitle());
				title = new Paragraph(aux.getTitle(),  FontFactory.getFont("arial", 14, Font.BOLDITALIC));
				
				
				try {
					document.add(title);
					
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (aux.getLinkYoutube() != null)
				{
					Chunk link = new Chunk("You tube");
					link.setAction(new PdfAction(new URL(aux.getLinkYoutube())));
					
					try {
						document.add(link);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				itimg = aux.getIterator();
				while (itimg.hasNext())
				{
					img = itimg.next();
					img.setAbsolutePosition(0, 0);
					document.setPageSize(new Rectangle(img.getWidth(),img.getHeight()));
					document.newPage();
					try {
						document.add(img);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			document.newPage();
			

			}
				document.close();
		}


		private static void leerHTML(File file, LinkedList<Pagina> paginas) 
		{
		Pagina page = null;
		BufferedReader br;
		String linea = null;
		
		try {
		br = new BufferedReader(new FileReader(file));
			
			while ((linea = br.readLine())!=null)
			{
				if (linea.contains("<span>") && linea.contains("</span>") && linea.contains("»"))
					page = new Pagina(extraerTitulo(linea));
				
				if (linea.contains("<img src") && linea.contains("imageanchor"))
					extraerImagen(linea, page);
			
				if (linea.contains("YOUtube") && linea.contains("target=\""))
				extraerLinkYoutube(linea, page);
				
			}
		} catch (IOException | BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(page != null)
		paginas.addLast(page);

		
		
		}


		private static String extraerTitulo(String linea) {
			// TODO Auto-generated method stub
			return linea.substring(8, linea.length()-7).trim();
		}


		private static void extraerImagen(String linea, Pagina page) throws BadElementException, MalformedURLException, IOException {
			// TODO Auto-generated method stub
			File auxFile;
			int start, end;
			start = linea.indexOf("<img src=\"")+"<img src=\"".length();
			end = linea.indexOf("\" border");
			String photofile = linea.substring(start, end);
			photofile = photofile.replace('/', '\\');
			auxFile = new File(ruta + photofile);
			Image photo = Image.getInstance(auxFile.toURL()); 
			page.addImage(photo);
				
		}
	
		private static void extraerLinkYoutube(String linea, Pagina page) {
			String[] url = linea.split("\"");
			page.addLinkYoutube(url[1]);
		}

		/**
	     * Creates a PDF document.
	     * @param filename the path to the new PDF document
	     * @throws    DocumentException 
	     * @throws    IOException 
	     */
	
	}
