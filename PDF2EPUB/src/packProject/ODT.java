package packProject;

import java.awt.PageAttributes.MediaType;
import java.io.File;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument.OdfMediaType;

public class ODT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Create a text document from a standard template (empty documents within the JAR)
		//OdfTextDocument odt=  OdfTextDocument.loadDocument(new File ("./PruebaPDF/Oda Nobuna.odt"));
		OdfTextDocument odt = OdfTextDocument.newTextDocument();
		odt.changeMode(OdfMediaType.TEXT_TEMPLATE);
		// Append text to the end of the document. 
		odt.addText("This is my very first ODF test");

		// Save document
		odt.save("./PruebaPDF/MyFilename.odt");
	}

}
