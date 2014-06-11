package packEpub;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import packPDF.pdfboxprueba;
import packParser.Parser;

import com.tecnick.htmlutils.htmlentities.HTMLEntities;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Date;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;

public class EpubWriter {

	public static void main(String[] args)
	{
	/*	//The book object is created
		Book book = new Book();
		
		//Adding the metadata about the book
		book.getMetadata().addAuthor(new Author("author"));
		book.getMetadata().addTitle("title");
		book.getMetadata().addDate(new Date("today"));		
		String a = pdfboxprueba.conversor();
		book.addSection("page1",new Resource(a.getBytes(), "page1.html") /*new Resource(a.getBytes(), null));
		//Adding the html pages
		//book.addSection(title, new );
		nl.siegmann.epublib.epub.EpubWriter writer =new nl.siegmann.epublib.epub.EpubWriter();
		try {
			writer.write(book, new FileOutputStream("C:\\Users\\ALBERTOF\\Desktop\\test1book1.epub"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Parser par = new Parser();
		par.parsePDF("./Origin/PDF/Oda Nobuna.pdf");
}

}
