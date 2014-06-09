package packPDF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.PDFBox;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import packTests.LectorPDF;

public class pdfboxprueba {

	public static void main(String[] args) {
		/*PDFParser parser;
		String parsedText = null;;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		PDFText2HTML html = null;
		File file = new File(fileName);
		if (!file.isFile()) {
			System.err.println("File " + fileName + " does not exist.");
			return null;
		}
		try {
			parser = new PDFParser(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			return null;
		}
		try {
		html= new PDFText2HTML("utf-8");
		
			parser.getDocument();
			PDDocument document = parser.getPDDocument();
			System.out.println(html.getText(pdDoc.g));
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper.setStartPage(1);
			pdfStripper.get
			
			pdfStripper.setEndPage(5);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (Exception e) {
			System.err
					.println("An exception occured in parsing the PDF Document."
							+ e.getMessage());
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String args[]){
		System.out.println(pdftoText("C:\\Users\\ALBERTOF\\git\\PDF2EPUB\\PDF2EPUB\\Origin\\PDF\\Oda Nobuna.pdf"));
	}}*/
		
		 conversor();
}

	private static void conversor() {
		try {
             PDDocument pd = PDDocument.load("C:\\Users\\ALBERTOF\\git\\PDF2EPUB\\PDF2EPUB\\Origin\\PDF\\Oda Nobuna.pdf"); //CARGAR EL PDF
             List l = pd.getDocumentCatalog().getAllPages();//NUMERO LAS PAGINAS DEL ARCHIVO
             Object[] obj = l.toArray();//METO EN UN OBJETO LA LISTA DE PAGINAS PARA MANIPULARLA
             PDPage page = (PDPage) obj[4];//PAGE ES LA PAGINA 1 DE LA QUE CONSTA EL ARCHIVO
             System.out.println("Hay " + obj.length + " hojas");
             
             PDDocument aux = new PDDocument();
             aux.addPage(page);
             PDFText2HTML html = new PDFText2HTML("utf-8");
             
            return html.getText(aux);
             
             for (int i = 0; i < obj.length; i++)
             {
            	 
             }
             
             pd.close();//CERRAMOS OBJETO ACROBAT
         } catch (IOException e) {
             if(e.toString()!=null){
               File archivo=new File("dañado_.txt");//SEPARA LOS DAÑADOS
             }
             System.out.println("Archivo dañado ");// INDICA EN CONSOLA CUALES SON LOS DAÑADOS
             e.printStackTrace();
         }//CATCH}
	}
}

