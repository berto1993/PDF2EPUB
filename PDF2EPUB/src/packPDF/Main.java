package packPDF;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;

import packLoader.Loaders;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PdfReader doc = null;
		try {
			doc = Loaders.getMyLoaders("C:\\Users\\ALBERTOF\\git\\PDF2EPUB\\PDF2EPUB\\Origin\\PDF\\Oda Nobuna.pdf").getPDFReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookmarSplitter bookmark = new BookmarSplitter(doc);
		
		bookmark.getMyBookmarkList();
	}

}
