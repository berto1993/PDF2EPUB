package packProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFOpener 
{
	public static PDFOpener myPdfOpener = null;
	private String path;
	
	private PDFOpener(String pPath) 
	{
		path = pPath;
	}
	
	public static PDFOpener getPdfOpener(String pPath)
	{
		if (myPdfOpener == null)
			myPdfOpener = new PDFOpener(pPath);
		return myPdfOpener;
	}
	
	public void pdfLoad() throws DocumentException
	{
	//	File file = new File(path);
		// Se crea el documento
		Document documento = new Document();
		 
		// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = null;
		try {
			ficheroPdf = new FileOutputStream("./PruebaPDF/Fichero"+new Date().getTime()+".pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// Se asocia el documento al OutputStream y se indica que el espaciado entre
		// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
		 
		// Se abre el documento.
		documento.open();
		documento.add(new Paragraph("Esto es el primer párrafo, normalito"));
		 
		documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
		                FontFactory.getFont("arial",   // fuente
		                22,                            // tamaño
		                Font.ITALIC,                   // estilo
		                BaseColor.CYAN)));             // color
		try
		{
		    Image foto = Image.getInstance("./images/pingu.jpg");
		    foto.scaleToFit(100, 100);
		    //foto.setAlignment(Chunk.ALIGN_MIDDLE);
		    //Para poner comentarios
		    foto.setAnnotation(new Annotation("Pingu", "La mascota"));;
		    documento.add(foto);
		}
		catch ( Exception e )
		{
		    e.printStackTrace();
		}
		PdfPTable tabla = new PdfPTable(3);
		for (int i = 0; i < 15; i++)
		{
		    tabla.addCell("celda " + i);
		}
		documento.add(tabla);
		documento.close();
		System.out.println(documento.isOpen());
	}
	
	
}
