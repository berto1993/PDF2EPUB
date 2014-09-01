package packTests;

import java.io.FileOutputStream;





import java.io.InputStream;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class Main1 {

	public static void main(String[] args) 
	{
	try{
		// Create new Book
					Book book = new Book();
			
					// Set the title
					book.getMetadata().addTitle("Epublib test book 1");
					
					// Add an Author
					book.getMetadata().addAuthor(new Author("Joe", "Tester"));
					// Set cover image
					book.setCoverImage(new Resource("./PDF2EPUB/images/pingu.jpg"));
				//	book.setCoverImage(new Resource(Main1.class.getResourceAsStream("./PDF2EPUB/images/pingu.jpg"),"pingu"));
					book.addSection("Introduccion", new Resource("./PDF2EPUB/Origin/error.htm"));
					TOCReference chapter1 = book.addSection("Capitulo1", new Resource("./PDF2EPUB/Origin/error.htm"));
					EpubWriter writer =new EpubWriter();
		writer.write(book, new FileOutputStream("C:\\Users\\ALBERTOF\\Desktop\\test1book1.epub"));
	} catch(Exception e) {
		e.printStackTrace();
	}
	}

	}


