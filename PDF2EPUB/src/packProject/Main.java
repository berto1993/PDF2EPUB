package packProject;

import com.itextpdf.text.DocumentException;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			PDFOpener.getPdfOpener("C:\\Users\\ALBERTOF\\Desktop").pdfLoad();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
