package packPDFJoiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;

public class Joiner {

	private static String ruta;

	public static void main(String[] args) 
	{
		String[] files = loadPDF2Join(args);
		try {
			manipulatePdf(files, ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String[] loadPDF2Join(String[] args) 
	{
		ArrayList<String> lista = new ArrayList<String>();
		if (args.length < 3)
		{
			System.exit(0);
		}
		else
		{
			for (int i = 0; i < args.length -1; i++)
			{
				lista.add(args[i]);
			}
			ruta = new File(args[0]).getParent() + "\\" + args[args.length - 1];
			if (!ruta.endsWith(".pdf"))
				ruta = ruta.concat(".pdf");
		}
		System.out.println(ruta);
		return lista.toArray(new String[args.length-1]);
	}

	public static void manipulatePdf(String[] src, String dest)
			throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfCopy copy
		= new PdfCopy(document, new FileOutputStream(dest));
		// step 3
		document.open();
		// step 4
		PdfReader reader;
		int page_offset = 0;
		int n;
		// Create a list for the bookmarks
		ArrayList<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
		java.util.List<HashMap<String,Object>> tmp;
		for (int i  = 0; i < src.length; i++) {
			reader = new PdfReader(src[i]);
			// merge the bookmarks
			tmp = SimpleBookmark.getBookmark(reader);
			if (tmp != null)
			{
				SimpleBookmark.shiftPageNumbers(tmp, page_offset, null);
				bookmarks.addAll(tmp);
			}
			// add the pages
			n = reader.getNumberOfPages();
			page_offset += n;
			for (int page = 0; page < n; ) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			copy.freeReader(reader);
			reader.close();
		}
		// Add the merged bookmarks
		copy.setOutlines(bookmarks);
		// step 5
		document.close();
	}

}
