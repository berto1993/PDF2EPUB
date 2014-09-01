package packTests;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.net.URI;
import java.util.Date;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument.OdfMediaType;

public class ODT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Create a text document from a standard template (empty documents within the JAR)
		//OdfTextDocument odt=  OdfTextDocument.loadDocument(new File ("./PruebaPDF/Oda Nobuna.odt"));
		OdfTextDocument odt = OdfTextDocument.newTextDocument();
	//	odt.changeMode(OdfMediaType.TEXT_TEMPLATE);
		// Append text to the end of the document. 
		odt.addText("This is my very first ODF test");
		odt.newImage(new URI("http://www.baka-tsuki.org/project/images/thumb/5/58/Oda_Nobuna_no_Yabou_V01_021.jpg/408px-Oda_Nobuna_no_Yabou_V01_021.jpg"));
		// Save document
		odt.save("./Output/PDF/MyFilename"+new Date().getTime()+".odt");
	}

}
