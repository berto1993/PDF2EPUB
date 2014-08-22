package packParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFText2HTML;

import com.itextpdf.text.pdf.PdfReader;

import packLoader.Loaders;
import packPDF.BookmarSplitter;
import packPDF.Bookmark;
import packPDF.Page;
import packTests.PDF2;

public class Parser 
{
	private String path;
	
	public Parser(String pPath)
	{
		path = pPath;
	}
	public void parsePDF()
	{
		PdfReader reader = null;
		try {
			reader = Loaders.getMyLoaders(path).getPDFReaderItext();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookmarSplitter bookmarks = new BookmarSplitter(reader);
		Page mypages[] = new Page[reader.getNumberOfPages()];
		
		PDDocument pd = null;
		try {
			pd = Loaders.getMyLoaders(path).getPDFReaderPdfBox();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           List l = pd.getDocumentCatalog().getAllPages();//NUMERO LAS PAGINAS DEL ARCHIVO
           Object[] obj = l.toArray();//METO EN UN OBJETO LA LISTA DE PAGINAS PARA MANIPULARLA
           PDPage page =null;//PAGE ES LA PAGINA 1 DE LA QUE CONSTA EL ARCHIVO
           PDDocument aux = null;
		try {
			aux = new PDDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           PDFText2HTML html = null;
		try {
			html = new PDFText2HTML("utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < obj.length; i++)
		{
			page = (PDPage) obj[i];
			aux.addPage(page);
			try {
				mypages[i] = new Page(i,html.getText(aux));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			aux.removePage(page);
		}
		addBookmars(mypages, bookmarks);
		if (mypages.length!=0)
		writeEbook(mypages);
	}

	private void writeEbook(Page[] mypages) 
	{
		
	Book book = new Book();
	
	for (int i = 1; i < mypages.length; i++)
	{
		//each page is added as a html file to the epub book
		book.addSection("page"+i,new Resource(mypages[i-1].getHtml().getBytes(), "page"+i+".html"));
	
	}	
	nl.siegmann.epublib.epub.EpubWriter writer =new nl.siegmann.epublib.epub.EpubWriter();
	try {
		writer.write(book, new FileOutputStream(/*path.replaceAll(".pdf", ".epub")*/path.substring(0, path.length()-4)+".epub"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private void addBookmars(Page[] mypages, BookmarSplitter bookmarks) 
	{
		Iterator<Bookmark> it = bookmarks.getBookmars().iterator();
		Bookmark bookmark = null;
		while (it.hasNext())
		{
			bookmark = it.next();
			System.out.println(bookmark.getPage());
			//each bookmark is added to its page
			mypages[bookmark.getPage()-1].addBookmark(bookmark);
		}
	}
}
