package packProject;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			      Document document = new Document();
			      PdfWriter.getInstance(document, new FileOutputStream("./PruebaPDF/prueba.pdf"));
			      document.open();
			      // Left
			      Paragraph paragraph = new Paragraph("This is right aligned text");
			      paragraph.setAlignment(Element.ALIGN_RIGHT);
			      document.add(paragraph);
			      // Centered
			      paragraph = new Paragraph("This is centered text");
			      paragraph.setAlignment(Element.ALIGN_CENTER);
			      document.add(paragraph);
			      // Left
			      paragraph = new Paragraph("This is left aligned text");
			      paragraph.setAlignment(Element.ALIGN_LEFT);
			      document.add(paragraph);
			      // Left with indentation
			      paragraph = new Paragraph("This is left aligned text with indentation");
			      paragraph.setAlignment(Element.ALIGN_LEFT);
			      paragraph.setIndentationLeft(50);
			      document.add(paragraph);
			      document.newPage();
			      paragraph = new Paragraph("This is right aligned text");
			      paragraph.setAlignment(Element.ALIGN_RIGHT);
			      document.add(paragraph);
			      // Centered
			      paragraph = new Paragraph("This is centered text");
			      paragraph.setAlignment(Element.ALIGN_CENTER);
			      document.add(paragraph);
			      // Left
			      paragraph = new Paragraph("This is left aligned text");
			      paragraph.setAlignment(Element.ALIGN_LEFT);
			      document.add(paragraph);
			      // Left with indentation
			      paragraph = new Paragraph("This is left aligned text with indentation");
			      paragraph.setAlignment(Element.ALIGN_LEFT);
			      paragraph.setIndentationLeft(50);
			      document.add(paragraph);

			      document.close();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			  }

}