package packProject;

import java.io.IOException;

import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.html.parser.Parser;
import javax.xml.bind.ParseConversionEvent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class Main {

	public static void main(String[] args) 
	{
	PdfReader pdf = null;
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
				/* Concatena en el string el caracter correspondiente al número obtenido. */
			//	mensaje = mensaje + new Character((char) aux).charValue();
	 }
	 }
}

