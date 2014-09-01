package packTests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.xml.xmp.PdfSchema;

public class LectorPDF {

	public static String main(String[] args) {
		// TODO Auto-generated method stub
		PdfReader reader = null;
		try {
			reader = new PdfReader("./Origin/PDF/like a gun.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int n = reader.getNumberOfPages();
        String str = null;
		try {
			str = PdfTextExtractor.getTextFromPage(reader, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Extracting the content from a particular page.
       return(str);
       	}

}
