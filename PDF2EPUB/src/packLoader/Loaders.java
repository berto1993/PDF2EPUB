package packLoader;

import java.io.IOException;

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
	
	public PdfReader getPDFReader() throws IOException
	{
		return new PdfReader(myPath);
	}

}
