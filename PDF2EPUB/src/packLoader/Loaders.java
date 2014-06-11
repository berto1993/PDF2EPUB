package packLoader;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.itextpdf.text.pdf.PdfReader;

public class Loaders 
{
	private static Loaders myLoaders =null;
	private static String myPath;
	
	private Loaders(String pmyPath)
	{
		myPath = pmyPath;
	}
	
	public static Loaders getMyLoaders(String pmyPath)
	{
		if (myLoaders == null)
			myLoaders = new Loaders(pmyPath);
		return myLoaders;
	}
	
	public PdfReader getPDFReaderItext() throws IOException
	{
		return new PdfReader(myPath);
	}

	public PDDocument getPDFReaderPdfBox() throws IOException {
		// TODO Auto-generated method stub
		return PDDocument.load(new File(myPath));
	}

}
