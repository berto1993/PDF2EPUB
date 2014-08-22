package packTests;

import java.io.IOException;

import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.html.parser.Parser;
import javax.xml.bind.ParseConversionEvent;

import nl.siegmann.epublib.epub.EpubWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class Main {

	public static void main(String[] args) 
	{
	/*PdfReader pdf = null;
	 byte[] page = null;
	 try {
		pdf = new PdfReader("./Origin/PDF/like a gun.pdf");
		System.out.println(pdf.getNumberOfPages());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		 int aux;
		 int longitud = page.length;
		 String mensaje = "";
		 for (int i = 0; i < longitud; i++) {
char a = new Character((char) page[i]).charValue();
System.out.println(a);
			 /* Recorre los bytes y los almacena. */
			//	aux = Integer.parseInt(byt, 2); // Convierte el byte en un entero.
				/* Concatena en el string el caracter correspondiente al n�mero obtenido. */
			//	mensaje = mensaje + new Character((char) aux).charValue();
		FileInputStream in = new FileInputStream("Origin/Nueva carpeta.zip");
		ZipInputStream zip = new ZipInputStream(new BufferedInputStream(in));
		byte[] buffer = new byte[1024];
		int count;
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
		if (!entry.isDirectory()) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while ((count = zip.read(buffer, 0, 1024)) != -1) {
		out.write(buffer, 0, count);
		}
		byte[] content = out.toByteArray(); // Obtener contenido	 }
	 
	 
}

